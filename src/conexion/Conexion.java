package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexion {

    // Librería de MySQL
    public String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "productos";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";

    public Connection conectarMySQL() throws ClassNotFoundException{
    // public static void conectarMySQL() throws ClassNotFoundException{
        Connection conexion = null;
        Statement st=null;
        ResultSet rt=null;

        try {
            Class.forName(driver);
            // try{
            conexion = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión establecida");
            // sql=(Statement) conexion.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conexion;

    }




}