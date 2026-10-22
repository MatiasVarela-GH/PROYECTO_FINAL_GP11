package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConexion{
    private String url;
    private String usuario; //
    private String password;
    
    private  Connection conexion = null;  // lo que importa  
    
    public MiConexion(String url, String usr, String pass){     
        this.url= url;  
        usuario=usr;
        password=pass;
    }
    public Connection buscarConexion() {
    if (conexion == null) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conexion = DriverManager.getConnection(
                url,
                usuario,
                password
            );

            System.out.println("Conexión a la base de datos exitosa.");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
            conexion = null;
        }
    }

    return conexion;
}
  }
