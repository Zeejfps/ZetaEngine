package envasilyev.zetaengine;

/**
 * Created by Zeejfps on 8/10/2016.
 */
public final class Time {

    public static final double NS_IN_MS = 1000000.0;
    public static final double NS_IN_SC = 1000000000.0;

    private static double startTime;
    private static double deltaTime;

    private Time() {}

    static void init() {

    }

    static void start() {
        startTime = System.nanoTime();
    }

    static void tick() {
        deltaTime = (System.nanoTime() - startTime) / NS_IN_MS;
        startTime = System.nanoTime();
    }

    public static float deltaTime() {
        return (float)deltaTime;
    }

}
