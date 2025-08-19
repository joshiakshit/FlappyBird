// GamePanel.java
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// STEP 1: Implement the MouseListener interface
public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener { // UPDATED

    private Bird bird;
    private List<Pipe> pipes;
    private Timer gameLoop;
    private boolean gameOver;
    private double score;
    private Image backgroundImage;

    public GamePanel() {
        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setBackground(Constants.BACKGROUND_COLOR);
        setFocusable(true);
        addKeyListener(this);

        // STEP 2: Add this panel as its own mouse listener
        addMouseListener(this); // NEW

        this.backgroundImage = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();

        startGame();

        gameLoop = new Timer(1000 / 60, this); // 60 FPS
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
        for (int i = 0; i < 3; i++) {
            pipes.add(new Pipe(Constants.SCREEN_WIDTH + i * Constants.PIPE_SPACING));
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
            g.drawString("Game Over", 75, Constants.SCREEN_HEIGHT / 2 - 50);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + (int) score, 140, Constants.SCREEN_HEIGHT / 2);
            g.drawString("Click or Press 'R' to Restart", 80, Constants.SCREEN_HEIGHT / 2 + 50); // Updated text
        } else {
            g.drawString(String.valueOf((int) score), Constants.SCREEN_WIDTH / 2 - 20, 70);
        }
    }

    private void update() {
        if (gameOver) return;

        bird.update();

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.update();

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5;
            }

            if (pipe.getTopBounds().intersects(bird.getBounds()) || pipe.getBottomBounds().intersects(bird.getBounds())) {
                gameOver = true;
            }

            if (pipe.x + pipe.width < 0) {
                pipes.remove(pipe);
                pipes.add(new Pipe(pipes.get(pipes.size() - 1).x + Constants.PIPE_SPACING));
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

    // --- Input Handling ---

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                bird.jump();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            startGame();
        }
    }

    // STEP 3: Implement the new mouse methods
    @Override
    public void mousePressed(MouseEvent e) { // NEW
        if (!gameOver) {
            bird.jump();
        } else {
            // Clicking will restart the game
            startGame();
        }
    }

    // Unused Listener Methods (must be here to satisfy the interfaces)
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}