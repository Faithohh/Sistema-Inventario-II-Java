package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class cargarLaboratoriosEnComboBox { 
    

    public void cargarLaboratorios(JComboBox<String> comboBox) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        // Consulta SQL para obtener el campo Numero_Laboratorio
        String sql = "SELECT Numero_Laboratorio FROM laboratorios ORDER BY Numero_Laboratorio ASC";

        try {
            // 1. Obtener la conexión
            // Asumiendo que Clases.Conexion.getConexion() devuelve la conexión correcta.
            cn = Clases.Conexion.getConexion();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            // 2. Limpiar el ComboBox antes de llenarlo
            comboBox.removeAllItems(); 

            // 3. Llenar el ComboBox con los resultados
            while (rs.next()) {
                String numeroLaboratorio = rs.getString("Numero_Laboratorio");
                comboBox.addItem(numeroLaboratorio);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar laboratorios en ComboBox: " + e.getMessage());
        } finally {
            // 4. Cierre SEGURO de los recursos
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}