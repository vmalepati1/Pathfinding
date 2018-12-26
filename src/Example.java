import geometry.CollisionGrid;
import geometry.Node;
import geometry.Vector2i;
import pathfinding.Pathfinding;

import java.util.List;

public class Example {

    public static void main(String[] args) {
        Pathfinding examplePathfinding = new Pathfinding();

        CollisionGrid exampleCollisionGrid = new CollisionGrid(8, 8);

        exampleCollisionGrid.setTileBlocked(2, 4, true);
        exampleCollisionGrid.setTileBlocked(3, 4, true);
        exampleCollisionGrid.setTileBlocked(4, 4, true);
        exampleCollisionGrid.setTileBlocked(2, 5, true);
        exampleCollisionGrid.setTileBlocked(3, 5, true);
        exampleCollisionGrid.setTileBlocked(4, 5, true);

        List<Node> path = examplePathfinding.findPath(new Vector2i(0,7), new Vector2i(7, 0), exampleCollisionGrid);

        for (Node n : path) {
            System.out.println("(" + n.tile.getX() + ", " + n.tile.getY() + ")");
        }
    }

}
