package application.mechanics;

public class Config {
    public static final long STEP_TIME = 30;
    public static final int THREADS_NUM = Runtime.getRuntime().availableProcessors();

    public static final int SHOT_REDUCING = 20;

    public static final int BLOCK_HEIGHT = 250 / 3 + 40;
    public static final int BLOCK_SIZE = 250;

    public static final int MAX_PLAYERS = 20;

    public static final int RADIUS = 3;
    public static final int SCORES_FOR_SHOT = 2;
    public static final int SCORES_FOR_KILL = 10;
    public static final double DAMAGE_COEFF_MIN = 0.5;
}
