import java.io.*;

import javax.swing.*;
/**
 * @author (John Centritto, Max Audelo)
 * @date (4/22/2015)
 * This is the main class for a Connect Four game. This class creates a static board that the entire game will take place on.
 * Methods will be called on the board to represent player moves and computer moves. This class also contains the "brain" for 
 * the AI, which is the minimax algorithm.
 * 
 */
public class ConnectFour {
    public static int number;
    private static Board mainBoard;// board used for the entire game
    public static FRAME mainFrame;

    private static boolean firstMove = true;
    private static int compMove; //global variable used to determine the best next position for the computer to place a chip

    public static void main(String[] args)throws IOException {
        mainBoard = new Board();
        mainFrame = new FRAME();
    }

    protected static void placeNumber(int num)
    {
        number = num;
        if(number >6)
        {
            JPanel dialogue = new JPanel();

            JOptionPane.showMessageDialog(dialogue, "Please select a new column.","Column Full!",
                JOptionPane.WARNING_MESSAGE);
        }
        else
        {

            mainBoard.place(number,1);
            //System.out.print(num);

            if(mainBoard.isWinner(1)) //check to see if the player has won after their move
            {

                mainBoard.printBoard();
                JOptionPane.showMessageDialog(null,"You have won!");
                System.exit(0);
                return;
            }

            if(firstMove == true) //makes the first move by the computer a random move
            {
                if(number-1>-1)
                {
                    mainBoard.place(number-1,2);
                }
                else
                    mainBoard.place(number+1, 2);
                firstMove = false;
            }
            else if(number < 7)
            {
                max(mainBoard.copyBoard(), 2, 10000); //changes compMove for computer
                mainBoard.place(compMove, 2);
            }

            mainBoard.printBoard();

            if(mainBoard.isWinner(2)) // check to see if the computer has won after its move
            {

                JOptionPane.showMessageDialog(null,"You have lost!");
                System.exit(0);
                return;
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
    private static int max(Board board, int depth, int minHeuristic)
    {
        int heuristic = -10000;
        if(depth == 1)
        {
            for (int i = 0; i<7; i++)
            {
                if(!board.fullColumn(i))
                {
                    board.place(i,2);
                    int h = board.getHeuristic();
                    if(h>heuristic)
                        heuristic = h;
                    board.remove(i);
                }

            }

            return heuristic;
        }    
        else
        {
            for(int i = 0; i<7; i++)
            {
                if(!board.fullColumn(i))
                {
                    board.place(i,2);
                    int h = min(board, depth-1, heuristic);
                    if(h>heuristic)
                    {
                        compMove = i;
                        heuristic = h;
                    }
                    board.remove(i);
                    if(heuristic >= minHeuristic)
                        return heuristic;
                }
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
    private static int min(Board board, int depth, int maxHeuristic)
    {
        int heuristic = 10000;
        if(depth == 1)
        {
            for (int i = 0; i<7; i++)
            {
                if(!board.fullColumn(i))
                {
                    board.place(i,1);
                    int h = board.getHeuristic();
                    if(h<heuristic)
                        heuristic = h;
                    board.remove(i);
                }
            }
            return heuristic;
        }    
        else
        {
            for(int i = 0; i<7; i++)
            {
                if(!board.fullColumn(i))
                {
                    board.place(i,1);
                    int h = max(board, depth-1,heuristic);
                    if(h<heuristic)
                        heuristic = h;
                    board.remove(i);
                    if(heuristic <= maxHeuristic)
                        return heuristic;
                }
            }
        }
        return heuristic;
    }

}
