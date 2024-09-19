/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopractico6bEntrega;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Personal
 */
public class JpanelImagen extends JPanel {
    private Image imagen= null;

    public JpanelImagen(String ruta) {
        setImage(ruta);
    }
    
    public void setImage(String ruta){
        if (!ruta.equals("")) {
            imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
            repaint();
        }
    
    }
    @Override
    public void paint(Graphics g){
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
            this.setOpaque(false);
            super.paint(g);
            
            
        }
    }
    
}
