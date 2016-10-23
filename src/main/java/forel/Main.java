package forel;

import java.util.ArrayList;
import java.util.Random;
import berestlabs.GraphCreator;
import berestlabs.IRClass;

public class Main
{  
    static GraphCreator gc = new GraphCreator();
    
    /**
     * call itself untill taxone found or last class element shoulb out of taxone
     * @param baseClass 
     */
    static void findTaxone(FORELClass baseClass)
    {
        FORELClass subclass = new FORELClass();
        
        int[] c = baseClass.selectNewCenter();
        baseClass.calculateNewRadius();
        ArrayList<int[]> tx = baseClass.calculateTaxone(c);
        
        gc.addIRClass(baseClass);
        
        if(baseClass.isTaxoneCalculated())
        {
            System.out.println("same");
            return;
        }
        else
        {
            subclass.learn(tx);
            subclass.setRadius(baseClass.getRadius());
            try
            {
                findTaxone(subclass);
            } 
            catch (Exception e) // can fail for last elements of base class
            {
                System.out.println("can't calculate taxone");
                return;
            }   
        }
    }
    
    
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
        
        FORELClass baseClass = new FORELClass();
        baseClass.learn(points);
        
        //gc.addIRClass(baseClass);
        //gc.show();
        
        System.out.println(baseClass.getRadius());
        
        findTaxone(baseClass);
        
        gc.show();
        
    }
    
}
