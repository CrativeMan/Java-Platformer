package animation;

import utilz.LoadSave;

import java.awt.image.BufferedImage;

public class PlayerAnimations {

    BufferedImage playerSpriteAtlas;
    private BufferedImage[][] animations;

    public PlayerAnimations(){
        playerSpriteAtlas = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        loadPlayerIdleAnimations();
    }

    private void loadPlayerIdleAnimations() {
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = playerSpriteAtlas.getSubimage(i * 64, j*40, 64, 40);
            }
        }
    }

    public BufferedImage[][] getAnimations() {
        return animations;
    }
}
