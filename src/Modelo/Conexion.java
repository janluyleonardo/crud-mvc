package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String base = "tienda";
    private final String url = "jdbc:mysql://127.0.0.1/" + base+"?characterEncoding=latin1";
    private final String user = "root";
    private final String pass = "Moreno880501";
    private Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            con = (Connection) DriverManager.getConnection(url, user, pass);
            con = (Connection) DriverManager.getConnection(url,user,pass);
            System.out.println("conexion exitosa a:" + base);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("error en conexion a BD " + base + " = "+e);
        }
        return con;
    }
}
