import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private Bird bird;
    private List<Pipe> pipes;
    private Timer gameLoop;
    private boolean gameOver;
    private int score; // FIX 1: Score is now an integer
    private Image backgroundImage;

    public GamePanel() {
        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setBackground(Constants.BACKGROUND_COLOR);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        this.backgroundImage = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();

        startGame();

        gameLoop = new Timer(1000 / 120, this);
        gameLoop.start();
    }

    private void startGame() {
        bird = new Bird();
        pipes = new ArrayList<>();
        gameOver = false;
        score = 0;
        placePipes();
    }

    private void placePipes() {
        pipes.clear();
        int startingX = Constants.SCREEN_WIDTH + 100;
        for (int i = 0; i < 5; i++) {
            pipes.add(new Pipe(startingX + i * Constants.PIPE_SPACING));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        bird.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        if (gameOver) {
            String gameOverText = "Game Over";
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(gameOverText);
            int x = (Constants.SCREEN_WIDTH - textWidth) / 2;
            g.drawString(gameOverText, x, Constants.SCREEN_HEIGHT / 2 - 50);

            String scoreText = "Score: " + score;
            textWidth = fm.stringWidth(scoreText);
            x = (Constants.SCREEN_WIDTH - textWidth) / 2;
            g.drawString(scoreText, x, Constants.SCREEN_HEIGHT / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String restartText = "Click or Press 'R' to Restart";
            fm = g.getFontMetrics();
            textWidth = fm.stringWidth(restartText);
            x = (Constants.SCREEN_WIDTH - textWidth) / 2;
            g.drawString(restartText, x, Constants.SCREEN_HEIGHT / 2 + 50);

        } else {
            g.drawString(String.valueOf(score), Constants.SCREEN_WIDTH / 2 - 20, 70);
        }
    }

    private void update() {
        if (gameOver) return;

        bird.update();

        int lastPipeX = 0;
        for (Pipe pipe : pipes) {
            pipe.update();

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score++;
            }

            if (pipe.getTopBounds().intersects(bird.getBounds()) || pipe.getBottomBounds().intersects(bird.getBounds())) {
                gameOver = true;
            }

            if (pipe.x > lastPipeX) {
                lastPipeX = pipe.x;
            }
        }

        for (Pipe pipe : pipes) {
            if (pipe.x + pipe.width < 0) {
                pipe.reset(lastPipeX + Constants.PIPE_SPACING);
            }
        }

        if (bird.y + bird.height > Constants.SCREEN_HEIGHT) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) { bird.jump(); }
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            startGame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) { bird.jump(); }
        else { startGame(); }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}