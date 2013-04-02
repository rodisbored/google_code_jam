/**
 * Author: Roderick Buenviaje
 * Date: April 1, 2013
 * Problem: T9 Spelling
 */

/**
 * This is the code that I wrote to practice for the Google Code Jam
 * 
 * Problem
 * The Latin alphabet contains 26 characters and telephones only have ten digits on the keypad. 
 * We would like to make it easier to write a message to your friend using a sequence of keypresses to indicate 
 * the desired characters. The letters are mapped onto the digits as shown below. To insert the character B for 
 * instance, the program would press 22. In order to insert two characters in sequence from the same key, 
 * the user must pause before pressing the key a second time. The space character ' ' should be printed to indicate 
 * a pause. For example, 2 2 indicates AA whereas 22 indicates B.
 * 
 * Input
 * The first line of input gives the number of cases, N. N test cases follow. Each case is a line of text formatted as
 * 
 * Each message will consist of only lowercase characters a-z and space characters ' '. Pressing zero emits a space.
 * 
 * 
 * Output
 * For each test case, output one line containing "Case #x: " followed by the message translated into the sequence of keypresses.
 *
 * https://code.google.com/codejam/contest/351101/dashboard#s=p2
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
