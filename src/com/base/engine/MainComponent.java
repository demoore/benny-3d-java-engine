package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class MainComponent {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Game Engine";
    public static final double FRAME_CAP = 5000.0;

    private boolean isRunning;
    private Game game;


    public MainComponent() {
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();
    }

    public void start() {

        if (isRunning) {
            return;
        }

        run();
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void run() {
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning) {

            boolean render = false;

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            //How much time still needs to be accounted for
            unprocessedTime += passedTime / (double) Time.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {

                render = true;

                unprocessedTime -= frameTime;


                if (Window.isCloseRequested()) {
                    stop();
                }


                Time.setDelta(frameTime);


                game.input();
                game.update();
                Input.update();

                if (frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        cleanUp();
    }

    private void render() {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }

    private void cleanUp() {
        Window.dispose();
    }

    public static void main(String[] args) {
        Window.createWindow(WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent();

        game.start();


    }
}
