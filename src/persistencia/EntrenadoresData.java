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
    public Entrenadores buscarEntrenadorPorDni(String dni) {

        String sql = "SELECT * FROM entrenadores WHERE DNI = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Entrenadores e = new Entrenadores();

                    e.setIdEntrenador(rs.getInt("ID_Entrenador"));
                    e.setDni(rs.getString("DNI"));
                    e.setNombre(rs.getString("Nombre"));
                    e.setApellido(rs.getString("Apellido"));
                    e.setEspecialidad(rs.getString("Especialidad"));
                    e.setEstado(rs.getBoolean("Estado"));

                    return e;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntrenadoresData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //BUSCAR ENTRENADORES POR ID
    public Entrenadores buscarEntrenador(int id) {

        String sql = "SELECT * FROM entrenadores WHERE ID_Entrenador = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Entrenadores e = new Entrenadores();

                    e.setIdEntrenador(rs.getInt("ID_Entrenador"));
                    e.setDni(rs.getString("DNI"));
                    e.setNombre(rs.getString("Nombre"));
                    e.setApellido(rs.getString("Apellido"));
                    e.setEspecialidad(rs.getString("Especialidad"));
                    e.setEstado(rs.getBoolean("Estado"));

                    return e;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntrenadoresData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
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

                e.setIdEntrenador(rs.getInt("ID_Entrenador"));
                e.setDni(rs.getString("DNI"));
                e.setNombre(rs.getString("Nombre"));
                e.setApellido(rs.getString("Apellido"));
                e.setEspecialidad(rs.getString("Especialidad"));
                e.setEstado(rs.getBoolean("Estado"));

                Entrenadores.add(e);
            }

            ps.close();

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Entrenadores;
    }


    public void actualizarEntrenador(Entrenadores e) {

        String query = "UPDATE entrenadores SET DNI = ?, Nombre = ?, Apellido = ?, Especialidad = ?, Estado = ? "
                + "WHERE ID_Entrenador = ?";

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

            System.out.println("Entrenador actualizado correctamente");

        } catch (SQLException ex) {
            System.out.println("No se pudo actualizar el entrenador. Verifica que el DNI no esté registrado.");
            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //BORRAR UN ENTRENADOR
    //DELETE
     public void borrarEntrenador(int idEntrenador) {

        String query = "UPDATE entrenadores SET Estado = 0 WHERE ID_Entrenador = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idEntrenador);

            ps.executeUpdate();

            ps.close();

            System.out.println("Entrenador dado de baja correctamente");

        } catch (SQLException ex) {

            Logger.getLogger(EntrenadoresData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean eliminarEntrenador(int idEntrenador) {

        String sql = "DELETE FROM entrenadores WHERE ID_Entrenador = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEntrenador);

            int filas = ps.executeUpdate();
            ps.close();

            return filas > 0;

        } catch (SQLException ex) {
            System.out.println("No se pudo eliminar el entrenador");
            return false;
        }
    }

}
