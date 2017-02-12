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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
    
    /*public static void writeToXml(String xmlName)
    {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("1");    
    }*/
    
    /**
     * split image into small squares
     * @param img image to split
     * @param size sizr of square
     * @return array list with squares
     */
    public static List imageSplit(BufferedImage img, int size) //throws IOException
    {
        int height = img.getHeight()/size; //TODO add size check
        int width = img.getWidth()/size;
        ArrayList<BufferedImage> images = new ArrayList();
        
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
                images.add(image);
                //ImageIO.write(image, "png", new File("output/img" + w + "-" + h + ".png"));
            }
        }
        
        return images;
    }
}
