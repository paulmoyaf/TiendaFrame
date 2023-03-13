package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexion {

    // Librería de MySQL
    // public String driver = "com.mysql.jdbc.Driver";
    public String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "productos";

    // Host
    public String hostname = "localhost";
    // public String hostname = "db4free.net";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    // public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    // public String url = "jdbc:mysql://db4free.net:3306/productos";

    // Nombre de usuario
    public String username = "root";
    // public String username = "root_web";

    // Clave de usuario
    public String password = "";
    // public String password = "Paul123456";

    public Connection conectarMySQL() throws ClassNotFoundException{
        Connection conexion = null;
        Statement st=null;
        ResultSet rt=null;

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión establecida");
            // sql=(Statement) conexion.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;

    }




}