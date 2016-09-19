package berestlabs;

import java.util.ArrayList;

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
