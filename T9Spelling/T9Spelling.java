/**
 * Author: Roderick Buenviaje
 * Date: April 1, 2013
 * Problem: Reverse Words
 */

/**
 * This is the code that I wrote to practice for the Google Code Jam
 * 
 * Problem
 * Given a list of space separated words, reverse the order of the words. Each line of text contains L letters and W words. 
 * A line will only consist of letters and space characters. There will be exactly one space character between each pair of consecutive words.
 * 
 * Input
 * The first line of input gives the number of cases, N.
 * N test cases follow. For each test case there will a line of letters and space characters indicating a list of space separated words. 
 * Spaces will not appear at the start or end of a line.
 * 
 * Output
 * For each test case, output one line containing "Case #x: " followed by the list of words in reverse order.
 */

import java.io.*;

public class T9Spelling
{
    private static String num1 = null;
    private static String num2 = "abc";
    private static String num3 = "def";
    private static String num4 = "ghi";
    private static String num5 = "jkl";
    private static String num6 = "mno";
    private static String num7 = "pqrs";
    private static String num8 = "tuv";
    private static String num9 = "wxyz";
    
    private static String[] numbers = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    /**
     * @param args - Input file
     */
    public static void main(String[] args)
    {
        String sDefaultFile = "C-large-practice.in";
        String sFile = null;
        BufferedReader reader = null;
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
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Exiting: " + sFile + " does not exist");
            e.printStackTrace();
            return;
        }
        
        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                t9_translate(inputN, reader);
                System.out.println("Processing Done!");
                
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private static void t9_translate(int inputN, BufferedReader reader)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("Output.txt"));
            String convertString = null;
            int iNumChar = 0;
        
            for (int i = 1; i <= inputN; i++)
            {
                out.write(String.format("Case #%d: ", i));
                
                convertString = reader.readLine();
                iNumChar = convertString.length();
                

                if (iNumChar > 1000)
                {
                    System.out.println("Error: Test case has more than 1000 characters. Skipping.");
                    out.write("ERROR!\n");
                    out.flush();
                    continue;
                }
                
                int iPreviousIndex = 0;
                
                for (int j = 0; j < iNumChar; j++)
                {
                    int iNumIndex = 0;
                    int iLetterIndex = 0;
                    
                    boolean bMatch = false;
                    
                    while (!bMatch && iNumIndex < 11)
                    {
                        iLetterIndex = numbers[iNumIndex].indexOf(convertString.charAt(j));
                        if (iLetterIndex != -1)
                        {
                            bMatch = true;
                            break;
                        }
                        iNumIndex++;
                    }
                    
                    if (iPreviousIndex == iNumIndex)
                    {
                        // Print space since we are using the same button
                        out.write(" ");
                    }
                    
                    for (int k = 0; k <= iLetterIndex; k++)
                    {
                        out.write(String.valueOf(iNumIndex));
                    }
                    
                    iPreviousIndex = iNumIndex;
                }
                out.write("\n");
                out.flush();
            }
            
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
