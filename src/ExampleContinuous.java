import continuous.ContinuousNode;
import continuous.ContinuousPathfinding;
import geometry.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ExampleContinuous {

    public static void main(String[] args) {
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
    }

}
