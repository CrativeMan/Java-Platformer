package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        MouseInputs mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
