/**
 * Author: Roderick Buenviaje 
 * Date: April 12, 2013 
 * Problem: Fair and Square
 * 
 * Problem
 * Little John likes palindromes, and thinks them to be fair (which is a fancy
 * word for nice). A palindrome is just an integer that reads the same backwards
 * and forwards - so 6, 11 and 121 are all palindromes, while 10, 12, 223 and
 * 2244 are not (even though 010=10, we don't consider leading zeroes when
 * determining whether a number is a palindrome).
 * 
 * He recently became interested in squares as well, and formed the definition
 * of a fair and square number - it is a number that is a palindrome and the
 * square of a palindrome at the same time. For instance, 1, 9 and 121 are fair
 * and square (being palindromes and squares, respectively, of 1, 3 and 11),
 * while 16, 22 and 676 are not fair and square: 16 is not a palindrome, 22 is
 * not a square, and while 676 is a palindrome and a square number, it is the
 * square of 26, which is not a palindrome.
 * 
 * Now he wants to search for bigger fair and square numbers. Your task is,
 * given an interval Little John is searching through, to tell him how many fair
 * and square numbers are there in the interval, so he knows when he has found
 * them all.
 * 
 * Solving this problem
 * Usually, Google Code Jam problems have 1 Small input and 1 Large input. This
 * problem has 1 Small input and 2 Large inputs. Once you have solved the Small
 * input, you will be able to download any of the two Large inputs. As usual,
 * you will be able to retry the Small input (with a time penalty), while you
 * will get only one chance at each of the Large inputs.
 * 
 * Input
 * The first line of the input gives the number of test cases, T. T lines
 * follow. Each line contains two integers, A and B - the endpoints of the
 * interval Little John is looking at.
 * 
 * Output
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the number of fair and square numbers
 * greater than or equal to A and smaller than or equal to B.
 * 
 * Limits
 * Small dataset
 * 1 ≤ T ≤ 100. 1 ≤ A ≤ B ≤ 1000.
 * 
 * First large dataset
 * 1 ≤ T ≤ 10000. 1 ≤ A ≤ B ≤ 10^14.
 * 
 * Second large dataset
 * 1 ≤ T ≤ 1000. 1 ≤ A ≤ B ≤ 10^100
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FairAndSquare
{
    public static void main(String[] args)
    {
        String sDefaultFile = "C-large-1.in";
        String sFile = null;
        BufferedReader reader = null;
        BufferedWriter out = null;
        
        int inputN = 0; // Number of test cases to process

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
        
        String[] endpoints = null;

        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());
                
                System.out.println("Processing " + inputN + " Values...");
                
                
                // Long set start
                // Compute possible palindromes before looping
//                Double testVal = 0.0;
//                Double counter = 1.0;
//                List<Double> palindromes = new ArrayList<Double>();

//                while (testVal < Double.valueOf("1000000000000000"))
////                while (testVal < Double.valueOf("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"))
//                {
//                    testVal = Math.pow(counter++, 2);
//                    if (palindromeCheck(testVal.doubleValue()))
//                    {
//                        palindromes.add(testVal.doubleValue());
//                    }
//                }
//                
//                System.out.println("proceeding to tests");
                
                // Long set end
                
                for (int i = 0; i < inputN; i++)
                {
                    out.write(String.format("Case #%d: ", i+1));
                    
                    endpoints = reader.readLine().split("\\s+");
                    
                    // Short set
                    out.write(String.format("%d\n", computeFS(BigInteger.valueOf(Long.parseLong(endpoints[0]))
                            , BigInteger.valueOf(Long.parseLong(endpoints[1])))));
                    
                    // Long set
//                    out.write(String.format("%d\n", computeFS(palindromes,
//                            Double.valueOf(endpoints[0]),
//                            Double.valueOf(endpoints[1]))));
                    
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

    // Short set
    public static int computeFS(BigInteger lowLimit, BigInteger highLimit)
    {
        int totalFS = 0;
        List<BigInteger> palindromes = new ArrayList<BigInteger>();
        List<BigInteger> squares = new ArrayList<BigInteger>();
        Double sqAnswer = 0.0;
        
        sqAnswer = Math.sqrt(lowLimit.doubleValue());
        sqAnswer -= (sqAnswer % 1.0);
        
        Double testVal = 0.0;
        Double counter = sqAnswer;

        while (testVal < highLimit.doubleValue())
        {
            testVal = Math.pow(counter++, 2);
            
            if (testVal <= highLimit.doubleValue() && testVal >= lowLimit.doubleValue())
            {
                if (palindromeCheck(testVal.doubleValue()))
                {
                    palindromes.add(BigInteger.valueOf(testVal.longValue()));
                }
            }
        }
        
        for (int i = 0; i < palindromes.size(); i++)
        {
            sqAnswer = Math.sqrt(palindromes.get(i).doubleValue());
            if (sqAnswer % 1.0 == 0)
            {
                if (palindromeCheck(sqAnswer.doubleValue()))
                {
                    squares.add(palindromes.get(i));
                }
            }
        }
        
        totalFS = squares.size();
        
        return totalFS;
    }
    
    // Long set
    public static int computeFS(List<Double> palindromes, Double lowLimit, Double highLimit)
    {
        int totalFS = 0;
        List<Double> squares = new ArrayList<Double>();
        Double sqAnswer = 0.0;
        
        System.out.println(lowLimit.doubleValue());
        
        for (int i = 0; i < palindromes.size(); i++)
        {
            if (palindromes.get(i).doubleValue() < lowLimit.doubleValue())
            {
                continue;
            }
            else if (palindromes.get(i).doubleValue() > highLimit.doubleValue())
            {
                break;
            }
            sqAnswer = Math.sqrt(palindromes.get(i).doubleValue());
            if (sqAnswer % 1.0 == 0)
            {
                if (palindromeCheck(sqAnswer.doubleValue()))
                {
                    squares.add(palindromes.get(i));
                }
            }
        }

        totalFS = squares.size();
        //      System.out.println(squares);

        return totalFS;
    }
    
    public static Boolean palindromeCheck(Double num)
    {
        Double origNum = num;
        Double reverse = 0.0;
        Double digit = 0.0;
        while (num > 0)
        {
            digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }

        if (origNum == reverse)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public static Boolean palindromeCheck(String num)
    {
        int iStringLength = num.length();
        
        for (int i = 0; i < (iStringLength/2+1); i++)
        {
            if (num.charAt(i) != num.charAt(iStringLength-i-1))
            {
                return false;
            }
        }
        return true;
    }
}
