/**
 * Author: Roderick Buenviaje 
 * Date: April 12, 2013 
 * Problem: Tic-Tac-Toe-Tomek
 * 
 * Problem
 * Tic-Tac-Toe-Tomek is a game played on a 4 x 4 square board. The board starts
 * empty, except that a single 'T' symbol may appear in one of the 16 squares.
 * There are two players: X and O. They take turns to make moves, with X
 * starting. In each move a player puts her symbol in one of the empty squares.
 * Player X's symbol is 'X', and player O's symbol is 'O'.
 * 
 * After a player's move, if there is a row, column or a diagonal containing 4
 * of that player's symbols, or containing 3 of her symbols and the 'T' symbol,
 * she wins and the game ends. Otherwise the game continues with the other
 * player's move. If all of the fields are filled with symbols and nobody won,
 * the game ends in a draw. See the sample input for examples of various winning
 * positions.
 * 
 * Given a 4 x 4 board description containing 'X', 'O', 'T' and '.' characters
 * (where '.' represents an empty square), describing the current state of a
 * game, determine the status of the Tic-Tac-Toe-Tomek game going on. The
 * statuses to choose from are:
 * 
 * "X won" (the game is over, and X won) "O won" (the game is over, and O won)
 * "Draw" (the game is over, and it ended in a draw) "Game has not completed"
 * (the game is not over yet) If there are empty cells, and the game is not
 * over, you should output "Game has not completed", even if the outcome of the
 * game is inevitable. 
 * 
 * Input
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of 4 lines with 4 characters each, with each
 * character being 'X', 'O', '.' or 'T' (quotes for clarity only). Each test
 * case is followed by an empty line.
 * 
 * Output
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is one of the statuses given above. Make
 * sure to get the statuses exactly right. When you run your code on the sample
 * input, it should create the sample output exactly, including the "Case #1: ",
 * the capital letter "O" rather than the number "0", and so on.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TTTTomek
{
    public static void main(String[] args)
    {
        String sDefaultFile = "A-large.in";
        String sFile = null;
        BufferedReader reader = null;
        BufferedWriter out = null;
        
        String unknown = "Game has not completed";
        String xWon = "X won";
        String oWon = "O won";
        String draw = "Draw";
        
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
        
        char[][] board = new char[4][4];
        char winCheck = 0;
        Boolean foundWinner = false;
        int loopCount = 0;
        
        if (reader != null)
        {
            try 
            {
                inputN = Integer.parseInt(reader.readLine());

                System.out.println("Processing " + inputN + " Values...");
                
                for (int i = 0; i < inputN; i++)
                {
                    out.write(String.format("Case #%d: ", i+1));
                    
                    foundWinner = false;
                    loopCount = 0;
                    winCheck = 0;
                    
                    for (int j = 0; j < 4; j++)
                    {
                        loopCount++;
                        board[j] = reader.readLine().toCharArray();
                        winCheck = rowCheck(board[j]);
                        if (winCheck != 0)
                        {
                            foundWinner = true;
                            for (int k = 0; k < 4-loopCount; k++)
                            {
                                reader.readLine();
                            }
                            break;
                        }
                    }

                    if (!foundWinner)
                    {
                        for (int l = 0; l < 4; l++)
                        {
                            char[] column = {board[0][l], board[1][l], board[2][l], board[3][l]};
                            
                            winCheck = rowCheck(column);
                            if (winCheck != 0)
                            {
                                foundWinner = true;
                                break;
                            }
                        }
                    }
                    if (!foundWinner)
                    {
                        winCheck = diagCheck1(board);
                        if (winCheck != 0)
                        {
                            foundWinner = true;
                        }
                    }
                    if (!foundWinner)
                    {
                        winCheck = diagCheck2(board);
                        if (winCheck != 0)
                        {
                            foundWinner = true;
                        }
                    }
                    if (!foundWinner)
                    {
                        winCheck = drawCheck(board);
                    }
                    
                    if (winCheck == 0)
                    {
                        out.write(draw + "\n");
                    }
                    else if (winCheck == 'X')
                    {
                        out.write(xWon + "\n");
                    }
                    else if (winCheck == 'O')
                    {
                        out.write(oWon + "\n");
                    }
                    else if (winCheck == '.')
                    {
                        out.write(unknown + "\n");
                    }
                    
                    reader.readLine();
                    
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
    
    public static char rowCheck(char[] row)
    {
        char initialChar = 0;
        int startChar = 0;
        
        initialChar = row[0];
        
        if (initialChar == 'T')
        {
            initialChar = row[1];
            startChar = 1;
        }
        if (initialChar == '.')
        {
            return 0;
        }

        for (int i = startChar; i < 3; i++)
        {
            if (row[i+1] == 'T')
            {
                continue;
            }
            else if (initialChar != row[i+1])
            {
                return 0;
            }
        }
        
        if (initialChar == 'X')
        {
            return 'X';
        }
        else
        {
            return 'O';
        }
    }
    
    public static char diagCheck1(char[][] board)
    {
        char initialChar = 0;
        int iStartChar = 0;
        
        initialChar = board[0][0];
        
        if (initialChar == 'T')
        {
            initialChar = board[1][1];
            iStartChar = 1;
        }
        if (initialChar == '.')
        {
            return 0;
        }
        
        for (int i = iStartChar; i < 4; i++)
        {
            if (board[i][i] == 'T')
            {
                continue;
            }
            else if (initialChar != board[i][i])
            {
                return 0;
            }
        }
        
        if (initialChar == 'X')
        {
            return 'X';
        }
        else
        {
            return 'O';
        }
    }
    
    public static char diagCheck2(char[][] board)
    {
        char initialChar = 0;
        int iStartCharX = 0;
        int iStartCharY = 3;
        
        initialChar = board[0][3];
        
        if (initialChar == 'T')
        {
            initialChar = board[1][2];
            iStartCharX = 1;
            iStartCharY = 2;
        }
        if (initialChar == '.')
        {
            return 0;
        }
        
        for (int i = 0; i < 4-iStartCharX; i++)
        {
            if (board[iStartCharX+i][iStartCharY-i] == 'T')
            {
                continue;
            }
            else if (initialChar != board[iStartCharX+i][iStartCharY-i])
            {
                return 0;
            }
        }
        
        if (initialChar == 'X')
        {
            return 'X';
        }
        else
        {
            return 'O';
        }
    }
    
    public static char drawCheck(char[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (board[i][j] == '.')
                {
                    return '.';
                }
            }
        }
        
        return 0;
    }
}
