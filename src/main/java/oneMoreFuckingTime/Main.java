package oneMoreFuckingTime;

import berestlabs.IRClass;
import berestlabs.IRExamenator;
import berestlabs.IRUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author vitaliy
 */
public class Main 
{
    public static void main(String args[]) throws IOException //too many code under try-catch for non-handling situation (always exit on exception)
    {
        IRExamenator examenator = new IRExamenator();
        
        for (String path : args)  
        {
            int[][] mas = IRUtil.imageToMatrix(IRUtil.loadImage(path));
            IRClass c = new IRClass();
            c.learn(mas, mas.length);
            c.setName(path.substring(13, path.length()-6)); //TODO fix it for unified naming
            examenator.addExamClass(c);
        }   
        
        BufferedImage input = IRUtil.loadImage("input.png");
        ArrayList<BufferedImage> images = IRUtil.imageSplit(input, 40);
        ArrayList<IRClass> surfaces = new ArrayList<>();
        
        int i = 0; //TODO counter to avoid renaming. fix it. later
        for(BufferedImage image : images)
        {
            int[][] mas = IRUtil.imageToMatrix(image);
            IRClass c = new IRClass();
            c.learn(mas, mas.length);
            surfaces.add(c);
            
            String nm;
            IRClass recognized = examenator.recognize(c.getEtalonVector());
            if(recognized != null)
            {
                nm = recognized.getName();
            }
            else
            {
                nm = "unrecognized";
            }
            i++;
            ImageIO.write(image, "png", new File("output/" + i + "-" + nm + ".png"));
        }
        
    }
}
