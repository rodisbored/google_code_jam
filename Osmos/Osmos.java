/**
 * Author: Roderick Buenviaje
 * Date: May 4, 2013
 * Problem: Osmos
 * Round 1B A for GCJ 2013
 */

/*
 * Armin is playing Osmos, a physics-based puzzle game developed by Hemisphere
 * Games. In this game, he plays a "mote", moving around and absorbing smaller
 * motes. 
 * 
 * A "mote" in English is a small particle. In this game, it's a thing
 * that absorbs (or is absorbed by) other things! The game in this problem has a
 * similar idea to Osmos, but does not assume you have played the game. 
 * 
 * When Armin's mote absorbs a smaller mote, his mote becomes bigger by the smaller
 * mote's size. Now that it's bigger, it might be able to absorb even more
 * motes. For example: suppose Armin's mote has size 10, and there are other
 * motes of sizes 9, 13 and 19. At the start, Armin's mote can only absorb the
 * mote of size 9. When it absorbs that, it will have size 19. Then it can only
 * absorb the mote of size 13. When it absorbs that, it'll have size 32. Now
 * Armin's mote can absorb the last mote. 
 * 
 * Note that Armin's mote can absorb another mote if and only if the other mote is smaller. 
 * If the other mote is the same size as his, his mote can't absorb it. 
 * 
 * You are responsible for the program that creates motes for Armin to absorb. The program 
 * has already created some motes, of various sizes, and has created Armin's mote.
 * Unfortunately, given his mote's size and the list of other motes, it's
 * possible that there's no way for Armin's mote to absorb them all. 
 * 
 * You want to fix that. There are two kinds of operations you can perform, in any order,
 * any number of times: you can add a mote of any positive integer size to the
 * game, or you can remove any one of the existing motes. What is the minimum
 * number of times you can perform those operations in order to make it possible
 * for Armin's mote to absorb every other mote? 
 * 
 * For example, suppose Armin's mote is of size 10 and the other motes are of sizes 
 * [9, 20, 25, 100]. This game isn't currently solvable, but by adding a mote of size 3 and 
 * removing the mote of size 100, you can make it solvable in only 2 operations. The
 * answer here is 2. 
 * 
 * Input 
 * The first line of the input gives the number of test
 * cases, T. T test cases follow. The first line of each test case gives the
 * size of Armin's mote, A, and the number of other motes, N. The second line
 * contains the N sizes of the other motes. All the mote sizes given will be
 * integers. 
 * 
 * Output 
 * For each test case, output one line containing "Case #x: y",
 * where x is the case number (starting from 1) and y is the minimum number of
 * operations needed to make the game solvable.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Osmos
{
    static long operations = 0;
    
    static long initialMote = 0;
    static long numMotes = 0;
    
    static long currentMoteSize = 0;
    
    public static void main(String[] args)
    {
//        String sDefaultFile = "A-small-attempt0.in";
        String sDefaultFile = "A-large.in";
        String sFile = null;
        BufferedReader reader = null;
        BufferedWriter out = null;

        long inputN = 0; // Number of test cases to process

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

        List<Long> motes = new ArrayList<Long>();
        String[] values;
        
        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                
                long initialMoteNumber = 0;
                
                for (long i = 0; i < inputN; i++)
                {
                    out.write(String.format("Case #%d: ", i+1));
                    values = reader.readLine().split("\\s+");
                    
                    initialMote = Long.parseLong(values[0]);
                    numMotes = Long.parseLong(values[1]);
                    
                    motes.clear();
                    
                    currentMoteSize = initialMote;
                    operations = 0;
                    
                    values = reader.readLine().split("\\s+");
                    for (String s : values)
                    {
                        motes.add(Long.parseLong(s));
                    }
                    
                    Collections.sort(motes);
                    
                    if (currentMoteSize == 1)
                    {
                        out.write(motes.size() + "\n");
                        continue;
                    }
                    
                    Iterator<Long> setIterator = motes.iterator();
                    while (setIterator.hasNext()) 
                    {
                        Long num = setIterator.next();
                        if (currentMoteSize > num)
                        {
                            currentMoteSize += num;
                            setIterator.remove();
                        }
                    }
                    
                    initialMoteNumber = motes.size();
                    
                    if (initialMoteNumber != 0 && initialMoteNumber != 1)
                    {
                        recurseFunc(motes);
                        if (operations < initialMoteNumber)
                        {
                            out.write(operations + "\n");
                        }
                        else
                        {
                            out.write(initialMoteNumber + "\n");
                        }
                    }
                    else
                    {
                        out.write(initialMoteNumber + "\n");
                    }
                    
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
    
    public static void recurseFunc(List<Long> motes)
    {
        if (motes.isEmpty())
        {
            return;
        }
        
        Iterator<Long> setIterator = motes.iterator();
        while (setIterator.hasNext()) 
        {
            Long num = setIterator.next();
            if (currentMoteSize > num)
            {
                currentMoteSize += num;
                setIterator.remove();
            }
        }
        
        numMotes = motes.size();
        
        if (numMotes == 0 || numMotes == 1)
        {
            operations += numMotes;
            return;
        }
        else
        {
            currentMoteSize += (currentMoteSize - 1);
            operations++;
            
            recurseFunc(motes);
        }
    }
}
