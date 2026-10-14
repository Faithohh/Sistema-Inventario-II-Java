
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailValidator {
    
    public static boolean validarFormato(String email) {
        if (email == null) return false;
        // Expresión regular estándar para formato de email: xxx@yyy.zzz
        final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(EMAIL_REGEX);
    }
    
    public static boolean existeCorreoEnBD(String correo) { // <-- ¡CORRECCIÓN!
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConexion();
            // Contar cuántas filas tienen ese correo/DNI
            String sql = "SELECT COUNT(*) FROM personal WHERE Dni_o_Correo = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Si el conteo es mayor que 0, el correo ya existe
                return rs.getInt(1) > 0; 
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error SQL al verificar existencia del correo: " + e.getMessage());
            return true; // Bloquear por seguridad
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
    
}
