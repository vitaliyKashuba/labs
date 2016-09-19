/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

/**
 * Image Recognition class
 */
public class IRClass 
{
    static int classcount=0;
    
    String name;
    double radius;
    int[] etalonVector;
    int[][] realizations;
    
    public IRClass() 
    {
        setName("class"+classcount);
        classcount++;
    }
    
    /**
     * calculate distance between two vectors
     * @return distance
     */
    public static double calculateDistance(int [] vector1, int [] vector2)
    {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++)
        {
            sum = sum + Math.pow(vector1[i]-vector2[i], 2);
        }
        return Math.sqrt(sum);
    }
    
    public void setName(String name)
    {
        this.name = name;
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
