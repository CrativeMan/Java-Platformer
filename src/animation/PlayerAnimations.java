package animation;

import utilz.LoadSave;

import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.ATTACK_1;

public class PlayerAnimations extends Animator{

    BufferedImage playerSpriteAtlas;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex;
    private float airSpeed = 0f;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false, inAir = false;

    public PlayerAnimations(){
        playerSpriteAtlas = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        loadPlayerAnimations();
    }

    @Override
    public void updateAnimationTick() {
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

    @Override
    public void setAnimation() {
        int startAnimation = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if(inAir) {
            if(airSpeed < 0)
                playerAction = JUMPING;
            else
                playerAction = FALLING;
        }
        if(attacking)
            playerAction = ATTACK_1;

        if(startAnimation != playerAction)
            resetAniTick();

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void loadPlayerAnimations() {
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = playerSpriteAtlas.getSubimage(i * 64, j*40, 64, 40);
            }
        }
    }

    // Getter and setter
    public BufferedImage[][] getAnimations() {
        return animations;
    }
    public int getAniTick() {
        return aniTick;
    }
    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }
    public int getAniIndex() {
        return aniIndex;
    }
    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }
    public int getPlayerAction() {
        return playerAction;
    }
    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public boolean isAttacking() {
        return attacking;
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }
    public BufferedImage getPlayerSpriteAtlas() {
        return playerSpriteAtlas;
    }
    public void setPlayerSpriteAtlas(BufferedImage playerSpriteAtlas) {
        this.playerSpriteAtlas = playerSpriteAtlas;
    }
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
    public void setAirSpeed(float airSpeed) {
        this.airSpeed = airSpeed;
    }
}
