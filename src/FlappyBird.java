// FlappyBird.java
import javax.swing.JFrame;

public class FlappyBird {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Create and add the game panel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack(); // Sizes the frame to fit the preferred size of its components
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}