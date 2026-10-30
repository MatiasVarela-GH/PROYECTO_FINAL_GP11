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

     // Validar cantidad de entradas
        if (pase.getCantidadEntradas() != 8
                && pase.getCantidadEntradas() != 12
                && pase.getCantidadEntradas() != 20) {

            System.out.println("La cantidad de entradas debe ser 8, 12 o 20.");
            return;
        }

     // Calcular fecha de vencimiento 30 dias
        pase.setFechaFin(pase.getFechaInicio().plusDays(30));

        String sql = "INSERT INTO pases "
                + "(ID_Socio, CantidadEntradas, Fecha_Inicio, Fecha_Fin, Costo, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setInt(2, pase.getCantidadEntradas());
            ps.setDate(3, Date.valueOf(pase.getFechaInicio()));
            ps.setDate(4, Date.valueOf(pase.getFechaFin()));
            ps.setDouble(5, pase.getCosto());
            ps.setBoolean(6, pase.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                pase.setIdPase(rs.getInt(1));
                System.out.println("Pase guardado correctamente. ID: "
                        + pase.getIdPase());
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al guardar el pase: " + ex.getMessage());
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // PASE POR ID )
    public Pase buscarPase(int id) {

        String sql = "SELECT * FROM pases WHERE ID_Pase = ?";
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

                pase.setSocio(socio);

                pase.setCantidadEntradas(rs.getInt("CantidadEntradas"));
                pase.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
                pase.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
                pase.setCosto(rs.getDouble("Costo"));
                pase.setEstado(rs.getBoolean("Estado"));
            }

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pase;
    }

    // LISTA DE LOS PASES
    public List<Pase> listarPases() {

        List<Pase> pases = new ArrayList<>();

        String sql = "SELECT * FROM pases";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
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

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pase;
    }

    // ACTUALIZAR PASE 
    public void actualizarPase(Pase pase) {

        String sql = "UPDATE pases SET ID_Socio = ?, "
                + "CantidadEntradas = ?, Fecha_Inicio = ?, "
                + "Fecha_Fin = ?, Costo = ?, Estado = ? "
                + "WHERE ID_Pase = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pase.getSocio().getIdSocio());
            ps.setInt(2, pase.getCantidadEntradas());
            ps.setDate(3, Date.valueOf(pase.getFechaInicio()));
            ps.setDate(4, Date.valueOf(pase.getFechaFin()));
            ps.setDouble(5, pase.getCosto());
            ps.setBoolean(6, pase.isEstado());
            ps.setInt(7, pase.getIdPase());

            ps.executeUpdate();

            ps.close();

            System.out.println("Pase actualizado correctamente.");

        } catch (SQLException ex) {
            Logger.getLogger(PaseData.class.getName()).log(Level.SEVERE, null, ex);
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