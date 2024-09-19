/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package trabajopractico6bEntrega;

import ServicioProducto.Producto;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import trabajopractico6bEntrega.Principal;

/**
 *
 * @author Personal
 */
public class ConsultaPorRubro extends JInternalFrameImagen {

    private Principal principal;
    private DefaultTableModel TbRubro;
    
    public ConsultaPorRubro() {
        this.principal = Principal.getInstancia();
        initComponents();
        this.setSize(785, 700);
        this.setImagen("/imagen/FondoAzulRombo.png");
        this.TbRubro = new DefaultTableModel();
        TbRubro.addColumn("Codigo");
        TbRubro.addColumn("Descripcion");
        TbRubro.addColumn("Precio");
        TbRubro.addColumn("Categoria");
        TbRubro.addColumn("Stock");
        jTableRubro.setModel(TbRubro);
        jTableRubro.setDefaultEditor(Object.class, null);
        llenarTabla();
        
    }

    public void llenarTabla() {
        TbRubro.setRowCount(0); 
        if (principal.getConjuntoProducto().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                TbRubro.addRow(new Object[]{
                "Lista vacia",
                 });
            }
            
        } else {
            for (Producto producto : principal.getConjuntoProducto()) {
                TbRubro.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getRubro(),
                    producto.getStock()
                });
            }
        }

    }
    public void buscarPorItems() {
    String items = jComboBoxRubro.getSelectedItem().toString().trim().toLowerCase();
    TbRubro.setRowCount(0);  
    boolean encontro = false;  
    
   
    for (Producto producto : principal.getConjuntoProducto()) {
        
        if (producto.getRubro().trim().toLowerCase().contains(items)) {
            
            TbRubro.addRow(new Object[]{
                producto.getCodigo(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getRubro(),
                producto.getStock()
            });
            encontro = true;  
        }
    }
    
    
    if (!encontro) {
        JOptionPane.showMessageDialog(this, "No se encontró un producto en el rubro " + items, "Buscar Producto", JOptionPane.WARNING_MESSAGE);
        llenarTabla();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLtituloRubro = new javax.swing.JLabel();
        jLtipoRubro = new javax.swing.JLabel();
        jComboBoxRubro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRubro = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLtituloRubro.setBackground(new java.awt.Color(255, 255, 255));
        jLtituloRubro.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLtituloRubro.setForeground(new java.awt.Color(255, 255, 255));
        jLtituloRubro.setText("Consulta por Rubro");

        jLtipoRubro.setBackground(new java.awt.Color(255, 255, 255));
        jLtipoRubro.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLtipoRubro.setForeground(new java.awt.Color(204, 204, 204));
        jLtipoRubro.setText("Tipo");

        jComboBoxRubro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comestible", "Limpieza ", "Perfumería" }));
        jComboBoxRubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxRubroItemStateChanged(evt);
            }
        });

        jTableRubro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableRubro);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLtituloRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLtipoRubro)
                        .addGap(94, 94, 94)
                        .addComponent(jComboBoxRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLtituloRubro)
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLtipoRubro)
                    .addComponent(jComboBoxRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRubroItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {  
        buscarPorItems();
    }
    }//GEN-LAST:event_jComboBoxRubroItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxRubro;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLtipoRubro;
    private javax.swing.JLabel jLtituloRubro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRubro;
    // End of variables declaration//GEN-END:variables
}
