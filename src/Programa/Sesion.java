
package Programa;


import Clases.MetodoUsuarioRegistro;
import javax.swing.JOptionPane;
import Clases.EmailValidator;

public class Sesion extends javax.swing.JFrame {
    
    private MetodoUsuarioRegistro metodosRegistro;
    
    private java.io.FileInputStream fis;
    private String rutaArchivo;
    private int longitudBytes;
    private String nombreArchivo;
    
    
    
    public Sesion() {
        initComponents();
        
        inicializarFotoPorDefecto(); 
        metodosRegistro = new MetodoUsuarioRegistro();
    }

    private void limpiarCampos() {
        rstxtnomlogin.setText("");
        rstxtapelog.setText("");
        rstxtcorrelog.setText(""); // Faltaba este campo
        rstxtcontralog.setText("");
        rstxtconfilog.setText("");
    }
    private void inicializarFotoPorDefecto() {
    java.io.File archivoDefecto = null;
    
    try {
        // 💡 RUTA RELATIVA RECOMENDADA - Haz que la ruta apunte desde la raíz del proyecto o desde el JAR.
        // Asumo que tu carpeta de imágenes se llama 'Imagenes' y está en 'src'.
        archivoDefecto = new java.io.File("src/Imagenes/User.jpg");
        
        // Verifica si la ruta relativa falla y usa la absoluta como respaldo (o solo usa la relativa).
        if (!archivoDefecto.exists()) {
             // Si la ruta relativa falla, intenta la ruta absoluta (ADAPTAR ESTA RUTA A TU EQUIPO)
             archivoDefecto = new java.io.File("E:\\CICLO II\\PROGRAMACIÓN CONCURRENTE CON BBDD\\PROYECTO DEL INVENTARIO\\Prototipo05\\Prototipo\\src\\Imagenes\\User.jpg");
        }
        
        if (!archivoDefecto.exists()) {
            // Si no se encuentra en ninguna de las dos, lanzamos la excepción
            throw new java.io.FileNotFoundException("No se encontró el archivo de imagen en ninguna de las rutas.");
        }
        
        // --- Carga de archivo y datos (SI ES EXITOSA) ---
        
        // 🔑 NOTA CLAVE: La ruta debe ser asignada para que el método de guardar pueda reabrir el FileInputStream.
        rutaArchivo = archivoDefecto.getAbsolutePath(); 
        fis = new java.io.FileInputStream(archivoDefecto);
        longitudBytes = (int) archivoDefecto.length();
        nombreArchivo = archivoDefecto.getName();
        
        // Cargar imagen en el label (rslbfotolog)
        if (rslbfotolog != null) {
            int width = rslbfotolog.getWidth() > 0 ? rslbfotolog.getWidth() : 138;
            int height = rslbfotolog.getHeight() > 0 ? rslbfotolog.getHeight() : 138;
            
            java.awt.Image imagenEscalada = new javax.swing.ImageIcon(archivoDefecto.getAbsolutePath()).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            rslbfotolog.setIcon(new javax.swing.ImageIcon(imagenEscalada));
        }
        
    } catch (java.io.FileNotFoundException e) {
        System.err.println("Advertencia: No se encontró la imagen por defecto. " + e.getMessage());
        
        // 🛑 CRUCIAL: Si la foto por defecto falla, todas las variables de foto se restablecen a NULL/0.
        // Esto provocará que la validación en el botón "Guardar" detenga el registro.
        fis = null;
        longitudBytes = 0;
        nombreArchivo = null;
        rutaArchivo = null; // Evita NullPointerException en el botón Guardar
        
        if (rslbfotolog != null) {
            rslbfotolog.setIcon(null);
            rslbfotolog.setText("No hay foto (Error)");
        }
    } catch (Exception e) {
        System.err.println("Error general al inicializar foto por defecto: " + e.getMessage());
        // Se asegura que las variables queden en NULL/0 en caso de otro error
        fis = null;
        longitudBytes = 0;
        nombreArchivo = null;
        rutaArchivo = null;
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        RegistroSesion = new javax.swing.JPanel();
        Sesion = new javax.swing.JPanel();
        rsbtningresar = new rojeru_san.rsbutton.RSButtonRound();
        rstxtuser1 = new rojeru_san.rsfield.RSTextMaterial();
        jLabel2 = new javax.swing.JLabel();
        rsbtningresar1 = new rojeru_san.rsbutton.RSButtonRound();
        rstxtcontra = new RSMaterialComponent.RSPasswordMaterial();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rSPanelImage4 = new rojerusan.RSPanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Registrase = new javax.swing.JPanel();
        rstxtapelog = new rojeru_san.rsfield.RSTextMaterial();
        rstxtnomlogin = new rojeru_san.rsfield.RSTextMaterial();
        rstxtcorrelog = new rojeru_san.rsfield.RSTextMaterial();
        rstxtcontralog = new rojeru_san.rsfield.RSTextMaterial();
        rstxtconfilog = new rojeru_san.rsfield.RSTextMaterial();
        rsbtnguardarusuario = new RSMaterialComponent.RSButtonMaterialIconTwo();
        rslbfotolog = new rojerusan.RSLabelImage();
        rscargarfotolog = new rojerusan.RSButtonHover();
        informacion = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(90, 140, 207));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(13, 42, 92));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Horizontal Line_2.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 40, 40));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Close Window_3.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 40, 40));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minimize Window_1.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 40, 40));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 1200, 40));

        RegistroSesion.setBackground(new java.awt.Color(255, 153, 153));
        RegistroSesion.setLayout(new java.awt.CardLayout());

        Sesion.setBackground(new java.awt.Color(0, 26, 77));
        Sesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rsbtningresar.setBackground(new java.awt.Color(14, 60, 120));
        rsbtningresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Thumbs Up_2.png"))); // NOI18N
        rsbtningresar.setText("Registrar");
        rsbtningresar.setActionCommand("Registrate");
        rsbtningresar.setColorHover(new java.awt.Color(90, 140, 207));
        rsbtningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtningresarActionPerformed(evt);
            }
        });
        Sesion.add(rsbtningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 200, -1));

        rstxtuser1.setBackground(new java.awt.Color(0, 26, 77));
        rstxtuser1.setForeground(new java.awt.Color(255, 255, 255));
        rstxtuser1.setColorMaterial(new java.awt.Color(90, 140, 207));
        rstxtuser1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rstxtuser1.setPlaceholder("Ingresar Correo...");
        Sesion.add(rstxtuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 300, 40));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("O");
        Sesion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 20, -1));

        rsbtningresar1.setBackground(new java.awt.Color(14, 60, 120));
        rsbtningresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_2.png"))); // NOI18N
        rsbtningresar1.setText("Ingresar");
        rsbtningresar1.setColorHover(new java.awt.Color(90, 140, 207));
        rsbtningresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtningresar1ActionPerformed(evt);
            }
        });
        Sesion.add(rsbtningresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        rstxtcontra.setBackground(new java.awt.Color(0, 26, 77));
        rstxtcontra.setForeground(new java.awt.Color(255, 255, 255));
        rstxtcontra.setColorMaterial(new java.awt.Color(90, 140, 207));
        rstxtcontra.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rstxtcontra.setPhColor(new java.awt.Color(255, 255, 255));
        rstxtcontra.setPlaceholder("Ingresar Contraseña....");
        rstxtcontra.setSelectionColor(new java.awt.Color(109, 109, 109));
        Sesion.add(rstxtcontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 300, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¿Te has olvidado tu contaseña? Recuperala");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        Sesion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CONTRASEÑA");
        Sesion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CORREO");
        Sesion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        javax.swing.GroupLayout rSPanelImage4Layout = new javax.swing.GroupLayout(rSPanelImage4);
        rSPanelImage4.setLayout(rSPanelImage4Layout);
        rSPanelImage4Layout.setHorizontalGroup(
            rSPanelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        rSPanelImage4Layout.setVerticalGroup(
            rSPanelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        Sesion.add(rSPanelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 30, 40));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Key_3.png"))); // NOI18N
        Sesion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 70, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lock_3.png"))); // NOI18N
        Sesion.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 60, 50));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("INICIAR SESION");
        Sesion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/User_4.png"))); // NOI18N
        Sesion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 60, 40));

        RegistroSesion.add(Sesion, "card5");

        Registrase.setBackground(new java.awt.Color(14, 60, 120));
        Registrase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rstxtapelog.setBackground(new java.awt.Color(14, 60, 120));
        rstxtapelog.setForeground(new java.awt.Color(255, 255, 255));
        rstxtapelog.setPlaceholder("APELLIDO");
        Registrase.add(rstxtapelog, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        rstxtnomlogin.setBackground(new java.awt.Color(14, 60, 120));
        rstxtnomlogin.setForeground(new java.awt.Color(255, 255, 255));
        rstxtnomlogin.setPlaceholder("NOMBRE");
        Registrase.add(rstxtnomlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        rstxtcorrelog.setBackground(new java.awt.Color(14, 60, 120));
        rstxtcorrelog.setForeground(new java.awt.Color(255, 255, 255));
        rstxtcorrelog.setColorMaterial(new java.awt.Color(255, 255, 255));
        rstxtcorrelog.setPlaceholder("CORREO");
        Registrase.add(rstxtcorrelog, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        rstxtcontralog.setBackground(new java.awt.Color(14, 60, 120));
        rstxtcontralog.setForeground(new java.awt.Color(255, 255, 255));
        rstxtcontralog.setPlaceholder("CONTRASEÑA");
        Registrase.add(rstxtcontralog, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        rstxtconfilog.setBackground(new java.awt.Color(14, 60, 120));
        rstxtconfilog.setForeground(new java.awt.Color(255, 255, 255));
        rstxtconfilog.setPlaceholder("CONFIRMAR CONTRASEÑA");
        Registrase.add(rstxtconfilog, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));

        rsbtnguardarusuario.setBackground(new java.awt.Color(90, 140, 207));
        rsbtnguardarusuario.setText("GUARDAR");
        rsbtnguardarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnguardarusuarioActionPerformed(evt);
            }
        });
        Registrase.add(rsbtnguardarusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 180, -1));

        rslbfotolog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/User.jpg"))); // NOI18N
        Registrase.add(rslbfotolog, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 138, 138));

        rscargarfotolog.setBackground(new java.awt.Color(90, 140, 207));
        rscargarfotolog.setText("Insertar foto");
        rscargarfotolog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rscargarfotologActionPerformed(evt);
            }
        });
        Registrase.add(rscargarfotolog, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 138, -1));

        RegistroSesion.add(Registrase, "card3");

        jPanel3.add(RegistroSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, 32, 550, 570));

        informacion.setBackground(new java.awt.Color(10, 26, 60));
        informacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Copilot_20251120_115629-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        informacion.add(rSPanelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 490, 490));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_4950001949542975160.gif"))); // NOI18N
        jLabel9.setText("jLabel9");
        informacion.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 630, 560));

        jPanel3.add(informacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 29, 630, 570));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-11, 0, 1170, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rsbtnguardarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnguardarusuarioActionPerformed
        String dniCorreo = rstxtcorrelog.getText().trim(); 
    String nombres = rstxtnomlogin.getText().trim();
    String apellidos = rstxtapelog.getText().trim();
    String contrasena = rstxtcontralog.getText().trim();
    String confirmarContrasena = rstxtconfilog.getText().trim();
    
    final String RANGO_ID_POR_DEFECTO = "2"; 
    if (dniCorreo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (!contrasena.equals(confirmarContrasena)) {
        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Por favor, verifique.", "Error de Contraseña", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (!EmailValidator.validarFormato(dniCorreo)) {
        JOptionPane.showMessageDialog(this, "El campo 'Correo' debe ser un formato de correo electrónico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        return;
    }

        if (EmailValidator.existeCorreoEnBD(dniCorreo)) { 
        JOptionPane.showMessageDialog(this, "Este correo ya está registrado. Use otro.", "Correo Duplicado", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (rutaArchivo == null) {
        JOptionPane.showMessageDialog(this, "Error de Sistema: No se pudo cargar la imagen por defecto. No se puede continuar.", "Error de Configuración", JOptionPane.ERROR_MESSAGE);
        return; 
    }
    
    java.io.FileInputStream nuevoFis = null;
    boolean registroExitoso = false;
    
    try {
        nuevoFis = new java.io.FileInputStream(rutaArchivo);

        registroExitoso = metodosRegistro.registrarPersonalConFoto(
            dniCorreo,
            nombres,
            apellidos,
            RANGO_ID_POR_DEFECTO,
            contrasena,
            nuevoFis,
            longitudBytes,
            nombreArchivo
        );
        
        if (registroExitoso) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente. Ahora puede iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            RegistroSesion.removeAll();
            RegistroSesion.add(Sesion);
            RegistroSesion.repaint();
            RegistroSesion.revalidate();
        } else {
             JOptionPane.showMessageDialog(this, "Error al registrar el usuario. Verifique la base de datos.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (java.io.FileNotFoundException e) {
        System.err.println("Error de archivo: No se pudo acceder a la foto. " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Error: El archivo de imagen no fue encontrado en la ruta.", "Error", JOptionPane.ERROR_MESSAGE);
        
    } finally {
    }
    }//GEN-LAST:event_rsbtnguardarusuarioActionPerformed

    private void rscargarfotologActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rscargarfotologActionPerformed
        javax.swing.JFileChooser selector = new javax.swing.JFileChooser();
        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Imagen (JPG, PNG)", "jpg", "jpeg", "png");
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(this);

        if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = selector.getSelectedFile();

            try {
                // 1. Obtener la ruta del archivo (para visualización)
                rutaArchivo = archivo.getAbsolutePath();

                // 2. Preparar el flujo de bytes (se sobrescribe la foto por defecto)
                fis = new java.io.FileInputStream(archivo);

                // 3. Obtener el tamaño y nombre 
                longitudBytes = (int) archivo.length();
                nombreArchivo = archivo.getName();

                // 4. Mostrar la imagen en rslbfotolog
                javax.swing.ImageIcon icono = new javax.swing.ImageIcon(rutaArchivo);

                int width = rslbfotolog.getWidth() > 0 ? rslbfotolog.getWidth() : 138;
                int height = rslbfotolog.getHeight() > 0 ? rslbfotolog.getHeight() : 138;
                
                java.awt.Image imagenEscalada = icono.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
                rslbfotolog.setIcon(new javax.swing.ImageIcon(imagenEscalada));

            } catch (java.io.FileNotFoundException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: El archivo de imagen no fue encontrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                // Si hay error, volvemos a cargar la foto por defecto
                inicializarFotoPorDefecto(); 
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
                // Si hay otro error, volvemos a cargar la foto por defecto
                inicializarFotoPorDefecto(); 
            }
        } else {
            // Si el usuario cancela, mantenemos la imagen actual (ya sea la cargada o la por defecto).
        }
    }//GEN-LAST:event_rscargarfotologActionPerformed

    private void rsbtningresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtningresar1ActionPerformed
       
         String usuario = rstxtuser1.getText();
    
        String contrasena = new String(rstxtcontra.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar usuario y contraseña.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String rolUsuario = metodosRegistro.obtenerRolUsuario(usuario, contrasena);

        if (rolUsuario != null) {

            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + rolUsuario + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            Inicio principal = new Inicio(rolUsuario);
            principal.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
            rstxtcontra.setText("");
        }
    }//GEN-LAST:event_rsbtningresar1ActionPerformed

    private void rsbtningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtningresarActionPerformed
        RegistroSesion.removeAll();
        RegistroSesion.add(Registrase);
        RegistroSesion.repaint();
        RegistroSesion.revalidate();
    }//GEN-LAST:event_rsbtningresarActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
     new Programa.RecuperacionPaso1().setVisible(true); 
     this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    // Este método DEBE estar en la clase Sesion.java
// Este método actúa como un "envoltorio" o adaptador.
public boolean registrarUsuarioInicial(
    String dniCorreo, 
    String nombres, 
    String apellidos, 
    String contrasena, 
    java.io.FileInputStream fis, 
    int longitudBytes, 
    String nombreArchivo
) {
    // ✅ CORRECCIÓN: Llama al método real a través de la instancia 'metodosRegistro'.
    // Los parámetros de foto (fis, longitudBytes, nombreArchivo) son ignorados aquí
    // ya que solo llamas a la versión "SinFoto", pero la firma es compatible.
    
    return metodosRegistro.registrarNuevoUsuarioSinFoto(dniCorreo, nombres, apellidos, contrasena);
}
    private void limpiarFoto() {
    // 1. Resetear las variables de la foto
    fis = null;
    longitudBytes = 0;
    nombreArchivo = null;
    
    // 2. Limpiar el componente visual de la foto (lbfotouser)
    rslbfotolog.setIcon(null);
    rslbfotolog.setText(""); // O lo que uses por defecto
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Registrase;
    private javax.swing.JPanel RegistroSesion;
    private javax.swing.JPanel Sesion;
    private javax.swing.JPanel informacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojerusan.RSPanelImage rSPanelImage4;
    private RSMaterialComponent.RSButtonMaterialIconTwo rsbtnguardarusuario;
    private rojeru_san.rsbutton.RSButtonRound rsbtningresar;
    private rojeru_san.rsbutton.RSButtonRound rsbtningresar1;
    private rojerusan.RSButtonHover rscargarfotolog;
    private rojerusan.RSLabelImage rslbfotolog;
    private rojeru_san.rsfield.RSTextMaterial rstxtapelog;
    private rojeru_san.rsfield.RSTextMaterial rstxtconfilog;
    private RSMaterialComponent.RSPasswordMaterial rstxtcontra;
    private rojeru_san.rsfield.RSTextMaterial rstxtcontralog;
    private rojeru_san.rsfield.RSTextMaterial rstxtcorrelog;
    private rojeru_san.rsfield.RSTextMaterial rstxtnomlogin;
    private rojeru_san.rsfield.RSTextMaterial rstxtuser1;
    // End of variables declaration//GEN-END:variables
}
