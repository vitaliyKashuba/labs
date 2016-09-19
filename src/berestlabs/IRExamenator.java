package berestlabs;

import java.util.ArrayList;

/**
 * class to test IRClass for correct recognition
 */
public class IRExamenator 
{
    private static ArrayList<IRClass> EXAM_CLASSES;
    
    public static void addExamClass(IRClass c)
    {
        EXAM_CLASSES.add(c);
    }
    
    public static void test(int [] realization, IRClass expectedClass) 
    {
        
    }
}
