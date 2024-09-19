/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopractico6bEntrega;

import javax.swing.JInternalFrame;

/**
 *
 * @author Personal
 */
public class JInternalFrameImagen extends JInternalFrame{
    protected String path;
    private final JpanelImagen jpanelImagen;

    public JInternalFrameImagen() {
        this.path = "";
        jpanelImagen = new JpanelImagen(path);
        setContentPane(jpanelImagen);
    }
    public void setImagen(String path){
        jpanelImagen.setImage(path);
    }
    
    
}
