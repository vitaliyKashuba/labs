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
    
    public void addIRClass()
    {
        // get coords
        
        // get etalon
        
        //get radius
        
        //add coords and etalon in different colors but same mrker
        
        addCircle();
    }
    
    private void addCircle()
    {
        //calculate and add coords of circles
    }
    
    void show()
    {
        
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
