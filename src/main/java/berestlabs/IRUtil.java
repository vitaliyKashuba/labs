/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import oneMoreFuckingTime.IRSurfaceClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import oneMoreFuckingTime.IRSurfaceClass.IRSCCoordinates;

/**
 * Utility functions for Image Recognition
 */
public class IRUtil 
{
    /**
     * load image from filesystem
     * @param path path to image
     * @return BufferedImage
     */
    public static BufferedImage loadImage(String path) throws IOException
    {
        return ImageIO.read(new File(path));
    }
    
    /**
     * convert image to matrix
     */
    public static int[][] imageToMatrix(BufferedImage img)
    {
        int h = img.getHeight();
        int w = img.getWidth();
        int[][] matrix = new int [h][w];
        Color color;
        //System.out.println("height" + h + " " + "width" + w);
        for(int i = 0; i< h; i++)
        {
           for(int k = 0; k < w; k++) 
           {
               color = new Color(img.getRGB(k, i));
               matrix[i][k] = (color.getRed()+color.getGreen()+color.getBlue())/3; // [k][i] ??
           }
        }
        return matrix;
    }
    
    /**
     * split image into small squares
     * @param img image to split
     * @param size sizr of square
     * @return array list or IRSurfaceClasses that contains image squares and its coordinates
     */
    public static ArrayList imageSplit(BufferedImage img, int size) //throws IOException
    {
        int height = img.getHeight()/size; //TODO add size check
        int width = img.getWidth()/size;

        ArrayList<IRSurfaceClass> images = new ArrayList<>();
        
        for(int h = 0; h < height; h++)
        {
            for(int w = 0; w < width; w++)
            {
                BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                int t = 0;
                for(int i = 0; i < size; i++)
                {
                    for(int j = 0; j < size; j++)
                    {
                        image.setRGB(i, j, img.getRGB( (w*size + i), (h*size + j) )  );
                    }
                }
                
                images.add(new IRSurfaceClass(image, w, h));
            }
        }
        
        return images;
    }
    
    /**
     * builds image (map) from small recognized surface images
     * @param surfaces
     * @param sizeX size (must be equal to loaded image)
     * @param sizeY size (must be equal to loaded image)
     * @return 
     */
    public static BufferedImage imageBuild(ArrayList<IRSurfaceClass> surfaces, int sizeX, int sizeY) //TODO add size check
    {
        BufferedImage map = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        for (IRSurfaceClass s : surfaces)
        {
            BufferedImage img = s.getSurfaceImage();
            for (int x = 0; x < img.getWidth(); x++)
            {
                for (int y = 0; y < img.getHeight(); y++)
                {
                    map.setRGB(s.getX()*img.getWidth() + x, s.getY()*img.getHeight() + y, img.getRGB(x, y));
                }
            }
        }
        
        return map;
    }
    
    /**
     * write image to filesystem
     * @param img
     * @param path
     * @throws IOException 
     */
    public static void imageWrite(BufferedImage img, String path) throws IOException
    {
        ImageIO.write(img, "png", new File(path));
    }
    
    /**
     * draw border for image
     * @param img
     * @param color color of border
     */
    public static void drawBorder(BufferedImage img, Color color)
    {
        for (int i = 0; i < img.getWidth(); i++)
        {
            for (int j = 0; j < img.getHeight(); j++)
            {
                if (i == 0 || j == 0 || i == img.getWidth()-1 || j == img.getHeight()-1)
                {
                    img.setRGB(i, j, color.getRGB());
                }
            }
        }
    }
    
    /**
     * draw text on image
     * @param img
     * @param text
     * @param color
     * @param x coordinate of text
     * @param y coordinate of text 
     */
    public static void drawText(BufferedImage img, String text, Color color, int x, int y)
    {
        Graphics graphics = img.getGraphics();
        graphics.setColor(color);
        graphics.drawString(text, 0, 20);
    }
}
