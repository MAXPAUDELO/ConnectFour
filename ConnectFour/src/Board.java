
public class Board {

    private int[][] graph;
    private FRAME frame;

    /**
     * Constructor that initializes a Board with a 2-d array called graph that contains all 0s
     */
    public Board()
    {
        graph = new int[6][7]; //row x column
        frame = new FRAME();
    }

    /**
     * This method will be called to represent placing a game piece down one of the columns
     * @param column The column to place the game piece down
     * @param playerOrComp Chip to be placed. 1 represents a player chip, 2 represents a computer chip
     */
    public void place(int column, int playerOrComp)
    {
        int i = 0;
        while(i<6 && graph[i][column] == 0)
        {
            i++;
        }
        if(i == 0) //the top position of that column is full
            System.err.print("The column is full");
        else
            graph[i-1][column] = playerOrComp;
    }

    /**
     * This method will remove the chip at the uppermost position of a certain column
     * @param column The column the remove the chip from
     */
    public void remove(int column)
    {
        for(int i = 0; i < 6; i++)
        {
            if(graph[i][column] != 0)
            {
                graph[i][column] = 0;
                return;
            }
        }  
    }

    /**
     * Will determine if the player has won or will determine if the computer has won
     * depending on the corresponding parameter n
     * @param playerOrComp If it is a 1, it is checking to see if the player has won. 
     * If it is a 2, it is checking to see if the computer won
     * @return False if no winner, True if winner
     */
    public boolean isWinner(int playerOrComp)
    {
        if(checkHorizontals(4,playerOrComp) + checkVerticals(4,playerOrComp) 
        + checkDiagonals(4,playerOrComp) > 0)
        {
            return true;
        }
        else 
            return false;
    }

    /**
     * Copies the numbers from the graph of the Board that
     * called the method into the graph of a newly created board
     * @return The new board with copied graph
     */
    public Board copyBoard()
    {
        Board newB = new Board();
        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 7; k++){

                newB.graph[i][k] = this.graph[i][k];
            }
        }
        return newB;
    }

    /**
     * Calculates the heuristic value for a specific board depending on the numbers in the graph.
     * Higher heuristic values mean that the board's graph is good for the computer.
     * Lower heuristic values mean that the board's graph is good for the player.
     * @return The heuristic value
     */
    public int getHeuristic()
    {

        return 0;
    }

    /**
     * This method will return the number of groups of chips 'in a row' in the vertical columns
     * @param numInRow Specifies the number of chips in a group to look for
     * @param chip Specifies whether to look for player chips with a 1 or computer chips with a 2
     * @return The number of groups containing the specified amount of chips 'in a row'
     */
    private int checkVerticals(int numInRow, int chip)
    {
        int groups = 0;
        for(int i = 0; i<7; i++)
        {
            int sequence = 0;
            for(int k = 0; k<6; k++)
            {
                if(graph[k][i] == chip)
                {
                    sequence++;
                    if(sequence == numInRow)
                    {
                        groups++;
                        sequence = 0;
                    }

                }
                else
                    sequence = 0;
            }
        }
        return groups;
    }

    /**
     * This method will return the number of groups of chips 'in a row' in the horizontal rows
     * @param numInRow Specifies the number of chips in a group to look for
     * @param chip Specifies whether to look for player chips with a 1 or computer chips with a 2
     * @return The number of groups containing the specified amount of chips 'in a row'
     */
    private int checkHorizontals(int numInRow, int chip)
    {
        int groups = 0;
        for(int i = 0; i<6; i++)
        {
            int sequence = 0;
            for(int k = 0; k<7; k++)
            {
                if(graph[i][k] == chip)
                {
                    sequence++;
                    if(sequence == numInRow)
                    {
                        groups++;
                        sequence = 0;
                    }

                }
                else
                    sequence = 0;
            }
        }
        return groups;
    }

    /**
     * This method will return the number of groups of chips 'in a row' in the diagonals
     * @param numInRow Specifies the number of chips in a group to look for
     * @param chip Specifies whether to look for player chips with a 1 or computer chips with a 2
     * @return The number of groups containing the specified amount of chips 'in a row'
     */
    private int checkDiagonals(int numInRow, int chip)
    {
        return 0;
    }

}
