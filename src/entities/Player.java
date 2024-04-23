package entities;

import animation.PlayerAnimations;
import main.Game;

import java.awt.*;

import static utilz.HelpMethods._canMoveHere;

public class Player extends Entity {
    private final PlayerAnimations animator;
    private boolean left, up, right, down;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        animator = new PlayerAnimations();
        initHitbox(x, y, 20*Game.SCALE, 28*Game.SCALE);
    }

    public void update(){
        updatePos();
        animator.updateAnimationTick();
        animator.setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animator.getAnimations()[animator.getPlayerAction()][animator.getAniIndex()],
                (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),  width, height, null);
        drawHitbox(g);
    }

    private void updatePos() {
        animator.setMoving(false);
        if(!left && !up && !right && !down)
            return;

        float xSpeed = 0;
        float ySpeed = 0;

        float playerSpeed = 2.0f;
        if(left && !right)
            xSpeed = -playerSpeed;
        else if(right && !left)
            xSpeed = playerSpeed;
        if(up && !down)
            ySpeed = -playerSpeed;
        else if(down && !up)
            ySpeed = playerSpeed;

        if(_canMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            animator.setMoving(true);
        }
    }

    public void loadLevelData(int[][] lvlData){
        this.lvlData = lvlData;
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
    public PlayerAnimations getAnimator(){return animator;}
}
