import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pipe {
    public int x, y, width, height;
    public boolean passed = false;
    private Image topPipeImage;
    private Image bottomPipeImage;

    public Pipe(int startX) {
        this.x = startX;
        this.width = Constants.PIPE_WIDTH;
        this.height = Constants.PIPE_HEIGHT;
        randomizeHeight();

        this.topPipeImage = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        this.bottomPipeImage = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
    }

    public void randomizeHeight() {
        int topPipeBottomY = 120 + (int)(Math.random() * 300);
        this.y = topPipeBottomY - Constants.PIPE_HEIGHT;
    }

    public void reset(int newX) {
        this.x = newX;
        this.passed = false;
        randomizeHeight();
    }

    public void update() {
        x += Constants.PIPE_VELOCITY_X;
    }

    public void draw(Graphics g) {
        g.drawImage(topPipeImage, x, y, width, height, null);
        g.drawImage(bottomPipeImage, x, y + height + Constants.PIPE_GAP, width, height, null);
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, y + height + Constants.PIPE_GAP, width, height);
    }
}