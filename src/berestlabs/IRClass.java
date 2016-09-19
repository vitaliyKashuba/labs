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
       
}
