/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berestlabs;

import java.awt.List;
import java.util.ArrayList;

/**
 * Image Recognition class
 */
public class IRClass 
{
    static int CLASSCOUNT=0;
    
    private String name;
    private String basicName;
    private double radius;
    private int[] etalonVector;
    private int[][] realizations;
    private TwoDimensionalData twoDimensionalData;
    private int attributesCount; // etalon length, realizations width
    private int realizationsCount; // realizations.length, matrix high
    
    private IRClass nextLevelClass;
    private int level;
    
    public IRClass(String basicName ,int level) 
    {
        setName("[level"+level+"]"+basicName);
        setBasicName(basicName);
        this.level = level;
    }
    
    public IRClass() 
    {
        setName("class"+CLASSCOUNT);
        setBasicName(this.getName());
        CLASSCOUNT++;
        level = 0;
    }
    
    /**
     * check, is vector belong to more then one class from list
     * @param vector
     * @param classes
     * @return 
     */
    public static boolean liesOnIntersect(int[] vector, ArrayList<IRClass> classes)
    {
        int entryCount = 0;
        for (IRClass c : classes)
        {
            if (calculateDistance(vector, c.getEtalonVector()) < c.getRadius())
            {
                entryCount++;
            }
        }
        if (entryCount>1)//1?
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * calculate distance between two vectors
     * @return distance
     */
    public static double calculateDistance(int [] vector1, int [] vector2) //move to IRUtil?
    {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++)
        {
            try 
            {
                sum = sum + Math.pow(vector1[i]-vector2[i], 2);
            } catch (ArrayIndexOutOfBoundsException e) //ad-hocked to avoid rebuilding of whole class. just ignore las elements
            {
                return Math.sqrt(sum);
            }    
        }
        return Math.sqrt(sum);
    }

    public void setBasicName(String basicName) 
    {
        this.basicName = basicName;
    }
    
    public String getBasicName()
    {
        return basicName;
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
    
    public IRClass getNextLevelClass()
    {
        return nextLevelClass;
    }
    
    public TwoDimensionalData getTwoDimensionalData()
    {
        if (twoDimensionalData == null)
        {
            this.convertToTwoDimensialSpace();
        }
        
        return twoDimensionalData;
    }
    
        /**
     * separate realizationt that lies on intersect of classes
     * create new IRClass inside with own etalon
     * @param classes intersect classes that should be separated
     */
    public void deepLearn(ArrayList<IRClass> classes)
    {
        ArrayList<int[]> intersectVectors = new ArrayList<>();
        
        for (int[] vector : realizations)
        {
            for(IRClass cl : classes)
            {
                if (cl!=this) // to not separate own realizations
                {
                    if (calculateDistance(vector, cl.getEtalonVector()) < cl.getRadius())
                    {
                        intersectVectors.add(vector);
                    }
                }
            }
        }
        
        /*if(intersectVectors.size()<3)
        {
            return;
        }*/
        
        nextLevelClass = new IRClass(this.basicName, this.level+1);
        nextLevelClass.learn(intersectVectors);  
        
        /*ArrayList<IRClass> nextLevelClasses = new ArrayList<>();
        for (IRClass c : classes)
        {
            
        }*/
        
        //deepLearn(classes);
    }
    
    public void learn(ArrayList<int[]> rlz)
    {
        int[][] rArray = new int[rlz.size()][rlz.get(0).length];
        for(int i = 0; i < rlz.size(); i++)
        {
            System.arraycopy(rlz.get(i), 0, rArray[i], 0, rlz.get(i).length);
        }
        
        learn(rArray, rArray.length);
    }
    
    /**
     * add realizations of class, calculate etalon vector and find radius
     * @param learnMatrix matrix with realizations
     * @param limitHeight value of realizations to learn. should be less then learnMatrix height
     * 
     * made to avoid code rafactoring
     * call learn(learnMatrix, limitHeight, 0)
     */
    public void learn(int[][] learnMatrix, int limitHeight)
    {
        learn(learnMatrix, limitHeight, 0);
    }
    
    /**
     * add realizations of class, calculate etalon vector and find radius
     * @param learnMatrix matrix with realizations
     * @param limitHeight value of realizations to learn. should be less then learnMatrix height
     * @param limitWidth value of attributes that should be unlearned
     */
    public void learn(int[][] learnMatrix, int limitHeight, int limitWidth) throws IllegalArgumentException
    {
        if (learnMatrix.length < limitHeight)
        {
            throw new IllegalArgumentException("param limit should be <"+learnMatrix.length);
        }
        
        int width = learnMatrix[0].length;
        realizations = new int[limitHeight][width - limitWidth];
        for (int i = 0; i < limitHeight; i++)
        {
            for (int j = 0; j < width - limitWidth; j ++)
            {
                realizations[i][j] = learnMatrix[i][j];
            }
        }
        
        calculateEtalon();
        findRadius();
        
        attributesCount = etalonVector.length;
        realizationsCount = realizations.length;
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
    
    /**
     * create two-dimensial coordinates of etalon and realizations inside IRClass
     * can be bugs if attributes value is not divide on 2
     */
    public void convertToTwoDimensialSpace()
    {
        int [] tdEtalon = new int[2];
        int [][] tdRealizations = new int[realizationsCount][2];
        double tdRadius=0;
        
        int x1 =0, x2 = 0;
        for (int i = 0; i < attributesCount/2; i++)
        {
            x1 = x1 + etalonVector[i];
            x2 = x2 + etalonVector[attributesCount - i-1];
        }
        tdEtalon[0] = x1/(attributesCount/2);
        tdEtalon[1] = x2/(attributesCount/2);
        
        for (int h = 0; h < realizationsCount; h++)
        {
            x1 = 0;
            x2 = 0;
            
            for (int w = 0; w < attributesCount/2; w++)
            {
                x1 = x1 + realizations[h][w];
                x2 = x2 + realizations[h][attributesCount - w-1];
            }
            
            tdRealizations[h][0] = x1/(attributesCount/2);
            tdRealizations[h][1] = x2/(attributesCount/2);
        }
        
        for ( int [] tdRealization : tdRealizations)
        {
            double newRadius = calculateDistance(tdEtalon, tdRealization);
            if(newRadius > tdRadius)
            {
                tdRadius = newRadius;
            }   
        }
        
        twoDimensionalData = new TwoDimensionalData(tdEtalon, tdRealizations, tdRadius);
    }
    
    /**
     * contain etalon and realizations for two-dimensional graphic
     */
    public class TwoDimensionalData
    {
        private int [] twoDimEtalon;
        private int [][] twoDimRealizations;
        private double twoDimRadius;

        public TwoDimensionalData(int[] twoDimEtalon, int[][] twoDimRealizations, double twoDimRadius) 
        {
            this.twoDimEtalon = twoDimEtalon;
            this.twoDimRealizations = twoDimRealizations;
            this.twoDimRadius = twoDimRadius;
        }

        public int[] getTwoDimEtalon() 
        {
            return twoDimEtalon;
        }

        public int[][] getTwoDimRealizations() 
        {
            return twoDimRealizations;
        }
        
        public double getTwoDimRadius() 
        {
            return twoDimRadius;
        }
    
    }
    
}
