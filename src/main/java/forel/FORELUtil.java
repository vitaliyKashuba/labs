package forel;

import java.util.ArrayList;
import java.util.Random;

public class FORELUtil
{
    static Random random = new Random(System.currentTimeMillis()); //for real random
    //static Random random = new Random();
    
    /**
     * generate set of points within limits
     * @return 
     */
    public static ArrayList<int[]> generatePoints(int count, int xLimitLow, int xLimitHigh, int yLimitLow, int yLimitHigh)
    {
        ArrayList points = new ArrayList();
        
        for (int i = 0; i < count; i++)
        {
            points.add(generatePoint(xLimitLow, xLimitHigh, yLimitLow, yLimitHigh));
        }
        return points;
    }
    
    /**
     * generate point with coordinats limits
     * 
     * @return point[0] - x, point[1] - y;
     */
    public static int[] generatePoint(int xLimitLow, int xLimitHigh, int yLimitLow, int yLimitHigh)
    {
        int[] point = new int[2];
        
        point[0] = generateNumber(xLimitLow, xLimitHigh);
        point[1] = generateNumber(yLimitLow, yLimitHigh);
        
        return point;
    }
    
    public static int generateNumber(int limitLow, int limitHigh)
    {
        return random.nextInt(limitHigh - limitLow) + limitLow;
    }
}
