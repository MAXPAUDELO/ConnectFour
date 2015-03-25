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
        String color = event.getActionCommand();

        if(color.equals("Red") || color.equals("Black"))
        {
            
            frame.setVisible(false);
            makeBoard();
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

        
        ImageIcon icon = new ImageIcon("image/ConnectFourBackGround.png"); 
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        
        JLabel moves = new JLabel("Moves: " + numMoves);
        
        
        
        
        contentPane.add(moves, BorderLayout.SOUTH);
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
