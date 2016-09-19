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
        String path = "G:\\__haveNoIdeaWhatImDoingHere\\1\\Лаб_1_варианты\\7\\1.bmp";
        String path2 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\Лаб_1_варианты\\7\\2.bmp";
        
        int[][] m = IRUtil.imageToMatrix(IRUtil.loadImage(path));
        System.out.println(m.length + " " + m[0].length);
        
        IRClass class1 = new IRClass();
        class1.learn(IRUtil.imageToMatrix(IRUtil.loadImage(path)), 80);
        
        IRClass class2 = new IRClass();
        class2.learn(IRUtil.imageToMatrix(IRUtil.loadImage(path2)), 80);
        
        System.out.println(IRClass.isIntersect(class1, class2));
        
        IRExamenator.addExamClass(class2);
        IRExamenator.addExamClass(class1);
        
        IRExamenator.test(class1.getEtalonVector(), class1, true);
    }
    
}
