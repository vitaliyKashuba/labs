package oneMoreFuckingTime;

import berestlabs.IRClass;
import berestlabs.IRUtil;
import java.awt.image.BufferedImage;

/**
 * the same as IRClass, but has surface image and its coordinate on global map
 * @author vitaliy
 */
public class IRSurfaceClass extends IRClass
{
    private BufferedImage surfaceImage;
    private int xCoordinate;
    private int yCoordinate;
    
    /**
     * auto-learning IRClass contructor
     * @param img image to learn from
     * @param x coordinate on map
     * @param y coordinate on map
     */
    public IRSurfaceClass(BufferedImage img, int x, int y) 
    {
        super();
        this.surfaceImage = img;
        this.xCoordinate = x;
        this.yCoordinate = y;
        
        int[][] mas = IRUtil.imageToMatrix(img);
        this.learn(mas, mas.length);
    }

    public int getX() 
    {
        return xCoordinate;
    }

    public int getY() 
    {
        return yCoordinate;
    }

    public BufferedImage getSurfaceImage() 
    {
        return surfaceImage;
    }
    
}
