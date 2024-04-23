package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    /**
     * Checks if an entity can move to a given pixel position in the current level
     * @param x - x of target position
     * @param y - y of target position
     * @param width - width of entity
     * @param height - height of entity
     * @param lvlData - lvlData of the current level
     * @return - returns false if any of the checks fail
     */
    public static boolean _canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if(!IsSolid(x,y, lvlData))
            if(!IsSolid(x+width, y+height, lvlData))
                if(!IsSolid(x+width, y, lvlData))
                    if(!IsSolid(x, y+height, lvlData))
                        return true;
        return false;
    }

    //TODO: Write JDocs for _getEntityXPosNextToWall
    public static float _getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);

        if(xSpeed > 0){
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    //TODO: Write JDocs for _getEntityYPosHitFloorCeiling
    public static float _getEntityYPosHitFloorCeiling(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);

        if(airSpeed > 0){
            // Falling - touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }

    }

    //TODO: Write JDocs for _isEntityOnFloor
    public static boolean _isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Check pixel below bottom left and right
        if(!IsSolid(hitbox.x, hitbox.y+hitbox.height+1, lvlData))
            if(!IsSolid(hitbox.x, hitbox.y+1, lvlData))
                return false;
        return true;
    }

    //TODO: Write JDocs for IsSolid
    private static boolean IsSolid(float x, float y, int[][] lvlData){
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }
}
