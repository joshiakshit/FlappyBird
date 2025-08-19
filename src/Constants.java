// Constants.java
import java.awt.Color;

public class Constants {
    // Screen dimensions
    public static final int SCREEN_WIDTH = 1152;
    public static final int SCREEN_HEIGHT = 648;

    // Bird properties
    public static final int BIRD_START_X = SCREEN_WIDTH / 8;
    public static final int BIRD_START_Y = SCREEN_HEIGHT / 2;
    public static final int BIRD_WIDTH = 64;
    public static final int BIRD_HEIGHT = 64;
    public static final double GRAVITY = 0.6;
    public static final double JUMP_STRENGTH = -9;

    // Pipe properties
    public static final int PIPE_WIDTH = 64;
    public static final int PIPE_HEIGHT = 512;
    public static final int PIPE_GAP = 200; // Vertical gap between pipes
    public static final int PIPE_SPACING = 300; // Horizontal distance between pipes
    public static final int PIPE_VELOCITY_X = -4;

    // Colors
    public static final Color BACKGROUND_COLOR = Color.cyan;
}