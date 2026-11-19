package Vistas;

import entidades.Asistencia;
import entidades.Socios;
import entidades.Clases;
import entidades.Pase;

import persistencia.MiConexion;
import persistencia.SocioData;
import persistencia.ClasesData;
import persistencia.AsistenciaData;
import persistencia.PaseData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaAsistencia extends javax.swing.JInternalFrame {

    private MiConexion conexion;

    private SocioData socioData;
    private ClasesData clasesData;
    private AsistenciaData asistenciaData;
    private PaseData paseData;

    private List<Socios> sociosDisponibles = new ArrayList<>();
    private List<Clases> clasesDisponibles = new ArrayList<>();

    public VistaAsistencia(MiConexion conexion) {
        initComponents();

        this.conexion = conexion;

        socioData = new SocioData(conexion);
        clasesData = new ClasesData(conexion);
        asistenciaData = new AsistenciaData(conexion);
        paseData = new PaseData(conexion);

        txtPase.setEditable(false);

        dateChooserAsistencia.setDate(
                java.sql.Date.valueOf(LocalDate.now())
        );

        cargarSocios();
        cargarClases();
        limpiarInformacionPase();
        cargarTablaAsistencias();
    }

    public javax.swing.JPanel getPanelAsistencia() {
        return vistaPases;
    }

    private void cargarSocios() {

        sociosDisponibles = socioData.listarSocios();

        cmbSocio.removeAllItems();
        cmbSocio.addItem("Seleccionar socio...");

        for (Socios socio : sociosDisponibles) {

            if (socio.isEstado()) {

                String nombre = socio.getApellido() + ", "
                        + socio.getNombre()
                        + " - DNI: " + socio.getDni();

                cmbSocio.addItem(nombre);
            }
        }
    }

    private void cargarClases() {

        clasesDisponibles = clasesData.listarClasesActivas();

        cmbClase.removeAllItems();
        cmbClase.addItem("Seleccionar clase...");

        for (Clases clase : clasesDisponibles) {
            cmbClase.addItem(clase.toString());
        }
    }

    public void actualizarListas() {
        cargarSocios();
        cargarClases();
        limpiarInformacionPase();
        cargarTablaAsistencias();
    }

    private Socios obtenerSocioSeleccionado() {

        int indice = cmbSocio.getSelectedIndex();

        if (indice <= 0) {
            return null;
        }

        int contador = 0;

        for (Socios socio : sociosDisponibles) {

            if (socio.isEstado()) {

                contador++;

                if (contador == indice) {
                    return socio;
                }
            }
        }

        return null;
    }

    private Clases obtenerClaseSeleccionada() {

        int indice = cmbClase.getSelectedIndex();

        if (indice <= 0) {
            return null;
        }

        int posicion = indice - 1;

        if (posicion >= clasesDisponibles.size()) {
            return null;
        }

        return clasesDisponibles.get(posicion);
    }

    private void limpiarInformacionPase() {

        txtPase.setText("");
        lblEntradasDisponibles.setText("--");
        lblFechaVencimiento.setText("--");
    }

    private void actualizarInformacionPase() {

        Socios socio = obtenerSocioSeleccionado();

        limpiarInformacionPase();

        if (socio == null) {
            return;
        }

        Pase pase = paseData.buscarPaseActivo(socio.getIdSocio());

        if (pase == null) {
            txtPase.setText("Sin pase vigente");
            return;
        }

        if ("LIBRE".equals(pase.getTipoPase())) {

            txtPase.setText("Pase Libre");
            lblFechaVencimiento.setText("∞");

        } else {

            txtPase.setText("Pase Limitado");
            lblFechaVencimiento.setText(
                    String.valueOf(pase.getCantidadEntradas())
            );
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        lblEntradasDisponibles.setText(
                pase.getFechaFin().format(formato)
        );
    }

    private void cargarTablaAsistencias() {

        DefaultTableModel modelo
                = (DefaultTableModel) tablaAsistencias.getModel();

        modelo.setRowCount(0);

        DateTimeFormatter formato
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Asistencia asistencia : asistenciaData.listarAsistencias()) {

            int idSocio = asistencia.getSocio().getIdSocio();
            int idClase = asistencia.getClase().getIdClase();

            Socios socioEncontrado = null;
            Clases claseEncontrada = null;

            for (Socios socio : sociosDisponibles) {
                if (socio.getIdSocio() == idSocio) {
                    socioEncontrado = socio;
                    break;
                }
            }

            for (Clases clase : clasesDisponibles) {
                if (clase.getIdClase() == idClase) {
                    claseEncontrada = clase;
                    break;
                }
            }

            String nombreSocio = socioEncontrado != null
                    ? socioEncontrado.getApellido() + ", "
                    + socioEncontrado.getNombre()
                    : "Socio #" + idSocio;

            String dni = socioEncontrado != null
                    ? String.valueOf(socioEncontrado.getDni())
                    : "-";

            String nombreClase = claseEncontrada != null
                    ? claseEncontrada.getDisciplina()
                    : "Clase #" + idClase;

            modelo.addRow(new Object[]{
                asistencia.getIdAsistencia(),
                nombreSocio,
                dni,
                nombreClase,
                asistencia.getFechaAsistencia().format(formato)
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaPases = new javax.swing.JPanel();
        tituloAsistencia = new javax.swing.JLabel();
        lblSocio = new javax.swing.JLabel();
        lblClase = new javax.swing.JLabel();
        lblFechaDeInicio = new javax.swing.JLabel();
        lblEntradasDisponibles = new javax.swing.JLabel();
        txtPase = new javax.swing.JTextField();
        btnRegistrarAsistencia = new javax.swing.JButton();
        cmbSocio = new javax.swing.JComboBox<>();
        cmbClase = new javax.swing.JComboBox<>();
        lblCosto1 = new javax.swing.JLabel();
        lblCosto2 = new javax.swing.JLabel();
        dateChooserAsistencia = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAsistencias = new javax.swing.JTable();
        lblCosto3 = new javax.swing.JLabel();
        tituloAsistencia1 = new javax.swing.JLabel();
        lblFechaVencimiento = new javax.swing.JLabel();

        vistaPases.setBackground(new java.awt.Color(244, 245, 246));
        vistaPases.setName("panelPases"); // NOI18N

        tituloAsistencia.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        tituloAsistencia.setForeground(new java.awt.Color(51, 51, 51));
        tituloAsistencia.setText("Informacion del pase");

        lblSocio.setBackground(new java.awt.Color(244, 245, 246));
        lblSocio.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblSocio.setForeground(new java.awt.Color(51, 51, 51));
        lblSocio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSocio.setText("Socio");

        lblClase.setBackground(new java.awt.Color(244, 245, 246));
        lblClase.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblClase.setForeground(new java.awt.Color(51, 51, 51));
        lblClase.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblClase.setText("Clase");

        lblFechaDeInicio.setBackground(new java.awt.Color(244, 245, 246));
        lblFechaDeInicio.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblFechaDeInicio.setForeground(new java.awt.Color(51, 51, 51));
        lblFechaDeInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDeInicio.setText("Fecha de asistencia");

        lblEntradasDisponibles.setBackground(new java.awt.Color(244, 245, 246));
        lblEntradasDisponibles.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblEntradasDisponibles.setForeground(new java.awt.Color(0, 0, 0));
        lblEntradasDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEntradasDisponibles.setText("--/--/----");

        txtPase.setEditable(false);
        txtPase.setBackground(new java.awt.Color(255, 255, 255));
        txtPase.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPase.setForeground(new java.awt.Color(51, 51, 51));
        txtPase.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtPase.setCaretColor(new java.awt.Color(132, 184, 42));
        txtPase.setPreferredSize(new java.awt.Dimension(220, 30));
        txtPase.addActionListener(this::txtPaseActionPerformed);

        btnRegistrarAsistencia.setBackground(new java.awt.Color(204, 255, 102));
        btnRegistrarAsistencia.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnRegistrarAsistencia.setForeground(new java.awt.Color(51, 51, 51));
        btnRegistrarAsistencia.setText("Registrar Asistencia");
        btnRegistrarAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarAsistencia.setFocusPainted(false);
        btnRegistrarAsistencia.setPreferredSize(new java.awt.Dimension(140, 40));
        btnRegistrarAsistencia.addActionListener(this::btnRegistrarAsistenciaActionPerformed);

        cmbSocio.setBackground(new java.awt.Color(255, 255, 255));
        cmbSocio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbSocio.setForeground(new java.awt.Color(48, 48, 48));
        cmbSocio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar socio..." }));
        cmbSocio.setName("Seleccionar entrenador"); // NOI18N
        cmbSocio.addActionListener(this::cmbSocioActionPerformed);

        cmbClase.setBackground(new java.awt.Color(255, 255, 255));
        cmbClase.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbClase.setForeground(new java.awt.Color(48, 48, 48));
        cmbClase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "12", "20", "Pase Libre" }));
        cmbClase.setToolTipText("");
        cmbClase.setName("Seleccionar entrenador"); // NOI18N
        cmbClase.addActionListener(this::cmbClaseActionPerformed);

        lblCosto1.setBackground(new java.awt.Color(244, 245, 246));
        lblCosto1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblCosto1.setForeground(new java.awt.Color(51, 51, 51));
        lblCosto1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCosto1.setText("Pase");

        lblCosto2.setBackground(new java.awt.Color(244, 245, 246));
        lblCosto2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblCosto2.setForeground(new java.awt.Color(51, 51, 51));
        lblCosto2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCosto2.setText("Entradas disponibles");

        dateChooserAsistencia.addPropertyChangeListener(this::dateChooserAsistencia);

        tablaAsistencias.setBackground(new java.awt.Color(204, 204, 204));
        tablaAsistencias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaAsistencias.setForeground(new java.awt.Color(51, 51, 51));
        tablaAsistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Socio", "DNI", "Clase", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAsistencias.setGridColor(new java.awt.Color(51, 51, 51));
        tablaAsistencias.setRowHeight(28);
        tablaAsistencias.setSelectionBackground(new java.awt.Color(204, 255, 102));
        tablaAsistencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsistenciasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAsistencias);

        lblCosto3.setBackground(new java.awt.Color(244, 245, 246));
        lblCosto3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblCosto3.setForeground(new java.awt.Color(51, 51, 51));
        lblCosto3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCosto3.setText("Fecha de vencimiento");

        tituloAsistencia1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        tituloAsistencia1.setForeground(new java.awt.Color(51, 51, 51));
        tituloAsistencia1.setText("Gestion de asistencias");

        lblFechaVencimiento.setBackground(new java.awt.Color(244, 245, 246));
        lblFechaVencimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblFechaVencimiento.setForeground(new java.awt.Color(255, 51, 51));
        lblFechaVencimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaVencimiento.setText("--");

        javax.swing.GroupLayout vistaPasesLayout = new javax.swing.GroupLayout(vistaPases);
        vistaPases.setLayout(vistaPasesLayout);
        vistaPasesLayout.setHorizontalGroup(
            vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaPasesLayout.createSequentialGroup()
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(tituloAsistencia1))
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSocio)))
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblClase)
                            .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(vistaPasesLayout.createSequentialGroup()
                                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateChooserAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFechaDeInicio))
                                .addGap(37, 37, 37)
                                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCosto1)
                                    .addComponent(txtPase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(vistaPasesLayout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCosto3)
                                    .addComponent(lblCosto2))
                                .addGap(18, 18, 18)
                                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEntradasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(vistaPasesLayout.createSequentialGroup()
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(tituloAsistencia))
                    .addGroup(vistaPasesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegistrarAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        vistaPasesLayout.setVerticalGroup(
            vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaPasesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tituloAsistencia1)
                .addGap(20, 20, 20)
                .addComponent(lblSocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblClase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto1)
                    .addComponent(lblFechaDeInicio))
                .addGap(18, 18, 18)
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(tituloAsistencia)
                .addGap(25, 25, 25)
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto2)
                    .addComponent(lblFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vistaPasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto3)
                    .addComponent(lblEntradasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(vistaPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(vistaPases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaseActionPerformed

    private void btnRegistrarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAsistenciaActionPerformed

        Socios socio = obtenerSocioSeleccionado();
        Clases clase = obtenerClaseSeleccionada();

        if (socio == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná un socio.");
            return;
        }

        if (clase == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná una clase.");
            return;
        }

        if (dateChooserAsistencia.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná la fecha de asistencia.");
            return;
        }

        LocalDate fecha = dateChooserAsistencia.getDate()
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        Asistencia asistencia = new Asistencia();
        asistencia.setSocio(socio);
        asistencia.setClase(clase);
        asistencia.setFechaAsistencia(fecha);

        boolean registrada = asistenciaData.guardarAsistencia(asistencia);

        if (registrada) {

            JOptionPane.showMessageDialog(this,
                    "Asistencia registrada correctamente.");

            cargarTablaAsistencias();
            actualizarInformacionPase();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo registrar la asistencia. "
                    + "Verificá que el socio tenga un pase vigente, "
                    + "que haya cupo y que no esté registrada previamente.");
        }

    }//GEN-LAST:event_btnRegistrarAsistenciaActionPerformed

    private void cmbSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSocioActionPerformed
        actualizarInformacionPase();
    }//GEN-LAST:event_cmbSocioActionPerformed

    private void cmbClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClaseActionPerformed

    }//GEN-LAST:event_cmbClaseActionPerformed

    private void dateChooserAsistencia(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserAsistencia
        if ("date".equals(evt.getPropertyName())) {
            actualizarInformacionPase();
        }
    }//GEN-LAST:event_dateChooserAsistencia

    private void tablaAsistenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsistenciasMouseClicked


    }//GEN-LAST:event_tablaAsistenciasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarAsistencia;
    private javax.swing.JComboBox<String> cmbClase;
    private javax.swing.JComboBox<String> cmbSocio;
    private com.toedter.calendar.JDateChooser dateChooserAsistencia;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClase;
    private javax.swing.JLabel lblCosto1;
    private javax.swing.JLabel lblCosto2;
    private javax.swing.JLabel lblCosto3;
    private javax.swing.JLabel lblEntradasDisponibles;
    private javax.swing.JLabel lblFechaDeInicio;
    private javax.swing.JLabel lblFechaVencimiento;
    private javax.swing.JLabel lblSocio;
    private javax.swing.JTable tablaAsistencias;
    private javax.swing.JLabel tituloAsistencia;
    private javax.swing.JLabel tituloAsistencia1;
    private javax.swing.JTextField txtPase;
    private javax.swing.JPanel vistaPases;
    // End of variables declaration//GEN-END:variables
}
