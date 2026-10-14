package Clases; // Ajusta este paquete según donde guardes tus métodos

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.security.SecureRandom;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;


public class RecuperarContrasenaMetodo {

    private String generarTokenAleatorio(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            sb.append(random.nextInt(10)); // Genera un dígito entre 0 y 9
        }
        return sb.toString();
    }

    private boolean guardarTokenEnBD(String correo, String token) {
        Connection con = null;
        PreparedStatement ps = null;
        
        // Define la expiración: 1 hora (60 minutos * 60 segundos * 1000 milisegundos)
        Date expiracion = new Date(System.currentTimeMillis() + (60 * 60 * 1000));
        
        try {
            con = Conexion.getConexion();
            
            // Asegúrate de que tu tabla 'personal' tenga las columnas:
            // token_recuperacion (VARCHAR) y token_expiracion (DATETIME)
            String sql = "UPDATE personal SET token_recuperacion = ?, token_expiracion = ? WHERE Dni_o_Correo = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, token);
            ps.setTimestamp(2, new java.sql.Timestamp(expiracion.getTime())); // Usamos java.sql.Timestamp
            ps.setString(3, correo);

            return ps.executeUpdate() > 0; // True si se actualizó una fila
        } catch (SQLException e) {
            System.err.println("Error SQL al guardar token: " + e.getMessage());
            return false;
        } finally {
            // Cierre de recursos (debes implementar los métodos de cierre en tu clase de utilidades)
            try { if (ps != null) ps.close(); } catch (SQLException ex) {}
            try { if (con != null) con.close(); } catch (SQLException ex) {}
        }
    }

    public boolean existeUsuarioPorCorreo(String correo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT COUNT(*) FROM personal WHERE Dni_o_Correo = ?";
        
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el usuario existe
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario por correo: " + e.getMessage());
            return false;
        } finally {
            // Cierre de recursos...
        }
    }

    private boolean enviarCorreoConToken(String destinatario, String token) {
        // ATENCIÓN: DEBES CAMBIAR ESTOS VALORES
        final String remitenteEmail = "tukibot8@gmail.com"; 
        final String remitentePassword = "zhrr jxnk runk yhla"; // ¡NO TU CONTRASEÑA NORMAL!

        Properties props = new Properties();
        // Configuración para Gmail (ajusta si usas otro servicio)
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitenteEmail, remitentePassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitenteEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Código de Recuperación de Contraseña");
            
            String contenido = "Hola,\n\n"
                  + "Tu código de verificación para restablecer la contraseña es:\n\n"
                  + "🔑 " + token + " 🔑\n\n"
                  + "Este código es válido por 60 minutos. Si no solicitaste este cambio, ignora este correo.\n\n"
                  + "Atentamente,\nTu equipo de soporte Q.V.L.C.";
                             
            message.setText(contenido);
            Transport.send(message);
            return true;
            
        } catch (MessagingException e) {
            System.err.println("Error al enviar correo (MessagingException): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
   
    public boolean iniciarRecuperacion(String correo) {
        
        // 1. Verificar si el correo existe
        if (!existeUsuarioPorCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "El correo ingresado no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // 2. Generar el Token y guardarlo en la BD
        String token = generarTokenAleatorio(6);
        if (!guardarTokenEnBD(correo, token)) {
            JOptionPane.showMessageDialog(null, "Error interno al preparar la recuperación.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // 3. Enviar el correo con el Token
        if (enviarCorreoConToken(correo, token)) {
            JOptionPane.showMessageDialog(null, "Se ha enviado un código de verificación a su correo.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo. Revise la configuración SMTP o su conexión.", "Error de Envío", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean restablecerContrasena(String token, String nuevaContrasena) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConexion();
            
            // 1. Verificar Token y Expiración (el token debe ser válido y NO estar expirado)
            String sqlCheck = "SELECT Dni_o_Correo FROM personal WHERE token_recuperacion = ? AND token_expiracion > NOW()";
            ps = con.prepareStatement(sqlCheck);
            ps.setString(1, token);
            rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Código inválido o expirado. Intente de nuevo.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // 2. Si es válido, actualizar la contraseña y anular el token
            String correoUsuario = rs.getString("Dni_o_Correo"); // Obtener el usuario asociado
            
            String sqlUpdate = "UPDATE personal SET Contrasena = ?, token_recuperacion = NULL, token_expiracion = NULL WHERE Dni_o_Correo = ?";
            ps.close(); // Cerrar el statement anterior
            ps = con.prepareStatement(sqlUpdate);
            // **IMPORTANTE:** Aquí DEBES encriptar la nuevaContrasena antes de guardarla.
            ps.setString(1, nuevaContrasena); // <--- ¡Asegúrate de ENCRIPTAR esto!
            ps.setString(2, correoUsuario);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error SQL al restablecer contraseña: " + e.getMessage());
            return false;
        } finally {
            // Cierre de recursos...
        }
    }
    
    public String verificarToken(String token) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = Conexion.getConexion();
        // Verifica Token y que NO esté expirado
        String sqlCheck = "SELECT Dni_o_Correo FROM personal WHERE token_recuperacion = ? AND token_expiracion > NOW()";
        ps = con.prepareStatement(sqlCheck);
        ps.setString(1, token);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("Dni_o_Correo"); // Retorna el correo si es válido
        }
        return null; // Token inválido o expirado
    } catch (SQLException e) {
        System.err.println("Error SQL al verificar token: " + e.getMessage());
        return null;
    } finally {
        // ... (Cierre de recursos)
    }
}
    
}
