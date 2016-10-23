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
    
    /**
     * !! should be called from taxone creation method, because by default radius calculated from rlz points
     * @param radius 
     */
    void setRadius(double radius)
    {
        this.radius = radius;
    }
    
    /**
     * new radius = old radius * 0.9
     * @return 
     */
    double calculateNewRadius()
    {
        radius = radius*RADIUS_CORRECTION_COEFFICIENT;
        return radius;
    }
    
    /**
     * return points within radius of new center
     * @param center
     * @return 
     */
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
    
    /**
     * used to stop recursive taxon extraction
     * if base class points is same with taxon points -> taxon extracted
     * @return 
     */
    public boolean isTaxoneCalculated()
    {
        System.out.println("p " + points.size() + "tx " + taxonePoints.size());
        if (points.size() == taxonePoints.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
