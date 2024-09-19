package trabajopractico6bEntrega;

import ServicioProducto.Producto;
import java.awt.Component;
import java.util.ArrayList;
import java.util.FormatterClosedException;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.DefaultButtonModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import trabajopractico6bEntrega.Principal;

public class ConsultaDEproducto extends JInternalFrameImagen {

    private DefaultTableModel tabProducto;
    private Principal principal; //singleton 

    public ConsultaDEproducto() {
        this.principal = Principal.getInstancia();  // para obtener la instancia única de Principal
        initComponents();
        this.setSize(785, 650);
        this.setImagen("/imagen/FondoAzulConFlechas.png");
        this.tabProducto = new DefaultTableModel();
        tabProducto.addColumn("Codigo");
        tabProducto.addColumn("Descripcion");
        tabProducto.addColumn("Precio");
        tabProducto.addColumn("Categoria");
        tabProducto.addColumn("Stock");

        // No permitir la edición de las celdas
        jTableProducto.setDefaultEditor(Object.class, null);
        jTableProducto.setModel(tabProducto);
        //para ver los productos en la tabla
        actualizarTodosProductos();
    }

    public void nuevoProducto() {
        try {

            if (verificarCamposLlenos()) {
                String codigo = jTextCodigoProducto.getText();
                String descripcion = jTextDescripcionProducto.getText();
                Double precio = Double.parseDouble(jTextPrecioProducto.getText()); // Puede lanzar NumberFormatException
                String rubro = (String) jComboboxRubroProducto.getSelectedItem();
                Integer stock = (Integer) jSpinnerProducto.getValue();
                Producto nuevoProducto = new Producto(codigo, descripcion, precio, rubro, stock);

                if (principal.getConjuntoProducto().add(nuevoProducto)) {
                    JOptionPane.showMessageDialog(this, "El producto fue agregado correctamente", "Verificación de datos", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTodosProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "El producto se encuentra repetido", "Verificación de datos", JOptionPane.ERROR_MESSAGE);
                    vaciarCampos(jPanelProducto);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos vacíos, verifique", "Error en los datos", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar el precio, verifique que sea un número válido", "Error en los datos", JOptionPane.ERROR_MESSAGE);
        } catch (FormatterClosedException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos, verifique", "Error en los datos", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error en los datos", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void vaciarCampos(JPanel jPanelProducto) {
        for (Component c : jPanelProducto.getComponents()) {
            if (c instanceof JTextField) {
                JTextField caja = (JTextField) c;
                caja.setText("");  // Vaciar campo de texto
            } else if (c instanceof JComboBox) {
                JComboBox combo = (JComboBox) c;
                combo.setSelectedIndex(-1);  // Resetear combo box
            } else if (c instanceof JSpinner) {
                JSpinner spinner = (JSpinner) c;
                spinner.setValue(1);
            }
        }

    }

    public boolean verificarCamposLlenos() {

        for (Component c : jPanelProducto.getComponents()) {
            if (c instanceof JTextField) {
                JTextField caja = (JTextField) c;
                if (caja.getText().trim().isEmpty()) {
                    return false; // texte vacio
                }
            } else if (c instanceof JComboBox) {
                JComboBox combo = (JComboBox) c;
                if (combo.getSelectedIndex() == -1) {
                    return false; // combo box vacio
                }
            } else if (c instanceof JSpinner) {
                JSpinner spinner = (JSpinner) c;
                Object value = spinner.getValue();
                if (value == null || Integer.parseInt(value.toString()) <= 0) {
                    return false; // spinner vacio
                }
            }
        }
        return true; // Todos los campos están llenos
    }

    public void actualizarTodosProductos() {
        tabProducto.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
        for (Producto producto : principal.getConjuntoProducto()) {
            // Agregar una fila a la tabla con los datos del producto
            tabProducto.addRow(new Object[]{
                producto.getCodigo(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getRubro(),
                producto.getStock()
            });
        }
    }

    public void mostrarProductosComboBox() {
        String categoriaSeleccionada = (String) jComboBoxProducto.getSelectedItem();
        tabProducto.setRowCount(0);  // Limpiar la tabla actual

        for (Producto producto : principal.getConjuntoProducto()) {
            // Filtrar productos por la categoría seleccionada
            if (categoriaSeleccionada == null || producto.getRubro().equals(categoriaSeleccionada)) {
                // Agregar una fila a la tabla para cada producto
                tabProducto.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getRubro(),
                    producto.getStock()
                });
                
            }else if (categoriaSeleccionada.equalsIgnoreCase("Todos los productos")) {
                    actualizarTodosProductos();
                    
                    }
        }

    }

    public void borrarProducto() {
        int filaSeleccionada = jTableProducto.getSelectedRow();

        if (filaSeleccionada != -1) { // Verificar si se ha seleccionado alguna fila
            // Eliminar el producto del conjunto usando un iterador
            String codigo = (String) jTableProducto.getValueAt(filaSeleccionada, 0);
            Iterator<Producto> iterador = principal.getConjuntoProducto().iterator();
            while (iterador.hasNext()) {
                Producto producto = iterador.next();
                if (producto.getCodigo().equals(codigo)) {
                    iterador.remove();
                    mostrarProductosComboBox();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void confirmarSalir(){
         int respuesta = JOptionPane.showConfirmDialog(this,"¿Deseas salir de la aplicación?","Confirmar salida",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0); 
        }
    }

    public void actualizarConButton() {
        int filaSeleccionada = jTableProducto.getSelectedRow();

        if (filaSeleccionada != -1) { // Verificar si se ha seleccionado alguna fila
            if (verificarCamposLlenos()) {
                try {
                    // Obtener los valores de los campos de entrada
                    String codigo = jTextCodigoProducto.getText();
                    String descripcion = jTextDescripcionProducto.getText();
                    Double precio = Double.valueOf(jTextPrecioProducto.getText());
                    String rubro = (String) jComboboxRubroProducto.getSelectedItem();
                    Integer stock = (Integer) jSpinnerProducto.getValue();

                    // Actualizar los valores en la tabla
                    tabProducto.setValueAt(codigo, filaSeleccionada, 0);
                    tabProducto.setValueAt(descripcion, filaSeleccionada, 1);
                    tabProducto.setValueAt(precio, filaSeleccionada, 2);
                    tabProducto.setValueAt(rubro, filaSeleccionada, 3);
                    tabProducto.setValueAt(stock, filaSeleccionada, 4);

                    JOptionPane.showMessageDialog(this, "Producto actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El precio ingresado no es válido", "Error en los datos", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos vacíos, por favor verifique", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para actualizar", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBnuevoProducto = new javax.swing.JButton();
        jPanelProducto = new javax.swing.JPanel();
        jLCodigoProducto = new javax.swing.JLabel();
        jLrubroProducto = new javax.swing.JLabel();
        jLdescripcionProducto = new javax.swing.JLabel();
        jLStockProducto = new javax.swing.JLabel();
        jLPrecioProducto = new javax.swing.JLabel();
        jTextDescripcionProducto = new javax.swing.JTextField();
        jTextCodigoProducto = new javax.swing.JTextField();
        jTextPrecioProducto = new javax.swing.JTextField();
        jSpinnerProducto = new javax.swing.JSpinner();
        jComboboxRubroProducto = new javax.swing.JComboBox<>();
        jBbuscarProducto = new javax.swing.JButton();
        jBsalirProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProducto = new javax.swing.JTable();
        jBguardaProducto = new javax.swing.JButton();
        jBactualizarProducto = new javax.swing.JButton();
        jBeliminarProducto = new javax.swing.JButton();
        jLbuscarProducto = new javax.swing.JLabel();
        jLtituloProducto = new javax.swing.JLabel();
        jComboBoxProducto = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jBnuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file_8524457.png"))); // NOI18N
        jBnuevoProducto.setText("Nuevo");
        jBnuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnuevoProductoActionPerformed(evt);
            }
        });

        jPanelProducto.setBackground(new java.awt.Color(204, 204, 255));

        jLCodigoProducto.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLCodigoProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLCodigoProducto.setText("Codigo");

        jLrubroProducto.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLrubroProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLrubroProducto.setText("Rubro");

        jLdescripcionProducto.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLdescripcionProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLdescripcionProducto.setText("Descripcion");

        jLStockProducto.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLStockProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLStockProducto.setText("Stock");

        jLPrecioProducto.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLPrecioProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLPrecioProducto.setText("Precio");

        jTextCodigoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboboxRubroProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comestible", "Limpieza ", "Perfumería" }));

        jBbuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LUPA_CHICA.png"))); // NOI18N
        jBbuscarProducto.setText("Buscar");
        jBbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jBsalirProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Exit-Page_icon-icons.com_53732.png"))); // NOI18N
        jBsalirProducto.setText("Cerrar");
        jBsalirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalirProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProductoLayout = new javax.swing.GroupLayout(jPanelProducto);
        jPanelProducto.setLayout(jPanelProductoLayout);
        jPanelProductoLayout.setHorizontalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCodigoProducto)
                    .addComponent(jLPrecioProducto)
                    .addComponent(jLdescripcionProducto))
                .addGap(26, 26, 26)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelProductoLayout.createSequentialGroup()
                                .addComponent(jLrubroProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboboxRubroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProductoLayout.createSequentialGroup()
                                .addComponent(jLStockProducto)
                                .addGap(57, 57, 57)
                                .addComponent(jSpinnerProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addComponent(jBbuscarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBsalirProducto))
                    .addComponent(jTextDescripcionProducto))
                .addGap(40, 40, 40))
        );
        jPanelProductoLayout.setVerticalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCodigoProducto)
                            .addComponent(jComboboxRubroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLrubroProducto)))
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLStockProducto)
                            .addComponent(jSpinnerProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLPrecioProducto))))
                .addGap(18, 18, 18)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdescripcionProducto)
                    .addComponent(jTextDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBsalirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBbuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
        );

        jTableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableProducto);

        jBguardaProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/load_7037283.png"))); // NOI18N
        jBguardaProducto.setText("Guardar");
        jBguardaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jBactualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cycle_11044764.png"))); // NOI18N
        jBactualizarProducto.setText("Actualizar");
        jBactualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarProductoActionPerformed(evt);
            }
        });

        jBeliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete_4980658.png"))); // NOI18N
        jBeliminarProducto.setText("Eliminar");
        jBeliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeliminarProductoActionPerformed(evt);
            }
        });

        jLbuscarProducto.setBackground(new java.awt.Color(204, 204, 204));
        jLbuscarProducto.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLbuscarProducto.setForeground(new java.awt.Color(204, 204, 204));
        jLbuscarProducto.setText("Filtrar por categoria");

        jLtituloProducto.setBackground(new java.awt.Color(255, 255, 255));
        jLtituloProducto.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLtituloProducto.setForeground(new java.awt.Color(204, 204, 204));
        jLtituloProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/market-penetration_16137262.png"))); // NOI18N
        jLtituloProducto.setText("Gestion de Productos");

        jComboBoxProducto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los productos", "Comestible", "Limpieza ", "Perfumería" }));
        jComboBoxProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProductoActionPerformed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLtituloProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBguardaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBnuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLbuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jComboBoxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBactualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBeliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLtituloProducto)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbuscarProducto)
                    .addComponent(jComboBoxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBeliminarProducto)
                    .addComponent(jBactualizarProducto)
                    .addComponent(jBguardaProducto)
                    .addComponent(jBnuevoProducto))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        mostrarProductosComboBox();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        nuevoProducto();
        actualizarTodosProductos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBnuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnuevoProductoActionPerformed
        vaciarCampos(jPanelProducto);
    }//GEN-LAST:event_jBnuevoProductoActionPerformed

    private void jBactualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarProductoActionPerformed
        actualizarConButton();
    }//GEN-LAST:event_jBactualizarProductoActionPerformed

    private void jBsalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalirProductoActionPerformed
       confirmarSalir();
    }//GEN-LAST:event_jBsalirProductoActionPerformed

    private void jBeliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeliminarProductoActionPerformed
        borrarProducto();
    }//GEN-LAST:event_jBeliminarProductoActionPerformed

    private void jComboBoxProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBactualizarProducto;
    private javax.swing.JButton jBbuscarProducto;
    private javax.swing.JButton jBeliminarProducto;
    private javax.swing.JButton jBguardaProducto;
    private javax.swing.JButton jBnuevoProducto;
    private javax.swing.JButton jBsalirProducto;
    private javax.swing.JComboBox<String> jComboBoxProducto;
    private javax.swing.JComboBox<String> jComboboxRubroProducto;
    private javax.swing.JLabel jLCodigoProducto;
    private javax.swing.JLabel jLPrecioProducto;
    private javax.swing.JLabel jLStockProducto;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLbuscarProducto;
    private javax.swing.JLabel jLdescripcionProducto;
    private javax.swing.JLabel jLrubroProducto;
    private javax.swing.JLabel jLtituloProducto;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerProducto;
    private javax.swing.JTable jTableProducto;
    private javax.swing.JTextField jTextCodigoProducto;
    private javax.swing.JTextField jTextDescripcionProducto;
    private javax.swing.JTextField jTextPrecioProducto;
    // End of variables declaration//GEN-END:variables
}
