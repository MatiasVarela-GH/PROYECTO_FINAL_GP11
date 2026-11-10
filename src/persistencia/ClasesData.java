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
import java.sql.Time;
import java.time.LocalDate;

public class ClasesData {

    private Connection con = null;
    private EntrenadoresData ed;

    public ClasesData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
        this.ed = new EntrenadoresData(conexion);
    }

    //---------------------------------------------------------------
    //           GUARDAR CLASE (INSERT)
    //          VALIDAR LA ESPECIALIDAD DEL ENTRENADOR Y HORARIOS ANTES
    //          DE GUARDAR.
    //---------------------------------------------------------------
    public void guardarClase(Clases clase) {

        // =========================
        // CHEQUEO QUE LA CLASE NO SEA NULA
        // =========================
        if (clase == null) {
            System.out.println("Error: La clase no puede ser nula.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR NO SEA NULO
        // =========================
        if (clase.getEntrenador() == null) {
            System.out.println("El entrenador no puede ser nulo, debe ser asignado uno.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR NO ESTÉ DADO DE BAJA
        // =========================
        if (!clase.getEntrenador().isEstado()) {
            System.out.println("El entrenador debe estar activo.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL HORARIO NO SEA NULL
        // =========================
        if (clase.getHorario() == null) {
            System.out.println("Error: La clase debe tener un horario asignado.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA DISCIPLINA NO SEA NULA
        // =========================
        if (clase.getDisciplina() == null || clase.getDisciplina().isBlank()) {
            System.out.println("Error: La disciplina no puede estar vacía.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA CAPACIDAD SEA > 0
        // =========================
        if (clase.getCapacidad() <= 0) {
            System.out.println("Error: La capacidad debe ser mayor a cero.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR NO TENGA SU ESPECIALIDAD NULA / VACÍA
        // =========================
        if (clase.getEntrenador().getEspecialidad() == null || clase.getEntrenador().getEspecialidad().isBlank()) {
            System.out.println("Error: El entrenador debe tener una especialidad asignada.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR NO TENGA OTRA CLASE EN EL MISMO HORARIO
        // =========================
        if (existeHorarioEntrenador(clase.getEntrenador().getIdEntrenador(), clase.getHorario())) {
            System.out.println("Error: El entrenador ya tiene una clase asignada en ese horario.");
            return;
        }

        // =========================
        // Validación: la especialidad del entrenador debe
        // coincidir con la disciplina de la clase
        // =========================
        Entrenadores entrenador = clase.getEntrenador();

        if (!entrenador.getEspecialidad().equalsIgnoreCase(clase.getDisciplina())) {
            System.out.println("Error: La especialidad del entrenador (" + entrenador.getEspecialidad()
                    + ") no coincide con la disciplina de la clase (" + clase.getDisciplina() + ").");
            return;
        }

        String sql = "INSERT INTO clases(Disciplina, ID_Entrenador, Horario, Capacidad, Estado)"
                + " VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, clase.getDisciplina());
            ps.setInt(2, clase.getEntrenador().getIdEntrenador());
            ps.setTime(3, Time.valueOf(clase.getHorario()));
            ps.setInt(4, clase.getCapacidad());
            ps.setBoolean(5, clase.isEstado());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    clase.setIdClase(rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el ID de la clase");
                }
            }

            System.out.println("Clase guardada correctamente");

        } catch (SQLException ex) {
            System.out.println("No se pudo insertar la clase");
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // =========================
    // listarClasesActivas()
    // =========================
    public List<Clases> listarClasesActivas() {

        List<Clases> clases = new ArrayList<>();

        String sql = "SELECT c.*, e.Nombre, e.Apellido, e.DNI, e.Especialidad, e.Estado as EstadoEntrenador "
                + "FROM clases c JOIN entrenadores e ON c.ID_Entrenador = e.ID_Entrenador "
                + "WHERE c.Estado = 1 "
                + "ORDER BY c.Horario";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Clases clase = new Clases();
                clase.setIdClase(rs.getInt("ID_Clase"));
                clase.setDisciplina(rs.getString("Disciplina"));
                clase.setHorario(rs.getTime("Horario").toLocalTime());
                clase.setCapacidad(rs.getInt("Capacidad"));
                clase.setEstado(rs.getBoolean("Estado"));

                Entrenadores ent = new Entrenadores();
                ent.setIdEntrenador(rs.getInt("ID_Entrenador"));
                ent.setNombre(rs.getString("Nombre"));
                ent.setApellido(rs.getString("Apellido"));
                ent.setDni(rs.getString("DNI"));
                ent.setEspecialidad(rs.getString("Especialidad"));
                ent.setEstado(rs.getBoolean("EstadoEntrenador"));

                clase.setEntrenador(ent);
                clases.add(clase);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }

    // =========================
    // buscarClasesPorEntrenador()
    // =========================
    public List<Clases> buscarClasesPorEntrenador(int idEntrenador) {

        List<Clases> clases = new ArrayList<>();

        String sql = "SELECT c.*, e.Nombre, e.Apellido, e.DNI, e.Especialidad, e.Estado as EstadoEntrenador "
                + "FROM clases c JOIN entrenadores e ON c.ID_Entrenador = e.ID_Entrenador "
                + "WHERE c.ID_Entrenador = ? "
                + "AND c.Estado = 1 "
                + "ORDER BY c.Horario";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrenador);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Clases clase = new Clases();
                    clase.setIdClase(rs.getInt("ID_Clase"));
                    clase.setDisciplina(rs.getString("Disciplina"));
                    clase.setHorario(rs.getTime("Horario").toLocalTime());
                    clase.setCapacidad(rs.getInt("Capacidad"));
                    clase.setEstado(rs.getBoolean("Estado"));

                    Entrenadores ent = new Entrenadores();
                    ent.setIdEntrenador(rs.getInt("ID_Entrenador"));
                    ent.setNombre(rs.getString("Nombre"));
                    ent.setApellido(rs.getString("Apellido"));
                    ent.setDni(rs.getString("DNI"));
                    ent.setEspecialidad(rs.getString("Especialidad"));
                    ent.setEstado(rs.getBoolean("EstadoEntrenador"));

                    clase.setEntrenador(ent);
                    clases.add(clase);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }

// =========================
// existeHorarioEntrenador()
// =========================
    public boolean existeHorarioEntrenador(int idEntrenador, java.time.LocalTime horario) {

        String sql = "SELECT COUNT(*) FROM clases "
                + "WHERE ID_Entrenador = ? "
                + "AND Estado = 1 "
                + "AND Horario < ADDTIME(?, '01:00:00') "
                + "AND ADDTIME(Horario, '01:00:00') > ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrenador);
            ps.setTime(2, Time.valueOf(horario));
            ps.setTime(3, Time.valueOf(horario));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

// =========================
// SOBRECARGA DEL MÉTODO existeHorarioEntrenador()
// =========================
    public boolean existeHorarioEntrenador(int idEntrenador, java.time.LocalTime horario, int idClase) {

        String sql = "SELECT COUNT(*) FROM clases "
                + "WHERE ID_Entrenador = ? "
                + "AND Estado = 1 "
                + "AND ID_Clase != ? "
                + "AND Horario < ADDTIME(?, '01:00:00') "
                + "AND ADDTIME(Horario, '01:00:00') > ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrenador);
            ps.setInt(2, idClase);
            ps.setTime(3, Time.valueOf(horario));
            ps.setTime(4, Time.valueOf(horario));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // =========================
    //  contarAsistenciasPorClaseYFecha()
    // =========================
    public int contarAsistenciasPorClaseYFecha(int idClase, LocalDate fecha) {

        int contador = 0;

        String sql = "SELECT COUNT(*) FROM asistencias "
                + "WHERE ID_Clase = ? AND Fecha_Asistencia = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idClase);
            ps.setDate(2, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    contador = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contador;
    }

    // =========================
    // listarClasesDisponibles() - Muestra clases, 
    //entrenador y capacidad disponible para una fecha dada
    // =========================
    public void listarClasesDisponibles(LocalDate fecha) {
        List<Clases> activas = listarClasesActivas();

        System.out.println("--- CLASES DISPONIBLES PARA LA FECHA: " + fecha + " ---");
        for (Clases c : activas) {
            int inscriptos = contarAsistenciasPorClaseYFecha(c.getIdClase(), fecha);
            int disponibles = c.getCapacidad() - inscriptos;

            System.out.println("ID: " + c.getIdClase()
                    + " | Disciplina: " + c.getDisciplina()
                    + " | Horario: " + c.getHorario()
                    + " | Entrenador: " + c.getEntrenador().getNombre() + " " + c.getEntrenador().getApellido()
                    + " | Capacidad Total: " + c.getCapacidad()
                    + " | Lugares Disponibles: " + (disponibles > 0 ? disponibles : 0));
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

        String sql = "SELECT * FROM clases";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }

//---------------------------------------------------------------
//           ACTUALIZAR CLASE (UPDATE)
//---------------------------------------------------------------
    public void actualizarClase(Clases c) {

        // =========================
        // CHEQUEO QUE LA CLASE NO SEA NULA
        // =========================
        if (c == null) {
            System.out.println("La clase no puede ser nula, debe asignarse una.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ID DE LA CLASE SEA VÁLIDO
        // =========================
        if (c.getIdClase() <= 0) {
            System.out.println("Error: el ID de la clase no es válido.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR ESTÉ ASIGNADO Y ACTIVO
        // =========================
        if (c.getEntrenador() == null || !c.getEntrenador().isEstado()) {
            System.out.println("Error: el entrenador debe estar asignado y activo.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA DISCIPLINA NO SEA NULA NI VACÍA
        // =========================
        if (c.getDisciplina() == null || c.getDisciplina().isBlank()) {
            System.out.println("Error: la disciplina no puede estar vacía.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA ESPECIALIDAD DEL ENTRENADOR NO SEA NULA NI VACÍA
        // =========================
        if (c.getEntrenador().getEspecialidad() == null
                || c.getEntrenador().getEspecialidad().isBlank()) {
            System.out.println("Error: el entrenador debe tener una especialidad asignada.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA ESPECIALIDAD DEL ENTRENADOR COINCIDA CON LA DISCIPLINA
        // =========================
        if (!c.getDisciplina().equalsIgnoreCase(c.getEntrenador().getEspecialidad())) {
            System.out.println("La disciplina del entrenador no cumple con la disciplina requerida por la clase.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL HORARIO NO SEA NULO
        // =========================
        if (c.getHorario() == null) {
            System.out.println("Error: la clase debe tener un horario asignado.");
            return;
        }

        // =========================
        // CHEQUEO QUE LA CAPACIDAD SEA MAYOR A CERO
        // =========================
        if (c.getCapacidad() <= 0) {
            System.out.println("Error: la capacidad debe ser mayor a cero.");
            return;
        }

        // =========================
        // CHEQUEO QUE EL ENTRENADOR NO TENGA OTRA CLASE EN EL MISMO HORARIO
        // =========================
        if (existeHorarioEntrenador(
                c.getEntrenador().getIdEntrenador(),
                c.getHorario(),
                c.getIdClase())) {

            System.out.println("Error: el entrenador ya tiene otra clase asignada en ese horario.");
            return;
        }

        String sql = "UPDATE clases SET Disciplina = ?, ID_Entrenador = ?, Horario = ?, Capacidad = ?, Estado = ?"
                + " WHERE ID_Clase = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDisciplina());
            ps.setInt(2, c.getEntrenador().getIdEntrenador());
            ps.setTime(3, Time.valueOf(c.getHorario()));
            ps.setInt(4, c.getCapacidad());
            ps.setBoolean(5, c.isEstado());
            ps.setInt(6, c.getIdClase());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Clase actualizada correctamente");
            } else {
                System.out.println("No se encontró la clase");
            }

        } catch (SQLException ex) {
            System.out.println("No se pudo actualizar la clase.");
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //--------------------------------------------------------------
    //           BORRAR CLASE (BAJA LÓGICA)
    //---------------------------------------------------------------
    public void borrarClase(int idClase) {

        String sql = "UPDATE clases SET Estado = 0 WHERE ID_Clase = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idClase);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Clase dada de baja correctamente");
            } else {
                System.out.println("No se encontró la clase");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean tieneClases(int idEntrenador) {

        String sql = "SELECT COUNT(*) FROM clases WHERE ID_Entrenador = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrenador);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    //--------------------------------------------------------------
    //         FILTRAR CLASES POR DISCIPLINA
    //---------------------------------------------------------------
    public List<Clases> buscarClasesPorDisciplina(String disciplina) {

        List<Clases> clases = new ArrayList<>();

        String sql = "SELECT c.*, e.Nombre, e.Apellido, e.DNI, "
                + "e.Especialidad, e.Estado as EstadoEntrenador "
                + "FROM clases c JOIN entrenadores e "
                + "ON c.ID_Entrenador = e.ID_Entrenador "
                + "WHERE c.Disciplina LIKE ? "
                + "AND c.Estado = 1 "
                + "ORDER BY c.Horario";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + disciplina + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Clases clase = new Clases();
                    clase.setIdClase(rs.getInt("ID_Clase"));
                    clase.setDisciplina(rs.getString("Disciplina"));
                    clase.setHorario(rs.getTime("Horario").toLocalTime());
                    clase.setCapacidad(rs.getInt("Capacidad"));
                    clase.setEstado(rs.getBoolean("Estado"));

                    Entrenadores ent = new Entrenadores();
                    ent.setIdEntrenador(rs.getInt("ID_Entrenador"));
                    ent.setNombre(rs.getString("Nombre"));
                    ent.setApellido(rs.getString("Apellido"));
                    ent.setDni(rs.getString("DNI"));
                    ent.setEspecialidad(rs.getString("Especialidad"));
                    ent.setEstado(rs.getBoolean("EstadoEntrenador"));

                    clase.setEntrenador(ent);
                    clases.add(clase);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasesData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }

}
