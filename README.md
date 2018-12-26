# Pathfinding

A simple pathfinding algorithm for determining the best paths in a grid-like area space using the A* algorithm.

## How to Use It

Here is some simple code and visualization for how to use the algorithm.

```java
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
```

The code returns the following path:
```java
(1, 6)
(1, 5)
(1, 4)
(2, 3)
(3, 2)
(4, 1)
(5, 1)
(6, 1)
(7, 0)
```

### What the grid space looks like before the algorithm starts:

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/PathfindingBefore.png "The grid before the algorithm starts.")

### What the grid space looks like after the algorithm is finished:

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/PathfindingAfter.png "The grid after the algorithm starts.")
