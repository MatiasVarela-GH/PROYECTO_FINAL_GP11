/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Rutina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
public class RutinaData {
    
    private Connection con= null;
    
    public RutinaData(MiConexion conexion){
        this.con= conexion.buscarConexion();
    }
    // =========================
    //GUARDAR RUTINA/INSERT
    // =========================
    public void guardarRutina(Rutina rutina){
        String sql = "INSERT INTO rutinas(NombreEjercicio, GrupoMuscular, Descripcion, RutaAnimacion, Estado) VALUES(?,?,?,?,?)";
        
    try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rutina.getNombreEjercicio());
            ps.setString(2, rutina.getGrupoMuscular());
            ps.setString(3, rutina.getDescripcion());
            ps.setString(4, rutina.getRutaAnimacion());
            ps.setBoolean(5, rutina.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                rutina.setIdRutina(rs.getInt(1));
                System.out.println("Rutina guardada con ID: " + rutina.getIdRutina());
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     // =========================
    //BUSCAR RUTINA POR ID/SELECT
    // =========================
    public Rutina buscarRutina(int id) {
        String sql = "SELECT * FROM rutinas WHERE ID_Rutina = ?";
        Rutina rutina = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rutina = new Rutina();
                rutina.setIdRutina(rs.getInt("ID_Rutina"));
                rutina.setNombreEjercicio(rs.getString("NombreEjercicio"));
                rutina.setGrupoMuscular(rs.getString("GrupoMuscular"));
                rutina.setDescripcion(rs.getString("Descripcion"));
                rutina.setRutaAnimacion(rs.getString("RutaAnimacion"));
                rutina.setEstado(rs.getBoolean("Estado"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutina;
    }
     // =========================
    //LISTAR RUTINAS/SELECT
    // =========================
    public List<Rutina> listarRutinas() {
        List<Rutina> rutinas = new ArrayList<>();
        String sql = "SELECT * FROM rutinas";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rutina rutina = new Rutina();
                rutina.setIdRutina(rs.getInt("ID_Rutina"));
                rutina.setNombreEjercicio(rs.getString("NombreEjercicio"));
                rutina.setGrupoMuscular(rs.getString("GrupoMuscular"));
                rutina.setDescripcion(rs.getString("Descripcion"));
                rutina.setRutaAnimacion(rs.getString("RutaAnimacion"));
                rutina.setEstado(rs.getBoolean("Estado"));
                rutinas.add(rutina);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutinas;
    }
    // =========================
    //ACTUALIZAR RUTINA/UPDATE
    // =========================
    public void actualizarRutina(Rutina rutina) {
        String sql = "UPDATE rutinas SET "
                + "NombreEjercicio = ?, "
                + "GrupoMuscular = ?, "
                + "Descripcion = ?, "
                + "RutaAnimacion = ?, "
                + "Estado = ? "
                + "WHERE ID_Rutina = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rutina.getNombreEjercicio());
            ps.setString(2, rutina.getGrupoMuscular());
            ps.setString(3, rutina.getDescripcion());
            ps.setString(4, rutina.getRutaAnimacion());
            ps.setBoolean(5, rutina.isEstado());
            ps.setInt(6, rutina.getIdRutina());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // =========================
    //DAR DE BAJA RUTINA
    // =========================

    public void borrarRutina(int idRutina) {
        String sql = "UPDATE rutinas SET Estado = 0 WHERE ID_Rutina = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idRutina);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // =========================
    // DAR DE ALTA RUTINA
    // =========================
     public void activarRutina(int idRutina) {

        String sql = "UPDATE rutinas SET Estado = 1 WHERE ID_Rutina = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idRutina);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
     // =========================
     //ELIMINAR RUTINA DEFINITIVAMENTE/DELETE
     // =========================
     public void eliminarRutina(int idRutina) {

        String sql = "DELETE FROM rutinas WHERE ID_Rutina = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idRutina);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(RutinaData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
