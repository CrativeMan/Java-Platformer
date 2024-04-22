package entities;

import animation.PlayerAnimations;

import java.awt.*;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private final PlayerAnimations playerAnimations;
    private int aniTick;
    private int aniIndex;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        playerAnimations = new PlayerAnimations();
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(playerAnimations.getAnimations()[playerAction][aniIndex], (int) x, (int) y,  width, height, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        int aniSpeed = 15;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex ++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if(attacking)
            playerAction = ATTACK_1;

        if(startAnimation != playerAction)
            resetAniTick();

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        float playerSpeed = 2.0f;
        if(left && !right){
            x -= playerSpeed;
            moving = true;
        } else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if(down && !up){
            y += playerSpeed;
            moving = true;
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
    public boolean isAttacking() {
        return attacking;
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
