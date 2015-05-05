
/**
 * This class defines a connect four board. Its main component is a 6x7 2d array. This stores values for the chips placed by
 * the player and computer. This class offers a lot of functionality to the 2d array such as adding and removing chips and counting groups
 * of chips.
 */
public class Board{

    private int[][] graph;
    int dGroups = 0; //used by checkDiagonals method
    int dSequence = 0; //used by checkDiagonals method

    /**
     * Constructor that initializes a Board with a 2-d array called graph that contains all 0s
     */
    public Board()
    {
        graph = new int[6][7]; //row x column
    }

    /**
     * This method is used to print all of the numbers stored in the 2d array
     */
    public void printBoard(){

        for(int i = 0; i<6; i++)
        {
            for(int k = 0; k<7; k++)
            {

                if(graph[i][k] == 1)
                {

                    ConnectFour.mainFrame.setPlayer(k,(5-i));
                }

                if(graph[i][k] == 2)
                {

                    ConnectFour.mainFrame.setComp(k,(5-i));
                }

            }
        }
    }

    /**
     * This method will be called to represent placing a game piece down one of the columns
     * @param column The column to place the game piece down
     * @param chip 1 represents a player chip, 2 represents a computer chip
     */
    public void place(int column, int chip)
    {
        for(int i=5; i>-1; i--)
        {
            if(graph[i][column] == 0)
            {
                graph[i][column] = chip;
                return;
            }

        }
        ConnectFour.placeNumber(7);

    }

    /**
     * This method checks to see if a specified column is filled to the top with chips
     * @param column The column to check if filled
     * @return Returns true if the column is full, false if not
     */
    public boolean fullColumn(int i)
    {
        if(graph[0][i] == 0)
            return false;
        else
            return true;
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
     * depending on the corresponding paramter n
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
        int h = (checkVerticals(3,2) + checkHorizontals(3,2) + checkDiagonals(3,2))*10
            - (checkVerticals(3,1) + checkHorizontals(3,1) + checkDiagonals(3,1))*10
            + (checkVerticals(2,2) + checkHorizontals(2,2) + checkDiagonals(2,2))
            - (checkVerticals(2,1) + checkHorizontals(2,1) + checkDiagonals(2,1));
        if(checkVerticals(4,2) + checkHorizontals(4,2) + checkDiagonals(4,2) >= 1)
            h = h + 100;
        if(checkVerticals(4,1) + checkHorizontals(4,1) + checkDiagonals(4,1) >= 1)
            h = h - 100;

        return h;
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
        dGroups = 0;
        dSequence = 0;
        check(3,0,numInRow,chip);
        check(2,1,numInRow,chip);
        check(1,2,numInRow,chip);
        check(0,3,numInRow,chip);
        dSequence = 0;

        check(4,0,numInRow,chip);
        check(3,1,numInRow,chip);
        check(2,2,numInRow,chip);
        check(1,3,numInRow,chip);
        check(0,4,numInRow,chip);
        dSequence = 0;

        check(5,0,numInRow,chip);
        check(4,1,numInRow,chip);
        check(3,2,numInRow,chip);
        check(2,3,numInRow,chip);
        check(1,4,numInRow,chip);
        check(0,5,numInRow,chip);
        dSequence = 0;

        check(5,1,numInRow,chip);
        check(4,2,numInRow,chip);
        check(3,3,numInRow,chip);
        check(2,4,numInRow,chip);
        check(1,5,numInRow,chip);
        check(0,6,numInRow,chip);
        dSequence = 0;

        check(5,2,numInRow,chip);
        check(4,3,numInRow,chip);
        check(3,4,numInRow,chip);
        check(2,5,numInRow,chip);
        check(1,6,numInRow,chip);
        dSequence = 0;

        check(5,3,numInRow,chip);
        check(4,4,numInRow,chip);
        check(3,5,numInRow,chip);
        check(2,6,numInRow,chip);
        dSequence = 0;

        check(0,3,numInRow,chip);
        check(1,4,numInRow,chip);
        check(2,5,numInRow,chip);
        check(3,6,numInRow,chip);
        dSequence = 0;

        check(0,2,numInRow,chip);
        check(1,3,numInRow,chip);
        check(2,4,numInRow,chip);
        check(3,5,numInRow,chip);
        check(4,6,numInRow,chip);
        dSequence = 0;

        check(0,1,numInRow,chip);
        check(1,2,numInRow,chip);
        check(2,3,numInRow,chip);
        check(3,4,numInRow,chip);
        check(4,5,numInRow,chip);
        check(5,6,numInRow,chip);
        dSequence = 0;

        check(0,0,numInRow,chip);
        check(1,1,numInRow,chip);
        check(2,2,numInRow,chip);
        check(3,3,numInRow,chip);
        check(4,4,numInRow,chip);
        check(5,5,numInRow,chip);
        dSequence = 0;

        check(1,0,numInRow,chip);
        check(2,1,numInRow,chip);
        check(3,2,numInRow,chip);
        check(4,3,numInRow,chip);
        check(5,4,numInRow,chip);
        dSequence = 0;

        check(2,0,numInRow,chip);
        check(3,1,numInRow,chip);
        check(4,2,numInRow,chip);
        check(5,3,numInRow,chip);

        return dGroups;
    }

    /**
     * This method is called many times by the checkDiagonals method
     * This is used to count the number of groups of chips of a certain size in the diagonals
     * If a sequence of the correct size is found then dGroups will increment, meaning a groups has been found in a diagonal
     * @param i The row to check for the chip in
     * @param k The column to check for the chip in
     * @param numInRow The size of the group to check if exists in diagonals
     * @chip 1 if looking for player chip, 2 if looking for computer chip
     */
    public void check(int i, int k, int numInRow, int chip)
    {
        if(graph[i][k] == chip)
        {
            dSequence++;
            if(dSequence == numInRow)
            {
                dGroups++;
                dSequence = 0;
            }

        }
        else
            dSequence = 0;

    }
}
