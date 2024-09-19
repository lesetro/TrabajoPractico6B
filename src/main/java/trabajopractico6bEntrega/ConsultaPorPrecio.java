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
public class ConsultaPorPrecio extends JInternalFrameImagen {

    private Principal principal;
    private DefaultTableModel TbPrecio;

    public ConsultaPorPrecio() {
        this.principal = Principal.getInstancia();
        initComponents();
        this.setSize(785, 700);
        this.setImagen("/imagen/FondoAzulCuadrados.png");
        this.TbPrecio = new DefaultTableModel();
        TbPrecio.addColumn("Codigo");
        TbPrecio.addColumn("Descripcion");
        TbPrecio.addColumn("Precio");
        TbPrecio.addColumn("Categoria");
        TbPrecio.addColumn("Stock");
        jTable1.setModel(TbPrecio);
        jTable1.setDefaultEditor(Object.class, null);
        llenarTabla();

    }

    public void llenarTabla() {
        TbPrecio.setRowCount(0);

        if (principal.getConjuntoProducto().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                TbPrecio.addRow(new Object[]{
                    "Lista vacia",});
            }

        } else {
            for (Producto producto : principal.getConjuntoProducto()) {
                TbPrecio.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getRubro(),
                    producto.getStock()
                });
            }
        }

    }

    public void buscarPorPrecio() {

        TbPrecio.setRowCount(0);

        String textopMenor = jTxtPrecioMenor.getText().trim();
        String textopMayor = jTxtPrecioMayor.getText().trim();

        Double pMenor = null;
        Double pMayor = null;

        try {
            
            if (!textopMenor.isEmpty()) {
                pMenor = Double.valueOf(textopMenor);
            }
            if (!textopMayor.isEmpty()) {
                pMayor = Double.valueOf(textopMayor);
            }

            if (pMenor == null && pMayor == null) {
                JOptionPane.showMessageDialog(null, "Ingrese al menos un valor de precio", "Validación", JOptionPane.INFORMATION_MESSAGE);
                llenarTabla();
                return;
            }

            
            for (Producto producto : principal.getConjuntoProducto()) {
                double precioProducto = producto.getPrecio();

                // Que muesto todos los productoe menores al valor
                if (pMenor != null && pMayor == null && precioProducto <= pMenor) {
                    agregarProductoATabla(producto);
                } // que muestre todos los productos mayores al valor
                else if (pMenor == null && pMayor != null && precioProducto >= pMayor) {
                    agregarProductoATabla(producto);
                } // Si se ingresaron ambos precios, que muestre el intervalo
                else if (pMenor != null && pMayor != null && precioProducto >= pMenor && precioProducto <= pMayor) {
                    agregarProductoATabla(producto);
                }
            }

        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, "Ingrese un valor válido", "Validación", JOptionPane.INFORMATION_MESSAGE);
            jTxtPrecioMenor.setText("");
            jTxtPrecioMayor.setText("");
            llenarTabla();
    
        }
    }


    private void agregarProductoATabla(Producto producto) {
        TbPrecio.addRow(new Object[]{
            producto.getCodigo(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getRubro(),
            producto.getStock()
        });
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jLtituloPrecio = new javax.swing.JLabel();
        jLprecio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtPrecioMenor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtPrecioMayor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBuscarPrecio = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLtituloPrecio.setBackground(new java.awt.Color(255, 255, 255));
        jLtituloPrecio.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLtituloPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jLtituloPrecio.setText("Consulte por precio");

        jLprecio.setBackground(new java.awt.Color(255, 255, 255));
        jLprecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLprecio.setForeground(new java.awt.Color(204, 204, 204));
        jLprecio.setText("Coloque entre que precios busca");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Precio menor");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Precio Mayor");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jBuscarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/browsing_7178506.png"))); // NOI18N
        jBuscarPrecio.setText("Buscar");
        jBuscarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBuscarPrecioActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLtituloPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLprecio)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtPrecioMenor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtPrecioMayor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jBuscarPrecio)
                        .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLtituloPrecio)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLprecio)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTxtPrecioMenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtPrecioMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jBuscarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBuscarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBuscarPrecioActionPerformed
        buscarPorPrecio();

    }//GEN-LAST:event_jBuscarPrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBuscarPrecio;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLprecio;
    private javax.swing.JLabel jLtituloPrecio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtPrecioMayor;
    private javax.swing.JTextField jTxtPrecioMenor;
    // End of variables declaration//GEN-END:variables
}
