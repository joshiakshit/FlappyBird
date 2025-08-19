import java.awt.Color;

public class Constants {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    public static final int BIRD_START_X = SCREEN_WIDTH / 8;
    public static final int BIRD_START_Y = SCREEN_HEIGHT / 2;
    public static final int BIRD_WIDTH = 48;
    public static final int BIRD_HEIGHT = 48;
    public static final double GRAVITY = 0.3; // Was 0.6
    public static final double JUMP_STRENGTH = -6; // Was -9

    public static final int PIPE_WIDTH = 64;
    public static final int PIPE_HEIGHT = 512;
    public static final int PIPE_GAP = 200;
    public static final int PIPE_SPACING = 300;
    public static final int PIPE_VELOCITY_X = -2; // Was -4

    public static final Color BACKGROUND_COLOR = Color.cyan;
}