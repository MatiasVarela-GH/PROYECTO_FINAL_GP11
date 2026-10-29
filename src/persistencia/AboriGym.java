package persistencia;
import entidades.Entrenadores;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import entidades.Clases;
import java.time.LocalTime;
public class AboriGym {

    public static void main(String[] args) {
        System.out.println("--- AboriGym ---");

        MiConexion conexion = new MiConexion(
                "jdbc:mariadb://localhost:3306/aborigym_gp11",
                "root",
                ""
        );
        
        conexion.buscarConexion();

        System.out.println("-- Integrantes --");
        System.out.println("Juan Cruz Gagliano");
        System.out.println("Matias Varela");
        System.out.println("Pablo German Carnota");
        System.out.println("Thiago Hernan Carnota");
        
        EntrenadoresData opcional = new EntrenadoresData(conexion);
        
        // Guardar
        Entrenadores entrenador = new Entrenadores();
       
        entrenador.setApellido("Sanchez");
        entrenador.setNombre("Javier");
        entrenador.setDni("40485556");
        entrenador.setEspecialidad("Musculacion");
        entrenador.setEstado(true);
        
        opcional.guardarEntrenador(entrenador);
        System.out.println("ID generado: " + entrenador.getIdEntrenador());
        
        // Buscar x DNI
        
        Entrenadores buscarXDni = opcional.buscarEntrenadorPorDni("40485556");
        
        if (buscarXDni != null) {
            System.out.println("Entrenador encontrado: " + buscarXDni.getApellido() + " " + buscarXDni.getNombre() 
            );
        } else  {
            System.out.println("Entrenador NO encontrado...");
        }
        
        // Buscar x ID
        
        Entrenadores buscarXId = opcional.buscarEntrenador(1);
        
        if (buscarXId != null) {
            System.out.println("Entrenador encontrado: " + buscarXId.getApellido() + " " + buscarXId.getNombre() 
            );
        } else  {
            System.out.println("Entrenador NO encontrado...");
        }
        
        // Actualizar

        entrenador.setNombre("Francisco");
        entrenador.setDni("34567890");
        
        opcional.actualizarEntrenador(entrenador);

        // Listar 
        System.out.println(" TODOS LOS ENTRENADORES ");

        List<Entrenadores> lista = opcional.listarEntrenadores();

        for (Entrenadores e : lista) {
            System.out.println(e.getIdEntrenador() + " " + e.getApellido() + " " + e.getNombre());
        }

        // Borrar
        opcional.borrarEntrenador(2);

        Entrenadores dadoDeBaja = opcional.buscarEntrenador(2);

        if (dadoDeBaja != null) {
            System.out.println("Estado del entrenador: " + dadoDeBaja.isEstado());
        }
        
        ClasesData clasesData = new ClasesData(conexion);
        Entrenadores entrenadorExistente =
                
        opcional.buscarEntrenadorPorDni("34567890");
        
        Clases clase = new Clases();
        
        clase.setDisciplina("Musculacion");
        clase.setEntrenador(entrenadorExistente);
        clase.setHorario(LocalTime.of(18, 0));
        clase.setCapacidad(15);
        clase.setEstado(true);
        
        clasesData.guardarClase(clase);
        System.out.println("ID generado de la clase: " + clase.getIdClase());
        System.out.println(clase);
        
    }
}
