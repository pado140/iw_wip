/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alert.icons;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Padovano
 */
public class IconImage {
    
    public static Image get(String name){
        Image icon=null;
        try {
            icon = ImageIO.read(IconImage.class.getResourceAsStream(name.substring(0, name.lastIndexOf(".")-1)+".png"));
        } catch (IOException ex) {
            Logger.getLogger(IconImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icon;       
    } 
    
}
