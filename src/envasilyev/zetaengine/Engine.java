package envasilyev.zetaengine;

/**
 * Created by Zeejfps on 6/16/2016.
 */
public final class Engine {

    public static int maxFramesToSkip = 5;

    private Engine(){}

    private static Scene scene;
    private static double nsPerUpdate;
    private static boolean showDebug;

    public static void init() {
        Screen.init();
        Window.init();
        Input.init();
        Time.init();
        setFixedUpdateInterval(30);
    }

    public static void setFixedUpdateInterval(int ups) {
        nsPerUpdate = Time.NS_IN_SC / ups;
    }

    public static void loadScene(Scene s) {
        if (scene != null) {
            scene.unload();
        }
        scene = s;
        new Thread(new Runnable() {
            @Override
            public void run() {
                scene.load();
                loop(scene);
            }
        }).start();
    }

    public static void showDebug(boolean show) {
        showDebug = show;
    }

    private static int fps, ups;
    private static long startTime;
    private static void loop(Scene scene) {

        int skippedFrames = 0;
        double lag = 0, current, elapsed;
        double previous = System.nanoTime();
        Time.start();
        scene.start();
        startTime = System.currentTimeMillis();
        while(scene.isLoaded()) {
            current = System.nanoTime();
            elapsed = current - previous;
            Time.tick();

            previous = current;
            lag += elapsed;

            while (lag >= nsPerUpdate && skippedFrames < maxFramesToSkip) {
                scene.fixedUpdate();
                lag -= nsPerUpdate;
                skippedFrames++;
                ups++;
            }
            skippedFrames = 0;

            scene.update();
            scene.render();
            scene.postRender();
            Window.updateFramebuffer();
            fps++;
            if (System.currentTimeMillis() - startTime >= 1000) {
                if (showDebug) {
                    System.out.println("UPS: " + ups + ", FPS: " + fps);
                }
                fps = 0;
                ups = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
