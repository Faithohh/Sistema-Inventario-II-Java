package Clases;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost/prueba";
    private static final String USER = "root";
    private static final String PASS = "";
    public static Connection getConexion() throws SQLException { 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa");
            return con;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado. " + e.getMessage());
            throw new SQLException("Driver JDBC no encontrado: " + e.getMessage());
        }
    }
}