import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 1152;
        int boardHeight = 658;

        JFrame frame = new JFrame("Flappy Bird");
        //These lines are used to make the frame visible and some extra adjustment and set its size
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);

    }
}
