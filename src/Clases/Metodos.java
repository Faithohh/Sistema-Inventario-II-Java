package Clases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;


public class Metodos {

    public void aplicarImageRenderer(JTable tabla, int columnIndex) {
        tabla.getColumnModel().getColumn(columnIndex).setCellRenderer(new ImageRenderer());
    }
    public static class ImageRenderer extends DefaultTableCellRenderer {
        
        // 🚨 RECUERDA CAMBIAR ESTA RUTA
        private static final String RUTA_BASE_FOTOS = "C:\\Users\\Usuario\\Imágenes\\Fotos_Personal\\"; 
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);

            String nombreArchivo = (value == null) ? null : value.toString();
            
            if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                try {
                    File archivo = new File(RUTA_BASE_FOTOS + nombreArchivo);
                    
                    if (archivo.exists()) {
                        ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
                        label.setIcon(new ImageIcon(img));
                        
                        if (table.getRowHeight(row) < 50) {
                            table.setRowHeight(row, 50);
                        }
                    } else {
                        label.setText("Archivo no encontrado");
                    }
                } catch (Exception e) {
                    label.setText("Error");
                }
            } else {
                label.setText("Sin Foto");
            }
            
            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
                label.setOpaque(true);
            } else {
                label.setBackground(new Color(0, 0, 0, 0));
                label.setOpaque(false);
            }
            
            return label;
        }
    }
    
    public void play(String rutaArchivo) {
        try {
            java.io.File soundFile = new File(rutaArchivo).getAbsoluteFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            
        } catch (Exception ex) {
            System.err.println("Error al reproducir el sonido: " + ex.getMessage());
            ex.printStackTrace();
        }
    }    
    public void hora(JSpinner spinner) {
        SpinnerDateModel modeloHora = new SpinnerDateModel();
        spinner.setModel(modeloHora);
        JSpinner.DateEditor timeEditor = 
            new JSpinner.DateEditor(spinner, "hh:mm a");
        spinner.setEditor(timeEditor);
    }    
    public String fecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaActual);
    }    
    public void cargarAlarmas(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
    tabla.setModel(modelo);
    
    modelo.addColumn("ID PC");
    modelo.addColumn("TIEMPO (AM/PM)");
    modelo.addColumn("DESCRIPCIÓN"); 
    
    java.text.SimpleDateFormat fmt12h = new java.text.SimpleDateFormat("h:mm a");
    
    String sql = "SELECT Id_Pc, Tiempo, Descripcion FROM alarma ORDER BY Id_Pc, Tiempo";
    
    ResultSet rs = null;
    Connection con = null;
    PreparedStatement ps = null;
    
    try {
        con = Conexion.getConexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Object[] fila = new Object[3];
            
            java.sql.Time tiempoDB = rs.getTime("Tiempo"); 
            String tiempo12h = fmt12h.format(tiempoDB); 
            
            fila[0] = rs.getObject("Id_Pc");
            fila[1] = tiempo12h;
            fila[2] = rs.getObject("Descripcion");
            
            modelo.addRow(fila);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar las alarmas: " + e.getMessage());
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
    public void cargarFotos(JTable tabla) {        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(modelo);
        
        modelo.addColumn("NOMBRE DE ARCHIVO"); 
        
        String sql = "SELECT Nombre_Archivo FROM imagenes"; 
        ResultSet rs = null;
        Connection con = null;
        
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Object[] fila = new Object[1];
                
                fila[0] = rs.getString("Nombre_Archivo"); 
                
                modelo.addRow(fila);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al cargar la lista de fotos. Asegúrese de que la columna 'Nombre_Archivo' exista. Mensaje: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al cargar la lista de fotos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }    

}