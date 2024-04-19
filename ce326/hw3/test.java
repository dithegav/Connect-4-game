import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class test extends JFrame {
    private JLayeredPane layeredPane;
    private JPanel boardPanel;
    private JPanel piecePanel;
    private BufferedImage boardImage;
    private BufferedImage redPieceImage;
    
    public test() {
        setTitle("Connect 4");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Load the board image
        try {
            URL imageURL = new URL("https://cgi.csc.liv.ac.uk/~trp/Teaching/Entries/2013/10/21_COMP327_-_Practical_Assignment_1_2013_files/FIAL_Board.png");
            boardImage = ImageIO.read(imageURL);
        } catch (IOException e) {
            System.out.println("Error loading board image: " + e.getMessage());
        }
        
        // Load the game piece image
        try {
            File redpiece = new File("red.jpg");
            redPieceImage = ImageIO.read(redpiece);
        } catch (IOException e) {
            System.out.println("Error loading game piece image: " + e.getMessage());
        }
        
        // Create the board panel
        boardPanel = new JPanel() {
            // Override the paintComponent method to draw the board image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        boardPanel.setLayout(new GridLayout(6, 7));
        
        // Create the game piece panel
        piecePanel = new JPanel() {
            // Override the paintComponent method to draw the game piece image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(redPieceImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        piecePanel.setOpaque(false);
        
        // Create the layered pane and add the panels to it
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 600));
        layeredPane.add(boardPanel, 0);
        layeredPane.add(piecePanel, 1);
        
        // Set the bounds of the panels
        boardPanel.setBounds(0, 0, 600, 600);
        piecePanel.setBounds(90, 90, 60, 60); // Change the x and y coordinates to position the piece
        
        // Add the layered pane to the frame
        add(layeredPane, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public static void main(String []args) {
        test test1 = new test();
    }
}
