package berestlabs;

import java.util.ArrayList;
import java.util.Map;

/**
 * class to test IRClass for correct recognition
 */
public class IRExamenator 
{
    private ArrayList<IRClass> EXAM_CLASSES = new ArrayList<>();
    
    public void addExamClass(IRClass c)
    {
        EXAM_CLASSES.add(c);
    }
    
    /**
     * exam clases and show statictics
     * 
     * @return effectivenes of recognition
     * 
     * @param testMaterial ArrayList with pairs vector-expected class
     * @param log 'true' to print info on console
     */
    public double exam(ArrayList<ExamPair> testMaterial, boolean log)
    {
        int sucessCount = 0;
        int failCount = 0;
        for (ExamPair pair : testMaterial)
        {
            boolean result = test(pair.getVector(), pair.getExpectedClass(), log);
            if(result)
            {
                sucessCount++;
            }
            else
            {
                failCount++;
            }
        }
        
        System.out.println("Exam finished");
        System.out.println(sucessCount + " recognized correctly, " + failCount + " recognized wrong");
        
        return (double)sucessCount/(sucessCount+failCount)*100;
    }
    
    /**
     * test recognition
     * 
     * @param realization vector of class realization
     * @param expectedClass
     * @param log 'true' to print info on console
     * @return true if result of recognition is same to expected result
     */
    public boolean test(int [] realization, IRClass expectedClass, boolean log) 
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
    public IRClass recognize(int [] realization)
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
    
    /**
     * class to contain exam pair (vector - expected class)
     * used to send data to exam method by one call
     * 
     * used because TreeMap cannot used vith arrays in key
     */
    public static class ExamPair
    {
        int[] vector;
        IRClass expectedClass;

        public ExamPair(int [] vector, IRClass expectedClass) 
        {
            this.vector = vector;
            this.expectedClass = expectedClass;
        } 
        
        int[] getVector()
        {
            return vector;
        }
        
        IRClass getExpectedClass()
        {
            return expectedClass;
        }
    }
    
}
