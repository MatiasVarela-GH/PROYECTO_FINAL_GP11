package persistencia;

import entidades.Pase;
import entidades.Socios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaseData {

    private Connection con = null;

    public PaseData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    // GUARDAR PASE (INSERT)
  public void guardarPase(Pase pase) {

    if (con == null) {
        System.out.println("No hay conexión con la base de datos.");
        return;
    }

    if (pase == null || pase.getSocio() == null
            || pase.getFechaInicio() == null) {
        System.out.println("Debe indicar un socio y una fecha de inicio.");
        return;
    }

    // VALIDAMOS CANTIDAD DE ENTRADAS
    if (pase.getCantidadEntradas() != 8
            && pase.getCantidadEntradas() != 12
            && pase.getCantidadEntradas() != 20) {

        System.out.println("La cantidad de entradas debe ser 8, 12 o 20.");
        return;
    }

    // Verificar que el socio no tenga otro pase activo // vigente
    String sqlExiste = "SELECT COUNT(*) FROM pases "
            + "WHERE ID_Socio = ? AND Estado = 1 "
            + "AND Fecha_Inicio <= CURDATE() "
            + "AND Fecha_Fin >= CURDATE() "
            + "AND CantidadEntradas > 0";

    try (PreparedStatement ps = con.prepareStatement(sqlExiste)) {

        ps.setInt(1, pase.getSocio().getIdSocio());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("El socio ya tiene un pase activo y vigente.");
                return;
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(PaseData.class.getName())
                .log(Level.SEVERE, null, ex);
        return;
    }

    // Calcular fecha de vencimiento (30 días)
    pase.setFechaFin(pase.getFechaInicio().plusDays(30));

    String sql = "INSERT INTO pases "
            + "(ID_Socio, CantidadEntradas, Fecha_Inicio, Fecha_Fin, Costo, Estado) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = con.prepareStatement(
            sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, pase.getSocio().getIdSocio());
        ps.setInt(2, pase.getCantidadEntradas());
        ps.setDate(3, Date.valueOf(pase.getFechaInicio()));
        ps.setDate(4, Date.valueOf(pase.getFechaFin()));
        ps.setDouble(5, pase.getCosto());
        ps.setBoolean(6, pase.isEstado());

        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                pase.setIdPase(rs.getInt(1));
                System.out.println("Pase guardado correctamente. ID: "
                        + pase.getIdPase());
            }
        }

    } catch (SQLException ex) {
        System.out.println("Error al guardar el pase: " + ex.getMessage());
        Logger.getLogger(PaseData.class.getName())
                .log(Level.SEVERE, null, ex);
    }
}

    // PASE POR ID )
    public Pase buscarPase(int id) {

        String sql = "SELECT p.*, s.Nombre, s.Apellido, s.DNI "
                + "FROM pases p "
                + "INNER JOIN socios s ON p.ID_Socio = s.ID_Socio "
                + "WHERE p.ID_Pase = ?";
        Pase pase = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                pase = new Pase();

                pase.setIdPase(rs.getInt("ID_Pase"));

                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellido(rs.getString("Apellido"));
                socio.setDni(rs.getString("DNI"));

                pase.setSocio(socio);

                pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pase;
    }

    // LISTA DE LOS PASES
    public List<Pase> listarPases() {

        List<Pase> pases = new ArrayList<>();

        String sql = "SELECT p.*, s.Nombre, s.Apellido, s.DNI "
                + "FROM pases p "
                + "INNER JOIN socios s ON p.ID_Socio = s.ID_Socio ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Pase pase = new Pase();

                pase.setIdPase(rs.getInt("ID_Pase"));

                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellido(rs.getString("Apellido"));
                socio.setDni(rs.getString("DNI"));
                pase.setSocio(socio);

                pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));

                pases.add(pase);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pases;
    }

    // LISTA PASES DE UN SOCIO
    public List<Pase> listarPasesPorSocio(int idSocio) {

        List<Pase> pases = new ArrayList<>();

        String sql = "SELECT * FROM pases WHERE ID_Socio = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Pase pase = new Pase();

                pase.setIdPase(rs.getInt("ID_Pase"));

                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));

                pase.setSocio(socio);

                pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));

                pases.add(pase);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pases;
    }

    // BUSCAR PASE ACTIVO Y VIGENTE DE UN SOCIO
    public Pase buscarPaseActivo(int idSocio) {

        String sql = "SELECT * FROM pases "
                + "WHERE ID_Socio = ? AND Estado = 1 "
                + "AND Fecha_Inicio <= CURDATE() "
                + "AND Fecha_Fin >= CURDATE() "
                + "AND CantidadEntradas > 0 "
                + "ORDER BY Fecha_Inicio DESC LIMIT 1";

        Pase pase = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                pase = new Pase();

                pase.setIdPase(rs.getInt("ID_Pase"));

                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));

                pase.setSocio(socio);

                pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pase;
    }

   // ACTUALIZAR PASE
    public void actualizarPase(Pase pase) {

        if (con == null || pase == null || pase.getSocio() == null
                || pase.getFechaInicio() == null || pase.getFechaFin() == null) {
            System.out.println("Debe proporcionar los datos del pase.");
            return;
        }

        if (pase.getFechaFin().isBefore(pase.getFechaInicio())) {
            System.out.println("La fecha de vencimiento no puede ser anterior al inicio.");
            return;
        }

        // Verificamos que no exista otro pase activo en el mismo período
        if (pase.isEstado()) {

            String sqlExiste = "SELECT COUNT(*) FROM pases "
                    + "WHERE ID_Socio = ? AND ID_Pase != ? "
                    + "AND Estado = 1 "
                    + "AND Fecha_Inicio <= ? AND Fecha_Fin >= ?";

            try (PreparedStatement ps = con.prepareStatement(sqlExiste)) {

                ps.setInt(1, pase.getSocio().getIdSocio());
                ps.setInt(2, pase.getIdPase());
                ps.setDate(3, Date.valueOf(pase.getFechaFin()));
                ps.setDate(4, Date.valueOf(pase.getFechaInicio()));

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("El socio ya tiene otro pase activo en ese período.");
                        return;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(PaseData.class.getName())
                        .log(Level.SEVERE, null, ex);
                return;
            }
        }

        String sql = "UPDATE pases SET ID_Socio = ?, "
                + "CantidadEntradas = ?, Fecha_Inicio = ?, "
                + "Fecha_Fin = ?, Costo = ?, Estado = ? "
                + "WHERE ID_Pase = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setInt(2, pase.getCantidadEntradas());
            ps.setDate(3, Date.valueOf(pase.getFechaInicio()));
            ps.setDate(4, Date.valueOf(pase.getFechaFin()));
            ps.setDouble(5, pase.getCosto());
            ps.setBoolean(6, pase.isEstado());
            ps.setInt(7, pase.getIdPase());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pase actualizado correctamente.");
            } else {
                System.out.println("No se encontró el pase indicado.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    // BAJA DE PASE
    public void borrarPase(int idPase) {

        String sql = "UPDATE pases SET Estado = 0 WHERE ID_Pase = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idPase);
            ps.executeUpdate();

            ps.close();

            System.out.println("Pase dado de baja correctamente.");

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
