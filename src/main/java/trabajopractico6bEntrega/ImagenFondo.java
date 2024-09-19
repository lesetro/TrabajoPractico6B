
package trabajopractico6bEntrega;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
 

public class ImagenFondo implements Border{
    public BufferedImage back;
 
    public ImagenFondo(){
        try {
            URL imagePath = new URL(getClass().getResource("/imagen/Fondo_edificios.png").toString());
            back = ImageIO.read(imagePath);
        } catch (Exception ex) {   
            ex.getStackTrace();
        }
    }

    
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // Define las dimensiones fijas que quieres para la imagen
    int imgWidth = 800;  // Ancho deseado para la imagen
    int imgHeight = 700; // Alto deseado para la imagen
    // Dibuja la imagen escalada al tamaño del componente
    g.drawImage(back, 0, 0, imgWidth, imgHeight, c);
}
 
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
 
    public boolean isBorderOpaque() {
        return false;
    }
 
}
