package berestlabs;

import java.util.TreeMap;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.Circle;
import org.knowm.xchart.style.markers.Marker;

/**
 * clas for creating and showing graphs
 */
public class GraphCreator 
{
    private TreeMap<Integer, Integer> Circles; //contains coords of circles points
    private XYChart chart;
    
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
        
        addCircle();
    }
    
    private void addCircle()
    {
        //calculate and add coords of circles
    }
    
    void show()
    {
        new SwingWrapper(chart).displayChart();
    }
    
    public GraphCreator()
    {
        chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);
    }
}
