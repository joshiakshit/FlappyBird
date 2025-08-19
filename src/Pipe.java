// Pipe.java
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pipe {
    public int x, y, width, height;
    public boolean passed = false; // To track for scoring
    private Image topPipeImage;
    private Image bottomPipeImage;

    public Pipe(int startX) {
        this.x = startX;
        this.width = Constants.PIPE_WIDTH;
        this.height = Constants.PIPE_HEIGHT;

        // *** THIS IS THE CORRECTED LOGIC ***
        // We calculate a random position for the bottom of the top pipe,
        // ensuring there's always enough room on screen.
        int topPipeBottomY = 120 + (int)(Math.random() * 300); // Random int between 120 and 420
        this.y = topPipeBottomY - Constants.PIPE_HEIGHT;

        this.topPipeImage = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        this.bottomPipeImage = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
    }

    public void update() {
        x += Constants.PIPE_VELOCITY_X;
    }

    public void draw(Graphics g) {
        // Top Pipe
        g.drawImage(topPipeImage, x, y, width, height, null);
        // Bottom Pipe
        g.drawImage(bottomPipeImage, x, y + height + Constants.PIPE_GAP, width, height, null);
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, y + height + Constants.PIPE_GAP, width, height);
    }
}