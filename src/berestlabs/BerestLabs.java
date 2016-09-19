/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.io.IOException;

/**
 *
 * @author ShadoW_of_the_DeatH
 */
public class BerestLabs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        String path = "G:\\__haveNoIdeaWhatImDoingHere\\BerestLabs\\1.jpg";
        int[][] m = IRUtil.imageToMatrix(IRUtil.loadImage(path));
        System.out.println(m.length + " " + m[0].length);
        
        //IRClass class1 = new IRClass();
        //class1.learn(m, 10);
        //System.out.println(class1.realizations.length + " " + class1.realizations[0].length);
    }
    
}
