package Programa;

import Clases.Metodos;
import Clases.MetodoUsuario;
import Clases.Conexion;
import Clases.MetodoPcRegistro;
import Clases.AlarmaTask;
import Clases.MetodoUsuarioRegistro;
import Clases.RangoComboItem;

import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.File; // Para manejar el archivo seleccionado
import java.io.FileInputStream; // Para el FileInputStream (fis)
import java.io.FileNotFoundException;

import java.awt.Image; // Para escalar la imagen
import javax.swing.ImageIcon; // Para mostrar la imagen en el JLabel
import javax.swing.JFileChooser; // Para abrir el diálogo de selección de archivos
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel; 
import javax.swing.table.TableRowSorter;

public class Inicio extends javax.swing.JFrame {
    
    private String rolActual;
    private Clases.MetodoHardwareRegistro metodosHardware;    
    private Clases.MetodoUsuarioRegistro metodosRegistro;
    private TableRowSorter trsFiltro;
    private FileInputStream fis;
    private String rutaArchivo;
    private int longitudBytes;
    private String nombreArchivo;
       
    private boolean inicializando = true;
    
    
    MetodoUsuarioRegistro cargarInfop = new MetodoUsuarioRegistro();
    Metodos metodos = new Metodos();
    MetodoUsuario metodousuario = new MetodoUsuario();
    MetodoPcRegistro metodosPc = new MetodoPcRegistro(); 
    
    AlarmaTask alarmaTask = new AlarmaTask();
    
    MetodoUsuarioRegistro metodosUsuarioRegistro = new MetodoUsuarioRegistro();
    MetodoPcRegistro metodopcregistro = new MetodoPcRegistro();
    

    
    public void inicializarBuscador() {
    trsFiltro = new TableRowSorter(rstabla.getModel());
    rstabla.setRowSorter(trsFiltro);
}
    
    public Inicio(String rol) {
    initComponents();
    this.rolActual = rol;
    configurarAccesoPorRol(); 
}
    
    public Inicio() {

    initComponents();
    cargarRangosEnComboBox();
    cmbRango.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbRangoActionPerformed(evt);
        }
    });
    actualizartablas();
    inicializando = true;
    metodosHardware = new Clases.MetodoHardwareRegistro();

    metodosHardware.cargarComboBoxLaboratoriosFiltro(rscblab);

    rscblab.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String filtroSeleccionado = rscblab.getSelectedItem().toString();
                metodosHardware.cargarPCsPorLaboratorio(rstbpc, filtroSeleccionado);
            }
        }
    });
    
    metodosHardware.cargarComboBoxEstados(rscbestado);

    metodosHardware.cargarComboBoxLaboratoriosFiltro(rscblaboratorio);

    metodosHardware.cargarPCsPorLaboratorio(rstbpc, "TODOS");

    cargarInfop.cargarInfoPersonal(tablaPersonal);
    metodousuario.cargarInfoPersonal(rstabla);

    metodopcregistro.cargarinfopc(rstbregistro);
    metodopcregistro.cargarInfoEquipos(tbhardware);
    
    alarmaTask.cargarpcalarmas(rstbalarma);

    DefaultTableModel modelo = (DefaultTableModel) rstabla.getModel();
    trsFiltro = new TableRowSorter<>(modelo);
    rstabla.setRowSorter(trsFiltro);

    alarmaTask.iniciar();

    int totalPCs = metodosPc.contarpcs();
    rslbpc.setText("Total de PCs: " + String.valueOf(totalPCs));

    int totalAlarmas = metodosPc.contaralarmas();
    rslbalarma.setText("Total de Alarmas: " + String.valueOf(totalAlarmas));

    inicializando = false;
}
    
    private void configurarAccesoPorRol() {
    
    String rol = this.rolActual.toLowerCase(); 
    
    javax.swing.JComponent[] componentesExclusivosAdmin = {
        rsbtnusuarios,
        rsbtneliminarpc
    };
    
    boolean esAdmin = rol.equals("administrador");
    
    for (javax.swing.JComponent componente : componentesExclusivosAdmin) {
        if (componente != null) {
            componente.setVisible(esAdmin);
        }
    }
}
    
    private void cargarTablaUsuarios() {
    if (metodosRegistro == null) {
        metodosRegistro = new Clases.MetodoUsuarioRegistro();
    }
    
    metodosRegistro.cargarInfoPersonal(tablaPersonal);
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mihardware = new javax.swing.JMenuItem();
        misoftware = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        Administrador = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        rsbtnusuarios = new RSMaterialComponent.RSButtonMaterialIconDos();
        rsbtnperfil = new RSMaterialComponent.RSButtonMaterialIconDos();
        rsbtnalarmas = new RSMaterialComponent.RSButtonMaterialIconDos();
        rsbtnregistro = new RSMaterialComponent.RSButtonMaterialIconDos();
        button = new RSMaterialComponent.RSButtonMaterialIconDos();
        rsbtnprincipal = new RSMaterialComponent.RSButtonMaterialIconDos();
        rsbtnsalir = new RSMaterialComponent.RSButtonMaterialIconDos();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Principal = new javax.swing.JPanel();
        hardware = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        rstbpc = new RSMaterialComponent.RSTableMetroCustom();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbhardware = new RSMaterialComponent.RSTableMetroCustom();
        rscblab = new rojerusan.RSComboMetro();
        txtIdEquipo = new rojeru_san.rsfield.RSTextMaterial();
        txtmarcapc = new rojeru_san.rsfield.RSTextMaterial();
        txttipopc = new rojeru_san.rsfield.RSTextMaterial();
        txtdescripcionpc = new rojeru_san.rsfield.RSTextMaterial();
        cbestadopc = new rojerusan.RSComboMetro();
        rsbuscar3 = new rojeru_san.rsfield.RSTextMaterial();
        rsbtnprincipal1 = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnguardarpc = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnactualizar = new RSMaterialComponent.RSButtonMaterialIconDos();
        jDateChooserFecha = new rojeru_san.rsdate.RSDateChooser();
        usuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonal = new RSMaterialComponent.RSTableMetroCustom();
        txtDniCorreo = new rojeru_san.rsfield.RSTextMaterial();
        txtNombres = new rojeru_san.rsfield.RSTextMaterial();
        txtApellidos = new rojeru_san.rsfield.RSTextMaterial();
        txtContrasena = new rojeru_san.rsfield.RSTextMaterial();
        rstxtccontraseña = new rojeru_san.rsfield.RSTextMaterial();
        cmbRango = new RSMaterialComponent.RSComboBoxMaterial();
        rsbtnactualizar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        lbfotouser = new rojerusan.RSLabelImage();
        rscargarfoto1 = new rojerusan.RSButtonHover();
        rsbtneliminaruser = new RSMaterialComponent.RSButtonMaterialIconTwo();
        rstxtid = new rojeru_san.rsfield.RSTextMaterial();
        rsbtnguardarusuario = new RSMaterialComponent.RSButtonMaterialIconTwo();
        registrospc = new javax.swing.JPanel();
        rstxtserie = new rojerusan.RSMetroTextFullPlaceHolder();
        rstxtmarca = new rojerusan.RSMetroTextFullPlaceHolder();
        rscblaboratorio = new rojerusan.RSComboMetro();
        rsbtnguardarpc = new rojerusan.RSMaterialButtonRound();
        rscbestado = new rojerusan.RSComboMetro();
        rsdtregistro = new rojerusan.RSDateChooser();
        rsbtneliminarpc = new rojerusan.RSMaterialButtonRound();
        jScrollPane8 = new javax.swing.JScrollPane();
        rstbregistro = new RSMaterialComponent.RSTableMetroCustom();
        principal = new javax.swing.JPanel();
        rsbuscar = new rojeru_san.rsfield.RSTextMaterial();
        rslbpc = new rojeru_san.rslabel.RSLabelBorderRound();
        rSLabelBorderRound2 = new rojeru_san.rslabel.RSLabelBorderRound();
        rslbalarma = new rojeru_san.rslabel.RSLabelBorderRound();
        rSButtonRound1 = new rojeru_san.rsbutton.RSButtonRound();
        jScrollPane6 = new javax.swing.JScrollPane();
        rstabla = new RSMaterialComponent.RSTableMetroCustom();
        alarmas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextPane();
        rscbperiodo = new rojerusan.RSComboMetro();
        rsbtnguardar = new RSMaterialComponent.RSButtonMaterialRipple();
        rstxthora = new rojerusan.RSMetroTextFullPlaceHolder();
        rstxtminuto = new rojerusan.RSMetroTextFullPlaceHolder();
        rsbtneliminar = new RSMaterialComponent.RSButtonMaterialRipple();
        jScrollPane3 = new javax.swing.JScrollPane();
        rstbalarma = new RSMaterialComponent.RSTableMetroCustom();
        perfil = new javax.swing.JPanel();
        rsguardar = new rojerusan.RSButtonHover();
        rscargarfoto = new rojerusan.RSButtonHover();
        jPanel4 = new javax.swing.JPanel();
        rsfoto = new rojeru_san.rslabel.RSLabelImage();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbfotos = new RSMaterialComponent.RSTableMetroCustom();
        rstxtusername = new rojeru_san.rsfield.RSTextMaterial();
        rstxtusername1 = new rojeru_san.rsfield.RSTextMaterial();
        rstxtusername2 = new rojeru_san.rsfield.RSTextMaterial();
        rstxtusername4 = new rojeru_san.rsfield.RSTextMaterial();
        rstxtusername5 = new rojeru_san.rsfield.RSTextMaterial();
        rstxtusername6 = new rojeru_san.rsfield.RSTextMaterial();
        jPanel3 = new javax.swing.JPanel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jPanel5 = new javax.swing.JPanel();

        jPopupMenu1.setMinimumSize(new java.awt.Dimension(200, 6));

        mihardware.setText("HARDWARE");
        mihardware.setPreferredSize(new java.awt.Dimension(197, 22));
        mihardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mihardwareActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mihardware);

        misoftware.setText("SOFTWARE");
        misoftware.setPreferredSize(new java.awt.Dimension(197, 22));
        misoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misoftwareActionPerformed(evt);
            }
        });
        jPopupMenu1.add(misoftware);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(90, 140, 207));
        jPanel2.setPreferredSize(new java.awt.Dimension(1260, 700));

        Administrador.setBackground(new java.awt.Color(0, 26, 77));
        Administrador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Profile_1.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        Administrador.add(rSPanelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        rsbtnusuarios.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnusuarios.setText("USUARIOS");
        rsbtnusuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnusuarios.setIconTextGap(1);
        rsbtnusuarios.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        rsbtnusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnusuariosActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnusuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 229, -1));

        rsbtnperfil.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnperfil.setText("PERFIL");
        rsbtnperfil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnperfil.setIconTextGap(1);
        rsbtnperfil.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON);
        rsbtnperfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnperfilActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnperfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 229, -1));

        rsbtnalarmas.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnalarmas.setText("ALARMAS");
        rsbtnalarmas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnalarmas.setIconTextGap(1);
        rsbtnalarmas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCESS_ALARM);
        rsbtnalarmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnalarmasActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnalarmas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 229, -1));

        rsbtnregistro.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnregistro.setText("REGISTRO PC");
        rsbtnregistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnregistro.setIconTextGap(1);
        rsbtnregistro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIBRARY_ADD);
        rsbtnregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnregistroActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 359, 229, -1));

        button.setBackground(new java.awt.Color(2, 58, 111));
        button.setText("MANTENIMIENTO");
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setIconTextGap(1);
        button.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SETTINGS);
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        Administrador.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 303, 229, -1));

        rsbtnprincipal.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnprincipal.setText("PRINCIPAL");
        rsbtnprincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnprincipal.setIconTextGap(1);
        rsbtnprincipal.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);
        rsbtnprincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnprincipalActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 247, 229, -1));

        rsbtnsalir.setBackground(new java.awt.Color(2, 58, 111));
        rsbtnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnsalir.setIconTextGap(1);
        rsbtnsalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.YOUTUBE_SEARCHED_FOR);
        rsbtnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnsalirActionPerformed(evt);
            }
        });
        Administrador.add(rsbtnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 644, 49, -1));
        Administrador.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 230, 10));
        Administrador.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 230, 10));

        Principal.setBackground(new java.awt.Color(255, 204, 204));
        Principal.setLayout(new java.awt.CardLayout());

        hardware.setBackground(new java.awt.Color(0, 26, 77));

        rstbalarma = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rstbpc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1"},
                {"2"},
                {"3"},
                {"4"},
                {"5"},
                {"6"},
                {"7"},
                {"8"},
                {"9"}
            },
            new String [] {
                "ID"
            }
        ));
        rstbpc.setBackgoundHead(new java.awt.Color(31, 95, 168));
        rstbpc.setBackgoundHover(new java.awt.Color(31, 95, 168));
        rstbpc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rstbpcMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(rstbpc);

        rstbalarma = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbhardware.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_PC", "MARCA", "TIPO", "DESCRIPCION", "ID_ESTADO", "FECHA"
            }
        ));
        tbhardware.setBackgoundHead(new java.awt.Color(31, 95, 168));
        tbhardware.setBackgoundHover(new java.awt.Color(31, 95, 168));
        jScrollPane7.setViewportView(tbhardware);

        rscblab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        rscblab.setToolTipText("");
        rscblab.setColorArrow(new java.awt.Color(31, 95, 168));
        rscblab.setColorBorde(new java.awt.Color(31, 95, 168));
        rscblab.setColorFondo(new java.awt.Color(31, 95, 168));
        rscblab.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rscblab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rscblabActionPerformed(evt);
            }
        });

        txtIdEquipo.setEditable(false);
        txtIdEquipo.setPlaceholder("NOMBRE...");

        txtmarcapc.setPlaceholder("MARCA...");

        txttipopc.setPlaceholder("TIPO...");

        txtdescripcionpc.setPlaceholder("DESCRIPCION...");

        cbestadopc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Operativo", "En Reparación", "Fuera de Servicio" }));
        cbestadopc.setToolTipText("");
        cbestadopc.setColorBorde(new java.awt.Color(31, 95, 168));
        cbestadopc.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        rsbuscar3.setPlaceholder("BUSCAR...");

        rsbtnprincipal1.setText("ELIMINAR");
        rsbtnprincipal1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rsbtnprincipal1.setIconTextGap(1);
        rsbtnprincipal1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);

        btnguardarpc.setText("GUARDAR");
        btnguardarpc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardarpc.setIconTextGap(1);
        btnguardarpc.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);
        btnguardarpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarpcActionPerformed(evt);
            }
        });

        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnactualizar.setIconTextGap(1);
        btnactualizar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hardwareLayout = new javax.swing.GroupLayout(hardware);
        hardware.setLayout(hardwareLayout);
        hardwareLayout.setHorizontalGroup(
            hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hardwareLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rsbuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(hardwareLayout.createSequentialGroup()
                        .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rscblab, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hardwareLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(hardwareLayout.createSequentialGroup()
                                        .addComponent(txtIdEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtmarcapc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttipopc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtdescripcionpc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(hardwareLayout.createSequentialGroup()
                                        .addComponent(cbestadopc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnguardarpc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rsbtnprincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(hardwareLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addContainerGap())
        );
        hardwareLayout.setVerticalGroup(
            hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hardwareLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rscblab, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmarcapc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttipopc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdescripcionpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hardwareLayout.createSequentialGroup()
                        .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbestadopc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardarpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rsbtnprincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        Principal.add(hardware, "card8");

        usuarios.setBackground(new java.awt.Color(0, 26, 77));

        tablaPersonal = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "USUARIO", "NOMBRE", "APELLIDO", "CONTRASEÑA", "RANGO"
            }
        ));
        tablaPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPersonalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPersonal);

        txtDniCorreo.setPlaceholder("CORREO");
        txtDniCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniCorreoKeyReleased(evt);
            }
        });

        txtNombres.setPlaceholder("NOMBRE");
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });

        txtApellidos.setPlaceholder("APELLIDO");
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosKeyReleased(evt);
            }
        });

        txtContrasena.setPlaceholder("CONTRASEÑA");
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyReleased(evt);
            }
        });

        rstxtccontraseña.setPlaceholder("CONFIRMAR CONTRASEÑA");
        rstxtccontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtccontraseñaKeyReleased(evt);
            }
        });

        cmbRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRangoActionPerformed(evt);
            }
        });

        rsbtnactualizar.setText("ACTUALIZAR");
        rsbtnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnactualizarActionPerformed(evt);
            }
        });

        rSPanelsSlider1.add(lbfotouser, "card2");

        rscargarfoto1.setText("Insertar foto");
        rscargarfoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rscargarfoto1ActionPerformed(evt);
            }
        });

        rsbtneliminaruser.setText("ELIMINAR");
        rsbtneliminaruser.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE_CIRCLE);
        rsbtneliminaruser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtneliminaruserActionPerformed(evt);
            }
        });

        rstxtid.setEditable(false);
        rstxtid.setPlaceholder("ID");
        rstxtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtidKeyReleased(evt);
            }
        });

        rsbtnguardarusuario.setText("GUARDAR");
        rsbtnguardarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnguardarusuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usuariosLayout = new javax.swing.GroupLayout(usuarios);
        usuarios.setLayout(usuariosLayout);
        usuariosLayout.setHorizontalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtDniCorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rstxtid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbRango, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rstxtccontraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rsbtnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rscargarfoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(rsbtnguardarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addComponent(rsbtneliminaruser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        usuariosLayout.setVerticalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addComponent(rstxtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDniCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rsbtnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addComponent(cmbRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rstxtccontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(rsbtnguardarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(rscargarfoto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsbtneliminaruser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        Principal.add(usuarios, "card9");

        registrospc.setBackground(new java.awt.Color(0, 26, 77));

        rstxtserie.setPlaceholder("SERIE");

        rstxtmarca.setPlaceholder("MARCA");

        rscblaboratorio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A-101", "B-205", "C-302" }));
        rscblaboratorio.setToolTipText("");
        rscblaboratorio.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        rsbtnguardarpc.setBackground(new java.awt.Color(31, 95, 168));
        rsbtnguardarpc.setText("Guardar");
        rsbtnguardarpc.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        rsbtnguardarpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnguardarpcActionPerformed(evt);
            }
        });

        rscbestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Operativo", "En Reparación", "Fuera de Servicio" }));
        rscbestado.setToolTipText("");
        rscbestado.setColorBorde(new java.awt.Color(31, 95, 168));
        rscbestado.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        rsbtneliminarpc.setBackground(new java.awt.Color(31, 95, 168));
        rsbtneliminarpc.setText("Eliminar");
        rsbtneliminarpc.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        rsbtneliminarpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtneliminarpcActionPerformed(evt);
            }
        });

        rstbregistro = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rstbregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SERIE", "MARCA", "LABORATORIO", "ESTADO", "REGISTRO", "ALARMA", "INCIDENCIAS"
            }
        ));
        rstbregistro.setBackgoundHead(new java.awt.Color(31, 95, 168));
        rstbregistro.setBackgoundHover(new java.awt.Color(31, 95, 168));
        jScrollPane8.setViewportView(rstbregistro);

        javax.swing.GroupLayout registrospcLayout = new javax.swing.GroupLayout(registrospc);
        registrospc.setLayout(registrospcLayout);
        registrospcLayout.setHorizontalGroup(
            registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrospcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrospcLayout.createSequentialGroup()
                        .addGap(0, 235, Short.MAX_VALUE)
                        .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(registrospcLayout.createSequentialGroup()
                                .addComponent(rstxtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rstxtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rscblaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registrospcLayout.createSequentialGroup()
                                .addComponent(rscbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rsdtregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rsbtneliminarpc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rsbtnguardarpc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(105, 105, 105))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        registrospcLayout.setVerticalGroup(
            registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrospcLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rstxtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rscblaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rstxtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrospcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rscbestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsdtregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rsbtnguardarpc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(rsbtneliminarpc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );

        Principal.add(registrospc, "card3");

        principal.setBackground(new java.awt.Color(0, 26, 77));

        rsbuscar.setBackground(new java.awt.Color(0, 26, 77));
        rsbuscar.setForeground(new java.awt.Color(255, 255, 255));
        rsbuscar.setColorMaterial(new java.awt.Color(31, 95, 168));
        rsbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rsbuscarKeyReleased(evt);
            }
        });

        rslbpc.setForeground(new java.awt.Color(255, 255, 255));
        rslbpc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rslbpc.setText("Total de PCs:");
        rslbpc.setBgBorde(new java.awt.Color(255, 255, 255));
        rslbpc.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        rSLabelBorderRound2.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelBorderRound2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelBorderRound2.setText("Inventario Laboratorio");
        rSLabelBorderRound2.setBgBorde(new java.awt.Color(255, 255, 255));
        rSLabelBorderRound2.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N

        rslbalarma.setForeground(new java.awt.Color(255, 255, 255));
        rslbalarma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rslbalarma.setText("Total de Alarmas:");
        rslbalarma.setBgBorde(new java.awt.Color(255, 255, 255));
        rslbalarma.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        rSButtonRound1.setBackground(new java.awt.Color(31, 95, 168));
        rSButtonRound1.setText("Nuevo Registro");
        rSButtonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound1ActionPerformed(evt);
            }
        });

        rstabla = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rstabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "USUARIO", "NOMBRE", "APELLIDO", "CONTRASEÑA"
            }
        ));
        rstabla.setBackgoundHead(new java.awt.Color(31, 95, 168));
        jScrollPane6.setViewportView(rstabla);
        if (rstabla.getColumnModel().getColumnCount() > 0) {
            rstabla.getColumnModel().getColumn(1).setHeaderValue("USUARIO");
        }

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalLayout.createSequentialGroup()
                        .addComponent(rSLabelBorderRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rslbpc, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rslbalarma, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 264, Short.MAX_VALUE))
                    .addGroup(principalLayout.createSequentialGroup()
                        .addComponent(rsbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSButtonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rslbalarma, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rslbpc, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSLabelBorderRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rsbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(124, 124, 124))
        );

        Principal.add(principal, "card5");

        alarmas.setBackground(new java.awt.Color(0, 26, 77));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":");

        jScrollPane2.setViewportView(txtdescripcion);

        rscbperiodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        rscbperiodo.setToolTipText("");
        rscbperiodo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        rsbtnguardar.setBackground(new java.awt.Color(31, 95, 168));
        rsbtnguardar.setText("Guardar");
        rsbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnguardarActionPerformed(evt);
            }
        });

        rstxthora.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rstxthora.setPlaceholder("HORA");

        rstxtminuto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rstxtminuto.setPlaceholder("MINUTO");

        rsbtneliminar.setBackground(new java.awt.Color(31, 95, 168));
        rsbtneliminar.setText("Eliminar Alarma");
        rsbtneliminar.setBackgroundHover(new java.awt.Color(31, 95, 168));
        rsbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtneliminarActionPerformed(evt);
            }
        });

        rstbalarma = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rstbalarma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "USUARIO", "Title 3"
            }
        ));
        rstbalarma.setBackgoundHead(new java.awt.Color(31, 95, 168));
        rstbalarma.setBackgoundHover(new java.awt.Color(31, 95, 168));
        jScrollPane3.setViewportView(rstbalarma);
        if (rstbalarma.getColumnModel().getColumnCount() > 0) {
            rstbalarma.getColumnModel().getColumn(1).setHeaderValue("USUARIO");
            rstbalarma.getColumnModel().getColumn(2).setHeaderValue("Title 3");
        }

        javax.swing.GroupLayout alarmasLayout = new javax.swing.GroupLayout(alarmas);
        alarmas.setLayout(alarmasLayout);
        alarmasLayout.setHorizontalGroup(
            alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alarmasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(alarmasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(rstxthora, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rstxtminuto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rscbperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rsbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rsbtneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 451, Short.MAX_VALUE))
            .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(alarmasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        alarmasLayout.setVerticalGroup(
            alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alarmasLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rstxthora, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rscbperiodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rstxtminuto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rsbtneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(653, 653, 653))
            .addGroup(alarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(alarmasLayout.createSequentialGroup()
                    .addGap(326, 326, 326)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
        );

        Principal.add(alarmas, "card4");

        perfil.setBackground(new java.awt.Color(0, 26, 77));

        rsguardar.setText("Guardar");
        rsguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsguardarActionPerformed(evt);
            }
        });

        rscargarfoto.setText("Insertar foto");
        rscargarfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rscargarfotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbfotos = new RSMaterialComponent.RSTableMetroCustom(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbfotos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Correo", "Nombre", "Apellido", "Contraseña", "Foto"
            }
        ));
        jScrollPane5.setViewportView(tbfotos);

        rstxtusername.setPlaceholder("Nombre...");
        rstxtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusernameKeyReleased(evt);
            }
        });

        rstxtusername1.setPlaceholder("Apellido...");
        rstxtusername1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusername1KeyReleased(evt);
            }
        });

        rstxtusername2.setPlaceholder("Correo...");
        rstxtusername2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusername2KeyReleased(evt);
            }
        });

        rstxtusername4.setPlaceholder("Confirmar contraseña...");
        rstxtusername4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusername4KeyReleased(evt);
            }
        });

        rstxtusername5.setPlaceholder("Contraseña anterior...");
        rstxtusername5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusername5KeyReleased(evt);
            }
        });

        rstxtusername6.setPlaceholder("Nueva contraseña...");
        rstxtusername6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rstxtusername6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout perfilLayout = new javax.swing.GroupLayout(perfil);
        perfil.setLayout(perfilLayout);
        perfilLayout.setHorizontalGroup(
            perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(perfilLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(perfilLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(perfilLayout.createSequentialGroup()
                                .addComponent(rstxtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rstxtusername6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(perfilLayout.createSequentialGroup()
                                .addComponent(rstxtusername1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rstxtusername4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(perfilLayout.createSequentialGroup()
                                .addComponent(rstxtusername2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rstxtusername5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, perfilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rscargarfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(perfilLayout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(rsguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        perfilLayout.setVerticalGroup(
            perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, perfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rscargarfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rstxtusername2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rstxtusername5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rstxtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rstxtusername6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rstxtusername1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rstxtusername4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(rsguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
        );

        Principal.add(perfil, "card2");

        jPanel3.setBackground(new java.awt.Color(31, 95, 168));

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(31, 95, 168));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Administrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Administrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rsguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsguardarActionPerformed
        if (rutaArchivo == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe cargar una foto con el botón 'Buscar' primero.");
        return;
    }
    String sql = "INSERT INTO imagenes (Nombre_Archivo, Imagen) VALUES (?, ?)";
    java.sql.Connection conn = null;
    java.sql.PreparedStatement ps = null;
    java.io.FileInputStream fis = null;

    try {
        fis = new java.io.FileInputStream(rutaArchivo); 
        
        conn = new Clases.Conexion().getConexion();
        ps = conn.prepareStatement(sql); 
        
        ps.setString(1, nombreArchivo);
        ps.setBinaryStream(2, fis, longitudBytes); 

        int resultado = ps.executeUpdate();

        if (resultado > 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Foto y nombre de archivo guardados con éxito.");
            metodos.cargarFotos(tbfotos); 
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo guardar la foto.");
        }

    } catch (java.io.FileNotFoundException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error: El archivo no se encuentra en la ruta original. " + e.getMessage());
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error de base de datos (SQL): " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error general al guardar la foto: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (ps != null) try { ps.close(); } catch (java.sql.SQLException e) {}
        if (conn != null) try { conn.close(); } catch (java.sql.SQLException e) {}
        if (fis != null) try { fis.close(); } catch (java.io.IOException e) {}
    }
    }//GEN-LAST:event_rsguardarActionPerformed

    private void rscargarfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rscargarfotoActionPerformed
        javax.swing.JFileChooser selector = new javax.swing.JFileChooser();
    javax.swing.filechooser.FileNameExtensionFilter filtro = 
        new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Imagen (JPG, PNG)", "jpg", "jpeg", "png");
    selector.setFileFilter(filtro);
    
    selector.showOpenDialog(this);
    java.io.File archivo = selector.getSelectedFile();

    if (archivo != null) {
        try {
            rutaArchivo = archivo.getAbsolutePath();
            longitudBytes = (int) archivo.length();
            nombreArchivo = archivo.getName(); 
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(rutaArchivo);
            java.awt.Image imagenEscalada = icono.getImage().getScaledInstance(197, 197, java.awt.Image.SCALE_SMOOTH);
            rsfoto.setIcon(new javax.swing.ImageIcon(imagenEscalada));
            
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
            rutaArchivo = null; 
            nombreArchivo = null;
        }
    }
    }//GEN-LAST:event_rscargarfotoActionPerformed

    private void rsbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rsbuscarKeyReleased
        String texto = rsbuscar.getText().trim();
    
    if (texto.length() == 0) {
        trsFiltro.setRowFilter(null);
    } else {
        try {
            trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2, 3, 4));
        } catch (java.util.regex.PatternSyntaxException e) {
            trsFiltro.setRowFilter(null);
        }
    }
    }//GEN-LAST:event_rsbuscarKeyReleased

    private void rsbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnguardarActionPerformed
        String horaStr = rstxthora.getText().trim();
    String minutoStr = rstxtminuto.getText().trim();
    String periodoStr = (String) rscbperiodo.getSelectedItem();
    int filaSeleccionada = rstbalarma.getSelectedRow();
    
    if (filaSeleccionada < 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una PC en la tabla antes de guardar la alarma.");
        return;
    }
    
    Object idPCSeleccionado = rstbalarma.getValueAt(filaSeleccionada, 0);
    String idPC = idPCSeleccionado.toString(); 

    if (horaStr.isEmpty() || minutoStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar la Hora y el Minuto.");
        return;
    }

    try (Connection con = Conexion.getConexion()) {
        int hora12h = Integer.parseInt(horaStr);
        int minuto = Integer.parseInt(minutoStr);
        
        int hora24h = hora12h;
        if (periodoStr.equalsIgnoreCase("PM") && hora12h < 12) {
            hora24h += 12;
        } else if (periodoStr.equalsIgnoreCase("AM") && hora12h == 12) {
            hora24h = 0;
        }
        String tiempoFormateado = String.format("%02d:%02d:00", hora24h, minuto);
        
        String sql = "INSERT INTO alarma (Tiempo, Descripcion, Id_Pc) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, tiempoFormateado);
        ps.setString(2, txtdescripcion.getText());
        ps.setString(3, idPC);

        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(this, "Alarma guardada exitosamente para la PC con ID: " + idPC + ".");
        metodos.cargarAlarmas(rstbalarma);
        
        int totalPCs = metodopcregistro.contarpcs();
        rslbpc.setText("Total de PCs: " + String.valueOf(totalPCs));
        
        int totalAlarmas = metodopcregistro.contaralarmas();
        rslbalarma.setText("Total de Alarmas: " + String.valueOf(totalAlarmas));
        
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error: La hora y el minuto deben ser números válidos. " + ex.getMessage());
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error de SQL/Conexión: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error de configuración: " + ex.getMessage());
    }
    actualizartablas();
    }//GEN-LAST:event_rsbtnguardarActionPerformed

    private void rsbtneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtneliminarActionPerformed
        int filaSeleccionada = rstbalarma.getSelectedRow();
    
    if (filaSeleccionada < 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una PC de la tabla para eliminar su alarma.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(
        this, 
        "¿Está seguro de que desea eliminar la alarma de la PC seleccionada?", 
        "Confirmar Eliminación", 
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        
        Object idPCSeleccionado = rstbalarma.getValueAt(filaSeleccionada, 0);
        String idPC = idPCSeleccionado.toString(); 

        try (Connection con = Conexion.getConexion()) {
            
            String sql = "DELETE FROM alarma WHERE Id_Pc = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idPC); 

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Alarma eliminada exitosamente para la PC con ID: " + idPC + ".");
                int totalPCs = metodopcregistro.contarpcs();
                rslbpc.setText("Total de PCs: " + String.valueOf(totalPCs));
        
                int totalAlarmas = metodopcregistro.contaralarmas();
                rslbalarma.setText("Total de Alarmas: " + String.valueOf(totalAlarmas));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna alarma para la PC seleccionada.");
            }
            
            metodos.cargarAlarmas(rstbalarma); 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de SQL/Conexión al eliminar la alarma: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    actualizartablas();
    }//GEN-LAST:event_rsbtneliminarActionPerformed

    private void rsbtnregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnregistroActionPerformed
        Principal.removeAll();
        Principal.add(registrospc);
        Principal.repaint();
        Principal.revalidate();
    }//GEN-LAST:event_rsbtnregistroActionPerformed

    private void rsbtnprincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnprincipalActionPerformed
        Principal.removeAll();
        Principal.add(principal);
        Principal.repaint();
        Principal.revalidate();        
    }//GEN-LAST:event_rsbtnprincipalActionPerformed

    private void rsbtnalarmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnalarmasActionPerformed
        Principal.removeAll();
        Principal.add(alarmas);
        Principal.repaint();
        Principal.revalidate();
    }//GEN-LAST:event_rsbtnalarmasActionPerformed

    private void rsbtnperfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnperfilActionPerformed
        Principal.removeAll();
        Principal.add(perfil);
        Principal.repaint();
        Principal.revalidate();
    }//GEN-LAST:event_rsbtnperfilActionPerformed

    private void rsbtnguardarpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnguardarpcActionPerformed
        String serie = rstxtserie.getText().trim();
    String marca = rstxtmarca.getText().trim();

    String nombreLaboratorio = rscblaboratorio.getSelectedItem().toString();
    String descripcionEstado = rscbestado.getSelectedItem().toString();

    java.util.Date fechaRegistro = new java.util.Date();

    int idLaboratorio = metodosPc.obtenerIdLaboratorioPorNombre(nombreLaboratorio);
    int idEstado = metodosPc.obtenerIdEstadoPorDescripcion(descripcionEstado);

    if (serie.isEmpty() || marca.isEmpty() || idLaboratorio <= 0 || idEstado <= 0) {
        JOptionPane.showMessageDialog(this, "Debe llenar todos los campos (Serie, Marca, Laboratorio y Estado). Verifique si el Laboratorio o Estado existen en la BD.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String descripcion = marca + " - " + serie;
    if (metodosPc.registrarPc(descripcion, serie, marca, idLaboratorio, idEstado, fechaRegistro)) {
        metodosPc.cargarinfopc(rstbregistro);
        int totalPCs = metodosPc.contarpcs();
        rslbpc.setText("Total de PCs: " + String.valueOf(totalPCs));

        int totalAlarmas = metodosPc.contaralarmas();
        rslbalarma.setText("Total de Alarmas: " + String.valueOf(totalAlarmas));
        rstxtserie.setText("");
        rstxtmarca.setText("");

        JOptionPane.showMessageDialog(this, "PC registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Error al registrar la PC. Revise la conexión a la base de datos o si la Serie ya existe.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
    }
    actualizartablas();
    }//GEN-LAST:event_rsbtnguardarpcActionPerformed

    private void rsbtneliminarpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtneliminarpcActionPerformed
        int filaSeleccionada = rstbregistro.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar la PC a eliminar de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int idPc = (int) rstbregistro.getValueAt(filaSeleccionada, 0);
    
    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la PC con ID: " + idPc + "?\nEsta acción también eliminará sus registros de Hardware, Software, etc.", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        if (metodosPc.eliminarPc(idPc)) {
            
            metodosPc.cargarinfopc(rstbregistro); 
            
            rslbpc.setText("Total de PCs: " + String.valueOf(metodosPc.contarpcs()));
            rslbalarma.setText("Total de Alarmas: " + String.valueOf(metodosPc.contaralarmas()));
            
            JOptionPane.showMessageDialog(this, "PC eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la PC. Verifique la consola para detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    actualizartablas();
    }//GEN-LAST:event_rsbtneliminarpcActionPerformed

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed
        Principal.removeAll();
        Principal.add(registrospc);
        Principal.repaint();
        Principal.revalidate();
    }//GEN-LAST:event_rSButtonRound1ActionPerformed

    private void rsbtnusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnusuariosActionPerformed
        Principal.removeAll();
        Principal.add(usuarios);
        Principal.repaint();
        Principal.revalidate();        
    }//GEN-LAST:event_rsbtnusuariosActionPerformed

    private void txtDniCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniCorreoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniCorreoKeyReleased

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosKeyReleased

    private void txtContrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaKeyReleased

    private void rstxtccontraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtccontraseñaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtccontraseñaKeyReleased

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        jPopupMenu1.show(button, 0, button.getHeight());        
    }//GEN-LAST:event_buttonActionPerformed

    private void mihardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mihardwareActionPerformed
        Principal.removeAll();
        Principal.add(hardware);
        Principal.repaint();
        Principal.revalidate(); 
    }//GEN-LAST:event_mihardwareActionPerformed

    private void misoftwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misoftwareActionPerformed
        /*Principal.removeAll();
        Principal.add(software);
        Principal.repaint();
        Principal.revalidate();*/
    }//GEN-LAST:event_misoftwareActionPerformed

    private void rstxtusernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusernameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusernameKeyReleased

    private void rstxtusername1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusername1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusername1KeyReleased

    private void rstxtusername2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusername2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusername2KeyReleased

    private void rstxtusername4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusername4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusername4KeyReleased

    private void rstxtusername5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusername5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusername5KeyReleased

    private void rstxtusername6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtusername6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtusername6KeyReleased

    private void rscargarfoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rscargarfoto1ActionPerformed
        javax.swing.JFileChooser selector = new javax.swing.JFileChooser();
    javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Imagen (JPG, PNG)", "jpg", "jpeg", "png");
    selector.setFileFilter(filtro);
    
    int resultado = selector.showOpenDialog(this);
    
    if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {
        java.io.File archivo = selector.getSelectedFile();
        
        try {
            // 1. Obtener la ruta del archivo (para visualización)
            rutaArchivo = archivo.getAbsolutePath();
            
            // 2. Preparar el flujo de bytes (CLAVE para el guardado)
            // 🚨 Aquí se inicializa la variable global 'fis'
            fis = new java.io.FileInputStream(archivo);
            
            // 3. Obtener el tamaño y nombre (requerido por el método de registro)
            longitudBytes = (int) archivo.length();
            nombreArchivo = archivo.getName();
            
            // 4. Mostrar la imagen en lbfotouser
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(rutaArchivo);
            
            // Nota: Se recomienda usar lbfotouser.getWidth()/getHeight() para escalado dinámico.
            // Usaré 197x197 como en tu ejemplo.
            java.awt.Image imagenEscalada = icono.getImage().getScaledInstance(197, 197, java.awt.Image.SCALE_SMOOTH);
            lbfotouser.setIcon(new javax.swing.ImageIcon(imagenEscalada));
            
        } catch (java.io.FileNotFoundException ex) {
            // Manejar error si el archivo no se encuentra
            javax.swing.JOptionPane.showMessageDialog(this, "Error: El archivo de imagen no fue encontrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            // Resetear variables en caso de error
            fis = null;
            rutaArchivo = null;
            nombreArchivo = null;
            longitudBytes = 0;
            lbfotouser.setIcon(null);
            
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
            // Resetear variables en caso de otro error
            fis = null;
            rutaArchivo = null;
            nombreArchivo = null;
            longitudBytes = 0;
            lbfotouser.setIcon(null);
        }
    }
    }//GEN-LAST:event_rscargarfoto1ActionPerformed

    private void rsbtneliminaruserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtneliminaruserActionPerformed
        javax.swing.JTable tabla = tablaPersonal; 
    
    int filaSeleccionada = tabla.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar la fila del usuario a eliminar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // El ID del empleado (Id_empleado) está en la primera columna (índice 0)
    String idEmpleadoStr = tabla.getValueAt(filaSeleccionada, 0).toString();
    int idEmpleado = Integer.parseInt(idEmpleadoStr);
    
    int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar al usuario con ID: " + idEmpleado + "? Esta acción es irreversible.", 
            "Confirmar Eliminación", 
            javax.swing.JOptionPane.YES_NO_OPTION);
    
    if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
        // Llamar al método de eliminación de la capa de lógica
        if (cargarInfop.eliminarPersonalYFoto(idEmpleado)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Recargar la tabla para reflejar los cambios
            cargarInfop.cargarInfoPersonal(tabla); 
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario. Verifique la conexión o intente nuevamente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_rsbtneliminaruserActionPerformed

    private void rsbtnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnsalirActionPerformed
        Programa.Sesion login = new Programa.Sesion(); 
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rsbtnsalirActionPerformed

    private void rstxtidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rstxtidKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rstxtidKeyReleased

    private void rsbtnguardarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnguardarusuarioActionPerformed
        String dniCorreo = txtDniCorreo.getText().trim();
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();

        // Obtener el objeto RangoComboItem seleccionado
        RangoComboItem itemSeleccionado = (RangoComboItem) cmbRango.getSelectedItem();

        // 🚨 Nueva Validación de selección
        if (itemSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un rango de usuario.", "Error de Validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el ID numérico real a partir del objeto
        String rangoIDSeleccionado = itemSeleccionado.getId();

        String contrasena = txtContrasena.getText();
        String confirmarContrasena = rstxtccontraseña.getText();

        // --- VALIDACIÓN DE CAMPOS ---
        if (dniCorreo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios.", "Error de Validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Por favor, verifíquelas.", "Error de Contraseña", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtContrasena.setText("");
            rstxtccontraseña.setText("");
            return;
        }

        // VALIDACIÓN DE LA FOTO
        if (fis == null || nombreArchivo == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar una foto para el usuario.", "Error de Foto", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // LLAMADA AL MÉTODO DE REGISTRO
        boolean registroExitoso = metodosUsuarioRegistro.registrarPersonalConFoto(
            dniCorreo, nombres, apellidos, rangoIDSeleccionado, contrasena,
            fis, longitudBytes, nombreArchivo);

        if (registroExitoso) {
            javax.swing.JOptionPane.showMessageDialog(this, "Personal registrado exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            limpiarFoto();
            limpiarCampos();

        } else {
            // Fallo en la base de datos
            javax.swing.JOptionPane.showMessageDialog(this, "Fallo al registrar el personal. Verifique la conexión o datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        metodosUsuarioRegistro.cargarInfoPersonal(tablaPersonal);
    }//GEN-LAST:event_rsbtnguardarusuarioActionPerformed

    private void rsbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnactualizarActionPerformed
        // 1. OBTENER Y VALIDAR ID
    String idEmpleadoString = rstxtid.getText().trim(); // Aún es String
    if (idEmpleadoString.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado de la tabla para actualizar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // **CONVERSIÓN CLAVE 1: String a int para el ID**
    int idEmpleadoInt;
    try {
        idEmpleadoInt = Integer.parseInt(idEmpleadoString);
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "ID de empleado inválido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // 2. OBTENER DATOS DE LA INTERFAZ (RESTO)
    String nombres = txtNombres.getText().trim();
    String apellidos = txtApellidos.getText().trim();
    String dniCorreo = txtDniCorreo.getText().trim(); 
    
    String nuevaContrasena = txtContrasena.getText();
    String confirmarContrasena = rstxtccontraseña.getText();
    
    // 3. OBTENER EL ID DEL RANGO SELECCIONADO
    String rangoID = null;
    Object itemSeleccionado = cmbRango.getSelectedItem();

    if (itemSeleccionado != null && itemSeleccionado instanceof Clases.RangoComboItem) {
        Clases.RangoComboItem rangoItem = (Clases.RangoComboItem) itemSeleccionado;
        rangoID = rangoItem.getId(); 
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un Rango válido.", "Error de Rango", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // --- VALIDACIONES ---
    if (nombres.isEmpty() || apellidos.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Los campos Nombre y Apellido son obligatorios.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // 4. MANEJO DE CONTRASEÑA
    String contrasenaFinal = null;
    
    if (!nuevaContrasena.isEmpty() || !confirmarContrasena.isEmpty()) {
        if (nuevaContrasena.equals(confirmarContrasena)) {
            contrasenaFinal = nuevaContrasena;
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error de Contraseña", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    // 5. LLAMADA AL MÉTODO DAO DE ACTUALIZACIÓN CON 9 ARGUMENTOS
    
    Clases.MetodoUsuarioRegistro metodosUsuarioRegistro = new Clases.MetodoUsuarioRegistro();
    
    // **Los últimos tres argumentos son para la foto.**
    // Asumimos que si no se seleccionó una nueva, las variables (this.fis, etc.) son null o 0.
    
    boolean actualizacionExitosa = metodosUsuarioRegistro.actualizarPersonal(
        idEmpleadoInt,            // 1. int
        dniCorreo,                // 2. String
        nombres,                  // 3. String
        apellidos,                // 4. String
        rangoID,                  // 5. String
        contrasenaFinal,          // 6. String (null si no se actualiza)
        this.fis,                 // 7. FileInputStream (this.fis: la foto cargada en la clase)
        this.longitudBytes,        // 8. int
        this.nombreArchivo         // 9. String
    );

    // 6. MANEJO DE RESULTADOS
    if (actualizacionExitosa) {
        javax.swing.JOptionPane.showMessageDialog(this, "Información de empleado actualizada exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        // Limpiar y recargar la tabla
        // ... (Tu código para limpiar y recargar) ...
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Fallo al actualizar el empleado. Verifique la conexión o datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_rsbtnactualizarActionPerformed

    private void tablaPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonalMouseClicked
        if (evt.getClickCount() == 2) { 
        int fila = tablaPersonal.getSelectedRow();

        if (fila < 0) {
            return;
        }

        try {
            javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaPersonal.getModel();

            // 1. CARGA DE DATOS EN LOS CAMPOS
            rstxtid.setText(modelo.getValueAt(fila, 0).toString());
            txtDniCorreo.setText(modelo.getValueAt(fila, 1).toString()); 
            txtNombres.setText(modelo.getValueAt(fila, 2).toString());
            txtApellidos.setText(modelo.getValueAt(fila, 3).toString());
            
            // Columna 5: RANGO (Contiene el NOMBRE, ej: "Administrador")
            String nombreRangoActual = modelo.getValueAt(fila, 5).toString();
            
            
            // ====================================================================
            // CAMBIO CLAVE: REEMPLAZAR EL BUCLE ANTIGUO CON LA FUNCIÓN ROBUSTA
            // ====================================================================
            
            // Llamamos al método que busca y selecciona el ítem por su NOMBRE
            establecerRangoSeleccionado(nombreRangoActual); 
            
            // ====================================================================
            
            
            // 2. LIMPIEZA DE CAMPOS DE CONTRASEÑA
            txtContrasena.setText(""); 
            rstxtccontraseña.setText(""); 
            
            // 3. DESHABILITAR LA EDICIÓN (Protección)
            rstxtid.setEnabled(false);       
            txtDniCorreo.setEnabled(false);   
            
            txtNombres.requestFocus(); 

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar datos de la fila: " + e.getMessage(), "Error de Carga", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_tablaPersonalMouseClicked

    private void rscblabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rscblabActionPerformed
        if (inicializando) {
        return;
    }
    
    // Obtener el valor de texto seleccionado ("TODOS" o "A-101")
    String filtroSeleccionado = rscblab.getSelectedItem().toString();
    
    // Llamar a la lógica de filtrado.
    if (metodosHardware != null) {
        metodosHardware.cargarPCsPorLaboratorio(rstbpc, filtroSeleccionado);
    }
    }//GEN-LAST:event_rscblabActionPerformed

    private void btnguardarpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarpcActionPerformed
        // 1. Obtener valores del formulario
    String marcaPC = txtmarcapc.getText().trim();  
    String tipoPC = txttipopc.getText().trim();    
    String descripcionPC = txtdescripcionpc.getText().trim();
    
    // 2. CAPTURAR EL TEXTO DEL ESTADO
    String estadoTexto = rscbestado.getSelectedItem() != null ? rscbestado.getSelectedItem().toString() : "";  

    // 3. CAPTURAR Y CONVERTIR LA FECHA SELECCIONADA (USANDO EL MÉTODO CONVENCIONAL)
    java.sql.Date fechaCompraSQL = null;
    
    try {
        // **ATENCIÓN: USAMOS getDatoFecha() COMO MÉTODO CORRECTO**
        java.util.Date fechaUtil = jDateChooserFecha.getDatoFecha(); 
        
        if (fechaUtil != null) {
            // Convertimos de java.util.Date a java.sql.Date
            fechaCompraSQL = new java.sql.Date(fechaUtil.getTime()); 
        }
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Error al obtener la fecha. Revise si el método 'getDatoFecha()' existe.", 
            "Error de Componente", javax.swing.JOptionPane.ERROR_MESSAGE);
        
        // Muestra el error exacto en la consola para depuración
        e.printStackTrace();
        return;
    }
    
    // =========================================================
    // 4. VALIDACIÓN DE CAMPOS
    // =========================================================
    if (marcaPC.isEmpty() || tipoPC.isEmpty() || descripcionPC.isEmpty() || fechaCompraSQL == null || estadoTexto.isEmpty() ||
        estadoTexto.startsWith("-- Seleccione"))  
    {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos (Marca, Tipo, Descripción, Fecha) y seleccionar un Estado válido.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    Clases.MetodoHardwareRegistro metodos = new Clases.MetodoHardwareRegistro();

    // 5. LLAMADA FINAL AL DAO
    boolean registroExitoso = metodos.registrarEquipoSimple(
        marcaPC, 
        tipoPC, 
        descripcionPC, 
        fechaCompraSQL, 
        estadoTexto     
    );
    
    // 6. MANEJO DE RESULTADOS Y LIMPIEZA
    if (registroExitoso) {
        javax.swing.JOptionPane.showMessageDialog(this, "¡Equipo Registrado Exitosamente!", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        
        // **LIMPIEZA DE CAMPOS**
        txtmarcapc.setText("");
        txttipopc.setText("");
        txtdescripcionpc.setText("");
        rscbestado.setSelectedIndex(0); 
        
        // **LIMPIEZA DE FECHA:** Usamos el setter correspondiente
        try {
            // **ATENCIÓN: USAMOS setDatoFecha(null)**
            jDateChooserFecha.setDatoFecha(null); 
        } catch (Exception ex) {
            // Si hay error, ignoramos la limpieza pero lo reportamos
            System.err.println("Error al intentar limpiar jDateChooserFecha. El método setDatoFecha(null) no existe.");
        }
        
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al registrar el Equipo. Revise la consola para detalles de la conexión.", "Error de BD", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnguardarpcActionPerformed

    private void rstbpcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rstbpcMouseClicked
        javax.swing.JTable tabla = rstbpc;
    int fila = tabla.getSelectedRow();
    
    if (fila >= 0) {
        try {
            // 1. Obtener el Id_Pc de la primera columna (columna 0)
            String idPcStr = tabla.getValueAt(fila, 0).toString();
            int idPc = Integer.parseInt(idPcStr);
            
            // 2. Buscar la Serie usando el Id_Pc (Método necesario en MetodoHardwareRegistro)
            String serieEncontrada = metodosHardware.buscarSeriePorIdPc(idPc);
            
            if (serieEncontrada != null) {
                // 3. Cargar SOLO la Serie en el campo de texto
                txtIdEquipo.setText(serieEncontrada);
            } else {
                txtIdEquipo.setText("PC no encontrada");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("Error de formato al convertir ID de PC: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al obtener la Serie de la PC: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_rstbpcMouseClicked

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

    }//GEN-LAST:event_btnactualizarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        actualizartablas();
    }//GEN-LAST:event_formWindowOpened

    private void cmbRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRangoActionPerformed
       Object selectedItem = cmbRango.getSelectedItem();
    
    if (selectedItem != null && selectedItem instanceof RangoComboItem) {
        
        RangoComboItem rangoSeleccionado = (RangoComboItem) selectedItem;
        
        // Ahora puedes llamar a getNombre() sin error
        String idRango = rangoSeleccionado.getId();
        String nombreRango = rangoSeleccionado.getNombre(); // << AHORA FUNCIONA
        
        // --- Ejemplo de uso ---
        System.out.println("ID seleccionado: " + idRango);
        System.out.println("Nombre seleccionado: " + nombreRango);
        
    } else {
        System.out.println("Ningún rango válido seleccionado.");
    }
    }//GEN-LAST:event_cmbRangoActionPerformed
    public void actualizartablas(){
    alarmaTask.cargarpcalarmas(rstbalarma);
    metodos.cargarFotos(tbfotos);
    metodousuario.cargarInfoPersonal(rstabla);
    metodopcregistro.cargarinfopc(rstbregistro); 
}
    private int obtenerRangoSegunCargo(String cargoNombre) {
    
    switch (cargoNombre) {
        case "Administrador":
            return 1;
        case "Usuario":
            return 2;
        
        default:
            return 0; 
    }
}
    public void limpiarCampos() {
    // 1. Limpiar el texto
    rstxtid.setText("");
    txtDniCorreo.setText("");
    txtNombres.setText("");
    txtApellidos.setText("");
    txtContrasena.setText("");
    rstxtccontraseña.setText("");
    
    if (cmbRango.getItemCount() > 0) {
        cmbRango.setSelectedIndex(0); 
    }
    
    rstxtid.setEnabled(true); 
    txtDniCorreo.setEnabled(true);
}
    private void limpiarFoto() {
    // 1. Resetear las variables de la foto
    fis = null;
    longitudBytes = 0;
    nombreArchivo = null;
    
    // 2. Limpiar el componente visual de la foto (lbfotouser)
    lbfotouser.setIcon(null);
    lbfotouser.setText(""); // O lo que uses por defecto
}
    private void rsbtninsertarfotoActionPerformed(java.awt.event.ActionEvent evt) {
    // Crear un selector de archivos (JFileChooser)
    JFileChooser fileChooser = new JFileChooser();
    
    // Filtro para aceptar solo archivos de imagen
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Archivos de Imagen (JPG, PNG)", "jpg", "jpeg", "png");
    fileChooser.setFileFilter(filter);
    
    // Mostrar el diálogo de abrir archivo
    int resultado = fileChooser.showOpenDialog(this);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        // El usuario seleccionó un archivo
        File archivo = fileChooser.getSelectedFile();
        
        try {
            // 1. Obtener la ruta del archivo para cargarlo
            rutaArchivo = archivo.getAbsolutePath();
            
            // 2. Preparar el flujo de bytes (FileInputStream)
            fis = new FileInputStream(archivo);
            
            // 3. Obtener el tamaño del archivo
            longitudBytes = (int) archivo.length();
            
            // 4. Obtener el nombre del archivo
            nombreArchivo = archivo.getName();
            
            // 5. Mostrar la imagen en el componente (lbfotouser)
            ImageIcon icon = new ImageIcon(rutaArchivo);
            
            // Escalar la imagen para que encaje en el JLabel (ajusta el tamaño según tu lbfotouser)
            Image img = icon.getImage().getScaledInstance(
                lbfotouser.getWidth(), 
                lbfotouser.getHeight(), 
                Image.SCALE_SMOOTH);
            
            lbfotouser.setIcon(new ImageIcon(img));
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: El archivo no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            fis = null;
            longitudBytes = 0;
            nombreArchivo = null;
        }
    }
}
    public void limpiarCamposHardware() {
    txtIdEquipo.setText("");
    txtmarcapc.setText("");
    txttipopc.setText("");
    txtdescripcionpc.setText("");
    
    txtIdEquipo.requestFocus(); 
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
    private void establecerRangoSeleccionado(String nombreRangoActual) {
    // 1. Iteramos sobre todos los ítems del ComboBox
    for (int i = 0; i < cmbRango.getItemCount(); i++) {
        
        // 2. Comparamos el nombre del ítem (el que muestra el toString())
        // Ignoramos mayúsculas/minúsculas para una comparación más robusta
        if (cmbRango.getItemAt(i).toString().equalsIgnoreCase(nombreRangoActual)) {
            
            // 3. ¡Encontramos el Nombre! Seleccionamos este ítem
            cmbRango.setSelectedIndex(i);
            return; // Terminamos la función
        }
    }
}
    private void cargarRangosEnComboBox() {
    
    try {
        // Asegúrate de que los imports de Clases.RangoExistentes y Clases.RangoComboItem estén al inicio del archivo
        Clases.RangoExistentes rangosExistentes = new Clases.RangoExistentes();
        
        java.util.List<Clases.RangoComboItem> listaRangos = rangosExistentes.obtenerTodosLosRangos();

        cmbRango.removeAllItems();
        
        for (Clases.RangoComboItem item : listaRangos) {
            cmbRango.addItem(item); 
        }
        
        if (cmbRango.getItemCount() > 0) {
            cmbRango.setSelectedIndex(-1); 
        }

    } catch (Exception e) {
        System.err.println("Error al cargar rangos: " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar los rangos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Administrador;
    private javax.swing.JPanel Principal;
    private javax.swing.JPanel alarmas;
    private RSMaterialComponent.RSButtonMaterialIconDos btnactualizar;
    private RSMaterialComponent.RSButtonMaterialIconDos btnguardarpc;
    private RSMaterialComponent.RSButtonMaterialIconDos button;
    private rojerusan.RSComboMetro cbestadopc;
    private RSMaterialComponent.RSComboBoxMaterial cmbRango;
    private javax.swing.JPanel hardware;
    private rojeru_san.rsdate.RSDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private rojerusan.RSLabelImage lbfotouser;
    private javax.swing.JMenuItem mihardware;
    private javax.swing.JMenuItem misoftware;
    private javax.swing.JPanel perfil;
    private javax.swing.JPanel principal;
    private rojeru_san.rsbutton.RSButtonRound rSButtonRound1;
    private rojeru_san.rslabel.RSLabelBorderRound rSLabelBorderRound2;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private javax.swing.JPanel registrospc;
    private RSMaterialComponent.RSButtonMaterialIconTwo rsbtnactualizar;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnalarmas;
    private RSMaterialComponent.RSButtonMaterialRipple rsbtneliminar;
    private rojerusan.RSMaterialButtonRound rsbtneliminarpc;
    private RSMaterialComponent.RSButtonMaterialIconTwo rsbtneliminaruser;
    private RSMaterialComponent.RSButtonMaterialRipple rsbtnguardar;
    private rojerusan.RSMaterialButtonRound rsbtnguardarpc;
    private RSMaterialComponent.RSButtonMaterialIconTwo rsbtnguardarusuario;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnperfil;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnprincipal;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnprincipal1;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnregistro;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnsalir;
    private RSMaterialComponent.RSButtonMaterialIconDos rsbtnusuarios;
    private rojeru_san.rsfield.RSTextMaterial rsbuscar;
    private rojeru_san.rsfield.RSTextMaterial rsbuscar3;
    private rojerusan.RSButtonHover rscargarfoto;
    private rojerusan.RSButtonHover rscargarfoto1;
    private rojerusan.RSComboMetro rscbestado;
    private rojerusan.RSComboMetro rscblab;
    private rojerusan.RSComboMetro rscblaboratorio;
    private rojerusan.RSComboMetro rscbperiodo;
    private rojerusan.RSDateChooser rsdtregistro;
    private rojeru_san.rslabel.RSLabelImage rsfoto;
    private rojerusan.RSButtonHover rsguardar;
    private rojeru_san.rslabel.RSLabelBorderRound rslbalarma;
    private rojeru_san.rslabel.RSLabelBorderRound rslbpc;
    private RSMaterialComponent.RSTableMetroCustom rstabla;
    private RSMaterialComponent.RSTableMetroCustom rstbalarma;
    private RSMaterialComponent.RSTableMetroCustom rstbpc;
    private RSMaterialComponent.RSTableMetroCustom rstbregistro;
    private rojeru_san.rsfield.RSTextMaterial rstxtccontraseña;
    private rojerusan.RSMetroTextFullPlaceHolder rstxthora;
    private rojeru_san.rsfield.RSTextMaterial rstxtid;
    private rojerusan.RSMetroTextFullPlaceHolder rstxtmarca;
    private rojerusan.RSMetroTextFullPlaceHolder rstxtminuto;
    private rojerusan.RSMetroTextFullPlaceHolder rstxtserie;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername1;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername2;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername4;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername5;
    private rojeru_san.rsfield.RSTextMaterial rstxtusername6;
    private RSMaterialComponent.RSTableMetroCustom tablaPersonal;
    private RSMaterialComponent.RSTableMetroCustom tbfotos;
    private RSMaterialComponent.RSTableMetroCustom tbhardware;
    private rojeru_san.rsfield.RSTextMaterial txtApellidos;
    private rojeru_san.rsfield.RSTextMaterial txtContrasena;
    private rojeru_san.rsfield.RSTextMaterial txtDniCorreo;
    private rojeru_san.rsfield.RSTextMaterial txtIdEquipo;
    private rojeru_san.rsfield.RSTextMaterial txtNombres;
    private javax.swing.JTextPane txtdescripcion;
    private rojeru_san.rsfield.RSTextMaterial txtdescripcionpc;
    private rojeru_san.rsfield.RSTextMaterial txtmarcapc;
    private rojeru_san.rsfield.RSTextMaterial txttipopc;
    private javax.swing.JPanel usuarios;
    // End of variables declaration//GEN-END:variables

}
