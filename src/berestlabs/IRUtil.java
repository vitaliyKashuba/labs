/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        int[][] matrix = new int [w][h];
        Color color;
        
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
}
