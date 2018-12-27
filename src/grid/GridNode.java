package grid;

import geometry.Vector2i;

public class GridNode {

    public Vector2i tile;
    public GridNode parent;
    public double fCost, gCost, hCost;

    public GridNode(Vector2i tile, GridNode parent, double gCost, double hCost) {
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

}
