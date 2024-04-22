package animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PlayerAnimations {

    BufferedImage playerSpriteAtlas;
    private BufferedImage[][] animations;

    public PlayerAnimations(){
        importImg();
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

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");
        try {
            playerSpriteAtlas = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BufferedImage[][] getanimations() {
        return animations;
    }
}
