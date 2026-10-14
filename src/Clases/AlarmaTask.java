package Clases; 

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AlarmaTask {
    
    private Timer timerAlarma;

    public void iniciar() {
        if (timerAlarma != null) {
            timerAlarma.cancel();
        }
        
        timerAlarma = new Timer();
        timerAlarma.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                verificarYActivar();
            }
        }, 0, 1000);
    }

    public void detener() {
        if (timerAlarma != null) {
            timerAlarma.cancel();
            timerAlarma = null;
            System.out.println("Verificación de alarma detenida.");
        }
    }

    private void verificarYActivar() {
        Date ahora = new Date();
        SimpleDateFormat fmtHM = new SimpleDateFormat("HH:mm"); 
        String tiempoActualHM = fmtHM.format(ahora); 
        
        Metodos metodos = new Metodos(); 
        
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psSelect = null;
        PreparedStatement psDelete = null;
        
        try {
            con = Conexion.getConexion();
            // Buscar por HH:mm% para evitar el problema de la coincidencia estricta del segundo
            String sqlSelect = "SELECT Descripcion, Tiempo FROM alarma WHERE Tiempo LIKE ?";
            psSelect = con.prepareStatement(sqlSelect);
            psSelect.setString(1, tiempoActualHM + "%"); 
            
            rs = psSelect.executeQuery();
            
            if (rs.next()) {
                String descripcion = rs.getString("Descripcion");
                String tiempoDB = rs.getString("Tiempo"); 
                
                metodos.play("src/Sonidos/virus.wav"); 
                JOptionPane.showMessageDialog(null, "¡ALARMA ACTIVA! Es hora de: " + descripcion);
                
                String sqlDelete = "DELETE FROM alarma WHERE Tiempo = ?";
                psDelete = con.prepareStatement(sqlDelete);
                psDelete.setString(1, tiempoDB);
                
                int filasEliminadas = psDelete.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Alarma activada y eliminada: " + descripcion + " a las " + tiempoDB);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error de BD en la verificación/eliminación: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psSelect != null) psSelect.close();
                if (psDelete != null) psDelete.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
    
    public void cargarpcalarmas(JTable tabla) {
    DefaultTableModel modelo = new DefaultTableModel();
    tabla.setModel(modelo);

    modelo.addColumn("ID PC");
    modelo.addColumn("MARCA");
    modelo.addColumn("ALARMA (Hora)");
    modelo.addColumn("ALARMA (Descripción)");

    SimpleDateFormat fmt12h = new SimpleDateFormat("h:mm a"); 

    String sql = "SELECT "
                    + "P.Id_Pc, P.Marca, "
                    + "A.Tiempo, A.Descripcion AS Descripcion_Alarma "
                    + "FROM pcs AS P "
                    + "LEFT JOIN alarma AS A ON P.Id_Pc = A.Id_Pc " 
                    + "ORDER BY P.Id_Pc, A.Tiempo";

    ResultSet rs = null;
    Connection con = null;
    PreparedStatement ps = null; 

    try {
        con = Conexion.getConexion(); 
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[4];

            fila[0] = rs.getObject("Id_Pc");      
            
            fila[1] = rs.getObject("Marca");      
            
            Time tiempoDB = rs.getTime("Tiempo");
            if (tiempoDB != null) {
                fila[2] = fmt12h.format(tiempoDB);
            } else {
                fila[2] = "Sin Alarma";
            }

            String descripcionAlarma = rs.getString("Descripcion_Alarma");
            fila[3] = (descripcionAlarma != null) ? descripcionAlarma : "---";

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
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    }
}