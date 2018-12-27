package grid;

import geometry.Vector2i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GridPathfinding {

    private Comparator<GridNode> nodeSorter = new Comparator<GridNode>() {
        public int compare(GridNode n0, GridNode n1) {
            if (n1.fCost < n0.fCost) return +1;
            if (n1.fCost > n0.fCost) return -1;
            return 0;
        }
    };

    public List<GridNode> findPath(Vector2i start, Vector2i goal) {
        List<GridNode> openList = new ArrayList<GridNode>();
        List<GridNode> closedList = new ArrayList<GridNode>();
        GridNode current = new GridNode(start, null, 0, getDistance(start, goal));
        openList.add(current);
        while (openList.size() > 0) {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);
            if (current.tile.equals(goal)) {
                List<GridNode> path = new ArrayList<GridNode>();
                while (current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();

                Collections.reverse(path);

                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9; i++) {
                if (i == 4) continue;
                int x = current.tile.getX();
                int y = current.tile.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;

                Vector2i a = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 1.44);
                double hCost = getDistance(a, goal);
                GridNode gridNode = new GridNode(a, current, gCost, hCost);
                if (vecInList(closedList, a) && gCost >= current.gCost) continue;
                if (!vecInList(openList, a) || gCost < current.gCost) openList.add(gridNode);
            }
        }
        closedList.clear();
        return null;
    }

    public List<GridNode> findPath(Vector2i start, Vector2i goal, CollisionGrid collisionGrid) {
        List<GridNode> openList = new ArrayList<GridNode>();
        List<GridNode> closedList = new ArrayList<GridNode>();
        GridNode current = new GridNode(start, null, 0, getDistance(start, goal));
        openList.add(current);
        while (openList.size() > 0) {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);
            if (current.tile.equals(goal)) {
                List<GridNode> path = new ArrayList<GridNode>();
                while (current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();

                Collections.reverse(path);

                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9; i++) {
                if (i == 4) continue;
                int x = current.tile.getX();
                int y = current.tile.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;

                if (collisionGrid.getTileBlocked(x + xi, y + yi)) continue;

                Vector2i a = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 1.44);
                double hCost = getDistance(a, goal);
                GridNode gridNode = new GridNode(a, current, gCost, hCost);
                if (vecInList(closedList, a) && gCost >= current.gCost) continue;
                if (!vecInList(openList, a) || gCost < current.gCost) openList.add(gridNode);
            }
        }
        closedList.clear();
        return null;
    }

    private boolean vecInList(List<GridNode> list, Vector2i vector) {
        for (GridNode n : list) {
            if (n.tile.equals(vector)) return true;
        }
        return false;
    }

    private double getDistance(Vector2i tile, Vector2i goal) {
        double dx = tile.getX() - goal.getX();
        double dy = tile.getY() - goal.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

}
