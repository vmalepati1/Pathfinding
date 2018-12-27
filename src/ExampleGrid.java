import grid.CollisionGrid;
import grid.GridNode;
import geometry.Vector2i;
import grid.GridPathfinding;

import java.util.List;

public class ExampleGrid {

    public static void main(String[] args) {
        GridPathfinding exampleGridPathfinding = new GridPathfinding();

        CollisionGrid exampleCollisionGrid = new CollisionGrid(8, 8);

        exampleCollisionGrid.setTileBlocked(2, 4, true);
        exampleCollisionGrid.setTileBlocked(3, 4, true);
        exampleCollisionGrid.setTileBlocked(4, 4, true);
        exampleCollisionGrid.setTileBlocked(2, 5, true);
        exampleCollisionGrid.setTileBlocked(3, 5, true);
        exampleCollisionGrid.setTileBlocked(4, 5, true);

        List<GridNode> path = exampleGridPathfinding.findPath(new Vector2i(0,7), new Vector2i(7, 0), exampleCollisionGrid);

        for (GridNode n : path) {
            System.out.println("(" + n.tile.getX() + ", " + n.tile.getY() + ")");
        }
    }

}
