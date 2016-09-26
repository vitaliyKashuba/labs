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
    public static int LEARNING_LIMIT = 10;
    
    public static void main(String[] args) throws IOException 
    {
        GraphCreator gc = new GraphCreator();

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
                gc.addIRClass(c);
            }
            IRExamenator.exam(examPairs, true);
            
            gc.show();
        }
        
        else //debug\run-from-IDE-code
        {
            String path = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\7\\1.bmp";
            String path2 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\7\\2.bmp";
            String path3 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\13\\1.bmp";
            String path4 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\13\\2.bmp";
            
            int[][] m1 = IRUtil.imageToMatrix(IRUtil.loadImage(path));
            int[][] m2 = IRUtil.imageToMatrix(IRUtil.loadImage(path2));
            int[][] m3 = IRUtil.imageToMatrix(IRUtil.loadImage(path3));
            int[][] m4 = IRUtil.imageToMatrix(IRUtil.loadImage(path4));

            IRClass class1 = new IRClass();
            class1.learn(m1, LEARNING_LIMIT);

            IRClass class2 = new IRClass();
            class2.learn(m2, LEARNING_LIMIT);

            IRClass class3 = new IRClass();
            class3.learn(m3, LEARNING_LIMIT);
            
            IRClass class4 = new IRClass();
            class4.learn(m4, LEARNING_LIMIT);

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
            
            
            //graphics code below
            
            /*
            
            
            
            class1.convertToTwoDimensialSpace();
            IRClass.TwoDimensionalData tdData = class1.getTwoDimensionalData();
            
            double[] xData = new double[tdData.getTwoDimRealizations().length];
            double[] yData = new double[tdData.getTwoDimRealizations().length];
            
            //System.out.println(tdData.getTwoDimRealizations().length);
            // Create Chart
            
            int[][] points = tdData.getTwoDimRealizations();
            
            for(int i = 0; i < points.length; i ++)
            {
                xData[i] = points[i][0];
                yData[i] = points[i][1];
            }
            
            int[] x = {tdData.getTwoDimEtalon()[0]};
            int[] y = {tdData.getTwoDimEtalon()[1]};
            
            //XYChart chart1 = QuickChart.getChart("Sample Chart", "X", "Y", "y(2x)", new double[] {120.0, 130.0}, new double []{150.0, 140.0});
            
            XYChart chart1 = new XYChartBuilder().width(800).height(600).build();
            chart1.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
            chart1.getStyler().setChartTitleVisible(false);
            chart1.getStyler().setLegendPosition(LegendPosition.InsideSW);
            chart1.getStyler().setMarkerSize(16);
            //chart1.getStyler().set;
            
            //chart1.addSeries("null", x, y);
            
            XYSeries series = chart1.addSeries("class1", xData, yData);
            series.setMarkerColor(Color.yellow);
            
            XYSeries series2 = chart1.addSeries("etalon1", x, y);
            series2.setMarkerColor(Color.BLACK);
            //chart1.addSeries("Gaussian Blob", xData, yData);

            
            
            
            
            
            class2.convertToTwoDimensialSpace();
            IRClass.TwoDimensionalData tdData2 = class2.getTwoDimensionalData();
            
            double[] xData2 = new double[tdData2.getTwoDimRealizations().length];
            double[] yData2 = new double[tdData2.getTwoDimRealizations().length];
            
            //System.out.println(tdData.getTwoDimRealizations().length);
            // Create Chart
            
            int[][] points2 = tdData2.getTwoDimRealizations();
            
            for(int i = 0; i < points.length; i ++)
            {
                xData2[i] = points2[i][0];
                yData2[i] = points2[i][1];
            }
            
            for(int i = 0; i < xData2.length; i++)
            {
                System.out.println(xData2[i] + " " + yData2[i]);
            }
            
            int[] x2 = {tdData2.getTwoDimEtalon()[0]};
            int[] y2 = {tdData2.getTwoDimEtalon()[1]};
            
            XYSeries series3 = chart1.addSeries("class2", xData2, yData2);
            series3.setMarkerColor(Color.BLUE);
            Marker circle = new Circle();
            series3.setMarker(circle);
            
            System.out.println(series3.getLineStyle());
            
            XYSeries series4 = chart1.addSeries("etalon2", x2, y2);
            series4.setMarkerColor(Color.DARK_GRAY);
                        
            double tdRadius = tdData2.getTwoDimRadius();
            //int roundCordsCount = (int)tdRadius*2;
            int roundCordsCount = 90;
            
            double[] xRound = new double[roundCordsCount];
            double[] yRound = new double[roundCordsCount];
            
            for(int i = 0; i<roundCordsCount; i++)
            {
                double yCord = 180 - tdRadius + i;
                double xCord = Math.pow( (Math.pow(tdRadius, 2) -  Math.pow(yCord-y2[0], 2) ), 0.5) + x2[0];
                
                xRound[i] = xCord;
                yRound[i] = yCord;
                
                System.out.println(xCord + " " + yCord);
            }
            
            System.out.println(tdRadius);
            
            chart1.addSeries("round", xRound, yRound);
            //chart1.addSeries("round2", yRound, xRound);
            
            // Show it
            new SwingWrapper(chart1).displayChart();*/
            
            
            
            
            
            gc.addIRClass(class1);
            gc.addIRClass(class2);
            //gc.addIRClass(class3);
            //gc.addIRClass(class4);
            
            gc.show();
                    
        }
    }
    
}
