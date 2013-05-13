import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Consonants
{
    static Set<String> vowels = new HashSet<String>(); 
    private static long totalCount = 0;
    
    public static void main(String[] args)
    {
        String sDefaultFile = "A-small-attempt2.in";
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

        String[] inputs = null;
        
        if (reader != null)
        {
            vowels.add("a");
            vowels.add("e");
            vowels.add("i");
            vowels.add("o");
            vowels.add("u");
            
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                
                for (int i = 0; i < inputN; i++)
                {
                    inputs = reader.readLine().split("\\s+");
                    out.write(String.format("Case #%d: ", i+1));
                    totalCount = 0;
                    
                    String name = inputs[0];
                    long nVal = Long.parseLong(inputs[1]);
                    
                    tempFunc(name, nVal);
                    
                    out.write(Long.toString(totalCount) + "\n");
                    
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
    
    public static void tempFunc(String name, long nVal)
    {
        long counter = 0;
        String tempString;
        
        if (name.length() == 0) return;
        
        for (int j = 1; j <= name.length(); j++)
        {
            tempString = name.substring(0, j);
//            if (tempString.matches("(.*)[^aeiou]{"+ nVal + "}(.*)"))
//            {
//                totalCount++;
//            }
            counter = 0;
            for (int k = 0; k < tempString.length(); k++)
            {
                if (vowels.contains(String.valueOf(tempString.charAt(k))))
                {
                    counter = 0;
                }
                else
                {
                    counter++;
                    
                    if (counter >= nVal)
                    {
                        totalCount++;
                        break;
                    }
                }
            }
        }
        
        for (int j = 1; j < name.length(); j++)
        {
            tempString = name.substring(name.length() - j, name.length());
//            if (tempString.matches(".*[^aeiou]{"+ nVal + "}.*"))
//            {
//                totalCount++;
//            }
            counter = 0;
            for (int k = 0; k < tempString.length(); k++)
            {
                if (vowels.contains(String.valueOf(tempString.charAt(k))))
                {
                    counter = 0;
                }
                else
                {
                    counter++;
                    
                    if (counter >= nVal)
                    {
                        totalCount++;
                        break;
                    }
                }
            }
        }
        if (name.length() - 1 > 1)
        {
            tempFunc(name.substring(1, name.length() - 1), nVal);
        }
    }
}
