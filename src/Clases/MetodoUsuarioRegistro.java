
package Clases;

import java.sql.*;
import javax.swing.JOptionPane;
import java.io.FileInputStream;

public class MetodoUsuarioRegistro {
        
    public void cargarInfoPersonal(javax.swing.JTable tabla) {
    java.sql.Connection con = null;
    java.sql.PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"ID", "CORREO", "NOMBRE", "APELLIDO", "CONTRASEÑA", "RANGO", "FOTO"});
    tabla.setModel(model);
    try {
        
        con = Conexion.getConexion(); 
        
        String sql = "SELECT " +
                     "p.Id_empleado, p.Dni_o_Correo, p.Nombres, p.Apellidos, p.Contrasena, pr.Nombre_Rango, i.Nombre_Archivo " +
                     "FROM personal p " +
                     "INNER JOIN permisos_rango pr ON p.id_rango = pr.id_rango " + // ✅ CORREGIDO
                     "INNER JOIN imagenes i ON p.Id_Imagen = i.Id"; // ✅ CORREGIDO
        
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
            fila[6] = rs.getString(7);
            
            model.addRow(fila);
        }
        
    } catch (java.sql.SQLException e) {
        System.err.println("Error al cargar la información del personal (JOIN): " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar la información del personal: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        
    } finally {
        
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
    
    public boolean registrarPersonal(String dniCorreo, String nombres, String apellidos, String cargo, String contrasena) {
    
    Connection con = null;
    PreparedStatement ps = null;
    
    String sql = "INSERT INTO personal (Dni_o_Correo, Nombres, Apellidos, Contrasena, Rango) VALUES (?, ?, ?, ?, ?)"; 

    
    try {
        con = Conexion.getConexion();
        ps = con.prepareStatement(sql);

        ps.setString(1, dniCorreo);

        ps.setString(2, nombres);

        ps.setString(3, apellidos);

        ps.setString(4, contrasena);

        ps.setString(5, cargo); 
        
        
        int filasAfectadas = ps.executeUpdate();
        
        return filasAfectadas > 0;
        
    } catch (SQLException e) {
        System.err.println("Error al registrar personal: " + e.getMessage());
        e.printStackTrace(); 
        return false;
        
    } finally {
        
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
    
    public boolean verificarUsuarioExistente(String usuario) {
        return false; 
    }    
    
    public boolean validarLogin(String usuario, String contrasena) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginExitoso = false;
        
        
        String sql = "SELECT * FROM personal WHERE Dni_o_Correo = ? AND Contrasena = ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                loginExitoso = true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al validar el login: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de sistema al iniciar sesión. Consulte al administrador.", "Error SQL", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos de login: " + e.getMessage());
            }
        }
        return loginExitoso;
    }
    
    public boolean registrarPersonalConFoto(String dniCorreo, String nombres, String apellidos, String rangoIDSeleccionado, String contrasena, FileInputStream fis, int longitudBytes, String nombreArchivo) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idImagen = -1;
        
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false); 
            
            String sqlImagen = "INSERT INTO imagenes (Imagen, Nombre_Archivo) VALUES (?, ?)";
            ps = con.prepareStatement(sqlImagen, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBinaryStream(1, fis, longitudBytes);
            ps.setString(2, nombreArchivo);
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idImagen = rs.getInt(1);
            } else {
                con.rollback();
                return false;
            }

            String rangoNumericoStr = rangoIDSeleccionado; 
            
            String nombreRangoTextual = rangoIDSeleccionado; 
            
            String sqlPersonal = "INSERT INTO personal (Dni_o_Correo, Nombres, Apellidos, Cargo, Contrasena, Rango, Id_Imagen) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            ps = con.prepareStatement(sqlPersonal);
            
            ps.setString(1, dniCorreo);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setString(4, nombreRangoTextual);
            ps.setString(5, contrasena);
            ps.setString(6, rangoNumericoStr);
            ps.setInt(7, idImagen);
            
            ps.executeUpdate();
            
            con.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error en rollback: " + ex.getMessage());
            }
            System.err.println("Error al registrar personal con foto (SQL o Datos): " + e.getMessage());
            return false;
            
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

    public int obtenerIdRango(String nombreRango) {
    java.sql.Connection con = null;
    java.sql.PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    int idRango = -1;
    
    try {
        con = Conexion.getConexion();
        
        String sql = "SELECT rango FROM permisos_rango WHERE Nombre_Rango = ?"; 
        
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreRango);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            idRango = rs.getInt("rango");
        }
        
    } catch (java.sql.SQLException e) {
        
        System.err.println("Error SQL al obtener el ID del rango: " + e.getMessage());
        idRango = -1;
    } finally {
        
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar recursos en obtenerIdRango: " + e.getMessage());
        }
    }
    return idRango;
}
    
    public boolean eliminarPersonalYFoto(int idEmpleado) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int idImagen = -1;
    
    try {
        con = Conexion.getConexion();
        con.setAutoCommit(false); 
        
        String sqlSelectImagen = "SELECT Id_Imagen FROM personal WHERE Id_empleado = ?";
        ps = con.prepareStatement(sqlSelectImagen);
        ps.setInt(1, idEmpleado);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            idImagen = rs.getInt("Id_Imagen");
        } else {
           
            return true; 
        }

        String sqlDeletePersonal = "DELETE FROM personal WHERE Id_empleado = ?";
        ps = con.prepareStatement(sqlDeletePersonal);
        ps.setInt(1, idEmpleado);
        ps.executeUpdate();
        
        if (idImagen > 0) {
            String sqlDeleteImagen = "DELETE FROM imagenes WHERE Id = ?";
            ps = con.prepareStatement(sqlDeleteImagen);
            ps.setInt(1, idImagen);
            ps.executeUpdate();
        }

        con.commit();
        return true;
        
    } catch (SQLException e) {
        try {
            if (con != null) con.rollback();
        } catch (SQLException ex) {
            System.err.println("Error en rollback: " + ex.getMessage());
        }
        System.err.println("Error al eliminar personal y foto: " + e.getMessage());
        return false;
        
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
    
    public boolean registrarNuevoUsuarioSinFoto(String dniCorreo, String nombres, String apellidos, String contrasena) {

    final String RANGO_ID_POR_DEFECTO = "2"; 

    Connection con = null;
    PreparedStatement ps = null;

    String sql = "INSERT INTO personal (Dni_o_Correo, Nombres, Apellidos, Contrasena, Rango) VALUES (?, ?, ?, ?, ?)"; 

    try {
        con = Conexion.getConexion();
        ps = con.prepareStatement(sql);

        ps.setString(1, dniCorreo);
        ps.setString(2, nombres);
        ps.setString(3, apellidos);
        ps.setString(4, contrasena);
        ps.setString(5, RANGO_ID_POR_DEFECTO);

        int filasAfectadas = ps.executeUpdate(); 

        return filasAfectadas > 0;

    } catch (SQLException e) {
        
        System.err.println("Error al registrar nuevo usuario sin foto: " + e.getMessage());
        return false;

    } finally {
        
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}

    public boolean actualizarPersonal(int id, String dniCorreo, String nombres, String apellidos,String rangoID, String contrasena, java.io.FileInputStream nuevoFis, int longitudBytes, String nombreArchivo) {
    
    Connection con = null;
    PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    int nuevoIdImagen = -1; 
    
    try {
        con = Conexion.getConexion();
        con.setAutoCommit(false);
        
        // 1. Manejo de la FOTO (Si se sube una nueva)
        if (nuevoFis != null) {
            String sqlInsertImagen = "INSERT INTO imagenes (Imagen, Nombre_Archivo) VALUES (?, ?)";
            ps = con.prepareStatement(sqlInsertImagen, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBinaryStream(1, nuevoFis, longitudBytes);
            ps.setString(2, nombreArchivo);
            
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    nuevoIdImagen = rs.getInt(1);
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }
        }
        
        // 2. Construcción del SQL de ACTUALIZACIÓN de PERSONAL
        // INCLUIMOS Dni_o_Correo en el UPDATE
        String sql = "UPDATE personal SET Dni_o_Correo = ?, Nombres = ?, Apellidos = ?, Rango = ?, Contrasena = IFNULL(?, Contrasena)";
        
        if (nuevoIdImagen != -1) { 
            sql += ", Id_Imagen = ?"; 
        }
        
        sql += " WHERE Id_empleado = ?";
        
        ps = con.prepareStatement(sql);
        int indice = 1;

        // 3. Asignación de parámetros
        ps.setString(indice++, dniCorreo); // <-- NUEVO: Dni_o_Correo es el primer parámetro
        ps.setString(indice++, nombres);
        ps.setString(indice++, apellidos);
        ps.setString(indice++, rangoID); // Ya corregido para aceptar el ID numérico del rango

        if (contrasena != null) {
            ps.setString(indice++, contrasena); 
        } else {
            // Si la contraseña es null, IFNULL usa la Contrasena existente.
            ps.setNull(indice++, java.sql.Types.VARCHAR);
        }

        if (nuevoIdImagen != -1) { 
            ps.setInt(indice++, nuevoIdImagen);
        }
        
        ps.setInt(indice++, id); // El ID de empleado es el último parámetro (WHERE clause)

        // 4. Ejecución y Commit/Rollback
        boolean actualizacionExitosa = ps.executeUpdate() > 0;
        
        if (actualizacionExitosa) {
            con.commit(); 
        } else {
            con.rollback();
        }
        
        return actualizacionExitosa;
        
    } catch (java.sql.SQLException e) {
        try {
            if (con != null) con.rollback();
        } catch (java.sql.SQLException ex) {
            System.err.println("Error en rollback al actualizar personal: " + ex.getMessage());
        }
        System.err.println("Error al actualizar personal (SQL): " + e.getMessage());
        return false;
    } finally {
        
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar recursos en actualizarPersonal: " + e.getMessage());
        }
    }
}

    public String obtenerRolUsuario(String usuario, String contrasena) {
        
    String sql = "SELECT pr.Nombre_Rango FROM personal p " +
                 "JOIN permisos_rango pr ON p.Rango = pr.rango " +
                 "WHERE p.Dni_o_Correo = ? AND p.Contrasena = ?"; 
    
    String nombreRol = null;

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, usuario); 
        ps.setString(2, contrasena); 

        try (java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nombreRol = rs.getString("Nombre_Rango"); 
            }
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error al obtener el rol del usuario: " + e.getMessage());
    }
    return nombreRol;
}   
}
