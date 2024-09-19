/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package trabajopractico6bEntrega;

import ServicioProducto.Producto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import trabajopractico6bEntrega.Principal;

/**
 *
 * @author Personal
 */
public class ConsultaPorNombre extends JInternalFrameImagen {

    private Principal principal;
    private DefaultTableModel TbNombre;

    public ConsultaPorNombre() {
        this.principal = Principal.getInstancia();
        initComponents();
        this.setSize(785, 700);
        this.setImagen("/imagen/FondoAzulConFlechas.png");
        this.TbNombre = new DefaultTableModel();
        //Armamos la tabla
        TbNombre.addColumn("Codigo");
        TbNombre.addColumn("Descripcion");
        TbNombre.addColumn("Precio");
        TbNombre.addColumn("Categoria");
        TbNombre.addColumn("Stock");
        jTableNombre.setDefaultEditor(Object.class, null);
        jTableNombre.setModel(TbNombre);
        llenarTabla();

    }

    public void llenarTabla() {
        TbNombre.setRowCount(0); 
        if (principal.getConjuntoProducto().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                TbNombre.addRow(new Object[]{
                "Lista vacia",
                 });
            }
            
        } else {
            for (Producto producto : principal.getConjuntoProducto()) {
                TbNombre.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getRubro(),
                    producto.getStock()
                });
            }
        }

    }
   public void buscarPorDescripcion() {
    String descripcion = jTbuscarPorNombre.getText().trim().toLowerCase();  // Convertir a minúsculas y eliminar espacios en blanco
    TbNombre.setRowCount(0);  
    boolean encontro = false;

    if (!descripcion.isEmpty()) {
        
        for (Producto producto : principal.getConjuntoProducto()) {
            if (producto.getDescripcion().toLowerCase().contains(descripcion)) {  
                TbNombre.addRow(new Object[]{
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
            JOptionPane.showMessageDialog(this, "No se encontró el producto", "Buscar Producto", JOptionPane.WARNING_MESSAGE);
            jTbuscarPorNombre.setText("");
        }
    } else {
        
        llenarTabla();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbuscarNombre = new javax.swing.JLabel();
        jTbuscarPorNombre = new javax.swing.JTextField();
        jLlogo = new javax.swing.JLabel();
        jLTituloNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNombre = new javax.swing.JTable();

        setBackground(new java.awt.Color(74, 112, 124));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLbuscarNombre.setBackground(new java.awt.Color(153, 153, 255));
        jLbuscarNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLbuscarNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLbuscarNombre.setText("Ingrese descripcion ");

        jTbuscarPorNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTbuscarPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTbuscarPorNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTbuscarPorNombreKeyReleased(evt);
            }
        });

        jLlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        jLTituloNombre.setBackground(new java.awt.Color(153, 153, 255));
        jLTituloNombre.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLTituloNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLTituloNombre.setText("Listado por Nombre");

        jTableNombre.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableNombre);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jTbuscarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLlogo)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(jLTituloNombre)
                    .addGap(178, 178, 178)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLlogo)
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTbuscarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbuscarNombre))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(jLTituloNombre)
                    .addContainerGap(554, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTbuscarPorNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscarPorNombreKeyPressed
       
        
    }//GEN-LAST:event_jTbuscarPorNombreKeyPressed

    private void jTbuscarPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscarPorNombreKeyReleased
        buscarPorDescripcion();
    }//GEN-LAST:event_jTbuscarPorNombreKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLTituloNombre;
    private javax.swing.JLabel jLbuscarNombre;
    private javax.swing.JLabel jLlogo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNombre;
    private javax.swing.JTextField jTbuscarPorNombre;
    // End of variables declaration//GEN-END:variables
}
