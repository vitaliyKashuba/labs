package forel;

import berestlabs.IRClass;
import java.util.ArrayList;

public class FORELClass extends IRClass
{
    public static final double RADIUS_CORRECTION_COEFFICIENT = 0.9;
    
    ArrayList<int[]> points;
    double radius;
    ArrayList<int[]> taxonePoints;
    
    @Override
    public void learn(ArrayList<int[]> rlz)
    {
        super.learn(rlz);
        
        points = rlz;
        radius = super.getRadius();
    }
    
    /**
     * @return coordinates of new center 
     */
    int[] selectNewCenter()
    {
        return points.get(FORELUtil.generateNumber(0, points.size()));
    }
    
    double calculateNewRadius()
    {
        radius = radius*RADIUS_CORRECTION_COEFFICIENT;
        return radius;
    }
    
    ArrayList<int[]> calculateTaxone(int [] center)
    {
        taxonePoints = new ArrayList<>();
        for (int[] point : points)
        {
            if (IRClass.calculateDistance(point, center)<radius)
            {
                taxonePoints.add(point);
            }
        }
        return (ArrayList<int[]>)taxonePoints.clone();
    }
    
}
