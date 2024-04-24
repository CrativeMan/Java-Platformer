package main;

import gamestates.GameState;
import gamestates.Playing;
import gamestates.Menu;

import java.awt.*;


public class Game implements Runnable{

    private final GameFrame gameFrame;
    private final GamePanel gamePanel;
    private Thread gameThread;
    private Playing playing;
    private Menu menu;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public static final int TILES_DEFAULT_SIZE = 32;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final float SCALE = 2.0f;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public Game(){
        System.out.println("Starting Game class ...");
        initClasses();

        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();

        //! You shall not pass
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {

        switch (GameState.state) {
            case MENU -> {
                menu.update();
            }
            case PLAYING -> {
                playing.update();
            }
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU -> {
                menu.render(g);
            }
            case PLAYING -> {
                playing.render(g);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Game thread started");

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates ++;
                deltaU --;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames ++;
                deltaF --;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if(GameState.state == GameState.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }
}
