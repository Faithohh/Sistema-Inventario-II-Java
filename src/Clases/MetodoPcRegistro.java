package Clases;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Time; 
import java.util.Date; 
import java.text.SimpleDateFormat;


public class MetodoPcRegistro {


    public boolean registrarPc(String descripcion, String serie, String marca, int idLaboratorio, int idEstado, java.util.Date fechaRegistro) {
    Connection cn = null;
    PreparedStatement pst = null;
    
    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(fechaRegistro.getTime());
    
    String sql = "INSERT INTO pcs (Descripcion, Serie, Marca, Id_Laboratorio, Id_Estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
    
    try {
        cn = Clases.Conexion.getConexion();
        pst = cn.prepareStatement(sql);
        
        pst.setString(1, descripcion);
        pst.setString(2, serie);
        pst.setString(3, marca);
        pst.setInt(4, idLaboratorio);
        pst.setInt(5, idEstado);
        pst.setTimestamp(6, sqlTimestamp);
        
        int filasAfectadas = pst.executeUpdate();
        return filasAfectadas > 0;
        
    } catch (SQLException e) {
        System.err.println("Error al registrar PC: " + e.getMessage());
        return false;
        
    } finally {
        try {
            if (pst != null) pst.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos de PC: " + e.getMessage());
        }
    }
}
    
    public void cargarinfopc(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
    tabla.setModel(modelo);

    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Marca");
    modelo.addColumn("Laboratorio");
    modelo.addColumn("Estado");
    modelo.addColumn("Registro");
    modelo.addColumn("Alarma");

    SimpleDateFormat fmt12h = new SimpleDateFormat("h:mm a"); 
    SimpleDateFormat fmtFecha = new SimpleDateFormat("dd/MM/yyyy");

    String sql = "SELECT "
                    + "P.Id_Pc, P.Serie, P.Marca, P.fecha_registro, "
                    + "L.Numero_Laboratorio, " 
                    + "E.Descripcion AS Descripcion_Estado, " 
                    + "A.Tiempo, "
                    + "I.Fecha_incidente "
                    + "FROM pcs AS P "
                    + "INNER JOIN laboratorios AS L ON P.Id_Laboratorio = L.Id_Laboratorio "
                    + "INNER JOIN estados AS E ON P.Id_Estado = E.Id_Estado "
                    + "LEFT JOIN alarma AS A ON P.Id_Pc = A.Id_Pc " 
                    + "LEFT JOIN incidencias AS I ON P.Id_Pc = I.Id_Pc "
                    + "ORDER BY P.Id_Pc";

    ResultSet rs = null;
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = Conexion.getConexion(); 
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[8];

            // Mapeo de columnas
            fila[0] = rs.getObject("Id_Pc");     
            fila[1] = rs.getObject("Serie");     
            fila[2] = rs.getObject("Marca");     
            fila[3] = rs.getObject("Numero_Laboratorio"); 
            fila[4] = rs.getObject("Descripcion_Estado");

            Date fechaRegDB = rs.getDate("fecha_registro");
            fila[5] = (fechaRegDB != null) ? fmtFecha.format(fechaRegDB) : "N/A";

            Time tiempoDB = rs.getTime("Tiempo");
            if (tiempoDB != null) {
                fila[6] = fmt12h.format(tiempoDB);
            } else {
                fila[6] = "Sin Alarma"; 
            }

            Date fechaIncDB = rs.getDate("Fecha_incidente");
            fila[7] = (fechaIncDB != null) ? fmtFecha.format(fechaIncDB) : "Sin Incidencia";

            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL al cargar datos: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error general: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos en cargarinfo: " + e.getMessage());
        }
    }    
    }
    
    public void cargarInfoEquipos(JTable tabla) {

    DefaultTableModel modelo = new DefaultTableModel();
    tabla.setModel(modelo);

    modelo.addColumn("Id_Equipo");
    modelo.addColumn("Marca");
    modelo.addColumn("Tipo");
    modelo.addColumn("Descripcion");
    modelo.addColumn("Id_Estado");

    String sql = "SELECT Id_Equipo, Marca, Tipo, Descripcion, Id_Estado FROM equipos";

    ResultSet rs = null;
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = Conexion.getConexion(); 
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[5];

            fila[0] = rs.getInt("Id_Equipo");
            fila[1] = rs.getString("Marca");
            fila[2] = rs.getString("Tipo");
            fila[3] = rs.getString("Descripcion");
            fila[4] = rs.getInt("Id_Estado");

            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL al cargar equipos: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error general: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos en cargarInfoEquipos: " + e.getMessage());
        }
    }
}
    
    public int contarpcs() {
    String sql = "SELECT COUNT(Id_Pc) FROM pcs";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int cantidad = 0;

    try {
        con = Conexion.getConexion(); 
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            cantidad = rs.getInt(1);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al contar PCs: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos en contarPCs: " + e.getMessage());
        }
    }
    return cantidad;
}
    
    public int contaralarmas() {
    String sql = "SELECT COUNT(Id_Alarma) FROM alarma"; 
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int cantidad = 0;

    try {
        con = Conexion.getConexion(); 
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            cantidad = rs.getInt(1);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al contar Alarmas: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos en contarAlarmas: " + e.getMessage());
        }
    }
    return cantidad;
}
    
    public int obtenerIdLaboratorioPorNombre(String nombreLaboratorio) {
    String sql = "SELECT Id_Laboratorio FROM laboratorios WHERE Numero_Laboratorio = ?";
    java.sql.Connection con = null;
    java.sql.PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    int id = 0;

    try {
        con = Clases.Conexion.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreLaboratorio);
        rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("Id_Laboratorio");
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error al obtener Id Laboratorio: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    return id;
}
    
    public int obtenerIdEstadoPorDescripcion(String descripcionEstado) {
    String sql = "SELECT Id_Estado FROM estados WHERE Descripcion = ?";
    java.sql.Connection con = null;
    java.sql.PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    int id = 0;

    try {
        con = Clases.Conexion.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, descripcionEstado);
        rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("Id_Estado");
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error al obtener Id Estado: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    return id;
}

    public boolean eliminarPc(int idPc) {
        Connection cn = null;
        PreparedStatement pst = null;
        
        // **IMPORTANTE: Si la tabla 'hardware' no existe, DEBES renombrar esta variable
        // con el nombre correcto de tu tabla de componentes.**
        String sqlAlarma = "DELETE FROM alarma WHERE Id_Pc = ?";
        String sqlHardware = "DELETE FROM hardware WHERE Id_Pc = ?"; 
        String sqlSoftware = "DELETE FROM software WHERE Id_Pc = ?";
        // String sqlIncidencias = "DELETE FROM incidencias WHERE Id_Pc = ?"; // <-- Eliminado por ser una tabla que ya no existe
        String sqlPc = "DELETE FROM pcs WHERE Id_Pc = ?";
        
        try {
            cn = Clases.Conexion.getConexion();
            cn.setAutoCommit(false); // Iniciar Transacción

            // 1. Eliminar Alarmas
            pst = cn.prepareStatement(sqlAlarma);
            pst.setInt(1, idPc);
            pst.executeUpdate();
            
            // 2. Eliminar Hardware (Verificar nombre de tabla si el error persiste)
            pst = cn.prepareStatement(sqlHardware);
            pst.setInt(1, idPc);
            pst.executeUpdate();
            
            // 3. Eliminar Software
            pst = cn.prepareStatement(sqlSoftware);
            pst.setInt(1, idPc);
            pst.executeUpdate();
            
            // 4. ELIMINACIÓN DE INCIDENCIAS REMOVIDA
            // El código para eliminar incidencias fue removido porque la tabla no existe.
            
            // 5. Eliminar PC principal
            pst = cn.prepareStatement(sqlPc);
            pst.setInt(1, idPc);
            int filasAfectadas = pst.executeUpdate();
            
            cn.commit(); // Confirmar la transacción
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            // Revertir la transacción si algo falla
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error al revertir: " + ex.getMessage());
            }
            System.err.println("Error al eliminar PC: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (pst != null) pst.close();
                // NOTA: Es importante cerrar el Connection al final.
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
    
}