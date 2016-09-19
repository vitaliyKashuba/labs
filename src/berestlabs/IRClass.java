/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.awt.List;

/**
 * Image Recognition class
 */
public class IRClass 
{
    static int CLASSCOUNT=0;
    
    private String name;
    private double radius;
    private int[] etalonVector;
    private int[][] realizations;
    
    public IRClass() 
    {
        setName("class"+CLASSCOUNT);
        CLASSCOUNT++;
    }
    
    /**
     * calculate distance between two vectors
     * @return distance
     */
    private static double calculateDistance(int [] vector1, int [] vector2)
    {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++)
        {
            sum = sum + Math.pow(vector1[i]-vector2[i], 2);
        }
        return Math.sqrt(sum);
    }
    
    /**
     * check is classes are intersect
     * @return 
     */
    public static boolean isIntersect(IRClass class1, IRClass class2)
    {
        if (calculateDistance(class1.getEtalonVector(), class2.getEtalonVector()) > class1.getRadius()+class2.getRadius())
        {
            return false;
        }
        else
        {
            //System.out.println("r2 " + class1.getRadius() + " r2 " + class2.getRadius() + " dist " + calculateDistance(class1.getEtalonVector(), class2.getEtalonVector()) + " ");
            return true;
        }
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public int[] getEtalonVector()
    {
        return etalonVector;
    }
    
    /**
     * add realizations of class, calculate etalon vector and find radius
     * @param learnMatrix matrix with realizations
     * @param limit value of realizations to learn. should be less then learnMatrix height
     */
    public void learn(int[][] learnMatrix, int limit) throws IllegalArgumentException
    {
        if (learnMatrix.length < limit)
        {
            throw new IllegalArgumentException("param limit should be <"+learnMatrix.length);
        }
        
        int width = learnMatrix[0].length;
        realizations = new int[limit][width];
        for (int i = 0; i < limit; i++)
        {
            for (int j = 0; j < width; j ++)
            {
                realizations[i][j] = learnMatrix[i][j];
            }
        }
        
        calculateEtalon();
        findRadius();
    }
    
    private void calculateEtalon()
    {
        etalonVector = new int[realizations[0].length];
        
        for (int w = 0; w < etalonVector.length; w++)
        {
            int sum = 0;
            for (int h = 0; h < realizations.length; h++)
            {
                sum = sum + realizations[h][w];
            }
            etalonVector[w] = sum / realizations.length;
        }    
    }
    
    /**
     * finds max radius
     */
    private void findRadius()
    {
        radius = 0;
        for(int[] realization : realizations)
        {
            double newRadius = calculateDistance(etalonVector, realization);
            if (newRadius > radius)
            {
                radius = newRadius;
            }
        }
    }
    
    
}
