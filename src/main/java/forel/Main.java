package forel;

import java.util.ArrayList;
import java.util.Random;

public class Main
{  
    public static void main(String[] args)
    {
        ArrayList pts1 = FORELUtil.generatePoints(15, 0, 60, 0, 60);
        ArrayList pts2 = FORELUtil.generatePoints(15, 40, 100, 40, 100);
        ArrayList pts3 = FORELUtil.generatePoints(15, 40, 100, 0, 60);
        ArrayList pts4 = FORELUtil.generatePoints(15, 0, 60, 40, 100);
        
        ArrayList<int[]> points = new ArrayList();
        points.addAll(pts1);
        points.addAll(pts2);
        points.addAll(pts3);
        points.addAll(pts4);
        
        for (int i = 0; i < points.size(); i++)
        {
            System.out.println(points.get(i)[0] + " " + points.get(i)[1]);
        }
    }
}
