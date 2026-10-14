package Clases;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MetodoUsuario {
    

public void cargarInfoPersonal(javax.swing.JTable tabla) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"ID", "USUARIO", "NOMBRE", "APELLIDO", "CONTRASEÑA", "RANGO", "FOTO"});
    tabla.setModel(model); 
    
    try {
        con = Conexion.getConexion(); 
        
        String sql = "SELECT " +
                     "p.Id_empleado, p.Dni_o_Correo, p.Nombres, p.Apellidos, p.Contrasena, pr.Nombre_Rango, i.Nombre_Archivo " +
                     "FROM personal p " +
                     "INNER JOIN permisos_rango pr ON p.id_rango = pr.id_rango " + 
                     "LEFT JOIN imagenes i ON p.Id_Imagen = i.Id " + // <--- CORRECCIÓN CLAVE: Usamos 'i.Id' para la unión
                     "ORDER BY p.Id_empleado ASC";
        
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Object[] fila = new Object[7]; 
            
            fila[0] = rs.getString(1); 
            fila[1] = rs.getString(2); 
            fila[2] = rs.getString(3);
            fila[3] = rs.getString(4); 
            fila[4] = rs.getString(5); 
            fila[5] = rs.getString(6); 
            
            String nombreFoto = rs.getString(7);
            fila[6] = (nombreFoto != null) ? nombreFoto : "No Asignada"; 
            
            model.addRow(fila);
        }
        
    } catch (SQLException e) {
        System.err.println("Error al cargar la información del personal: " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar la información del personal: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}  
}
