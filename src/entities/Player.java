package entities;

import animation.PlayerAnimations;

import java.awt.*;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private final PlayerAnimations playerAnimations;
    private boolean left, up, right, down;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        playerAnimations = new PlayerAnimations();
    }

    public void update(){
        updatePos();
        playerAnimations.updateAnimationTick();
        playerAnimations.setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(playerAnimations.getAnimations()[playerAnimations.getPlayerAction()][playerAnimations.getAniIndex()],
                (int) x, (int) y,  width, height, null);
    }

    private void updatePos() {
        playerAnimations.setMoving(false);

        float playerSpeed = 2.0f;
        if(left && !right){
            x -= playerSpeed;
            playerAnimations.setMoving(true);
        } else if(right && !left){
            x += playerSpeed;
            playerAnimations.setMoving(true);
        }

        if(up && !down){
            y -= playerSpeed;
            playerAnimations.setMoving(true);
        } else if(down && !up){
            y += playerSpeed;
            playerAnimations.setMoving(true);
        }
    }

    public void resetDirBooleans() {
        left = right = down = up = false;
    }

    // Getter and setter
    public boolean isDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public PlayerAnimations getAnimator(){return playerAnimations;}
}
