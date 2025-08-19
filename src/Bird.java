import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Bird {
    public int x, y, width, height;
    public double velocityY;
    private Image image;

    public Bird() {
        this.x = Constants.BIRD_START_X;
        this.y = Constants.BIRD_START_Y;
        this.width = Constants.BIRD_WIDTH;
        this.height = Constants.BIRD_HEIGHT;
        this.velocityY = 0;
        this.image = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
    }

    public void update() {
        velocityY += Constants.GRAVITY;
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void jump() {
        velocityY = Constants.JUMP_STRENGTH;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}