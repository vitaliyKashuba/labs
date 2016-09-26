package berestlabs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.Circle;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 * clas for creating and showing graphs
 * works on magic
 */
public class GraphCreator 
{
    private TreeMap<Double, Double> circles; //contains coords of circles points
    private XYChart chart;
    private static double ROUND_PAINTER_STEP = 0.02; //used in circle points calculation
    
    /**
     * add coords and circle-border of clas on graph
     */
    public void addIRClass(IRClass iRClass)
    {
        IRClass.TwoDimensionalData tdData = iRClass.getTwoDimensionalData();
        int[] etalon = tdData.getTwoDimEtalon();
        int[][] realizations = tdData.getTwoDimRealizations();
        double radius = tdData.getTwoDimRadius();
        int pointsCount = realizations.length; // make understanding of this code easier in future. useless now
        
        double[] xData = new double[pointsCount];
        double[] yData = new double[pointsCount];
        
        for(int i = 0; i < pointsCount; i ++)
            {
                xData[i] = realizations[i][0];
                yData[i] = realizations[i][1];
            }
        
        int x[] = {etalon[0]}; //because addSeries can't take non-array argument
        int y[] = {etalon[1]}; 
        
        chart.addSeries(iRClass.getName(), xData, yData);
        chart.addSeries(iRClass.getName() + "etalon", x, y);
        
        calculateCircle(etalon, radius);
    }
    
    private void calculateCircle(int [] center, double radius)
    {
        double firstY = center[1]-radius;
        int steps = (int)Math.ceil((radius*2)/ROUND_PAINTER_STEP); // count of points. seems like magic, but works right, no need to test
        double step = 0;
        
        double[] xRound = new double[steps];
        double[] yRound = new double[steps];
        
        for (int i = 0; i < steps; i++, step = step+ROUND_PAINTER_STEP)
        {
            double yCord = firstY + step;
            double xCord = Math.pow( (Math.pow(radius, 2) -  Math.pow(yCord-center[1], 2) ), 0.5) + center[0];
            if ( (Math.pow(radius, 2) -  Math.pow(yCord-center[1], 2) ) < 0)
            {
                xCord = 0 + center[0];      // here can be error caused 'double' type. sqrt(-val)
                //continue;
            }
            xRound[i] = xCord;
            yRound[i] = yCord;
            
            circles.put(xCord, yCord);
        }
        
        halfCircleMirror(xRound, yRound);
    }
    
    /**
     * mirrors coordinates of half-circle
     * caused by two solutions in XY-earch equation
     */
    private void halfCircleMirror(double xRound[], double yRound[])
    {
        double distanceToYAxis = Math.sqrt( Math.pow(xRound[0],2) );
        double[] xRoundMirror = new double[xRound.length];
        for(int i = 0; i < xRound.length; i++)
        {
            xRoundMirror[i] = xRound[i] * (-1) + distanceToYAxis*2;
            circles.put(xRoundMirror[i], yRound[i]);
        }
    }
    
    /**
     * called befor show graph
     */
    private void addCirclesOnGraph()
    {
        ArrayList<Double> xSet = new ArrayList<>();
        ArrayList<Double> ySet = new ArrayList<>();
        ArrayList<Double> szSet = new ArrayList<>(); // the worst realization for size select. never use this lib
        for(double key : circles.keySet())
        {
            xSet.add(key);
            ySet.add(circles.get(key));
            szSet.add(0.5);
        }
        XYSeries series = chart.addSeries("circles", xSet, ySet, szSet);
        series.setMarkerColor(Color.BLACK);
        //Marker circle = new Circle();
        //series.setMarker(circle);
        series.setMarker(SeriesMarkers.CIRCLE);
        
    }
    
    void show()
    {
        addCirclesOnGraph();
        new SwingWrapper(chart).displayChart();
    }
    
    public GraphCreator()
    {
        chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(5);
        
        circles = new TreeMap<>();
    }
}
