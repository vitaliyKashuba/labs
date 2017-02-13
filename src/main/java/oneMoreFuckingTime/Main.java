package oneMoreFuckingTime;

import berestlabs.GraphCreator;
import berestlabs.IRClass;
import berestlabs.IRExamenator;
import berestlabs.IRUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//import oneMoreFuckingTime.IRSurfaceClass.IRSCCoordinates;

/**
 *
 * @author vitaliy
 */
public class Main 
{
    public static void main(String args[]) throws IOException //too many code under try-catch for non-handling situation (always exit on exception)
    {
        IRExamenator examenator = new IRExamenator();
        GraphCreator gc = new GraphCreator();
        
        for (String path : args)  
        {
            int[][] mas = IRUtil.imageToMatrix(IRUtil.loadImage(path));
            IRClass c = new IRClass();
            c.learn(mas, mas.length);
            //c.setName(path.substring(13, path.length()-6)); // gc.show breacs if class names duolicates
            c.setName(path.substring(13, path.length()-4)); //TODO fix it for unified naming
            examenator.addExamClass(c);
            gc.addIRClass(c);
        }   
        
        BufferedImage input = IRUtil.loadImage("input/input.png");
        
        ArrayList<IRSurfaceClass> surfaces = IRUtil.imageSplit(input, 50);
        for (IRSurfaceClass surface : surfaces)
        {
            String nm;
            IRClass recognized = examenator.recognize(surface.getEtalonVector());
            if(recognized != null)
            {
                nm = recognized.getName();
            }
            else
            {
                nm = "unrecognized";
            }
            
            IRUtil.drawBorder(surface.getSurfaceImage(), Color.RED);
            IRUtil.drawText(surface.getSurfaceImage(), nm, Color.RED, 0, 20);
            //ImageIO.write(surface.getSurfaceImage(), "png", new File("output/" + surface.getX() + "-" + surface.getY() + ".png"));
        }
        
        BufferedImage output = IRUtil.imageBuild(surfaces, input.getWidth(), input.getHeight());
        IRUtil.imageWrite(output, "output/output.png");
        
        gc.show();
    }
}
