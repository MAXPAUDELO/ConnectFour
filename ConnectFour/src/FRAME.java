import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.*;

/**
 * The purpose of this class is to set up a GUI for the user to play a game of Connect Four against the computer
 */
public class FRAME implements ActionListener{
    private JFrame frame;
    private ImagePanel imagePanel;
    private int numMoves;
    /**
     * Creates the initial frame
     */

    public FRAME()
    {
        makeFrame();
    }

    /**
     * This method is used to leave to initial frame to go to the actual gameboard, 
     */
    public void actionPerformed(ActionEvent event)
    {
        String buttonName = event.getActionCommand();

        if(buttonName.equals("Red") || buttonName.equals("Black"))
        {
            
            frame.setVisible(false);
            makeBoard();
        }
        else if(buttonName.equals("Row 1"))
        {
        	ConnectFour.number = "1";
        }
        else if(buttonName.equals("Row 2"))
        {
        	ConnectFour.number = "2";
        }
        else if(buttonName.equals("Row 3"))
        {
        	ConnectFour.number = "3";
        }
        else if(buttonName.equals("Row 4"))
        {
        	ConnectFour.number = "4";
        }
        else if(buttonName.equals("Row 5"))
        {
        	ConnectFour.number = "5";
        }
        else if(buttonName.equals("Row 6"))
        {
        	ConnectFour.number = "6";
        }
    }

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

        // 
        //         BufferedImage myPicture = ImageIO.read(new File("ConnectFourBackGround.png"));
        //         JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //         contentPane.add(picLabel);

        frame.pack();
        frame.setVisible(true);
    }

    private void makeBoard()
    {
        frame = new JFrame("Connect Four Board");
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();
        JPanel bottomPanel = new JPanel();
        JPanel rowPanel = new JPanel(new GridLayout(1,6));

        
        ImageIcon icon = new ImageIcon("src/image/ConnectFourBackGround.png"); 
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        
        JLabel moves = new JLabel("Moves: " + numMoves);
        
        addButton(rowPanel, "Row 1");
        addButton(rowPanel, "Row 2");
        addButton(rowPanel, "Row 3");
        addButton(rowPanel, "Row 4");
        addButton(rowPanel, "Row 5");
        addButton(rowPanel, "Row 6");
        
        
        bottomPanel.add(rowPanel, BorderLayout.NORTH);
        bottomPanel.add(moves, BorderLayout.SOUTH);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        contentPane.add(thumb, BorderLayout.CENTER);
        
        //   contentPane.add(imagePanel, BorderLayout.CENTER);

        // 
        //         BufferedImage myPicture = ImageIO.read(new File("ConnectFourBackGround.png"));
        //         JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //         contentPane.add(picLabel);

        frame.pack();
        frame.setVisible(true);
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

}
