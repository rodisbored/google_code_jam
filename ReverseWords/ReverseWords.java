import java.io.*;

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


public class ReverseWords
{

    /**
     * @param args - Input file
     */
    public static void main(String[] args)
    {
        String sDefaultFile = "B-small.in";
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
                processWords(inputN, reader);
                System.out.println("Processing Done!");
                
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private static void processWords(int inputN, BufferedReader reader)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("Output.txt"));
            String[] aWords = null;
            int iNumWords = 0;
        
            for (int i = 1; i <= inputN; i++)
            {
                out.write(String.format("Case #%d: ", i));
                
                aWords = reader.readLine().split("\\s+");
                iNumWords = aWords.length;
                if (iNumWords > 1000)
                {
                    System.out.println("Error: Test case has more than 1000 words. Skipping.");
                    out.write("ERROR!\n");
                    out.flush();
                    continue;
                }
                for (int j = iNumWords; j > 0; j--, iNumWords--)
                {
                    // Array is 0 indexed so we need to subtract one
                    out.write(aWords[iNumWords-1]);
                    out.write(" ");
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
