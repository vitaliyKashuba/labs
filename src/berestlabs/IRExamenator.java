package berestlabs;

import java.util.ArrayList;
import java.util.Map;

/**
 * class to test IRClass for correct recognition
 */
public class IRExamenator 
{
    private static ArrayList<IRClass> EXAM_CLASSES = new ArrayList<>();
    
    public static void addExamClass(IRClass c)
    {
        EXAM_CLASSES.add(c);
    }
    
    public static void exam(Map testMaterial)
    {
        
    }
    
    /**
     * test recognition
     * 
     * @param realization vector of class realization
     * @param expectedClass
     * @param log 'true' to print info on console
     * @return true if result of recognition is same to expected result
     */
    public static boolean test(int [] realization, IRClass expectedClass, boolean log) 
    {
        IRClass recognized = recognize(realization);
        boolean isRcnz;
        
        if (recognized.equals(expectedClass))
        {
            isRcnz = true;
        }
        else
        {
            isRcnz = false;
        }
        
        if(log)
        {
            System.out.println(recognized.getName() + " recognized\t" + expectedClass.getName() + " expected\t " + (isRcnz ? "correct" : "incorrect"));
        }
        
        return isRcnz;
    }
    
    /**
     * try to recognize class
     * @param realization vector of class realization
     */
    public static IRClass recognize(int [] realization)
    {
        double minDistance = Integer.MAX_VALUE;
        IRClass recognizedClass = null; // chang to IRClass ?
        
        for (IRClass examinee : EXAM_CLASSES)
        {
            double dst = IRClass.calculateDistance(realization, examinee.getEtalonVector());
            if (dst < minDistance)
            {
                minDistance = dst;
                recognizedClass = examinee; // should be cloned?
            }
        }
        
        return recognizedClass;
    }
    
}
