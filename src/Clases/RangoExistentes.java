package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RangoExistentes { 

    public List<RangoComboItem> obtenerTodosLosRangos() {
        List<RangoComboItem> rangos = new ArrayList<>();
        
        // Consulta SQL
        String sql = "SELECT id_rango, Nombre_Rango FROM permisos_rango ORDER BY Nombre_Rango ASC";
        
        // Usamos la conexión obtenida de tu clase 'Conexion'
        try (Connection conn = Conexion.getConexion(); // <<< USANDO TU CLASE CONEXION
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idRango = rs.getString("id_rango");
                String nombreRango = rs.getString("Nombre_Rango");
                
                // Creamos una instancia de tu clase RangoComboItem
                RangoComboItem item = new RangoComboItem(idRango, nombreRango);
                rangos.add(item);
            }

        } catch (SQLException e) {
            // Manejo de errores de base de datos
            System.err.println("Error SQL al obtener rangos: " + e.getMessage());
            // No imprimimos la pila completa si la conexión falla, pero registramos el error
        }
        
        return rangos;
    }
}