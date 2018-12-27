# Pathfinding

Pathfinding algorithms for autonomous devices. There are two methods that are both based on the A* pathfinding algorithm that determine the most optimal path for robots to take in a field with obstacles. One method is confined to a grid while the other is continuous and allows complex vertex obstacles. 

## Grid Pathfinding

Here is some simple code and visualization for how to use the grid pathfinding algorithm.

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

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/GridPathfindingBefore.png "The grid space before the algorithm starts.")

### What the grid space looks like after the algorithm is finished:

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/GridPathfindingAfter.png "The grid space after the algorithm starts.")

## Continuous Pathfinding

Here is some simple code and visualization for how to use the continuous pathfinding algorithm.

```java
double sx = 10.0;
double sy = 10.0;
double gx = 50.0;
double gy = 50.0;
double gridSize = 1.0;
double robotSize = 1.0;

List<Vector2f> obstaclePositions = new ArrayList<Vector2f>();

for (int i = 0; i < 60; i++) {
    obstaclePositions.add(new Vector2f(i, 0.0));
}
for (int i = 0; i < 60; i++) {
    obstaclePositions.add(new Vector2f(60.0, i));
}
for (int i = 0; i < 61; i++) {
    obstaclePositions.add(new Vector2f(i, 60.0));
}
for (int i = 0; i < 61; i++) {
    obstaclePositions.add(new Vector2f(0.0, i));
}
for (int i = 0; i < 40; i++) {
    obstaclePositions.add(new Vector2f(20.0, i));
}
for (int i = 0; i < 40; i++) {
    obstaclePositions.add(new Vector2f(40.0, 60.0 - i));
}

ContinuousPathfinding exampleContinuousPathfinding = new ContinuousPathfinding();

List<ContinuousNode> path = exampleContinuousPathfinding.findPath(new Vector2f(sx, sy), new Vector2f(gx, gy), obstaclePositions, gridSize, robotSize);

for (ContinuousNode n : path) {
    System.out.println("(" + n.position.getX() + ", " + n.position.getY() + ")");
}
```

The code returns the following path:
```java
(10, 10)
(10, 11)
(10, 12)
(10, 13)
(10, 14)
(10, 15)
(10, 16)
(11, 17)
(12, 18)
(12, 19)
(12, 20)
(12, 21)
(12, 22)
(12, 23)
(12, 24)
(12, 25)
(13, 26)
(13, 27)
(13, 28)
(13, 29)
(13, 30)
(13, 31)
(13, 32)
(13, 33)
(13, 34)
(14, 35)
(15, 36)
(16, 37)
(17, 38)
(18, 39)
(19, 40)
(20, 41)
(21, 40)
(22, 39)
(22, 38)
(23, 37)
(24, 36)
(25, 35)
(26, 34)
(27, 33)
(28, 32)
(29, 31)
(30, 30)
(31, 29)
(32, 28)
(33, 27)
(34, 26)
(35, 25)
(36, 24)
(37, 23)
(38, 22)
(38, 21)
(39, 20)
(40, 19)
(41, 20)
(42, 21)
(43, 22)
(44, 23)
(45, 24)
(46, 25)
(47, 26)
(48, 27)
(49, 28)
(49, 29)
(49, 30)
(49, 31)
(49, 32)
(49, 33)
(49, 34)
(49, 35)
(49, 36)
(49, 37)
(49, 38)
(49, 39)
(49, 40)
(49, 41)
(49, 42)
(49, 43)
(49, 44)
(49, 45)
(49, 46)
(50, 47)
(50, 48)
(50, 49)
(50, 50)
```

### What the continuous space looks like before the algorithm starts:

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/ContinuousPathfindingBefore.png "The continuous space before the algorithm starts.")

### What the continuous space looks like after the algorithm is finished:

![alt text](https://github.com/vmalepati1/Pathfinding/blob/master/images/ContinuousPathfindingAfter.png "The continuous space after the algorithm starts.")
