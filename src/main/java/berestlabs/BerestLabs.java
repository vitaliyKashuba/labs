/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.Circle;
import org.knowm.xchart.style.markers.Marker;

/**
 *
 * @author ShadoW_of_the_DeatH
 */
public class BerestLabs {

    /**
     * @param args the command line arguments
     */
    public static int LEARNING_LIMIT = 80;
    static int LEVELS = 0;
    
    /**
     * create next-level classes within each class from classes
     * re-run itself until exception throws  //should be another quit condition?
     * @param classes 
     */
    static void deepLearnStatic(ArrayList<IRClass> classes, GraphCreator gc)
    {
        ArrayList<IRClass> nextLevelClases = new ArrayList<>();
        for(IRClass cl : classes)
        {
            cl.deepLearn(classes);
            nextLevelClases.add(cl.getNextLevelClass());
            gc.addIRClass(cl.getNextLevelClass());
        }
        LEVELS++;
        deepLearnStatic(nextLevelClases, gc);
    }
    
    public static void main(String[] args) throws IOException 
    {
        GraphCreator gc = new GraphCreator();
        IRExamenator examenator = new IRExamenator();
        ArrayList<IRClass> classes = new ArrayList<>();
        
        if (args.length > 0) //run code
        {
            ArrayList<IRExamenator.ExamPair> examPairs = new ArrayList<>();
            for (String path : args)
            {
                int[][] mas = IRUtil.imageToMatrix(IRUtil.loadImage(path));
                IRClass c = new IRClass();
                c.learn(mas, LEARNING_LIMIT);
                examenator.addExamClass(c);
                
                for (int i = LEARNING_LIMIT; i < mas.length; i++)
                {
                    examPairs.add(new IRExamenator.ExamPair(mas[i], c));
                }
                gc.addIRClass(c);
                classes.add(c);
            }  //class learning cycle
            
            //here begins the deep learning
            try 
            {
               deepLearnStatic(classes, gc);
            } catch (Exception e) 
            {
                System.out.println("learning finished, levels " + LEVELS);
            }
            //deep learning end
            
            examenator.exam(examPairs, true);
            
            try 
            {
                examenator.deepExam(examPairs, classes, true);
            } catch (Exception e) 
            {
                System.out.println("deep exam finished");
            }
            System.out.println(examenator.getDEEP_EXAM_SUCCESS_COUNT() + " recognized wright, " + examenator.getDEEP_EXAM_FAIL_COUNT() + " recognized wrong");
            //gc.show();     
        }  //end of run code
    }
}
