/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import entidades.Entrenadores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EntrenadoresData {
    private Connection con = null;
    
    public EntrenadoresData (MiConexion conexion) {
    this.con = conexion.buscarConexion();
    }
    
 //GUARDAR ENTRENADOR INSERT
    
    public void guardarEntrenador(Entrenadores e) {

        String sql = "INSERT INTO Entrenadores(Dni,nombre,apellido,especialidad,estado) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getEspecialidad());
            ps.setBoolean(5, e.isEstado());

            ps.executeUpdate();

            // Recuperamos el ID generado por MySQL
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                e.setIdEntrenador(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID");
            }

            ps.close();

            System.out.println("Entrenador guardado correctamente");

        } catch (SQLException ex) {

            System.out.println("No se pudo insertar el Entrenador");
            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //BUSCAR ENTRENADOR POR IDENTRNADORES
    //SELECT
    public Entrenadores buscarEntrenador(int id) {

        Entrenadores e = null;

        String sql = "SELECT * FROM alumno WHERE idEntrenador = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                e = new Entrenadores();

                e.setIdEntrenador(rs.getInt("idEntrenador"));
                e.setDni(rs.getString("dni"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setEspecialidad(rs.getString("especialidad"));
                e.setEstado(rs.getBoolean("estado"));
            }

            ps.close();

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }
    //LISTAR LOS ENTRENADORES
    // SELECT*
    public List<Entrenadores> listarEntrenadores() {

        List<Entrenadores> Entrenadores = new ArrayList<>();

        String query = "SELECT * FROM Entrenadores";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Entrenadores e = new Entrenadores();

                e.setIdEntrenador(rs.getInt("idEntrenador"));
                e.setDni(rs.getString("dni"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setEspecialidad(rs.getString("especialidad"));
                e.setEstado(rs.getBoolean("estado"));

                Entrenadores.add(e);
            }

            ps.close();

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Entrenadores;
    }
    
    //ACTUALIZAR ENTRENADORES
    //UPDATE
    public void actualizarAlumno(Entrenadores e) {

        String query = "UPDATE Entrenador SET dni = ?, nombre = ?, apellido = ?, Especialidad = ?, estado = ? "
                + "WHERE idEntrenador = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getEspecialidad());
            ps.setBoolean(5, e.isEstado());
            ps.setInt(6, e.getIdEntrenador());

            ps.executeUpdate();

            ps.close();

            System.out.println("Alumno actualizado correctamente");

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //BORRAR UN ENTRENADOR
    //DELETE
     public void borrarEntrenador(int idEntrenador) {

        String query = "DELETE FROM alumno WHERE idEntrenador = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idEntrenador);

            ps.executeUpdate();

            ps.close();

            System.out.println("Entrenador eliminado correctamente");

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }}
     //BUSCAR ENTRENADOR POR DNI
     //DELETE
      public Entrenadores buscarEntrenadorPorDni(int dni) {
    Entrenadores e = null;

    String sql = "SELECT * FROM Entrenadores WHERE dni = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, dni);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            e = new Entrenadores();
            e.setIdEntrenador(rs.getInt("idEntrenador"));
            e.setDni(rs.getString("dni"));
            e.setApellido(rs.getString("apellido"));
            e.setNombre(rs.getString("nombre"));
            e.setEspecialidad(rs.getString("especialidad"));
            e.setEstado(rs.getBoolean("estado"));
        }

        rs.close();
        ps.close();

    } catch (SQLException ex) {
        Logger.getLogger(EntrenadoresData.class.getName())
              .log(Level.SEVERE, null, ex);
    }

    return e;
}
     
        
    }
     
