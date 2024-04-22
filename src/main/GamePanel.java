package main;

import animation.PlayerAnimations;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private BufferedImage img;
    private PlayerAnimations playerAnimations;
    float xDelta, yDelta;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        playerAnimations = new PlayerAnimations();

        updateAnimationTick();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex ++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos() {
        if(!moving)
            return;
        switch (playerDirection){
            case LEFT:
                xDelta -= 5;
                break;
            case UP:
                yDelta -= 5;
                break;
            case RIGHT:
                xDelta += 5;
                break;
            case DOWN:
                yDelta += 5;
                break;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();

        setAnimation();
        updatePos();

        g.drawImage(playerAnimations.getanimations()[playerAction][aniIndex], (int) xDelta, (int) yDelta,  256, 160, null);
    }

}
