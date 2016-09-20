/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.*;
import org.knowm.xchart.SwingWrapper;

/**
 *
 * @author ShadoW_of_the_DeatH
 */
public class BerestLabs {

    /**
     * @param args the command line arguments
     */
    public static int LEARNING_LIMIT = 80;
    
    public static void main(String[] args) throws IOException 
    {
        
        if (args.length > 0) //run code
        {
            ArrayList<IRExamenator.ExamPair> examPairs = new ArrayList<>();
            for (String path : args)
            {
                int[][] mas = IRUtil.imageToMatrix(IRUtil.loadImage(path));
                IRClass c = new IRClass();
                c.learn(mas, LEARNING_LIMIT);
                IRExamenator.addExamClass(c);
                
                for (int i = LEARNING_LIMIT; i < mas.length; i++)
                {
                    examPairs.add(new IRExamenator.ExamPair(mas[i], c));
                }
            }
            IRExamenator.exam(examPairs, true);
        }
        
        else //debug\run-from-IDE-code
        {
            String path = "G:\\__haveNoIdeaWhatImDoingHere\\1\\Лаб_1_варианты\\7\\1.bmp";
            String path2 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\Лаб_1_варианты\\7\\2.bmp";
            String path3 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\Лаб_1_варианты\\8\\2.bmp";
            
            int[][] m1 = IRUtil.imageToMatrix(IRUtil.loadImage(path));
            int[][] m2 = IRUtil.imageToMatrix(IRUtil.loadImage(path2));
            int[][] m3 = IRUtil.imageToMatrix(IRUtil.loadImage(path3));

            IRClass class1 = new IRClass();
            class1.learn(m1, LEARNING_LIMIT);

            IRClass class2 = new IRClass();
            class2.learn(m2, LEARNING_LIMIT);

            IRClass class3 = new IRClass();
            class3.learn(m3, LEARNING_LIMIT);

            System.out.println(IRClass.isIntersect(class1, class2));

            IRExamenator.addExamClass(class2);
            IRExamenator.addExamClass(class1);
            IRExamenator.addExamClass(class3);

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
            for (int i = LEARNING_LIMIT; i < m1.length; i++)
            {
                examPairs.add(new IRExamenator.ExamPair(m1[i], class1));
                examPairs.add(new IRExamenator.ExamPair(m2[i], class2));
                examPairs.add(new IRExamenator.ExamPair(m3[i], class3));
            }

            //System.out.println(examPairs.size());
            IRExamenator.exam(examPairs, true);
            
            double[] xData = new double[] { 0.0, 1.0, 2.0 };
            double[] yData = new double[] { 0.0, 1.0, 0.0 };

            // Create Chart
            XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(2x)", xData, yData);

            // Show it
            new SwingWrapper(chart).displayChart();
                    
        }
    }
    
}
