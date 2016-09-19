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
    int radius;
    int[] etalonVector;
    int[][] realizations;
    
    public IRClass() 
    {
        setName("class"+classcount);
        classcount++;
    }
    
    /**
     * calculate distance between two classes
     * @return distance
     */
    public static int findDistance(IRClass class1, IRClass class2)
    {
        return 0;
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
    }
    
    private void calculateEtalon()
    {
        
    }
    
    private void findRadius()
    {
        
    }
    
    
}
