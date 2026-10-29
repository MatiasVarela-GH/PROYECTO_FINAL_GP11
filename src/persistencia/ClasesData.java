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
    private EntrenadoresData ed;

    public ClasesData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
        this.ed = new EntrenadoresData(conexion);
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
    
    //---------------------------------------------------------------
    //           BUSCAR CLASE POR ID (SELECT)
    //---------------------------------------------------------------
    public Clases buscarClasePorID(int id) {

        String sql = "SELECT * FROM clases WHERE ID_Clase= ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    
                    Clases c = new Clases();

                    c.setIdClase(rs.getInt("ID_Clase"));
                    c.setDisciplina(rs.getString("Disciplina"));
                    
                    int idEntrenador = rs.getInt("ID_Entrenador");
                    Entrenadores entrenador = ed.buscarEntrenador(idEntrenador);
                    c.setEntrenador(entrenador);

                    c.setHorario(rs.getTime("Horario").toLocalTime());
                    c.setCapacidad(rs.getInt("Capacidad"));
                    c.setEstado(rs.getBoolean("Estado"));

                    return c;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }

    
    //---------------------------------------------------------------
    //           LISTAR LAS CLASES (SELECT *)
    //---------------------------------------------------------------
    public List<Clases> listarClases() {

        List<Clases> clases = new ArrayList<>();

        String query = "SELECT * FROM clases";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Clases c = new Clases();

                    c.setIdClase(rs.getInt("ID_Clase"));
                    c.setDisciplina(rs.getString("Disciplina"));
                    
                    int idEntrenador = rs.getInt("ID_Entrenador");
                    Entrenadores entrenador = ed.buscarEntrenador(idEntrenador);
                    c.setEntrenador(entrenador);

                    c.setHorario(rs.getTime("Horario").toLocalTime());
                    c.setCapacidad(rs.getInt("Capacidad"));
                    c.setEstado(rs.getBoolean("Estado"));

                clases.add(c);
            }

            ps.close();

        } catch (SQLException ex) {

            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }
    
    
    //---------------------------------------------------------------
    //           ACTUALIZAR CLASE (UPDATE)
    //---------------------------------------------------------------

    public void actualizarClase(Clases c) {

        String sql = "UPDATE clases SET Disciplina = ?, ID_Entrenador = ?, Horario = ?, Capacidad = ?, Estado = ?"
        + " WHERE ID_Clase = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, c.getDisciplina());
            ps.setInt(2, c.getEntrenador().getIdEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(c.getHorario()));
            ps.setInt(4, c.getCapacidad());
            ps.setBoolean(5, c.isEstado());
            ps.setInt(6, c.getIdClase());

  

           int filas = ps.executeUpdate();

if (filas > 0) {
    System.out.println("Clase actualizada correctamente");
} else {
    System.out.println("No se encontró la clase");
}
ps.close();

        } catch (SQLException ex) {
            System.out.println("No se pudo actualizar la Clase. Verifica que el ID_Clase no esté registrado ya.");
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //--------------------------------------------------------------
    //           BORRAR CLASE (BAJA LÓGICA)
   //---------------------------------------------------------------

     public void borrarClase(int idClase) {

        String query = "UPDATE clases SET Estado = 0 WHERE ID_Clase = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idClase);

            ps.executeUpdate();

            ps.close();

            System.out.println("Clase dada de baja correctamente");

        } catch (SQLException ex) {

            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    
    
    
    
    
}
