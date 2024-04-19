import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Connect4Game extends JFrame {
    private JPanel boardPanel;
    private JPanel statusPanel;
    private JLabel currentPlayerLabel;
    private JLabel outcomeLabel;
    private BufferedImage boardImage;
    private BufferedImage redPieceImage;
    private BufferedImage yellowPieceImage;
    private BufferedImage whitePieceImage;
    int row;
    int col;
    
    public Connect4Game() {
        setTitle("Connect 4");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Load the board image
        try {
            URL boardFile = new URL("https://cgi.csc.liv.ac.uk/~trp/Teaching/Entries/2013/10/21_COMP327_-_Practical_Assignment_1_2013_files/FIAL_Board.png");
            boardImage = ImageIO.read(boardFile);
        } catch (IOException e) {
            System.out.println("Error loading board image: " + e.getMessage());
        }

        // try {
        //     BufferedImage backgroundImage = ImageIO.read(new File("board.jpg"));
        //     JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        //     backgroundLabel.setLayout(new BorderLayout());
        //     backgroundLabel.add(boardPanel, BorderLayout.CENTER);
        //     boardPanel.setOpaque(false); // Make the board panel transparent
        //     setContentPane(backgroundLabel); // Use the label as the content pane
        // } catch (IOException e) {
        //     System.out.println("Error loading background image: " + e.getMessage());
        // }
        
        // Load the game piece images
        try {
            File redpiece = new File("red.jpg");
            redPieceImage = ImageIO.read(redpiece);
            File yellowpiece = new File("yellow.jpg");
            yellowPieceImage = ImageIO.read(yellowpiece);
            File whitepiece = new File("white.jpg");
            whitePieceImage = ImageIO.read(whitepiece);
        } catch (IOException e) {
            System.out.println("Error loading game piece images: " + e.getMessage());
        }
        
        boardPanel = new JPanel() {
            // Override the paintComponent method to draw the board image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        boardPanel.setLayout(new GridLayout(6, 7));
        boardPanel.setBackground(new Color(1, 1, 1)); // Set background color to green
        //int row;
        for ( row= 0; row < 6; row++) {
            for (col = 0; col < 7; col++) {
                CircleButton button = new CircleButton();
                button.setOpaque(false); // Make button transparent
                button.setBorderPainted(false); // Remove button border
                button.addActionListener(e -> handlePlayerMove(row, col));
                boardPanel.add(button);
            }
        }
        
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(1, 2));
        currentPlayerLabel = new JLabel("Player 1's turn");
        outcomeLabel = new JLabel("");
        statusPanel.add(currentPlayerLabel);
        statusPanel.add(outcomeLabel);
        statusPanel.setBackground(new Color(0, 128, 0)); // Set background color to green
        
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void handlePlayerMove(int row, int col) {
        // Implement the logic for handling player moves
    }
    
    // Custom JButton class that draws the game piece image as a circle
    private class CircleButton extends JButton {
        private BufferedImage gamePieceImage;
        
        public CircleButton() {
            // Set the game piece image based on the current player
            if (Math.random() < 0.33) {
                gamePieceImage = redPieceImage;
            } else if (Math.random() < 0.67) {
                gamePieceImage = yellowPieceImage;
            } else {
                gamePieceImage = whitePieceImage;
            }
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (gamePieceImage != null) {
                g.drawImage(gamePieceImage, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }

    public static void main(String []args) {
        Connect4Game game = new Connect4Game();
    }
}