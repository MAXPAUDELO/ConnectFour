import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.border.*;
/**
 * @author (Max Audelo, John Centritto)
 * @date (4/22/2015)
 * The purpose of this class is to set up a GUI for the user to play a game of Connect Four against the computer. It allows the user to 
 * interact with the GUI as well, with use of buttons and JMenu's
 */
public class FRAME extends ConnectFour implements ActionListener{
    private JFrame frame;
    private int numMoves;
    private JLabel[][] colSlots;
    private int player;
    private JPanel chips;
    JLabel moves;
    /**
     * Creates the initial frame
     */

    public FRAME()
    {
        makeFrame();

    }

    /**
     * This is the first frame that the user sees. It asks the user to choose what color she would like to be, either black or red.
     * 
     */
    private void makeFrame()
    {
        frame = new JFrame("Connect Four");
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();

        JLabel label = new JLabel("Welcome! Would you like to be:");
        contentPane.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        addButton(buttonPanel, "Red");
        addButton(buttonPanel, "Black");

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(300,100);
        frame.setVisible(true);
    }

    /**
     * This is the game board. The user will click buttons with column names in order to drop a chip in the corresponding column.
     */

    private void makeGameBoard()
    {
        frame = new JFrame("Connect Four Board"); 
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();//content pane to hold all the components
        //JPanel bottomPanel = new JPanel(new FlowLayout());
        JPanel colPanel = new JPanel(new GridLayout(1,6));//creates a JPanl to hold the column buttons in a row

        chips = new JPanel(new GridLayout(6,7)); //the game board that will hold the chips
        colSlots = new JLabel[7][6];//each slot
        for (int row=5; row>=0; row--) { //creates a slot for each section of the grid
            for (int column=0; column<7; column++) { 
                colSlots[column][row] = new JLabel(); 
                //        
                colSlots[column][row].setHorizontalAlignment(SwingConstants.CENTER);
                colSlots[column][row].setBorder(new LineBorder(Color.yellow)); 
                chips.add(colSlots[column][row]);
            }
        }

        moves = new JLabel("Moves: " + numMoves);//label for the amount of moves
        addButton(colPanel, "col 1"); //adds column buttons
        addButton(colPanel, "col 2");
        addButton(colPanel, "col 3");
        addButton(colPanel, "col 4");
        addButton(colPanel, "col 5");
        addButton(colPanel, "col 6");
        addButton(colPanel, "col 7");

        //bottomPanel.add(colPanel); //sets all the components into place.
        //bottomPanel.add(moves);
        contentPane.add(colPanel, BorderLayout.SOUTH);
        contentPane.add(moves, BorderLayout.NORTH);
        contentPane.add(chips, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximized screen size
        frame.setVisible(true);
    }

    /**
     * Sets the chip color for the player or computer. 
     * @param column The column the chip should be placed.
     * @param row The row the chip should be placed.
     */
    public void setPlayer(int column, int row) { 
        if (player == 1) { 
            colSlots[column][row].setIcon(new ImageIcon("src/image/redChip.png")); 

        }  
        else { 

            colSlots[column][row].setIcon(new ImageIcon("src/image/blackChip.png"));
        }   

    }

    public void setComp(int column, int row) { 
        if (player == 1) { 
            colSlots[column][row].setIcon(new ImageIcon("src/image/blackChip.png")); 

        }  
        else { 
            colSlots[column][row].setIcon(new ImageIcon("src/image/redChip.png"));
        }   

        /**
         * Creates a menubar that allows the user to quit the game whenever she chooses.
         */
    }

    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quit(); }
            });
        fileMenu.add(quitItem);

    }

    /**
     * Adds a button to a panel. Created to make it easier to add multiple buttons. 
     * @param panel The panel to add the new button to.
     * @param buttonText The name of the button to be added.
     */

    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }

    /**
     * This method is used to give each button an action. Sets the color for the player, the computer, drops the chip into the board,
     * increments the moves counter, and updates the moves label. 
     */
    public void actionPerformed(ActionEvent event)
    {
        String buttonName = event.getActionCommand();

        if(buttonName.equals("Red"))
        {
            player = 1;
            frame.setVisible(false);
            makeGameBoard();

        }
        else if(buttonName.equals("Black")){
            player = 2;
            frame.setVisible(false);
            makeGameBoard();

        }

        if(buttonName.equals("col 1"))
        {
            ConnectFour.placeNumber(0);

            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }
        }
        else if(buttonName.equals("col 2"))
        {
            ConnectFour.placeNumber(1);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }
        }
        else if(buttonName.equals("col 3"))
        {
            ConnectFour.placeNumber(2);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }
        }
        else if(buttonName.equals("col 4"))
        {
            ConnectFour.placeNumber(3);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }
        }
        else if(buttonName.equals("col 5"))
        {
            ConnectFour.placeNumber(4);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }
        }
        else if(buttonName.equals("col 6"))
        {
            ConnectFour.placeNumber(5);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }

        }
        else if(buttonName.equals("col 7"))
        {
            ConnectFour.placeNumber(6);
            if(ConnectFour.number < 7)
            {
                numMoves++;
                moves.setText("Moves: " + numMoves);
            }

        }

    }
}
