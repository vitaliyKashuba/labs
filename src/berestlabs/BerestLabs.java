/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

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
        int learningLimit = 80;
        
        int[][] m1 = IRUtil.imageToMatrix(IRUtil.loadImage(path));
        int[][] m2 = IRUtil.imageToMatrix(IRUtil.loadImage(path2));
        
        IRClass class1 = new IRClass();
        class1.learn(m1, learningLimit);
        
        IRClass class2 = new IRClass();
        class2.learn(m2, learningLimit);
        
        System.out.println(IRClass.isIntersect(class1, class2));
        
        IRExamenator.addExamClass(class2);
        IRExamenator.addExamClass(class1);
        
        //IRExamenator.test(class1.getEtalonVector(), class1, true);
        
        /*TreeMap<int[], IRClass> unlearnedRealizations = new TreeMap<>();

        for (int i = learningLimit; i < m1.length; i++)
        {
            System.out.println(m1);
            //unlearnedRealizations.put(m1[i], class1);
            //unlearnedRealizations.put(m2[i], class2);
        }
        
        System.out.println(unlearnedRealizations.size());*/
        
        ArrayList<IRExamenator.ExamPair> examPairs = new ArrayList<>();
        for (int i = learningLimit; i < m1.length; i++)
        {
            examPairs.add(new IRExamenator.ExamPair(m1[i], class1));
            examPairs.add(new IRExamenator.ExamPair(m2[i], class2));
        }
        
        //System.out.println(examPairs.size());
        IRExamenator.exam(examPairs, true);
    }
    
}
