package EfectiveTester;

import static berestlabs.BerestLabs.LEARNING_LIMIT;
import berestlabs.IRClass;
import berestlabs.IRExamenator;
import berestlabs.IRUtil;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * test effectiveness of recognizing according to value of classes, class attributes and class realizations
 */
public class EffectiveTester 
{
    private static int LEARNING_LIMIT = 80;
    private static Workbook book;
    
    public static void main(String[] args) throws IOException 
    {
        testByRealizations(args, false);
        System.out.println("\n\n");
        testByAttribute(args, false);
        System.out.println("\n\n");
        testByClasses(args, false);
    }
    
    static void testByRealizations(String[] args, boolean writeToXML) throws IOException
    {
        int realizationLimit = 5;
        
        while (realizationLimit < 100)
        {
            double effect = test(args, 2, 0, realizationLimit);
            System.out.println("Learning limit = " + realizationLimit + " effectiveness = "+ effect);
            realizationLimit = realizationLimit + 5;
        }
    }
    
    static void testByAttribute(String[] args, boolean writeToXML) throws IOException
    {
        int ignoreAttributes = 0;
        
        while (ignoreAttributes < 100)
        {
            double efect = test(args, 2, ignoreAttributes, LEARNING_LIMIT);
            System.out.println("Attributes count = " + (100-ignoreAttributes) + " effectiveness = " + efect);
            ignoreAttributes = ignoreAttributes + 5;
        }
    }
    
    static void testByClasses(String[] args, boolean writeToXML) throws IOException
    {
        int classCount = 2;
        
        while (classCount <= args.length)
        {
            double efect = test(args, classCount, 0, LEARNING_LIMIT);
            System.out.println("Class count = " + classCount + " effectiveness = " + efect);
            classCount++;
        }
    }
    
    /**
     * calculate effectiveness of learning according to params
     * @param args pathes to learning images
     * @param classCount count of classes to learn
     * @param attributesLimit value of atttribute limit (image width limit)
     * @param realizationsLimit value of learning limit (image height limit)
     * @return effectiveness of learning
     * @throws IOException 
     */
    static double test(String[] args, int classCount, int attributesLimit, int realizationsLimit) throws IOException
    {
        IRExamenator examenator = new IRExamenator();
        
        ArrayList<IRExamenator.ExamPair> examPairs = new ArrayList<>();
        for (int i = 0; i < classCount; i ++)
        {
            int[][] mas = IRUtil.imageToMatrix(IRUtil.loadImage(args[i]));
            IRClass c = new IRClass();
            c.learn(mas, realizationsLimit,attributesLimit);
            examenator.addExamClass(c);

            for (int j = realizationsLimit; j < mas.length; j++)
            {
                examPairs.add(new IRExamenator.ExamPair(mas[j], c));
            }
        }

        return examenator.exam(examPairs, false);
    }
    
}
