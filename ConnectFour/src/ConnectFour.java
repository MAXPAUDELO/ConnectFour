import java.io.*;
import java.util.*;
public class ConnectFour {

    private static Board mainBoard;
    public static String number;
    private static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
    private static boolean done = false;
    private static int compMove; //global variable used to determine the best next position for the computer to place a chip

    public static void main(String[] args)throws IOException {
        mainBoard = new Board();


        while(!done)
        {
            number = stdin.readLine();
            if(Integer.parseInt(number) < 0 || Integer.parseInt(number) >6) // doesn't cause an out of bounds
                done = true;
            else
                mainBoard.place(Integer.parseInt(number),1);

            if(mainBoard.isWinner(1)) //check to see if the player has won after their move
            {
                done = true;
                System.out.println("You have won!");
            }

            max(mainBoard.copyBoard(), 4); //changes compMove for computer
            mainBoard.place(compMove, 2);

            if(mainBoard.isWinner(2)) // check to see if the computer has won after its move
            {
                done = true;
                System.out.println("You have lost!");
            }
        }
    }

    /**
     * This method will be called upon the computer's turn to make a move. It will also recursively call and recursively be called by min(). It is
     * used to generate all the possible scenarios that the computer can place a chip and choose the one with the highest heuristic value.
     * The compMove global variable will be changed to the computer's best next move by the end of the function.
     * @param board The board to go off of when generating the next level of options for moves
     * @param depth The depth of level of nodes left to generate
     * @return The heuristic that has the highest value for the level of nodes at that time
     */
    private static int max(Board board, int depth)
    {
        int heuristic = -1000;
        if(depth == 1)
        {
            for (int i = 0; i<7; i++)
            {
                board.place(i,2);
                int h = board.getHeuristic();
                if(h>heuristic)
                    heuristic = h;
                board.remove(i);
            }
            return heuristic;
        }    
        else
        {
            for(int i = 0; i<7; i++)
            {
                board.place(i,2);
                int h = min(board, depth-1);
                if(h>heuristic)
                {
                    compMove = i;
                    heuristic = h;
                }
                board.remove(i);
            }
        }
        return heuristic;
    }

     /**
     * This method will be recursively called by as well as recursively call max(). It is used to generate all the possible scenarios that the player 
     * can place a chip and choose the one with the lowest heuristic value in an attempt to guess what the player would do in their best interest.
     * @param board The board to go off of when generating the next level of options for moves
     * @param depth The depth of level of nodes left to generate
     * @return The heuristic that has the lowest value for the level of nodes at that time
     */
    private static int min(Board board, int depth)
    {
        int heuristic = 1000;
        if(depth == 1)
        {
            for (int i = 0; i<7; i++)
            {
                board.place(i,1);
                int h = board.getHeuristic();
                if(h<heuristic)
                    heuristic = h;
                board.remove(i);
            }
            return heuristic;
        }    
        else
        {
            for(int i = 0; i<7; i++)
            {
                board.place(i,1);
                int h = max(board, depth-1);
                if(h<heuristic)
                    heuristic = h;
                board.remove(i);
            }
        }
        return heuristic;
    }

public void setNumber(String num)
{
	number = num;
}
}
