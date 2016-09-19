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
    
    public static void test(int [] realization, IRClass expectedClass, boolean log) 
    {
        double minDistance = Integer.MAX_VALUE;
        String recognizedClass = "unrecognized"; // chang to IRClass ?
        boolean 
        
        for (IRClass examinee : EXAM_CLASSES)
        {
            double dst = IRClass.calculateDistance(realization, examinee.getEtalonVector());
            if (dst < minDistance)
            {
                minDistance = dst;
                recognizedClass = examinee.getName();
            }
        }
        
        if(log)
        {
            System.out.println(recognizedClass + " recognized\t" + expectedClass.getName() + " expected");
        }
    }
    
    public static IRClass recognize(int [] realization)
    {
        return null;
    }
    
}
