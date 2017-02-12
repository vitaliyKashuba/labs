package oneMoreFuckingTime;

import berestlabs.IRUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vitaliy
 */
public class Main 
{
    public static void main(String args[])
    {
        BufferedImage input;
        try 
        {
            input = IRUtil.loadImage("input.png");
            IRUtil.imageSplit(input, 40);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
