package Vistas;

import entidades.Socios;
import javax.swing.table.DefaultTableModel;
import persistencia.MiConexion;
import persistencia.SocioData;
import entidades.Entrenadores;
import java.util.List;
import entidades.Clases;
import java.time.LocalTime;

public class MenuPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());
    private MiConexion conexion;
    private SocioData socioData;
    private int socioSeleccionadoId = -1;

    public MenuPrincipal(MiConexion conexion) {
        initComponents();

        this.conexion = conexion;
        this.socioData = new SocioData(this.conexion);
        cargarTablaSocios();

        // Reorganizar las vistas del panel principal
        panelContenido.removeAll();
        panelContenido.setLayout(new java.awt.CardLayout());

        panelContenido.add(vistaInicio, "inicio");
        panelContenido.add(vistaSocios, "socios");
        panelContenido.add(vistaEntrenadores, "entrenadores");
        panelContenido.add(vistaClases, "clases");

        // Mostrar inicio al abrir el programa
        mostrarVista("inicio");
        cambiarColorBoton(btnInicio);
    }

    private void cargarTablaSocios() {

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        modelo.setRowCount(0);

        for (Socios socio : socioData.listarSocios()) {

            modelo.addRow(new Object[]{
                socio.getIdSocio(),
                socio.getDni(),
                socio.getNombre(),
                socio.getApellido(),
                socio.getEdad(),
                socio.getCorreo(),
                socio.getTelefono(),
                socio.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }

    private void limpiarCamposSocios() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    private void mostrarVista(String vista) {
        java.awt.CardLayout card = (java.awt.CardLayout) panelContenido.getLayout();
        card.show(panelContenido, vista);
    }

    private void cambiarColorBoton(javax.swing.JButton seleccionado) {

        // Restaurar todos los botones al color normal
        java.awt.Color fondo = new java.awt.Color(34, 39, 43);
        java.awt.Color texto = java.awt.Color.WHITE;

        btnInicio.setBackground(fondo);
        btnInicio.setForeground(texto);

        btnSocios.setBackground(fondo);
        btnSocios.setForeground(texto);

        btnEntrenadores.setBackground(fondo);
        btnEntrenadores.setForeground(texto);

        btnClases.setBackground(fondo);
        btnClases.setForeground(texto);

        // Resaltar el botón seleccionado
        seleccionado.setBackground(new java.awt.Color(184, 243, 74));
        seleccionado.setForeground(new java.awt.Color(34, 39, 43));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        tituloMenu = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnSocios = new javax.swing.JButton();
        btnEntrenadores = new javax.swing.JButton();
        btnClases = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelContenido = new javax.swing.JPanel();
        vistaInicio = new javax.swing.JPanel();
        panelDeControl = new javax.swing.JLabel();
        resumenGym = new javax.swing.JLabel();
        panelClases = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelEntrenadores = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelSocios = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vistaSocios = new javax.swing.JPanel();
        tituloSocios = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblEdad = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        vistaEntrenadores = new javax.swing.JPanel();
        tituloEntrenadores = new javax.swing.JLabel();
        lblDni1 = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblApellido1 = new javax.swing.JLabel();
        lblEspecialidad = new javax.swing.JLabel();
        txtDni1 = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtEspecialidad = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        btnGuardarEntrenador = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        btnActualizar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnAlta1 = new javax.swing.JButton();
        btnBaja1 = new javax.swing.JButton();
        btnEliminarEntrenador = new javax.swing.JButton();
        vistaClases = new javax.swing.JPanel();
        tituloClases = new javax.swing.JLabel();
        lblDisciplina = new javax.swing.JLabel();
        lblEntrenador = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblCapacidad = new javax.swing.JLabel();
        txtDisciplina = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        txtHorario = new javax.swing.JTextField();
        btnGuardarClase = new javax.swing.JButton();
        btnLimpiar2 = new javax.swing.JButton();
        btnActualizar2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnAlta2 = new javax.swing.JButton();
        btnBaja2 = new javax.swing.JButton();
        btnEliminarClase = new javax.swing.JButton();
        jcbSelecEntrenador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\tAboriGym - Gestión del gimnasio");
        setResizable(false);

        panelMenu.setBackground(new java.awt.Color(34, 38, 43));
        panelMenu.setPreferredSize(new java.awt.Dimension(170, 450));

        tituloMenu.setFont(new java.awt.Font("Century Gothic", 1, 32)); // NOI18N
        tituloMenu.setForeground(new java.awt.Color(184, 243, 74));
        tituloMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aborigym.png"))); // NOI18N
        tituloMenu.setMaximumSize(new java.awt.Dimension(100, 100));
        tituloMenu.setMinimumSize(new java.awt.Dimension(100, 100));
        tituloMenu.setPreferredSize(new java.awt.Dimension(180, 100));

        btnInicio.setBackground(new java.awt.Color(184, 243, 74));
        btnInicio.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casa.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setFocusPainted(false);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setIconTextGap(5);
        btnInicio.setMaximumSize(new java.awt.Dimension(81, 34));
        btnInicio.setMinimumSize(new java.awt.Dimension(81, 34));
        btnInicio.setOpaque(true);
        btnInicio.setPreferredSize(new java.awt.Dimension(145, 42));
        btnInicio.addActionListener(this::btnInicioActionPerformed);

        btnSocios.setBackground(new java.awt.Color(34, 39, 43));
        btnSocios.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        btnSocios.setForeground(new java.awt.Color(255, 255, 255));
        btnSocios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/socios.png"))); // NOI18N
        btnSocios.setText("Socios");
        btnSocios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSocios.setBorderPainted(false);
        btnSocios.setContentAreaFilled(false);
        btnSocios.setFocusPainted(false);
        btnSocios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSocios.setOpaque(true);
        btnSocios.setPreferredSize(new java.awt.Dimension(145, 42));
        btnSocios.addActionListener(this::btnSociosActionPerformed);

        btnEntrenadores.setBackground(new java.awt.Color(34, 39, 43));
        btnEntrenadores.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        btnEntrenadores.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrenadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrenadores.png"))); // NOI18N
        btnEntrenadores.setText("Entrenadores");
        btnEntrenadores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEntrenadores.setBorderPainted(false);
        btnEntrenadores.setContentAreaFilled(false);
        btnEntrenadores.setFocusPainted(false);
        btnEntrenadores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEntrenadores.setOpaque(true);
        btnEntrenadores.setPreferredSize(new java.awt.Dimension(145, 42));
        btnEntrenadores.addActionListener(this::btnEntrenadoresActionPerformed);

        btnClases.setBackground(new java.awt.Color(34, 39, 43));
        btnClases.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        btnClases.setForeground(new java.awt.Color(255, 255, 255));
        btnClases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clases.png"))); // NOI18N
        btnClases.setText("Clases");
        btnClases.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClases.setBorderPainted(false);
        btnClases.setContentAreaFilled(false);
        btnClases.setFocusPainted(false);
        btnClases.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClases.setOpaque(true);
        btnClases.setPreferredSize(new java.awt.Dimension(145, 42));
        btnClases.addActionListener(this::btnClasesActionPerformed);

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(51, 51, 51));
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setFocusPainted(false);
        btnSalir.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSocios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEntrenadores, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btnClases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tituloMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSocios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelContenido.setBackground(new java.awt.Color(244, 245, 246));
        panelContenido.setPreferredSize(new java.awt.Dimension(650, 450));
        panelContenido.setLayout(new java.awt.CardLayout());

        vistaInicio.setBackground(new java.awt.Color(255, 255, 255));
        vistaInicio.setPreferredSize(new java.awt.Dimension(650, 450));

        panelDeControl.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        panelDeControl.setForeground(new java.awt.Color(102, 102, 102));
        panelDeControl.setText("Resumen general del gimnasio");

        resumenGym.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        resumenGym.setForeground(new java.awt.Color(0, 0, 0));
        resumenGym.setText("Panel de control");

        panelClases.setBackground(new java.awt.Color(244, 245, 246));
        panelClases.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        panelClases.setPreferredSize(new java.awt.Dimension(190, 120));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Entrenadores");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 119));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Personal del gimnasio");

        javax.swing.GroupLayout panelClasesLayout = new javax.swing.GroupLayout(panelClases);
        panelClases.setLayout(panelClasesLayout);
        panelClasesLayout.setHorizontalGroup(
            panelClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClasesLayout.createSequentialGroup()
                .addGroup(panelClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClasesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3))
                    .addGroup(panelClasesLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panelClasesLayout.setVerticalGroup(
            panelClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClasesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        panelEntrenadores.setBackground(new java.awt.Color(244, 245, 246));
        panelEntrenadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        panelEntrenadores.setPreferredSize(new java.awt.Dimension(190, 120));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Clases");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 119));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Actividades del gimnasio");

        javax.swing.GroupLayout panelEntrenadoresLayout = new javax.swing.GroupLayout(panelEntrenadores);
        panelEntrenadores.setLayout(panelEntrenadoresLayout);
        panelEntrenadoresLayout.setHorizontalGroup(
            panelEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntrenadoresLayout.createSequentialGroup()
                .addGroup(panelEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEntrenadoresLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9))
                    .addGroup(panelEntrenadoresLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelEntrenadoresLayout.setVerticalGroup(
            panelEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntrenadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        panelSocios.setBackground(new java.awt.Color(244, 245, 246));
        panelSocios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        panelSocios.setPreferredSize(new java.awt.Dimension(190, 120));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Socios");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 119));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Total de socios activos");

        javax.swing.GroupLayout panelSociosLayout = new javax.swing.GroupLayout(panelSocios);
        panelSocios.setLayout(panelSociosLayout);
        panelSociosLayout.setHorizontalGroup(
            panelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSociosLayout.createSequentialGroup()
                .addGroup(panelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSociosLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7))
                    .addGroup(panelSociosLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelSociosLayout.setVerticalGroup(
            panelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSociosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout vistaInicioLayout = new javax.swing.GroupLayout(vistaInicio);
        vistaInicio.setLayout(vistaInicioLayout);
        vistaInicioLayout.setHorizontalGroup(
            vistaInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaInicioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelSocios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vistaInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaInicioLayout.createSequentialGroup()
                        .addComponent(panelClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vistaInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(resumenGym)
                        .addComponent(panelDeControl)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        vistaInicioLayout.setVerticalGroup(
            vistaInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resumenGym)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDeControl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vistaInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSocios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(560, Short.MAX_VALUE))
        );

        panelContenido.add(vistaInicio, "card7");

        vistaSocios.setBackground(new java.awt.Color(244, 245, 246));

        tituloSocios.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        tituloSocios.setForeground(new java.awt.Color(51, 51, 51));
        tituloSocios.setText("Gestion de socios");

        lblDni.setBackground(new java.awt.Color(244, 245, 246));
        lblDni.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblDni.setForeground(new java.awt.Color(51, 51, 51));
        lblDni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDni.setText("DNI");

        lblNombre.setBackground(new java.awt.Color(244, 245, 246));
        lblNombre.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombre.setText("Nombre");

        lblApellido.setBackground(new java.awt.Color(244, 245, 246));
        lblApellido.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(51, 51, 51));
        lblApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblApellido.setText("Apellido");

        lblTelefono.setBackground(new java.awt.Color(244, 245, 246));
        lblTelefono.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTelefono.setText("Telefono");

        txtDni.setBackground(new java.awt.Color(255, 255, 255));
        txtDni.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDni.setForeground(new java.awt.Color(51, 51, 51));
        txtDni.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDni.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(213, 220, 227), 1, true));
        txtDni.setCaretColor(new java.awt.Color(132, 184, 42));
        txtDni.setPreferredSize(new java.awt.Dimension(300, 30));
        txtDni.addActionListener(this::txtDniActionPerformed);

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(213, 220, 227), 1, true));
        txtNombre.setCaretColor(new java.awt.Color(132, 184, 42));
        txtNombre.setPreferredSize(new java.awt.Dimension(220, 30));
        txtNombre.addActionListener(this::txtNombreActionPerformed);

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtTelefono.setCaretColor(new java.awt.Color(132, 184, 42));
        txtTelefono.setPreferredSize(new java.awt.Dimension(220, 30));
        txtTelefono.addActionListener(this::txtTelefonoActionPerformed);

        txtApellido.setBackground(new java.awt.Color(255, 255, 255));
        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(51, 51, 51));
        txtApellido.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtApellido.setCaretColor(new java.awt.Color(132, 184, 42));
        txtApellido.setPreferredSize(new java.awt.Dimension(220, 30));
        txtApellido.addActionListener(this::txtApellidoActionPerformed);

        btnGuardar.setBackground(new java.awt.Color(204, 255, 102));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardar.setText("Guardar socio");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(140, 40));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(140, 40));
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(51, 51, 51));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setPreferredSize(new java.awt.Dimension(140, 40));
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nombre", "Apellido", "Edad", "Correo", "Telefono", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(51, 51, 51));
        jTable1.setRowHeight(28);
        jTable1.setSelectionBackground(new java.awt.Color(204, 255, 102));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnAlta.setBackground(new java.awt.Color(204, 255, 102));
        btnAlta.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAlta.setForeground(new java.awt.Color(51, 51, 51));
        btnAlta.setText("Dar de alta");
        btnAlta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlta.setFocusPainted(false);
        btnAlta.setPreferredSize(new java.awt.Dimension(140, 40));
        btnAlta.addActionListener(this::btnAltaActionPerformed);

        btnBaja.setBackground(new java.awt.Color(51, 51, 51));
        btnBaja.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja.setText("Dar de baja");
        btnBaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaja.setFocusPainted(false);
        btnBaja.setPreferredSize(new java.awt.Dimension(140, 40));
        btnBaja.addActionListener(this::btnBajaActionPerformed);

        btnEliminar.setBackground(new java.awt.Color(168, 61, 61));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar socio");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new java.awt.Dimension(140, 40));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        lblEdad.setBackground(new java.awt.Color(244, 245, 246));
        lblEdad.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblEdad.setForeground(new java.awt.Color(51, 51, 51));
        lblEdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEdad.setText("Edad");

        lblCorreo.setBackground(new java.awt.Color(244, 245, 246));
        lblCorreo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(51, 51, 51));
        lblCorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCorreo.setText("Correo");

        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(51, 51, 51));
        txtEdad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtEdad.setCaretColor(new java.awt.Color(132, 184, 42));
        txtEdad.setPreferredSize(new java.awt.Dimension(220, 30));
        txtEdad.addActionListener(this::txtEdadActionPerformed);

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtCorreo.setCaretColor(new java.awt.Color(132, 184, 42));
        txtCorreo.setPreferredSize(new java.awt.Dimension(220, 30));
        txtCorreo.addActionListener(this::txtCorreoActionPerformed);

        javax.swing.GroupLayout vistaSociosLayout = new javax.swing.GroupLayout(vistaSocios);
        vistaSocios.setLayout(vistaSociosLayout);
        vistaSociosLayout.setHorizontalGroup(
            vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaSociosLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(tituloSocios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaSociosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaSociosLayout.createSequentialGroup()
                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(vistaSociosLayout.createSequentialGroup()
                        .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(vistaSociosLayout.createSequentialGroup()
                                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDni)
                                    .addComponent(lblApellido)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(lblEdad)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblCorreo)
                                    .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTelefono)))
                            .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(vistaSociosLayout.createSequentialGroup()
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(100, 100, 100)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        vistaSociosLayout.setVerticalGroup(
            vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaSociosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloSocios)
                .addGap(18, 18, 18)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(lblTelefono))
                .addGap(6, 6, 6)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(lblCorreo))
                .addGap(6, 6, 6)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vistaSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContenido.add(vistaSocios, "card3");

        vistaEntrenadores.setBackground(new java.awt.Color(244, 245, 246));

        tituloEntrenadores.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        tituloEntrenadores.setForeground(new java.awt.Color(51, 51, 51));
        tituloEntrenadores.setText("Gestion de entrenadores");

        lblDni1.setBackground(new java.awt.Color(244, 245, 246));
        lblDni1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblDni1.setForeground(new java.awt.Color(51, 51, 51));
        lblDni1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDni1.setText("DNI");

        lblNombre1.setBackground(new java.awt.Color(244, 245, 246));
        lblNombre1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombre1.setText("Nombre");

        lblApellido1.setBackground(new java.awt.Color(244, 245, 246));
        lblApellido1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblApellido1.setForeground(new java.awt.Color(51, 51, 51));
        lblApellido1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblApellido1.setText("Apellido");

        lblEspecialidad.setBackground(new java.awt.Color(244, 245, 246));
        lblEspecialidad.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblEspecialidad.setForeground(new java.awt.Color(51, 51, 51));
        lblEspecialidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEspecialidad.setText("Especialidad");

        txtDni1.setBackground(new java.awt.Color(255, 255, 255));
        txtDni1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDni1.setForeground(new java.awt.Color(51, 51, 51));
        txtDni1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDni1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(213, 220, 227), 1, true));
        txtDni1.setCaretColor(new java.awt.Color(132, 184, 42));
        txtDni1.setPreferredSize(new java.awt.Dimension(300, 30));
        txtDni1.addActionListener(this::txtDni1ActionPerformed);

        txtNombre1.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre1.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombre1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(213, 220, 227), 1, true));
        txtNombre1.setCaretColor(new java.awt.Color(132, 184, 42));
        txtNombre1.setPreferredSize(new java.awt.Dimension(220, 30));
        txtNombre1.addActionListener(this::txtNombre1ActionPerformed);

        txtEspecialidad.setBackground(new java.awt.Color(255, 255, 255));
        txtEspecialidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEspecialidad.setForeground(new java.awt.Color(51, 51, 51));
        txtEspecialidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEspecialidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtEspecialidad.setCaretColor(new java.awt.Color(132, 184, 42));
        txtEspecialidad.setPreferredSize(new java.awt.Dimension(220, 30));
        txtEspecialidad.addActionListener(this::txtEspecialidadActionPerformed);

        txtApellido1.setBackground(new java.awt.Color(255, 255, 255));
        txtApellido1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido1.setForeground(new java.awt.Color(51, 51, 51));
        txtApellido1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtApellido1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtApellido1.setCaretColor(new java.awt.Color(132, 184, 42));
        txtApellido1.setPreferredSize(new java.awt.Dimension(220, 30));
        txtApellido1.addActionListener(this::txtApellido1ActionPerformed);

        btnGuardarEntrenador.setBackground(new java.awt.Color(204, 255, 102));
        btnGuardarEntrenador.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarEntrenador.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardarEntrenador.setText("Guardar entrenador");
        btnGuardarEntrenador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarEntrenador.setFocusPainted(false);
        btnGuardarEntrenador.setPreferredSize(new java.awt.Dimension(140, 40));

        btnLimpiar1.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar1.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar1.setText("Limpiar");
        btnLimpiar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar1.setFocusPainted(false);
        btnLimpiar1.setPreferredSize(new java.awt.Dimension(140, 40));

        btnActualizar1.setBackground(new java.awt.Color(51, 51, 51));
        btnActualizar1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnActualizar1.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar1.setText("Actualizar");
        btnActualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar1.setFocusPainted(false);
        btnActualizar1.setPreferredSize(new java.awt.Dimension(140, 40));

        jTable2.setBackground(new java.awt.Color(204, 204, 204));
        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(51, 51, 51));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nombre", "Apellido", "Especialidad", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(51, 51, 51));
        jTable2.setRowHeight(28);
        jTable2.setSelectionBackground(new java.awt.Color(204, 255, 102));
        jScrollPane2.setViewportView(jTable2);

        btnAlta1.setBackground(new java.awt.Color(204, 255, 102));
        btnAlta1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAlta1.setForeground(new java.awt.Color(51, 51, 51));
        btnAlta1.setText("Dar de alta");
        btnAlta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlta1.setFocusPainted(false);
        btnAlta1.setPreferredSize(new java.awt.Dimension(140, 40));
        btnAlta1.addActionListener(this::btnAlta1ActionPerformed);

        btnBaja1.setBackground(new java.awt.Color(51, 51, 51));
        btnBaja1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBaja1.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja1.setText("Dar de baja");
        btnBaja1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaja1.setFocusPainted(false);
        btnBaja1.setPreferredSize(new java.awt.Dimension(140, 40));

        btnEliminarEntrenador.setBackground(new java.awt.Color(168, 61, 61));
        btnEliminarEntrenador.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminarEntrenador.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarEntrenador.setText("Eliminar entrenador");
        btnEliminarEntrenador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarEntrenador.setFocusPainted(false);
        btnEliminarEntrenador.setPreferredSize(new java.awt.Dimension(140, 40));

        javax.swing.GroupLayout vistaEntrenadoresLayout = new javax.swing.GroupLayout(vistaEntrenadores);
        vistaEntrenadores.setLayout(vistaEntrenadoresLayout);
        vistaEntrenadoresLayout.setHorizontalGroup(
            vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaEntrenadoresLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vistaEntrenadoresLayout.createSequentialGroup()
                        .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDni1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellido1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDni1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtApellido1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre1)
                            .addComponent(lblEspecialidad)
                            .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEspecialidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(vistaEntrenadoresLayout.createSequentialGroup()
                        .addComponent(btnGuardarEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vistaEntrenadoresLayout.createSequentialGroup()
                        .addComponent(btnAlta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBaja1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnEliminarEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaEntrenadoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloEntrenadores)
                .addGap(175, 175, 175))
        );
        vistaEntrenadoresLayout.setVerticalGroup(
            vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaEntrenadoresLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloEntrenadores)
                .addGap(18, 18, 18)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni1)
                    .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido1)
                    .addComponent(lblEspecialidad))
                .addGap(6, 6, 6)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vistaEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaja1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContenido.add(vistaEntrenadores, "card3");

        vistaClases.setBackground(new java.awt.Color(244, 245, 246));

        tituloClases.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        tituloClases.setForeground(new java.awt.Color(51, 51, 51));
        tituloClases.setText("Gestion de clases");

        lblDisciplina.setBackground(new java.awt.Color(244, 245, 246));
        lblDisciplina.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblDisciplina.setForeground(new java.awt.Color(51, 51, 51));
        lblDisciplina.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDisciplina.setText("Disciplina");

        lblEntrenador.setBackground(new java.awt.Color(244, 245, 246));
        lblEntrenador.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblEntrenador.setForeground(new java.awt.Color(51, 51, 51));
        lblEntrenador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEntrenador.setText("Entrenador");

        lblHorario.setBackground(new java.awt.Color(244, 245, 246));
        lblHorario.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblHorario.setForeground(new java.awt.Color(51, 51, 51));
        lblHorario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHorario.setText("Horario");

        lblCapacidad.setBackground(new java.awt.Color(244, 245, 246));
        lblCapacidad.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblCapacidad.setForeground(new java.awt.Color(51, 51, 51));
        lblCapacidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCapacidad.setText("Capacidad");

        txtDisciplina.setBackground(new java.awt.Color(255, 255, 255));
        txtDisciplina.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDisciplina.setForeground(new java.awt.Color(51, 51, 51));
        txtDisciplina.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDisciplina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(213, 220, 227), 1, true));
        txtDisciplina.setCaretColor(new java.awt.Color(132, 184, 42));
        txtDisciplina.setPreferredSize(new java.awt.Dimension(300, 30));
        txtDisciplina.addActionListener(this::txtDisciplinaActionPerformed);

        txtCapacidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCapacidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCapacidad.setForeground(new java.awt.Color(51, 51, 51));
        txtCapacidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCapacidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtCapacidad.setCaretColor(new java.awt.Color(132, 184, 42));
        txtCapacidad.setPreferredSize(new java.awt.Dimension(220, 30));
        txtCapacidad.addActionListener(this::txtCapacidadActionPerformed);

        txtHorario.setBackground(new java.awt.Color(255, 255, 255));
        txtHorario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHorario.setForeground(new java.awt.Color(51, 51, 51));
        txtHorario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtHorario.setCaretColor(new java.awt.Color(132, 184, 42));
        txtHorario.setPreferredSize(new java.awt.Dimension(220, 30));
        txtHorario.addActionListener(this::txtHorarioActionPerformed);

        btnGuardarClase.setBackground(new java.awt.Color(204, 255, 102));
        btnGuardarClase.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarClase.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardarClase.setText("Guardar clase");
        btnGuardarClase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarClase.setFocusPainted(false);
        btnGuardarClase.setPreferredSize(new java.awt.Dimension(140, 40));

        btnLimpiar2.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar2.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar2.setText("Limpiar");
        btnLimpiar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar2.setFocusPainted(false);
        btnLimpiar2.setPreferredSize(new java.awt.Dimension(140, 40));

        btnActualizar2.setBackground(new java.awt.Color(51, 51, 51));
        btnActualizar2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnActualizar2.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar2.setText("Actualizar");
        btnActualizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar2.setFocusPainted(false);
        btnActualizar2.setPreferredSize(new java.awt.Dimension(140, 40));

        jTable3.setBackground(new java.awt.Color(204, 204, 204));
        jTable3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(51, 51, 51));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Disciplina", "Entrenador", "Horario", "Capacidad", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(51, 51, 51));
        jTable3.setRowHeight(28);
        jTable3.setSelectionBackground(new java.awt.Color(204, 255, 102));
        jScrollPane3.setViewportView(jTable3);

        btnAlta2.setBackground(new java.awt.Color(204, 255, 102));
        btnAlta2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAlta2.setForeground(new java.awt.Color(51, 51, 51));
        btnAlta2.setText("Dar de alta");
        btnAlta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlta2.setFocusPainted(false);
        btnAlta2.setPreferredSize(new java.awt.Dimension(140, 40));
        btnAlta2.addActionListener(this::btnAlta2ActionPerformed);

        btnBaja2.setBackground(new java.awt.Color(51, 51, 51));
        btnBaja2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBaja2.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja2.setText("Dar de baja");
        btnBaja2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaja2.setFocusPainted(false);
        btnBaja2.setPreferredSize(new java.awt.Dimension(140, 40));

        btnEliminarClase.setBackground(new java.awt.Color(168, 61, 61));
        btnEliminarClase.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminarClase.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarClase.setText("Eliminar clase");
        btnEliminarClase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarClase.setFocusPainted(false);
        btnEliminarClase.setPreferredSize(new java.awt.Dimension(140, 40));

        jcbSelecEntrenador.setBackground(new java.awt.Color(255, 255, 255));
        jcbSelecEntrenador.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jcbSelecEntrenador.setForeground(new java.awt.Color(48, 48, 48));
        jcbSelecEntrenador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar entrenador..." }));
        jcbSelecEntrenador.setName("Seleccionar entrenador"); // NOI18N
        jcbSelecEntrenador.addActionListener(this::jcbSelecEntrenadorActionPerformed);

        javax.swing.GroupLayout vistaClasesLayout = new javax.swing.GroupLayout(vistaClases);
        vistaClases.setLayout(vistaClasesLayout);
        vistaClasesLayout.setHorizontalGroup(
            vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaClasesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(vistaClasesLayout.createSequentialGroup()
                        .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDisciplina, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHorario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDisciplina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtHorario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEntrenador)
                            .addComponent(jcbSelecEntrenador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCapacidad)
                            .addComponent(txtCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                    .addGroup(vistaClasesLayout.createSequentialGroup()
                        .addComponent(btnAlta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btnBaja2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vistaClasesLayout.createSequentialGroup()
                        .addComponent(btnGuardarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(btnActualizar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(btnLimpiar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaClasesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloClases)
                .addGap(218, 218, 218))
        );
        vistaClasesLayout.setVerticalGroup(
            vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaClasesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloClases)
                .addGap(18, 18, 18)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisciplina)
                    .addComponent(lblEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSelecEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorario)
                    .addComponent(lblCapacidad))
                .addGap(6, 6, 6)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vistaClasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaja2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContenido.add(vistaClases, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        mostrarVista("inicio");
        cambiarColorBoton(btnInicio);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSociosActionPerformed
        mostrarVista("socios");
        cambiarColorBoton(btnSocios);
    }//GEN-LAST:event_btnSociosActionPerformed

    private void btnEntrenadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrenadoresActionPerformed
        mostrarVista("entrenadores");
        cambiarColorBoton(btnEntrenadores);
    }//GEN-LAST:event_btnEntrenadoresActionPerformed

    private void btnClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasesActionPerformed
        mostrarVista("clases");
        cambiarColorBoton(btnClases);
    }//GEN-LAST:event_btnClasesActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        if (socioSeleccionadoId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccioná un socio de la tabla.");
            return;
        }

        Socios socio = socioData.buscarSocio(socioSeleccionadoId);

        if (socio == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontró el socio.");
            return;
        }

        if (socio.isEstado()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "El socio ya está activo.");
            return;
        }

        socio.setEstado(true);
        socioData.actualizarSocio(socio);

        cargarTablaSocios();
        limpiarCamposSocios();
        socioSeleccionadoId = -1;

        javax.swing.JOptionPane.showMessageDialog(this,
                "Socio dado de alta correctamente.");
    }//GEN-LAST:event_btnAltaActionPerformed

    private void txtDni1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDni1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDni1ActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecialidadActionPerformed

    private void txtApellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellido1ActionPerformed

    private void btnAlta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlta1ActionPerformed

    private void txtDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisciplinaActionPerformed

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

    private void txtHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorarioActionPerformed

    private void btnAlta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlta2ActionPerformed

    private void jcbSelecEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSelecEntrenadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbSelecEntrenadorActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed

    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String edadTexto = txtEdad.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty()
                    || edadTexto.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Completá todos los campos.");
                return;
            }

            int edad = Integer.parseInt(edadTexto);

            if (edad <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Ingresá una edad válida.");
                return;
            }

            if (socioData.buscarSocioPorDni(dni) != null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Ya existe un socio con ese DNI.");
                return;
            }

            Socios socio = new Socios();
            socio.setDni(dni);
            socio.setNombre(nombre);
            socio.setApellido(apellido);
            socio.setEdad(edad);
            socio.setCorreo(correo);
            socio.setTelefono(telefono);
            socio.setEstado(true);

            socioData.guardarSocio(socio);

            cargarTablaSocios();

            limpiarCamposSocios();

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Socio guardado correctamente.");

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "La edad debe ser un número.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCamposSocios();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

        if (fila != -1) {

            int filaModelo = jTable1.convertRowIndexToModel(fila);

            socioSeleccionadoId = (int) jTable1.getModel().getValueAt(filaModelo, 0);

            txtDni.setText(jTable1.getModel().getValueAt(filaModelo, 1).toString());
            txtNombre.setText(jTable1.getModel().getValueAt(filaModelo, 2).toString());
            txtApellido.setText(jTable1.getModel().getValueAt(filaModelo, 3).toString());
            txtEdad.setText(jTable1.getModel().getValueAt(filaModelo, 4).toString());
            txtCorreo.setText(jTable1.getModel().getValueAt(filaModelo, 5).toString());
            txtTelefono.setText(jTable1.getModel().getValueAt(filaModelo, 6).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (socioSeleccionadoId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccioná un socio de la tabla.");
            return;
        }

        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String edadTexto = txtEdad.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty()
                    || edadTexto.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Completá todos los campos.");
                return;
            }

            int edad = Integer.parseInt(edadTexto);

            if (edad <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Ingresá una edad válida.");
                return;
            }

            Socios socio = new Socios();
            socio.setIdSocio(socioSeleccionadoId);
            socio.setDni(dni);
            socio.setNombre(nombre);
            socio.setApellido(apellido);
            socio.setEdad(edad);
            socio.setCorreo(correo);
            socio.setTelefono(telefono);
            socio.setEstado(true);

            socioData.actualizarSocio(socio);

            cargarTablaSocios();
            limpiarCamposSocios();
            socioSeleccionadoId = -1;

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Socio actualizado correctamente.");

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "La edad debe ser un número.");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        if (socioSeleccionadoId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccioná un socio de la tabla.");
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que querés dar de baja a este socio?",
                "Confirmar baja",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

            socioData.borrarSocio(socioSeleccionadoId);

            cargarTablaSocios();
            limpiarCamposSocios();

            socioSeleccionadoId = -1;

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Socio dado de baja correctamente.");
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (socioSeleccionadoId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccioná un socio de la tabla.");
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar definitivamente este socio?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

            socioData.eliminarSocio(socioSeleccionadoId);

            cargarTablaSocios();
            limpiarCamposSocios();
            socioSeleccionadoId = -1;

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Socio eliminado correctamente.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnActualizar2;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnAlta1;
    private javax.swing.JButton btnAlta2;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBaja1;
    private javax.swing.JButton btnBaja2;
    private javax.swing.JButton btnClases;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarClase;
    private javax.swing.JButton btnEliminarEntrenador;
    private javax.swing.JButton btnEntrenadores;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarClase;
    private javax.swing.JButton btnGuardarEntrenador;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiar2;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSocios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JComboBox<String> jcbSelecEntrenador;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblApellido1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDisciplina;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblDni1;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEntrenador;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelClases;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JLabel panelDeControl;
    private javax.swing.JPanel panelEntrenadores;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelSocios;
    private javax.swing.JLabel resumenGym;
    private javax.swing.JLabel tituloClases;
    private javax.swing.JLabel tituloEntrenadores;
    private javax.swing.JLabel tituloMenu;
    private javax.swing.JLabel tituloSocios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDisciplina;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtDni1;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JPanel vistaClases;
    private javax.swing.JPanel vistaEntrenadores;
    private javax.swing.JPanel vistaInicio;
    private javax.swing.JPanel vistaSocios;
    // End of variables declaration//GEN-END:variables
}
