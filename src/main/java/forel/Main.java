package forel;

import static berestlabs.BerestLabs.LEARNING_LIMIT;
import java.util.ArrayList;
import java.util.Random;
import berestlabs.GraphCreator;
import berestlabs.IRClass;
import berestlabs.IRUtil;
import java.io.IOException;

public class Main
{  
    static GraphCreator gc = new GraphCreator();
    static ArrayList<IRClass> taxones = new ArrayList<>();
    static ArrayList<int[]> points = new ArrayList();
    
    
    static void findAllTaxones(FORELClass baseClass)
    {
        ArrayList<int[]> tx = findTaxone(baseClass);
        
        IRClass taxone = new IRClass();
        taxone.learn(tx);
        taxones.add(taxone);
        
        /*if(taxones.size()>3)
        {
            return;
        }*/
        
        points.removeAll(tx);
        
        FORELClass taxoneExtractedClass = new FORELClass();
        taxoneExtractedClass.learn(points);
        
        findAllTaxones(taxoneExtractedClass);
    }
     
    /**
     * call itself untill taxone found or last class element shoulb out of taxone
     * @param baseClass 
     */
    static ArrayList<int[]> findTaxone(FORELClass baseClass)
    {
        FORELClass subclass = new FORELClass();
        
        int[] c = baseClass.selectNewCenter();
        baseClass.calculateNewRadius();
        ArrayList<int[]> tx = baseClass.calculateTaxone(c);
        
        //gc.addIRClass(baseClass);
        
        if(baseClass.isTaxoneCalculated())
        {
            System.out.println("same");
            return tx; //called inside
        }
        else
        {
            subclass.learn(tx);
            subclass.setRadius(baseClass.getRadius());
            try
            {
                tx = findTaxone(subclass);
            } 
            catch (Exception e) // can fail for last elements of base class
            {
                System.out.println("can't calculate taxone");
                return tx;
            }   
        }
        //System.out.println(tx.size());
        return tx; // called in last time
    }
    
    
    public static void main(String[] args) throws IOException
    {
        ArrayList pts1 = FORELUtil.generatePoints(15, 0, 60, 0, 60);
        ArrayList pts2 = FORELUtil.generatePoints(15, 40, 100, 40, 100);
        ArrayList pts3 = FORELUtil.generatePoints(15, 40, 100, 0, 60);
        ArrayList pts4 = FORELUtil.generatePoints(15, 0, 60, 40, 100);
        
        points.addAll(pts1);
        points.addAll(pts2);
        points.addAll(pts3);
        points.addAll(pts4);
        
        /*String path = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\7\\1.bmp";
        String path2 = "G:\\__haveNoIdeaWhatImDoingHere\\1\\var\\7\\2.bmp";
        
        int[][] m1 = IRUtil.imageToMatrix(IRUtil.loadImage(path));
        int[][] m2 = IRUtil.imageToMatrix(IRUtil.loadImage(path2));
        
        IRClass class1 = new IRClass();
        class1.learn(m1, LEARNING_LIMIT);

        IRClass class2 = new IRClass();
        class2.learn(m2, LEARNING_LIMIT);
        
        int[][] pts = class1.getTwoDimensionalData().getTwoDimRealizations();
        int[][] pts2 = class2.getTwoDimensionalData().getTwoDimRealizations();
        
        for (int[] p : pts)
        {
            points.add(p);
        }
        
        for (int[] p2 : pts2)
        {
            points.add(p2);
        }*/
        
        
        
        
        
        
        
        
        
        
        FORELClass baseClass = new FORELClass();
        baseClass.learn(points);
        
        gc.addIRClass(baseClass);
            
        
        try  //fault in last taxone because no exit-statement for recursion
        {
            findAllTaxones(baseClass);      
        }
        catch (Exception e)
        { }
        
        try  //can fail when taxone with one element
        {
            for(IRClass c : taxones)
            {
               gc.addIRClass(c);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Taxones count: " + taxones.size());
        
        gc.show();
        
    }
    
}
