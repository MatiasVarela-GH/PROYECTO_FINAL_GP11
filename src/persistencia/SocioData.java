/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import entidades.Socios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SocioData {
    private Connection con = null;

    public SocioData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    //  GUARDAR SOCIO (INSERT)
    public void guardarSocio(Socios socio) {
        String sql = "INSERT INTO socios (DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, socio.getDni());
            ps.setString(2, socio.getNombre());
            ps.setString(3, socio.getApellido());
            ps.setInt(4, socio.getEdad());
            ps.setString(5, socio.getCorreo());
            ps.setString(6, socio.getTelefono());
            ps.setBoolean(7, socio.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                socio.setIdSocio(rs.getInt(1));
                System.out.println("Socio guardado correctamente con ID: " + socio.getIdSocio());
            } else {
                System.out.println("No se pudo obtener el ID del socio.");
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar el Socio: " + ex.getMessage());
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //  BUSCAR SOCIO POR ID (SELECT)
    public Socios buscarSocio(int id) {
        String sql = "SELECT * FROM socios WHERE ID_Socio = ?";
        Socios socio = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                socio.setDni(rs.getString("DNI"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellido(rs.getString("Apellido"));
                socio.setEdad(rs.getInt("Edad"));
                socio.setCorreo(rs.getString("Correo"));
                socio.setTelefono(rs.getString("Telefono"));
                socio.setEstado(rs.getBoolean("Estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socio;
    }

    //  BUSCAR SOCIO POR DNI (SELECT)
    public Socios buscarSocioPorDni(String dni) {
        String sql = "SELECT * FROM socios WHERE DNI = ?";
        Socios socio = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                socio.setDni(rs.getString("DNI"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellido(rs.getString("Apellido"));
                socio.setEdad(rs.getInt("Edad"));
                socio.setCorreo(rs.getString("Correo"));
                socio.setTelefono(rs.getString("Telefono"));
                socio.setEstado(rs.getBoolean("Estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socio;
    }

    //  LISTAR TODOS LOS SOCIOS (SELECT *)
    public List<Socios> listarSocios() {
        List<Socios> socios = new ArrayList<>();
        String sql = "SELECT * FROM socios";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Socios socio = new Socios();
                socio.setIdSocio(rs.getInt("ID_Socio"));
                socio.setDni(rs.getString("DNI"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellido(rs.getString("Apellido"));
                socio.setEdad(rs.getInt("Edad"));
                socio.setCorreo(rs.getString("Correo"));
                socio.setTelefono(rs.getString("Telefono"));
                socio.setEstado(rs.getBoolean("Estado"));

                socios.add(socio);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socios;
    }

    //  ACTUALIZAR SOCIO (UPDATE)
    public void actualizarSocio(Socios socio) {
        String sql = "UPDATE socios SET DNI = ?, Nombre = ?, Apellido = ?, Edad = ?, Correo = ?, Telefono = ?, Estado = ? WHERE ID_Socio = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, socio.getDni());
            ps.setString(2, socio.getNombre());
            ps.setString(3, socio.getApellido());
            ps.setInt(4, socio.getEdad());
            ps.setString(5, socio.getCorreo());
            ps.setString(6, socio.getTelefono());
            ps.setBoolean(7, socio.isEstado());
            ps.setInt(8, socio.getIdSocio());

            ps.executeUpdate();
            ps.close();
            System.out.println("Socio actualizado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // BAJA LÓGICA DE SOCIO (UPDATE Estado = 0)
    public void borrarSocio(int idSocio) {
        String sql = "UPDATE socios SET Estado = 0 WHERE ID_Socio = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ps.executeUpdate();
            ps.close();
            System.out.println("Socio dado de baja lógicamente con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(SocioData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
