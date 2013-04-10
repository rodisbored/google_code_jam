/**
 * Author: Roderick Buenviaje
 * Date: April 9, 2013
 * Problem: Minimum Scalar Product
 */

/**
 * Problem
 * You are given two vectors v1=(x1,x2,...,xn) and v2=(y1,y2,...,yn). The scalar product of these vectors is a single number, 
 * calculated as x1y1+x2y2+...+xnyn.
 * Suppose you are allowed to permute the coordinates of each vector as you wish. Choose two permutations such 
 * that the scalar product of your two new vectors is the smallest possible, and output that minimum scalar product.
 * 
 * Input
 * The first line of the input file contains integer number T - the number of test cases. For each test case, 
 * the first line contains integer number n. The next two lines contain n integers each, giving the coordinates 
 * of v1 and v2 respectively.
 * 
 * Output
 * For each test case, output a line
 * 
 * Case #X: Y
 * 
 * where X is the test case number, starting from 1, and Y is the minimum scalar product of all permutations of the two given vectors.
 *
 * https://code.google.com/codejam/contest/32016/dashboard#s=p0
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MinimumScalarProduct
{
    public static void main(String[] args)
    {
        String sDefaultFile = "A-large-practice.in";
        String sFile = null;
        BufferedReader reader = null;
        BufferedWriter out = null;
        
        int inputN = 0; // Number of test cases to process
        int inputArg = 0;

        if (args.length == 0)
        {
            sFile = sDefaultFile;
            System.out.println("Using Default Input " + sFile);
        }
        else
        {
            sFile = args[0];  
            System.out.println("Using " + sFile);
        }
        
        File inputFile = new File(sFile);
        
        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String outString = sDefaultFile.substring(0, sDefaultFile.lastIndexOf('.'));
            out = new BufferedWriter(new FileWriter(outString + ".out"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Exiting: " + sFile + " does not exist");
            e.printStackTrace();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        String[] vector = null;
        int[] aVector1 = null;
        int[] aVector2 = null;
        
        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                
                for (int j = 0; j < inputN; j++)
                {
                    out.write(String.format("Case #%d: ", j+1));
                    
                    inputArg = Integer.parseInt(reader.readLine());
                    
                    vector = reader.readLine().split("\\s+");
                    aVector1 = new int[vector.length];
                    for (int i = 0; i < vector.length; i++)
                    {
                        aVector1[i] = Integer.parseInt(vector[i]);
                    }
                    
                    vector = reader.readLine().split("\\s+");
                    aVector2 = new int[vector.length];
                    for (int i = 0; i < vector.length; i++)
                    {
                        aVector2[i] = Integer.parseInt(vector[i]);
                    }
                    
                    out.write(String.format("%d\n", computeMinScalar(aVector1, aVector2, inputArg)));
                    out.flush();
                }
                
                System.out.println("Processing Done!");

                out.close();
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static long computeMinScalar(int[] v1, int[] v2, int args)
    {
        long total = 0;
        
        Arrays.sort(v1);
        Arrays.sort(v2);
        
        for (int i = 0; i < args; i++)
        {
            total += (long) v1[i] * v2[args-i-1];
        }
        
        return total;
    }
}
