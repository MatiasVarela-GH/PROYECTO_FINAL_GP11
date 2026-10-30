package persistencia;

import entidades.Asistencia;
import entidades.Socios;
import entidades.Clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenciaData {

    private Connection con = null;

    public AsistenciaData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    // REGISTRAR ASISTENCIA
    public void guardarAsistencia(Asistencia asistencia) {

        String sqlClase = "SELECT Capacidad, Estado FROM clases WHERE ID_Clase = ? FOR UPDATE";

        String sqlCantidad = "SELECT COUNT(*) FROM asistencias "
                + "WHERE ID_Clase = ? AND Fecha_Asistencia = ?";

        String sqlPase = "SELECT ID_Pase, CantidadEntradas FROM pases "
                + "WHERE ID_Socio = ? AND Estado = 1 "
                + "AND Fecha_Inicio <= ? AND Fecha_Fin >= ? "
                + "AND CantidadEntradas > 0 "
                + "ORDER BY Fecha_Inicio DESC LIMIT 1 FOR UPDATE";

        String sqlInsertar = "INSERT INTO asistencias "
                + "(ID_Socio, ID_Clase, Fecha_Asistencia) "
                + "VALUES (?, ?, ?)";

        String sqlDescontar = "UPDATE pases SET CantidadEntradas = ?, Estado = ? "
                + "WHERE ID_Pase = ?";

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return;
        }

        if (asistencia.getSocio() == null || asistencia.getClase() == null) {
            System.out.println("Debe indicar un socio y una clase.");
            return;
        }

        LocalDate fecha = asistencia.getFechaAsistencia();

        if (fecha == null) {
            fecha = LocalDate.now();
        }

        try {
            con.setAutoCommit(false);

            int idClase = asistencia.getClase().getIdClase();
            int idSocio = asistencia.getSocio().getIdSocio();

     // 1. Verificar que la clase exista y esté activa
            int capacidad;

            try (PreparedStatement ps = con.prepareStatement(sqlClase)) {
                ps.setInt(1, idClase);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("La clase no existe.");
                        con.rollback();
                        return;
                    }

                    capacidad = rs.getInt("Capacidad");

                    if (!rs.getBoolean("Estado")) {
                        System.out.println("La clase no está activa.");
                        con.rollback();
                        return;
                    }
                }
            }

     // 2. Verificar que haya lugar disponible
            try (PreparedStatement ps = con.prepareStatement(sqlCantidad)) {
                ps.setInt(1, idClase);
                ps.setDate(2, Date.valueOf(fecha));

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) >= capacidad) {
                        System.out.println("La clase está completa.");
                        con.rollback();
                        return;
                    }
                }
            }

    // 3. Buscar un pase activo con entradas disponibles
            int idPase;
            int entradas;

            try (PreparedStatement ps = con.prepareStatement(sqlPase)) {
                ps.setInt(1, idSocio);
                ps.setDate(2, Date.valueOf(fecha));
                ps.setDate(3, Date.valueOf(fecha));

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("El socio no tiene un pase activo con entradas disponibles.");
                        con.rollback();
                        return;
                    }

                    idPase = rs.getInt("ID_Pase");
                    entradas = rs.getInt("CantidadEntradas");
                }
            }

    // 4. Registrar la asistencia
            try (PreparedStatement ps = con.prepareStatement(
                    sqlInsertar, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, idSocio);
                ps.setInt(2, idClase);
                ps.setDate(3, Date.valueOf(fecha));

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        asistencia.setIdAsistencia(rs.getInt(1));
                    }
                }
            }

    // 5. Descontar una entrada del pase
            entradas--;

            try (PreparedStatement ps = con.prepareStatement(sqlDescontar)) {
                ps.setInt(1, entradas);
                ps.setBoolean(2, entradas > 0);
                ps.setInt(3, idPase);

                ps.executeUpdate();
            }

            con.commit();
            System.out.println("Asistencia registrada correctamente.");

        } catch (SQLException ex) {

            try {
                con.rollback();
            } catch (SQLException e) {
                Logger.getLogger(AsistenciaData.class.getName())
                        .log(Level.SEVERE, null, e);
            }

            Logger.getLogger(AsistenciaData.class.getName())
                    .log(Level.SEVERE, null, ex);

            System.out.println("No se pudo registrar la asistencia.");

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AsistenciaData.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    // BUSCAR ASISTENCIA POR ID
    public Asistencia buscarAsistencia(int idAsistencia) {

        String sql = "SELECT * FROM asistencias WHERE ID_Asistencia = ?";

        Asistencia asistencia = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAsistencia);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    asistencia = new Asistencia();

                    asistencia.setIdAsistencia(rs.getInt("ID_Asistencia"));

                    Socios socio = new Socios();
                    socio.setIdSocio(rs.getInt("ID_Socio"));
                    asistencia.setSocio(socio);

                    Clases clase = new Clases();
                    clase.setIdClase(rs.getInt("ID_Clase"));
                    asistencia.setClase(clase);

                    asistencia.setFechaAsistencia(
                            rs.getDate("Fecha_Asistencia").toLocalDate());
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return asistencia;
    }

    // LISTA DE TODAS LAS ASISTENCIAS
    public List<Asistencia> listarAsistencias() {

        String sql = "SELECT * FROM asistencias ORDER BY Fecha_Asistencia DESC";

        ArrayList<Asistencia> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Asistencia asistencia = new Asistencia();

                asistencia.setIdAsistencia(rs.getInt("ID_Asistencia"));

                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                asistencia.setSocio(socio);

                Clases clase = new Clases();
                clase.setIdClase(rs.getInt("ID_Clase"));
                asistencia.setClase(clase);

                asistencia.setFechaAsistencia(
                        rs.getDate("Fecha_Asistencia").toLocalDate());

                lista.add(asistencia);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    // LISTA DE ASISTENCIAS DE UN SOCIO
    public List<Asistencia> listarAsistenciasPorSocio(int idSocio) {

        String sql = "SELECT * FROM asistencias "
                + "WHERE ID_Socio = ? ORDER BY Fecha_Asistencia DESC";

        ArrayList<Asistencia> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSocio);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Asistencia asistencia = new Asistencia();

                    asistencia.setIdAsistencia(rs.getInt("ID_Asistencia"));

                    Socios socio = new Socios();
                    socio.setIdSocio(rs.getInt("ID_Socio"));
                    asistencia.setSocio(socio);

                    Clases clase = new Clases();
                    clase.setIdClase(rs.getInt("ID_Clase"));
                    asistencia.setClase(clase);

                    asistencia.setFechaAsistencia(
                            rs.getDate("Fecha_Asistencia").toLocalDate());

                    lista.add(asistencia);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return lista;
    }
}