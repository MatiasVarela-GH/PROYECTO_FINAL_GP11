
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

        // VALIDAMOS EL TIPO DE PASE
        if (pase.getTipoPase() == null || pase.getTipoPase().isEmpty()) {
            pase.setTipoPase("LIMITADO");
        }

        if (pase.getTipoPase().equals("LIMITADO")) {

            if (pase.getCantidadEntradas() != 8
                    && pase.getCantidadEntradas() != 12
                    && pase.getCantidadEntradas() != 20) {
                System.out.println("La cantidad de entradas debe ser 8, 12 o 20.");
                return;
            }

        } else if (pase.getTipoPase().equals("LIBRE")) {
            pase.setCantidadEntradas(0);
        } else {
            System.out.println("El tipo de pase no es valido.");
            return;
        }
        
        // Calcular fecha de vencimiento (30 días)
        pase.setFechaFin(pase.getFechaInicio().plusDays(30));

        // Verificar que el socio no tenga otro pase en el mismo período
        String sqlExiste = "SELECT COUNT(*) FROM pases "
                + "WHERE ID_Socio = ? AND Estado = 1 "
                + "AND Fecha_Inicio <= ? "
                + "AND Fecha_Fin >= ?";

        try (PreparedStatement ps = con.prepareStatement(sqlExiste)) {

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setDate(2, Date.valueOf(pase.getFechaFin()));
            ps.setDate(3, Date.valueOf(pase.getFechaInicio()));

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

        String sql = "INSERT INTO pases "
                + "(ID_Socio, CantidadEntradas, Fecha_Inicio, Fecha_Fin, Costo, Estado, TipoPase) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setInt(2, pase.getCantidadEntradas());
            ps.setDate(3, Date.valueOf(pase.getFechaInicio()));
            ps.setDate(4, Date.valueOf(pase.getFechaFin()));
            ps.setDouble(5, pase.getCosto());
            ps.setBoolean(6, pase.isEstado());
            ps.setString(7, pase.getTipoPase());

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

    // BUSCAR PASE POR ID
    public Pase buscarPase(int id) {

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return null;
        }

        String sql = "SELECT p.*, s.Nombre, s.Apellido, s.DNI "
                + "FROM pases p "
                + "INNER JOIN socios s ON p.ID_Socio = s.ID_Socio "
                + "WHERE p.ID_Pase = ?";

        Pase pase = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

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
                    pase.setTipoPase(rs.getString("TipoPase"));
                    pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                    pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                    pase.setCosto(rs.getDouble("Costo"));
                    pase.setEstado(rs.getBoolean("Estado"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return pase;
    }

    // LISTAR TODOS LOS PASES
    public List<Pase> listarPases() {

        List<Pase> pases = new ArrayList<>();

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return pases;
        }

        String sql = "SELECT p.*, s.Nombre, s.Apellido, s.DNI "
                + "FROM pases p "
                + "INNER JOIN socios s ON p.ID_Socio = s.ID_Socio";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

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
                pase.setTipoPase(rs.getString("TipoPase"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));

                pases.add(pase);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return pases;
    }

    // LISTAR PASES DE UN SOCIO
    public List<Pase> listarPasesPorSocio(int idSocio) {

        List<Pase> pases = new ArrayList<>();

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return pases;
        }

        String sql = "SELECT * FROM pases WHERE ID_Socio = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSocio);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Pase pase = new Pase();
                    pase.setIdPase(rs.getInt("ID_Pase"));

                    Socios socio = new Socios();
                    socio.setIdSocio(rs.getInt("ID_Socio"));

                    pase.setSocio(socio);

                    pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                    pase.setTipoPase(rs.getString("TipoPase"));
                    pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                    pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                    pase.setCosto(rs.getDouble("Costo"));
                    pase.setEstado(rs.getBoolean("Estado"));

                    pases.add(pase);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return pases;
    }

    // BUSCAR PASE ACTIVO Y VIGENTE DE UN SOCIO
    public Pase buscarPaseActivo(int idSocio) {

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return null;
        }

        String sql = "SELECT * FROM pases "
                + "WHERE ID_Socio = ? AND Estado = 1 "
                + "AND Fecha_Inicio <= CURDATE() "
                + "AND Fecha_Fin >= CURDATE() "
                + "AND (TipoPase = 'LIBRE' OR CantidadEntradas > 0) "
                + "ORDER BY Fecha_Inicio DESC LIMIT 1";

        Pase pase = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSocio);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    pase = new Pase();
                    pase.setIdPase(rs.getInt("ID_Pase"));

                    Socios socio = new Socios();
                    socio.setIdSocio(rs.getInt("ID_Socio"));

                    pase.setSocio(socio);

                    pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                    pase.setTipoPase(rs.getString("TipoPase"));
                    pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                    pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                    pase.setCosto(rs.getDouble("Costo"));
                    pase.setEstado(rs.getBoolean("Estado"));
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return pase;
    }

    
    // ACTUALIZAR PASE
    public boolean actualizarPase(Pase pase) {

        if (con == null || pase == null || pase.getSocio() == null
                || pase.getFechaInicio() == null || pase.getFechaFin() == null) {
            System.out.println("Debe proporcionar los datos del pase.");
            return false;
        }

        String tipoPase = pase.getTipoPase();

        if ("LIBRE".equals(tipoPase)) {
            if (pase.getCantidadEntradas() != 0) {
                System.out.println("El Pase Libre debe tener 0 entradas.");
                return false;
            }
        } else if ("LIMITADO".equals(tipoPase)) {
            if (pase.getCantidadEntradas() != 8
                    && pase.getCantidadEntradas() != 12
                    && pase.getCantidadEntradas() != 20) {
                System.out.println("La cantidad de entradas debe ser 8, 12 o 20.");
                return false;
            }
        } else {
            System.out.println("El tipo de pase no es válido.");
            return false;
        }

        if (!pase.getFechaFin().equals(pase.getFechaInicio().plusDays(30))) {
            System.out.println("El pase debe tener una duración de 30 días.");
            return false;
        }

        // Verificar que no exista otro pase activo en el mismo período
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
                        return false;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(PaseData.class.getName())
                        .log(Level.SEVERE, null, ex);
                return false;
            }
        }

        String sql = "UPDATE pases SET ID_Socio = ?, "
                + "CantidadEntradas = ?, TipoPase = ?, "
                + "Fecha_Inicio = ?, Fecha_Fin = ?, "
                + "Costo = ?, Estado = ? "
                + "WHERE ID_Pase = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setInt(2, pase.getCantidadEntradas());
            ps.setString(3, tipoPase);
            ps.setDate(4, Date.valueOf(pase.getFechaInicio()));
            ps.setDate(5, Date.valueOf(pase.getFechaFin()));
            ps.setDouble(6, pase.getCosto());
            ps.setBoolean(7, pase.isEstado());
            ps.setInt(8, pase.getIdPase());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pase actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el pase indicado.");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // BAJA LÓGICA DE PASE
    public boolean borrarPase(int idPase) {

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return false;
        }

        String sql = "UPDATE pases SET Estado = 0 "
                + "WHERE ID_Pase = ? AND Estado = 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPase);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pase dado de baja correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el pase o ya está inactivo.");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // ALTA LOGICA DE PASE
    public boolean darDeAltaPase(int idPase) {

        if (con == null) {
            System.out.println("No hay conexion con la base de datos.");
            return false;
        }

        String sql = "UPDATE pases SET Estado = 1 "
                + "WHERE ID_Pase = ? AND Estado = 0";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPase);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pase dado de alta correctamente.");
                return true;
            } else {
                System.out.println("No se encontro el pase o ya esta activo.");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
