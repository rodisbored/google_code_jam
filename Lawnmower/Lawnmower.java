/**
 * Author: Roderick Buenviaje 
 * Date: April 13, 2013 
 * Problem: Lawnmower
 * 
 * 
 * Problem 
 * Alice and Bob have a lawn in front of their house, shaped like an N
 * metre by M metre rectangle. Each year, they try to cut the lawn in some
 * interesting pattern. They used to do their cutting with shears, which was
 * very time-consuming; but now they have a new automatic lawnmower with
 * multiple settings, and they want to try it out.
 * 
 * The new lawnmower has a height setting - you can set it to any height h
 * between 1 and 100 millimetres, and it will cut all the grass higher than h it
 * encounters to height h. You run it by entering the lawn at any part of the
 * edge of the lawn; then the lawnmower goes in a straight line, perpendicular
 * to the edge of the lawn it entered, cutting grass in a swath 1m wide, until
 * it exits the lawn on the other side. The lawnmower's height can be set only
 * when it is not on the lawn.
 * 
 * Alice and Bob have a number of various patterns of grass that they could have
 * on their lawn. For each of those, they want to know whether it's possible to
 * cut the grass into this pattern with their new lawnmower. Each pattern is
 * described by specifying the height of the grass on each 1m x 1m square of the
 * lawn.
 * 
 * The grass is initially 100mm high on the whole lawn.
 * 
 * Input 
 * The first line of the input gives the number of test cases, T. T test
 * cases follow. Each test case begins with a line containing two integers: N
 * and M. Next follow N lines, with the ith line containing M integers ai,j
 * each, the number ai,j describing the desired height of the grass in the jth
 * square of the ith row.
 * 
 * Output 
 * For each test case, output one line containing "Case #x: y", where x
 * is the case number (starting from 1) and y is either the word "YES" if it's
 * possible to get the x-th pattern using the lawnmower, or "NO", if it's
 * impossible (quotes for clarity only).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Lawnmower
{
    public static void main(String[] args)
    {
        String sDefaultFile = "B-large.in";
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
        
        String[] dimensions = null;
        String[] rows = null;

        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());
                
                System.out.println("Processing " + inputN + " Values...");
                
                
                for (int i = 0; i < inputN; i++)
                {
                    out.write(String.format("Case #%d: ", i+1));
                    
                    dimensions = reader.readLine().split("\\s+");
                    
                    int[][] lawn = new int[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
                    
                    for (int j = 0; j < Integer.parseInt(dimensions[0]); j++)
                    {
                        rows = reader.readLine().split("\\s+");
                        for (int k = 0; k < rows.length; k++)
                        {
                            lawn[j][k] = Integer.parseInt(rows[k]);
                        }
                    }
                    
                    // Process the lawn here and check
                    if (checkPattern(lawn, Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1])))
                    {
                        out.write(String.format("YES\n"));
                    }
                    else
                    {
                        out.write(String.format("NO\n"));
                    }
                    
//                    for (int[] row : lawn)
//                        System.out.println(Arrays.toString(row));
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
    
    public static Boolean checkPattern(int[][] lawn, int x, int y)
    {
        Boolean columnCheck = true;
        Boolean rowCheck = true;
        
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                int cell = lawn[i][j];
                
                columnCheck = true;
                rowCheck = true;
                
                for (int k = 0; k < x; k++)
                {
                    // Check columns
                    if (cell < lawn[k][j])
                    {
                        columnCheck = false;
                        break;
                    }
                }
                
                if (!columnCheck)
                {
                    // Check rows
                    for (int k = 0; k < y; k++)
                    {
                        if (cell < lawn[i][k])
                        {
                            rowCheck = false;
                        }
                    }
                }
                
                // If both fail, then the lawn can not be created
                if (!columnCheck && !rowCheck)
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}
