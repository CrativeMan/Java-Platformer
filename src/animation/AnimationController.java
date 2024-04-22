package animation;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class AnimationController {
    public static class PlayerAnimator {
        private int animationTick, animationIndex, animationSpeed = 15;
        private int playerAction = IDLE;

        public void updateAnimationTick(int playerAction){
            animationTick++;
            if(animationTick >= animationSpeed) {
                animationTick = 0;
                animationIndex ++;
                if(animationTick >= GetSpriteAmount(playerAction)){
                    animationIndex = 0;
                }
            }
        }

        public void setAnimation(boolean moving){
            if(moving)
                playerAction = RUNNING;
            else
                playerAction = IDLE;
        }
    }
}
