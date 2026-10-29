package persistencia;

import entidades.Clases;
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

public class ClasesData {

    private Connection con = null;

    public ClasesData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    //---------------------------------------------------------------
    //           GUARDAR CLASE (INSERT)
    //---------------------------------------------------------------
    public void guardarClase(Clases c) {

        String sql = "INSERT INTO clases(Disciplina, ID_Entrenador, Horario, Capacidad, Estado)"
                + " VALUES (?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getDisciplina());
            ps.setInt(2, c.getEntrenador().getIdEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(c.getHorario()));
            ps.setInt(4, c.getCapacidad());
            ps.setBoolean(5, c.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                c.setIdClase(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID de la clase");
            }

            ps.close();

            System.out.println("Clase guardada correctamente");

        } catch (SQLException ex) {
            System.out.println("No se pudo insertar la clase");
            Logger.getLogger(ClasesData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    /*
    //---------------------------------------------------------------
    //           BUSCAR CLASE POR ID (SELECT)
    //---------------------------------------------------------------
    public Clases buscarClasePorID(String dni) {

        String sql = "SELECT * FROM clases WHERE ID_Clase= ?";

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
        }}
    
    */
    
    
    
    
    
}
