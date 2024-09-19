/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trabajopractico6bEntrega;

import ServicioProducto.Producto;
import java.util.TreeSet;
import javax.swing.JDesktopPane;


public class Principal extends javax.swing.JFrame {

    
    public TreeSet<Producto> conjuntoProducto= new TreeSet<>();
    private static Principal instanciaUnica = null;  // Instancia única de la clase
    
    private Principal() {
        initComponents();
        this.setTitle("De Todo S.A");
        this.setSize(800, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jDesktopPane1.setBorder(new ImagenFondo());
       

    } 
    // Método estático para obtener la instancia única
    public static Principal getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Principal();
        }
        return instanciaUnica;
    }

   

    public TreeSet<Producto> getConjuntoProducto() {
        return conjuntoProducto;
    }

    public void setConjuntoProducto(TreeSet<Producto> conjuntoProducto) {
        this.conjuntoProducto = conjuntoProducto;
    }

    @Override
    public String toString() {
        return "ConjuntoProducto{" + "conjuntoProducto=" + conjuntoProducto + '}';
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMCN = new javax.swing.JMenuItem();
        jMCR = new javax.swing.JMenuItem();
        jMCP = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGOcONnOMBRE_1.png"))); // NOI18N

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(296, 296, 296))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1)
                .addContainerGap(364, Short.MAX_VALUE))
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/setting_5465430.png"))); // NOI18N
        jMenu1.setText("Administracion");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setBackground(new java.awt.Color(204, 204, 255));
        jMenuItem1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_12008540.png"))); // NOI18N
        jMenuItem1.setText("Gestion de productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/question_8577697.png"))); // NOI18N
        jMenu2.setText("Consulta");

        jMCN.setBackground(new java.awt.Color(204, 204, 255));
        jMCN.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/courier_7606401.png"))); // NOI18N
        jMCN.setText("Consulta por nombre");
        jMCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCNActionPerformed(evt);
            }
        });
        jMenu2.add(jMCN);

        jMCR.setBackground(new java.awt.Color(204, 204, 255));
        jMCR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMCR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boxes_6690949_1.png"))); // NOI18N
        jMCR.setText("Consulta por rubro");
        jMCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCRActionPerformed(evt);
            }
        });
        jMenu2.add(jMCR);

        jMCP.setBackground(new java.awt.Color(204, 204, 255));
        jMCP.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMCP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/price-tag_11605411.png"))); // NOI18N
        jMCP.setText("Consulta por precio");
        jMCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCPActionPerformed(evt);
            }
        });
        jMenu2.add(jMCP);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCNActionPerformed
        jDesktopPane1.removeAll();
        ConsultaPorNombre CNombre = new ConsultaPorNombre();
        jDesktopPane1.add(CNombre);
        CNombre.toFront();
        CNombre.show();

    }//GEN-LAST:event_jMCNActionPerformed

    private void jMCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCPActionPerformed
        jDesktopPane1.removeAll();
        ConsultaPorPrecio CPrecio = new ConsultaPorPrecio();
        jDesktopPane1.add(CPrecio);
        CPrecio.toFront();
        CPrecio.show();
    }//GEN-LAST:event_jMCPActionPerformed

    private void jMCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCRActionPerformed
        jDesktopPane1.removeAll();
        ConsultaPorRubro CRubro = new ConsultaPorRubro();
        jDesktopPane1.add(CRubro);
        CRubro.toFront();
        CRubro.show();
    }//GEN-LAST:event_jMCRActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jDesktopPane1.removeAll();
        ConsultaDEproducto CProducto = new ConsultaDEproducto();
        jDesktopPane1.add(CProducto);
        CProducto.toFront();
        CProducto.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMCN;
    private javax.swing.JMenuItem jMCP;
    private javax.swing.JMenuItem jMCR;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
