package Clases; 

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane; // Importar para los mensajes de error/éxito

public class MetodoHardwareRegistro {

public boolean registrarEquipoSimple(String marcaPC, String tipoPC, String descripcionPC, java.sql.Date fechaCompra, String estadoTexto) {
    
    // Consulta SQL: Incluye la columna de Fecha y el campo de Estado como texto
    String sql = "INSERT INTO equipos (Marca, Tipo, Descripcion, Fecha_compra, Estado_Texto) VALUES (?, ?, ?, ?, ?)";
    
    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, marcaPC);
        ps.setString(2, tipoPC);
        ps.setString(3, descripcionPC);
        ps.setDate(4, fechaCompra);  // << Guarda la FECHA (java.sql.Date)
        ps.setString(5, estadoTexto); // << Guarda el TEXTO del estado (String)

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
        
    } catch (java.sql.SQLException e) {
        System.err.println("Error al registrar el Equipo en la tabla 'hardware': " + e.getMessage());
        return false;
    }
}
    
    public boolean registrarPC(String serie, int idLaboratorio, int idEquipo) {
    // Asumo que tu tabla PCS tiene: Id_Pc (PK), Serie, Id_Laboratorio, Id_Estado, Id_Equipo, fecha_registro
    // NOTA: Si tu tabla pcs ya no tiene Id_Estado, deberías pasarlo a este método. 
    
    // Si tu tabla pcs aun tiene Id_Estado, Id_Laboratorio, Serie y Id_Equipo:
    String sql = "INSERT INTO pcs (Serie, Id_Laboratorio, Id_Equipo, fecha_registro) " +
                 "VALUES (?, ?, ?, CURDATE())"; 
    
    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, serie);
        ps.setInt(2, idLaboratorio);
        ps.setInt(3, idEquipo);
        
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
        
    } catch (java.sql.SQLException e) {
        System.err.println("Error al registrar la PC: " + e.getMessage());
        return false;
    }
}
    
    public int buscarOInsertarDescripcionTipoHardware(String descripcionTipo) {
    // La tabla objetivo es 'tipo_hardware'
    
    // 1. Intentar buscar el ID (Asumo que el ID es Id_Tipo)
    String sqlBuscar = "SELECT Id_Tipo FROM tipo_hardware WHERE descripcion = ?";
    
    // 2. Si no existe, insertar (asumiendo que Id_Tipo es AUTO_INCREMENT)
    String sqlInsertar = "INSERT INTO tipo_hardware (descripcion) VALUES (?)";
    
    // La variable debe reflejar el ID de la tabla de tipo
    int idTipo = -1; 

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
         java.sql.PreparedStatement psInsertar = con.prepareStatement(sqlInsertar, java.sql.Statement.RETURN_GENERATED_KEYS)) {
        
        // Búsqueda
        psBuscar.setString(1, descripcionTipo);
        try (java.sql.ResultSet rs = psBuscar.executeQuery()) {
            if (rs.next()) {
                idTipo = rs.getInt("Id_Tipo"); // Obtiene el ID de la tabla 'tipo_hardware'
                return idTipo;
            }
        }

        // Si no existe, Insertar
        psInsertar.setString(1, descripcionTipo);
        psInsertar.executeUpdate();
        
        // Obtener el ID generado
        try (java.sql.ResultSet rs = psInsertar.getGeneratedKeys()) {
            if (rs.next()) {
                idTipo = rs.getInt(1); 
            }
        }
    } catch (java.sql.SQLException e) {
        // Asegúrate que el mensaje de error mencione la tabla correcta
        System.err.println("Error al buscar/insertar en tabla tipo_hardware: " + e.getMessage());
    }
    return idTipo;
}
   
    public int buscarOInsertarDescripcionAtributos(String descripcionAtributos) {
    // La tabla objetivo es 'atributos_hardware'
    
    // 1. Intentar buscar el ID (Asumo que el ID es Id_atributo)
    String sqlBuscar = "SELECT Id_atributo FROM atributos_hardware WHERE descripcion = ?";
    
    // 2. Si no existe, insertar
    String sqlInsertar = "INSERT INTO atributos_hardware (descripcion) VALUES (?)";
    
    // La variable debe reflejar el ID de la tabla de atributos, no Id_hardware
    int idAtributo = -1; 

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
         java.sql.PreparedStatement psInsertar = con.prepareStatement(sqlInsertar, java.sql.Statement.RETURN_GENERATED_KEYS)) {
        
        // Búsqueda
        psBuscar.setString(1, descripcionAtributos); // Usa el parámetro correcto
        try (java.sql.ResultSet rs = psBuscar.executeQuery()) {
            if (rs.next()) {
                idAtributo = rs.getInt("Id_atributo"); // Obtiene el ID de la tabla 'atributos_hardware'
                return idAtributo;
            }
        }

        // Si no existe, Insertar
        psInsertar.setString(1, descripcionAtributos); // Usa el parámetro correcto
        psInsertar.executeUpdate();
        
        // Obtener el ID generado
        try (java.sql.ResultSet rs = psInsertar.getGeneratedKeys()) {
            if (rs.next()) {
                idAtributo = rs.getInt(1); 
            }
        }
    } catch (java.sql.SQLException e) {
        // Asegúrate que el mensaje de error mencione la tabla correcta
        System.err.println("Error al buscar/insertar en tabla atributos_hardware: " + e.getMessage());
    }
    return idAtributo;
}
    
    public int buscarOInsertarDescripcionHardware(String descripcionHardware) {
    // 1. Intentar buscar el ID
    String sqlBuscar = "SELECT Id_hardware FROM hardware WHERE descripcion = ?";
    // 2. Si no existe, insertar (asumiendo que Id_hardware es AUTO_INCREMENT)
    String sqlInsertar = "INSERT INTO hardware (descripcion) VALUES (?)";
    int idHardware = -1;

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
         java.sql.PreparedStatement psInsertar = con.prepareStatement(sqlInsertar, java.sql.Statement.RETURN_GENERATED_KEYS)) {
        
        // Búsqueda
        psBuscar.setString(1, descripcionHardware);
        try (java.sql.ResultSet rs = psBuscar.executeQuery()) {
            if (rs.next()) {
                idHardware = rs.getInt("Id_hardware");
                return idHardware;
            }
        }

        // Si no existe, Insertar
        psInsertar.setString(1, descripcionHardware);
        psInsertar.executeUpdate();
        
        // Obtener el ID generado
        try (java.sql.ResultSet rs = psInsertar.getGeneratedKeys()) {
            if (rs.next()) {
                idHardware = rs.getInt(1); // Obtiene el valor de la primera columna generada (Id_hardware)
            }
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error al buscar/insertar en tabla hardware: " + e.getMessage());
    }
    return idHardware;
}
    
    public String buscarSeriePorIdPc(int idPc) {
    String sql = "SELECT Serie FROM pcs WHERE Id_Pc = ?";
    String serie = null;
    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idPc);
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                serie = rs.getString("Serie");
            }
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error al buscar Serie por ID de PC: " + e.getMessage());
    }
    return serie;
}
    
    public String obtenerNumeroLaboratorioPorId(int idLaboratorio) {
    String sql = "SELECT Numero_Laboratorio FROM laboratorios WHERE Id_Laboratorio = ?";
    String numero = null;
    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idLaboratorio);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numero = rs.getString("Numero_Laboratorio");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener número de laboratorio por ID: " + e.getMessage());
    }
    return numero;
}

    public String obtenerDescripcionEstadoPorId(int idEstado) {
    String sql = "SELECT Descripcion FROM estados WHERE Id_Estado = ?";
    String descripcion = null;
    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idEstado);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                descripcion = rs.getString("Descripcion");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener descripción de estado por ID: " + e.getMessage());
    }
    return descripcion;
}

    public int obtenerIdLaboratorioPorNumero(String numeroLaboratorio) {
    String sql = "SELECT Id_Laboratorio FROM laboratorios WHERE Numero_Laboratorio = ?";
    int idLaboratorio = -1;

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, numeroLaboratorio);
        
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idLaboratorio = rs.getInt("Id_Laboratorio");
            }
        }
    } catch (java.sql.SQLException e) {
        System.err.println("Error SQL al obtener ID de Laboratorio por número: " + e.getMessage());
    }
    return idLaboratorio;
}
    
    public void cargarComboBoxEstados(JComboBox<String> combo) {
    // Usamos la Descripcion ya que es lo que el método obtenerIdEstadoPorNombre() necesita.
    String sql = "SELECT Descripcion FROM estados ORDER BY Descripcion ASC";
    
    try (Connection con = Clases.Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        combo.removeAllItems();
        
        while (rs.next()) {
            combo.addItem(rs.getString("Descripcion"));
        }

    } catch (SQLException e) {
        System.err.println("Error al cargar ComboBox de Estados: " + e.getMessage());
    }
}
    
    public int obtenerIdEstadoPorNombre(String nombreEstado) { 
        // Usamos 'Descripcion' ya que la tabla 'estados' la tiene
        String sql = "SELECT Id_Estado FROM estados WHERE Descripcion = ?"; 
        int idEstado = -1; 
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombreEstado);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idEstado = rs.getInt("Id_Estado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener ID de Estado: " + e.getMessage());
        }
        return idEstado;
    }

    public void cargarComboBoxLaboratoriosFiltro(JComboBox<String> combo) {
        // Asumo que quieres mostrar el Numero_Laboratorio o Descripcion en lugar del ID
        // Si solo quieres el ID, el SQL es correcto, pero muestro el Numero_Laboratorio para mejor UX.
        String sql = "SELECT Numero_Laboratorio FROM laboratorios ORDER BY Id_Laboratorio";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            combo.addItem("TODOS"); // Opción para mostrar todos los registros
            
            while (rs.next()) {
                // Si quieres mostrar el ID usa rs.getString("Id_Laboratorio")
                // Si quieres el nombre, asegúrate de que el SELECT lo traiga.
                combo.addItem(rs.getString("Numero_Laboratorio")); 
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar ComboBox de Laboratorios: " + e.getMessage());
        }
    }
    
    public String[] buscarDetallesPC(String serie) {
        // Asumiendo que la tabla es 'pcs' y la Serie es única
        String sql = "SELECT Id_Pc, Serie, Marca, Descripcion, Id_Laboratorio, Id_Estado FROM pcs WHERE Serie = ?";
        String[] datosPC = null;

        try (Connection con = Clases.Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, serie);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    datosPC = new String[6];
                    datosPC[0] = rs.getString("Id_Pc");
                    datosPC[1] = rs.getString("Serie");
                    datosPC[2] = rs.getString("Marca");
                    datosPC[3] = rs.getString("Descripcion");
                    datosPC[4] = rs.getString("Id_Laboratorio");
                    datosPC[5] = rs.getString("Id_Estado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al buscar detalles de PC: " + e.getMessage());
        }
        return datosPC;
    }

    public void cargarPCsPorLaboratorio(javax.swing.JTable tabla, String filtroLaboratorio) {
    String sql;
    
    // Si el filtro es "TODOS", la consulta no lleva WHERE
    if (filtroLaboratorio.equals("TODOS")) {  
        sql = "SELECT Id_Pc FROM pcs";  
    } else {
        // Si hay filtro, la consulta lleva el Id_Laboratorio
        sql = "SELECT Id_Pc FROM pcs WHERE Id_Laboratorio = ?";
    }

    try (java.sql.Connection con = Clases.Conexion.getConexion();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar

        // Asignación del parámetro (solo si NO es "TODOS")
        if (!filtroLaboratorio.equals("TODOS")) {
            // *** AQUÍ OCURRE LA CONVERSIÓN DE TEXTO A ID ***
            int idNumerico = obtenerIdLaboratorioPorNumero(filtroLaboratorio);
            
            if (idNumerico != -1) {
                ps.setInt(1, idNumerico); 
            } else {
                // Si el Laboratorio no existe, salimos sin hacer la consulta.
                return;
            }
        }

        // Ejecución y llenado de la tabla rstbpc
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] fila = new Object[1];  
                fila[0] = rs.getString("Id_Pc");
                model.addRow(fila);
            }
        }

    } catch (Exception e) {
        System.err.println("Error al cargar IDs de PCs por Laboratorio: " + e.getMessage());
    }
}
    
    public void cargarHardwareGenerico(javax.swing.JTable tabla) {
        String sql = "SELECT " +
                     // CORRECCIÓN: Usamos h.Descripcion y le damos un alias 'Serie' para la JTable
                     "h.Descripcion AS Serie, " + 
                     "e.Descripcion AS Estado, " +
                     "t.Descripcion AS Tipo, " +
                     "h.Marca, " +
                     "a.Descripcion AS Caracteristicas " +
                     "FROM hardware h " +
                     "JOIN estados e ON h.Id_Estado = e.Id_Estado " +
                     "JOIN tipo_de_hardware t ON h.Id_TipoHardware = t.Id_Tipo_hardware " + // ¡CORREGIDO!
                     "JOIN atributos_hardware a ON h.Id_AtributoHardware = a.Id_Atributo " + 
                     "ORDER BY h.Descripcion"; // Ordenamos por la Descripción/Identificador
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                // Se asume que el modelo tiene 5 columnas en este orden
                Object[] fila = new Object[5]; 
                fila[0] = rs.getString("Serie"); // Obtiene el valor de h.Descripcion
                fila[1] = rs.getString("Estado");
                fila[2] = rs.getString("Tipo");
                fila[3] = rs.getString("Marca");
                fila[4] = rs.getString("Caracteristicas");
                model.addRow(fila);
            }

        } catch (Exception e) {
            System.err.println("Error al cargar Hardware Genérico: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla de Hardware: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean guardarHardwareGenerico(String nombrepc, String marcapc, String tipopc, String descripcionpc, int idEstado) {
    java.sql.Connection con = null;
    boolean exito = false;
    
    int idTipoHardware = -1;
    int idAtributoHardware = -1; 
    
    // Consultas SQL:
    String sqlTipo = "INSERT INTO tipo_de_hardware (Descripcion) VALUES (?)"; 
    String sqlAtributo = "INSERT INTO atributos_hardware (Descripcion) VALUES (?)";
    
    // CONSULTA FINAL CORREGIDA: Usa las columnas confirmadas (Id_Tipo_hardware, Id_atributo)
    // El campo Descripcion recibe la Marca (marcapc)
    String sqlHardware = "INSERT INTO hardware (Descripcion, Id_Tipo_hardware, Id_atributo, Id_Estado) VALUES (?, ?, ?, ?)"; 

    try {
        con = Clases.Conexion.getConexion();
        con.setAutoCommit(false); // 1. INICIAR TRANSACCIÓN

        // PASO 1: Insertar Tipo de Hardware (txttipopc) y obtener la FK
        try (java.sql.PreparedStatement psTipo = con.prepareStatement(sqlTipo, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            psTipo.setString(1, tipopc); // -> Columna: Descripcion de tipo_de_hardware
            psTipo.executeUpdate();
            try (java.sql.ResultSet rs = psTipo.getGeneratedKeys()) {
                if (rs.next()) {
                    idTipoHardware = rs.getInt(1);
                } else {
                    throw new java.sql.SQLException("Fallo al obtener la clave generada para tipo_de_hardware.");
                }
            }
        }

        // PASO 2: Insertar Atributos (txtdescripcionpc) y obtener la FK
        try (java.sql.PreparedStatement psAtributo = con.prepareStatement(sqlAtributo, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            psAtributo.setString(1, descripcionpc); // -> Columna: Descripcion de atributos_hardware
            psAtributo.executeUpdate();
            try (java.sql.ResultSet rs = psAtributo.getGeneratedKeys()) {
                if (rs.next()) {
                    idAtributoHardware = rs.getInt(1);
                } else {
                    throw new java.sql.SQLException("Fallo al obtener la clave generada para atributos_hardware.");
                }
            }
        }
        
        // PASO 3: Insertar en la tabla principal hardware
        if (idTipoHardware > 0 && idAtributoHardware > 0) {
            try (java.sql.PreparedStatement psHardware = con.prepareStatement(sqlHardware)) {
                
                // Mapeo de parámetros:
                psHardware.setString(1, marcapc);            // <- Marca (txtmarcapc) va a Descripcion de hardware
                psHardware.setInt(2, idTipoHardware);        // -> Id_Tipo_hardware (FK)
                psHardware.setInt(3, idAtributoHardware);    // -> Id_atributo (FK)
                psHardware.setInt(4, idEstado);              // -> Id_Estado (FK)
                
                if (psHardware.executeUpdate() > 0) {
                    con.commit(); 
                    exito = true;
                } else {
                    con.rollback(); 
                }
            }
        } else {
             throw new java.sql.SQLException("Fallo al generar las claves de Tipo o Atributo.");
        }

    } catch (java.sql.SQLException e) {
        System.err.println("Error en transacción al guardar Hardware: " + e.getMessage());
        try {
            if (con != null) con.rollback(); 
        } catch (java.sql.SQLException ex) {
            System.err.println("Error al hacer rollback: " + ex.getMessage());
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Error al registrar el Hardware: " + e.getMessage(), "Error de DB", javax.swing.JOptionPane.ERROR_MESSAGE);
        
    } finally {
        try {
            if (con != null) con.setAutoCommit(true); 
            if (con != null) con.close();
        } catch (java.sql.SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    return exito;
}
    
    public ResultSet obtenerHardwareYDetallePC() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    // Consulta SQL usando JOINS para obtener:
    // 1. Detalles del hardware (Marca, Tipo, Atributos)
    // 2. Detalles de la PC asociada (Serie, Marca de la PC)
    String sql = "SELECT " +
                 // Hardware
                 "    h.Id_Hardware, " +
                 "    h.Descripcion AS MarcaHardware, " +           // Marca del componente guardada en hardware.Descripcion
                 "    th.Descripcion AS TipoHardware, " +          // Descripción del Tipo
                 "    ah.Descripcion AS AtributosDetalle, " +      // Descripción de Atributos
                 // PC Asociada
                 "    p.Serie AS SeriePC, " +                      // Serie de la PC de la tabla pcs
                 "    p.Marca AS MarcaPC, " +                      // Marca de la PC de la tabla pcs
                 "    e.Descripcion AS Estado " +                  // Estado del componente
                 "FROM " +
                 "    hardware h " +
                 "JOIN " +
                 "    tipo_de_hardware th ON h.Id_Tipo_hardware = th.Id_Tipo_hardware " + 
                 "JOIN " +
                 "    atributos_hardware ah ON h.Id_atributo = ah.Id_atributo " + 
                 "JOIN " +
                 "    estado e ON h.Id_Estado = e.Id_Estado " +
                 "JOIN " +
                 "    pcs p ON h.Id_Pc = p.Id_Pc " +              // ¡VINCULACIÓN CLAVE! Relaciona Hardware con la PC
                 "ORDER BY " +
                 "    h.Id_Hardware DESC";
    
    try {
        con = Clases.Conexion.getConexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        return rs;
        
    } catch (SQLException e) {
        System.err.println("Error al obtener el hardware y detalle de PC: " + e.getMessage());
        
        // Cierre de recursos en caso de error
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
        return null;
    }
}
    
    public List<Object[]> obtenerDatosHardwareComoLista() {
    
    // Lista donde guardaremos los datos para retornarlos
    List<Object[]> listaHardware = new ArrayList<>();
    
    // Consulta SQL usando JOINS para obtener todos los detalles del hardware y la PC asociada.
    String sql = "SELECT " +
                 "    h.Id_Hardware, " +
                 "    h.Descripcion AS MarcaHardware, " +       // Marca del componente
                 "    th.Descripcion AS TipoHardware, " +      // Descripción del Tipo
                 "    ah.Descripcion AS AtributosDetalle, " +  // Descripción de Atributos
                 "    p.Serie AS SeriePC, " +                  // Serie de la PC asociada
                 "    p.Marca AS MarcaPC, " +                  // Marca de la PC asociada
                 "    e.Descripcion AS Estados " +              // Estado del componente
                 "FROM " +
                 "    hardware h " +
                 "JOIN " +
                 "    tipo_de_hardware th ON h.Id_Tipo_hardware = th.Id_Tipo_hardware " + 
                 "JOIN " +
                 "    atributos_hardware ah ON h.Id_atributo = ah.Id_atributo " + 
                 "JOIN " +
                 "    estados e ON h.Id_Estado = e.Id_Estado " +
                 "JOIN " +
                 "    pcs p ON h.Id_Pc = p.Id_Pc " +           // Vincula Hardware con la PC
                 "ORDER BY " +
                 "    h.Id_Hardware DESC";
    
    // Usamos try-with-resources para asegurar que Connection, PS y RS se cierren automáticamente
    try (Connection con = Clases.Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            // Creamos un arreglo de 7 objetos (las 7 columnas seleccionadas)
            Object[] fila = new Object[7];
            fila[0] = rs.getInt("Id_Hardware");
            fila[1] = rs.getString("MarcaHardware"); 
            fila[2] = rs.getString("TipoHardware");
            fila[3] = rs.getString("AtributosDetalle");
            fila[4] = rs.getString("SeriePC");
            fila[5] = rs.getString("MarcaPC");
            fila[6] = rs.getString("Estado");
            
            listaHardware.add(fila);
        }
        
    } catch (SQLException e) {
        System.err.println("Error al obtener el hardware y detalle de PC como lista: " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(null, "Error al consultar DB: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return null; // Retorna null si hay error
    }
    
    return listaHardware; // Retorna la lista de datos
}
    
}