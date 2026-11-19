package Vistas;

import entidades.Socios;
import persistencia.MiConexion;
import persistencia.SocioData;
import java.util.List;
import entidades.Pase;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import entidades.Pase;

public class VistaPases extends javax.swing.JInternalFrame {

    private SocioData socioData;
    private List<Socios> sociosDisponibles;
    private persistencia.PaseData paseData;
    private int idPaseSeleccionado = -1;

    public VistaPases(MiConexion conexion) {
        initComponents();

        socioData = new SocioData(conexion);
        paseData = new persistencia.PaseData(conexion);

        cargarSocios();
        cargarTablaPases();
        txtCosto.setEditable(false);
    }

    private void cargarSocios() {
        cmbSocio.removeAllItems();
        cmbSocio.addItem("Seleccionar socio...");

        sociosDisponibles = socioData.listarSocios();

        for (Socios socio : sociosDisponibles) {
            if (socio.isEstado()) {
                cmbSocio.addItem(
                        socio.getApellido() + ", "
                        + socio.getNombre() + " - DNI: "
                        + socio.getDni()
                );
            }
        }
    }

    private void calcularVencimiento() {
        java.util.Date fechaSeleccionada = dateChooserInicio.getDate();

        if (fechaSeleccionada == null) {
            lblVencimientoValor.setText("--/--/----");
            return;
        }

        java.time.LocalDate fechaInicio = fechaSeleccionada.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        java.time.LocalDate fechaVencimiento = fechaInicio.plusDays(30);

        lblVencimientoValor.setText(
                fechaVencimiento.format(
                        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                )
        );
    }

    private void cargarTablaPases() {

        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Pase pase : paseData.listarPases()) {

            modelo.addRow(new Object[]{
                pase.getIdPase(),
                pase.getSocio().getApellido() + ", " + pase.getSocio().getNombre(),
                pase.getSocio().getDni(),
                pase.getTipoPase().equals("LIBRE")
                    ? "∞" : pase.getCantidadEntradas(),
                pase.getFechaInicio().format(formato),
                pase.getFechaFin().format(formato),
                pase.getCosto(),
                pase.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaPases = new javax.swing.JPanel();
        tituloPases = new javax.swing.JLabel();
        lblSocio = new javax.swing.JLabel();
        lblEntradas = new javax.swing.JLabel();
        lblFechaDeInicio = new javax.swing.JLabel();
        lblVencimientoValor = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        btnGuardarPase = new javax.swing.JButton();
        btnLimpiar2 = new javax.swing.JButton();
        btnActualizar2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnBaja2 = new javax.swing.JButton();
        cmbSocio = new javax.swing.JComboBox<>();
        cmbEntradas = new javax.swing.JComboBox<>();
        lblCosto1 = new javax.swing.JLabel();
        lblCosto2 = new javax.swing.JLabel();
        dateChooserInicio = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(650, 450));

        vistaPases.setBackground(new java.awt.Color(244, 245, 246));
        vistaPases.setName("panelPases"); // NOI18N
        vistaPases.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloPases.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        tituloPases.setForeground(new java.awt.Color(51, 51, 51));
        tituloPases.setText("Gestion de pases");
        vistaPases.add(tituloPases, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 12, -1, -1));

        lblSocio.setBackground(new java.awt.Color(244, 245, 246));
        lblSocio.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblSocio.setForeground(new java.awt.Color(51, 51, 51));
        lblSocio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSocio.setText("Socio");
        vistaPases.add(lblSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 61, -1, -1));

        lblEntradas.setBackground(new java.awt.Color(244, 245, 246));
        lblEntradas.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblEntradas.setForeground(new java.awt.Color(51, 51, 51));
        lblEntradas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEntradas.setText("Entradas");
        vistaPases.add(lblEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 61, -1, -1));

        lblFechaDeInicio.setBackground(new java.awt.Color(244, 245, 246));
        lblFechaDeInicio.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblFechaDeInicio.setForeground(new java.awt.Color(51, 51, 51));
        lblFechaDeInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDeInicio.setText("Fecha de inicio");
        vistaPases.add(lblFechaDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 134, -1, -1));

        lblVencimientoValor.setBackground(new java.awt.Color(244, 245, 246));
        lblVencimientoValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblVencimientoValor.setForeground(new java.awt.Color(51, 51, 51));
        lblVencimientoValor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVencimientoValor.setText("--/--/----");
        vistaPases.add(lblVencimientoValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 90, 20));

        txtCosto.setBackground(new java.awt.Color(255, 255, 255));
        txtCosto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCosto.setForeground(new java.awt.Color(51, 51, 51));
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 220, 227)));
        txtCosto.setCaretColor(new java.awt.Color(132, 184, 42));
        txtCosto.setPreferredSize(new java.awt.Dimension(220, 30));
        txtCosto.addActionListener(this::txtCostoActionPerformed);
        vistaPases.add(txtCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 156, 290, -1));

        btnGuardarPase.setBackground(new java.awt.Color(204, 255, 102));
        btnGuardarPase.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarPase.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardarPase.setText("Guardar pase");
        btnGuardarPase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPase.setFocusPainted(false);
        btnGuardarPase.setPreferredSize(new java.awt.Dimension(140, 40));
        btnGuardarPase.addActionListener(this::btnGuardarPaseActionPerformed);
        vistaPases.add(btnGuardarPase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        btnLimpiar2.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar2.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar2.setText("Limpiar");
        btnLimpiar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar2.setFocusPainted(false);
        btnLimpiar2.setPreferredSize(new java.awt.Dimension(140, 40));
        btnLimpiar2.addActionListener(this::btnLimpiar2ActionPerformed);
        vistaPases.add(btnLimpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        btnActualizar2.setBackground(new java.awt.Color(51, 51, 51));
        btnActualizar2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnActualizar2.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar2.setText("Actualizar");
        btnActualizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar2.setFocusPainted(false);
        btnActualizar2.setPreferredSize(new java.awt.Dimension(140, 40));
        btnActualizar2.addActionListener(this::btnActualizar2ActionPerformed);
        vistaPases.add(btnActualizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        jTable3.setBackground(new java.awt.Color(204, 204, 204));
        jTable3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(51, 51, 51));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Socio", "DNI", "Entradas", "Fecha Inicio", "Vencimiento", "Costo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(51, 51, 51));
        jTable3.setRowHeight(28);
        jTable3.setSelectionBackground(new java.awt.Color(204, 255, 102));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        vistaPases.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 286, 617, -1));

        btnBaja2.setBackground(new java.awt.Color(51, 51, 51));
        btnBaja2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBaja2.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja2.setText("Dar de baja");
        btnBaja2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaja2.setFocusPainted(false);
        btnBaja2.setPreferredSize(new java.awt.Dimension(140, 40));
        btnBaja2.addActionListener(this::btnBaja2ActionPerformed);
        vistaPases.add(btnBaja2, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 723, -1, -1));

        cmbSocio.setBackground(new java.awt.Color(255, 255, 255));
        cmbSocio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbSocio.setForeground(new java.awt.Color(48, 48, 48));
        cmbSocio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar socio..." }));
        cmbSocio.setName("Seleccionar entrenador"); // NOI18N
        cmbSocio.addActionListener(this::cmbSocioActionPerformed);
        vistaPases.add(cmbSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 83, 290, 36));

        cmbEntradas.setBackground(new java.awt.Color(255, 255, 255));
        cmbEntradas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbEntradas.setForeground(new java.awt.Color(48, 48, 48));
        cmbEntradas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "12", "20", "Pase Libre" }));
        cmbEntradas.setToolTipText("");
        cmbEntradas.setName("Seleccionar entrenador"); // NOI18N
        cmbEntradas.addActionListener(this::cmbEntradasActionPerformed);
        vistaPases.add(cmbEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 83, 290, 36));

        lblCosto1.setBackground(new java.awt.Color(244, 245, 246));
        lblCosto1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblCosto1.setForeground(new java.awt.Color(51, 51, 51));
        lblCosto1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCosto1.setText("Costo");
        vistaPases.add(lblCosto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 134, -1, -1));

        lblCosto2.setBackground(new java.awt.Color(244, 245, 246));
        lblCosto2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblCosto2.setForeground(new java.awt.Color(51, 51, 51));
        lblCosto2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCosto2.setText("Feha de vencimiento");
        vistaPases.add(lblCosto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        dateChooserInicio.addPropertyChangeListener(this::dateChooserInicio);
        vistaPases.add(dateChooserInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 156, 290, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(vistaPases, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vistaPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntradasActionPerformed
        String plan = cmbEntradas.getSelectedItem().toString();

        switch (plan) {
            case "8":
                txtCosto.setText("38000");
                break;

            case "12":
                txtCosto.setText("48000");
                break;

            case "20":
                txtCosto.setText("65000");
                break;

            case "Pase Libre":
                txtCosto.setText("80000");
                break;
        }
    }//GEN-LAST:event_cmbEntradasActionPerformed

    private void cmbSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSocioActionPerformed

    private void btnBaja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja2ActionPerformed

        if (idPaseSeleccionado == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un pase de la tabla.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas dar de baja este pase?",
                "Confirmar baja",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            boolean eliminado = paseData.borrarPase(idPaseSeleccionado);

            if (eliminado) {
                JOptionPane.showMessageDialog(this,
                        "Pase dado de baja correctamente.");

                cargarTablaPases();
                idPaseSeleccionado = -1;

            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo dar de baja el pase.");
            }
        }

    }//GEN-LAST:event_btnBaja2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        int fila = jTable3.getSelectedRow();

        if (fila == -1) {
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();

        idPaseSeleccionado = Integer.parseInt(
                modelo.getValueAt(fila, 0).toString()
        );

        // Seleccionar socio
        String socioTexto = modelo.getValueAt(fila, 1).toString();
        String dni = modelo.getValueAt(fila, 2).toString();

        for (Socios socio : sociosDisponibles) {
            String nombreCompleto = socio.getApellido() + ", "
                    + socio.getNombre();

            if (socio.isEstado()
                    && nombreCompleto.equals(socioTexto)
                    && String.valueOf(socio.getDni()).equals(dni)) {

                String opcion = nombreCompleto + " - DNI: " + dni;
                cmbSocio.setSelectedItem(opcion);
                break;
            }
        }

        // Seleccionar plan
        String entradas = modelo.getValueAt(fila, 3).toString();

        if (entradas.equals("∞")) {
            cmbEntradas.setSelectedItem("Pase Libre");
        } else {
            cmbEntradas.setSelectedItem(entradas);
        }

        // Cargar fecha de inicio
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate fecha = LocalDate.parse(
                modelo.getValueAt(fila, 4).toString(), formato
        );

        dateChooserInicio.setDate(java.sql.Date.valueOf(fecha));

        // Cargar vencimiento
        lblVencimientoValor.setText(
                modelo.getValueAt(fila, 5).toString()
        );

        // Cargar costo
        txtCosto.setText(modelo.getValueAt(fila, 6).toString());

    }//GEN-LAST:event_jTable3MouseClicked

    private void btnActualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar2ActionPerformed

        if (idPaseSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un pase de la tabla.");
            return;
        }

        int indiceSocio = cmbSocio.getSelectedIndex();

        if (indiceSocio == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un socio.");
            return;
        }

        if (dateChooserInicio.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona la fecha de inicio.");
            return;
        }

        String socioTexto = cmbSocio.getSelectedItem().toString();
        Socios socioSeleccionado = null;

        for (Socios socio : sociosDisponibles) {
            String nombreCompleto = socio.getApellido() + ", "
                    + socio.getNombre() + " - DNI: " + socio.getDni();

            if (socio.isEstado() && nombreCompleto.equals(socioTexto)) {
                socioSeleccionado = socio;
                break;
            }
        }

        if (socioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No se pudo identificar el socio.");
            return;
        }

        String plan = cmbEntradas.getSelectedItem().toString();

        int entradas;
        String tipoPase;

        if (plan.equals("Pase Libre")) {
            entradas = 0;
            tipoPase = "LIBRE";
        } else {
            entradas = Integer.parseInt(plan);
            tipoPase = "LIMITADO";
        }

        double costo;

        try {
            costo = Double.parseDouble(txtCosto.getText().trim().replace(",", "."));

            if (costo <= 0) {
                JOptionPane.showMessageDialog(this, "Ingresa un costo mayor a cero.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El costo no es válido.");
            return;
        }

        LocalDate fechaInicio = dateChooserInicio.getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        Pase pase = new Pase();
        pase.setIdPase(idPaseSeleccionado);
        pase.setSocio(socioSeleccionado);
        pase.setCantidadEntradas(entradas);
        pase.setTipoPase(tipoPase);
        pase.setFechaInicio(fechaInicio);
        pase.setFechaFin(fechaInicio.plusDays(30));
        pase.setCosto(costo);
        pase.setEstado(true);

        boolean actualizado = paseData.actualizarPase(pase);

        if (actualizado) {
            JOptionPane.showMessageDialog(this,
                    "Pase actualizado correctamente.");

            cargarTablaPases();
            idPaseSeleccionado = -1;
            jTable3.clearSelection();

        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar el pase. Revisa la consola.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnActualizar2ActionPerformed

    private void btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar2ActionPerformed


    // Desmarcar el pase seleccionado
    idPaseSeleccionado = -1;
    jTable3.clearSelection();

    // Restablecer socio
    cmbSocio.setSelectedIndex(0);

    // Restablecer plan
    cmbEntradas.setSelectedIndex(0);

    // Limpiar fecha
    dateChooserInicio.setDate(null);

    // Limpiar costo y vencimiento
    txtCosto.setText("");
    lblVencimientoValor.setText("--/--/----");

    }//GEN-LAST:event_btnLimpiar2ActionPerformed

    private void btnGuardarPaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPaseActionPerformed

         int indiceSocio = cmbSocio.getSelectedIndex();

        if (indiceSocio == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un socio");
            return;
        }

        if (dateChooserInicio.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona la fecha de inicio");
            return;
        }

        if (txtCosto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un plan");
            return;
        }

        String socioTexto = cmbSocio.getSelectedItem().toString();
        Socios socioSeleccionado = null;

        for (Socios socio : sociosDisponibles) {
            String nombreCompleto = socio.getApellido() + ", "
                    + socio.getNombre() + " - DNI: " + socio.getDni();

            if (socio.isEstado() && nombreCompleto.equals(socioTexto)) {
                socioSeleccionado = socio;
                break;
            }
        }

        if (socioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No se pudo identificar el socio.");
            return;
        }

        String plan = cmbEntradas.getSelectedItem().toString();

        int entradas;
        String tipoPase;

        if (plan.equals("Pase Libre")) {
            entradas = 0;
            tipoPase = "LIBRE";
        } else {
            entradas = Integer.parseInt(plan);
            tipoPase = "LIMITADO";
        }

        double costo;

        try {
            costo = Double.parseDouble(txtCosto.getText().trim().replace(",", "."));

            if (costo <= 0) {
                JOptionPane.showMessageDialog(this, "Ingresa un costo mayor a cero.");
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un costo valido.");
            return;
        }

        LocalDate fechaInicio = dateChooserInicio.getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        Pase pase = new Pase();
        pase.setSocio(socioSeleccionado);
        pase.setCantidadEntradas(entradas);
        pase.setTipoPase(tipoPase);
        pase.setFechaInicio(fechaInicio);
        pase.setFechaFin(fechaInicio.plusDays(30));
        pase.setCosto(costo);
        pase.setEstado(true);

        paseData.guardarPase(pase);

        if (pase.getIdPase() > 0) {
            JOptionPane.showMessageDialog(this, "Pase guardado correctamente.");
            cargarTablaPases();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el pase. Revisa la consola.");

        }

    }//GEN-LAST:event_btnGuardarPaseActionPerformed

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoActionPerformed

    private void dateChooserInicio(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserInicio
        calcularVencimiento();
    }//GEN-LAST:event_dateChooserInicio


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar2;
    private javax.swing.JButton btnBaja2;
    private javax.swing.JButton btnGuardarPase;
    private javax.swing.JButton btnLimpiar2;
    private javax.swing.JComboBox<String> cmbEntradas;
    private javax.swing.JComboBox<String> cmbSocio;
    private com.toedter.calendar.JDateChooser dateChooserInicio;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblCosto1;
    private javax.swing.JLabel lblCosto2;
    private javax.swing.JLabel lblEntradas;
    private javax.swing.JLabel lblFechaDeInicio;
    private javax.swing.JLabel lblSocio;
    private javax.swing.JLabel lblVencimientoValor;
    private javax.swing.JLabel tituloPases;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JPanel vistaPases;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPanel getPanelPases() {
        return vistaPases;
    }

}
