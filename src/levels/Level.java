package levels;

public class Level {

    private int[][] lvlData;

    public Level(int[][] levelData) {
        this.lvlData = levelData;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }
}
