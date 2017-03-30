package oneMoreFuckingTime;

import berestlabs.IRClass;
import berestlabs.IRUtil;

import java.awt.image.BufferedImage;

/**
 * Image Recognition with Informational-Extreme Intelligent (IEI) technology class
 * binarize image, use some magic criteruims to find optimal radius
 */
public class IRIEIClass extends IRClass
{
    IRIEIClass(BufferedImage img)
    {
        super();

        int[][] mas = IRUtil.imageToMatrix(img);

        //this.learn(mas, mas.length);
    }

    void binarizeImage()
    {

    }

}
