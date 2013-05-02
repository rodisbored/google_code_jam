/**
 * Author: Roderick Buenviaje
 * Date: April 26, 2013
 * Problem: Bullseye
 */

/*
 * This problem was Round 1 A for GCJ 2013
 * 
 * Maria has been hired by the Ghastly Chemicals Junkies (GCJ) company to help
 * them manufacture bullseyes. A bullseye consists of a number of concentric
 * rings (rings that are centered at the same point), and it usually represents
 * an archery target. GCJ is interested in manufacturing black-and-white
 * bullseyes. 
 * 
 * Maria starts with t millilitres of black paint, which she will use
 * to draw rings of thickness 1cm (one centimetre). A ring of thickness 1cm is
 * the space between two concentric circles whose radii differ by 1cm. 
 * 
 * Maria draws the first black ring around a white circle of radius r cm. Then she
 * repeats the following process for as long as she has enough paint to do so:
 * 
 * 1. Maria imagines a white ring of thickness 1cm around the last black ring. 
 * 2. Then she draws a new black ring of thickness 1cm around that white ring. 
 * 
 * Note that each "white ring" is simply the space between two black rings. 
 * 
 * The area of a disk with radius 1cm is π cm2. One millilitre of paint is required to cover
 * area π cm2. What is the maximum number of black rings that Maria can draw?
 * Please note that: 
 * 
 * Maria only draws complete rings. If the remaining paint is not enough to draw
 * a complete black ring, she stops painting immediately. 
 * There will always be enough paint to draw at least one black ring.
 * 
 * Input 
 * The first line of the input gives the number of test cases, T. T test
 * cases follow. Each test case consists of a line containing two space
 * separated integers: r and t. 
 * 
 * Output 
 * For each test case, output one line containing "Case #x: y", where x
 * is the case number (starting from 1) and y is the maximum number of black
 * rings that Maria can draw.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Bullseye
{
    public static void main(String[] args)
    {
        String sDefaultFile = "A-small-attempt0.in";
//        String sDefaultFile = "A-large.in";
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

        try
        {
            reader = new BufferedReader(new FileReader(new File(sFile)));
            String outString = sDefaultFile.substring(0, sFile.lastIndexOf('.'));
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

        String[] values;
        
        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                
                for (int i = 0; i < inputN; i++)
                {
                    out.write(String.format("Case #%d: ", 1));
                    
                    values = reader.readLine().split("\\s+");
                    BigInteger rVal = new BigInteger(values[0]);
                    BigInteger tVal = new BigInteger(values[1]);
                    
                    BigInteger circles;
                    
//                    int val = rVal.intValue() * 2 +1;
                    
//                    long rValL = rVal.longValue() * -1;
//                    long tValL = tVal.longValue();
//                    
//                    long circ = (long)((rValL + Math.sqrt(rValL*rValL + 8 * tValL))/4);
                    
//                    rVal = (rVal.multiply(new BigInteger("2"))).add(BigInteger.ONE);
//                    
//                    (rVal.pow(2)).add(tVal.multiply((new BigInteger("8"))));
//                    circles = ((rVal.negate()).add(new BigInteger(Integer.toString((int)Math.sqrt(((rVal.pow(2)).add(tVal.multiply((new BigInteger("8")))).doubleValue())))))).divide(new BigInteger("4"));
                    
//                    circles = (tVal.add(new BigInteger("4"))).divide(
//                            ((rVal.multiply(new BigInteger("2")).add(new BigInteger("5")))));
//                    
//                    out.write(circ + "\n");
//                    out.write(circles.toString() + "\n");

                    BigInteger totalCount = BigInteger.ZERO;
                    
                    // This gives us our starting amount of black paint we need
                    rVal = (rVal.multiply(new BigInteger("2"))).add(BigInteger.ONE);

                    tVal = tVal.subtract(rVal);
                    
                    // This isn't really needed since the problem states we will always have
                    // enough paint for one circle
                    if (tVal.compareTo(BigInteger.ZERO) != -1)
                    {
                        totalCount = totalCount.add(BigInteger.ONE);
                    }

                    while (tVal.compareTo(BigInteger.ZERO) == 1)
                    {
                        // Subtract every other odd number
                        rVal = rVal.add(new BigInteger("4"));
                        
                        tVal = tVal.subtract(rVal);

                        if (tVal.compareTo(BigInteger.ZERO) != -1)
                        {
                            totalCount = totalCount.add(BigInteger.ONE);
                        }
                        
                        if (tVal.subtract(rVal).compareTo(BigInteger.ZERO) == -1)
                        {
                            break;
                        }
                    }
                    
                    out.write(totalCount.toString() + "\n");
                    
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
    
    public static int tempFunc()
    {
        return 0;
    }
}
