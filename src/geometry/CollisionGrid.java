package geometry;

public class CollisionGrid {

    /* False signifies a traversable tile and true signifies an untraversable tile. */
    private boolean[][] collisionGrid;
    private int tilesX;
    private int tilesY;

    public CollisionGrid(int tilesX, int tilesY) {
        this.collisionGrid = new boolean[tilesY][tilesX];
        this.tilesX = tilesX;
        this.tilesY = tilesY;
    }

    public void setTileBlocked(int tileX, int tileY, boolean blocked) {
        if (tileX < 0 || tileX >= tilesX || tileY < 0 || tileY >= tilesY) return;
        collisionGrid[tilesY - tileY - 1][tileX] = blocked;
    }

    public boolean getTileBlocked(int tileX, int tileY) {
        if (tileX < 0 || tileX >= tilesX || tileY < 0 || tileY >= tilesY) return true;
        return collisionGrid[tilesY - tileY - 1][tileX];
    }

}
