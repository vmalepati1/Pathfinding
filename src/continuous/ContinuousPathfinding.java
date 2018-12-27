package continuous;

import geometry.Vector2f;
import geometry.Vector2i;
import geometry.Vector3f;

import java.util.*;

public class ContinuousPathfinding {

    public List<ContinuousNode> findPath(Vector2f startPosition, Vector2f goalPosition, List<Vector2f> obstaclePositions, double resolution, double robotRadius) {
        ContinuousNode nstart = new ContinuousNode(new Vector2i((int)Math.round(startPosition.getX() / resolution), (int)Math.round(startPosition.getY() / resolution)), 0.0, -1);
        ContinuousNode ngoal = new ContinuousNode(new Vector2i((int)Math.round(goalPosition.getX() / resolution), (int)Math.round(goalPosition.getY() / resolution)), 0.0, -1);

        for (Vector2f v : obstaclePositions) {
            v.set(v.getX() / resolution, v.getY() / resolution);
        }

        DoubleSummaryStatistics summaryStatisticsX = obstaclePositions.stream().mapToDouble(Vector2f::getX).summaryStatistics();
        DoubleSummaryStatistics summaryStatisticsY = obstaclePositions.stream().mapToDouble(Vector2f::getY).summaryStatistics();

        int minx = (int) Math.round(summaryStatisticsX.getMin());
        int miny = (int) Math.round(summaryStatisticsY.getMin());
        int maxx = (int) Math.round(summaryStatisticsX.getMax());
        int maxy = (int) Math.round(summaryStatisticsY.getMax());

        int xwidth = Math.round(maxx - minx);
        int ywidth = Math.round(maxy - miny);

        boolean[][] obmap = new boolean[xwidth][ywidth];

        for (int ix = 0; ix < xwidth; ix++) {
            int x = ix + minx;
            for (int iy = 0; iy < ywidth; iy++) {
                int y = iy + miny;

                for (Vector2f v : obstaclePositions) {
                    double d = Math.sqrt(Math.pow((v.getX() - x), 2) + Math.pow((v.getY() - y), 2));

                    if (d <= robotRadius / resolution) {
                        obmap[ix][iy] = true;
                        break;
                    }
                }
            }
        }

        List<Vector3f> motion = new ArrayList<Vector3f>();

        motion.add(new Vector3f(1, 0, 1));
        motion.add(new Vector3f(0, 1, 1));
        motion.add(new Vector3f(-1, 0, 1));
        motion.add(new Vector3f(0, -1, 1));
        motion.add(new Vector3f(-1, -1, Math.sqrt(2)));
        motion.add(new Vector3f(-1, 1, Math.sqrt(2)));
        motion.add(new Vector3f(1, -1, Math.sqrt(2)));
        motion.add(new Vector3f(1, 1, Math.sqrt(2)));

        Map<Integer, ContinuousNode> openset = new HashMap<Integer, ContinuousNode>();
        Map<Integer, ContinuousNode> closedset = new HashMap<Integer, ContinuousNode>();

        openset.put(calculateIndex(nstart, xwidth, minx, miny), nstart);

        while (true) {
            double previousValue = Double.MAX_VALUE;
            int cid = 0;

            for (int key : openset.keySet()) {
                if(openset.get(key).cost + calculateHeuristic(ngoal, openset.get(key)) < previousValue) {
                    previousValue = openset.get(key).cost + calculateHeuristic(ngoal, openset.get(key));
                    cid = key;
                }
            }

            ContinuousNode current = openset.get(cid);

            if (current.position.getX() == ngoal.position.getX() && current.position.getY() == ngoal.position.getY()) {
                ngoal.pind = current.pind;
                ngoal.cost = current.cost;
                break;
            }

            openset.remove(cid);

            closedset.put(cid, current);

            for (int i = 0; i < motion.size(); i++) {
                ContinuousNode continuousNode = new ContinuousNode(new Vector2i((int) (current.position.getX() + motion.get(i).getX()), (int) (current.position.getY() + motion.get(i).getY())), current.cost + motion.get(i).getZ(), cid);

                int nid = calculateIndex(continuousNode, xwidth, minx, miny);

                if (closedset.containsKey(nid)) {
                    continue;
                }

                if (!verifyNode(continuousNode, obmap, minx, miny, maxx, maxy)) {
                    continue;
                }

                if (!openset.containsKey(nid)) {
                    openset.put(nid, continuousNode);
                } else {
                    if (openset.get(nid).cost >= continuousNode.cost) {
                        openset.put(nid, continuousNode);
                    }
                }
            }
        }

        List<ContinuousNode> rlist = calculateFinalPath(ngoal, closedset, resolution);

        return rlist;
    }

    List<ContinuousNode> calculateFinalPath(ContinuousNode ngoal, Map<Integer, ContinuousNode> closedset, double resolution) {
        List<ContinuousNode> rlist = new ArrayList<ContinuousNode>();

        rlist.add(ngoal);

        int pind = ngoal.pind;

        while (pind != -1) {
            ContinuousNode n = closedset.get(pind);
            rlist.add(n);
            pind = n.pind;
        }

        Collections.reverse(rlist);

        return rlist;
    }

    int calculateIndex(ContinuousNode continuousNode, int xwidth, int xmin, int ymin) {
        return (continuousNode.position.getY() - ymin) * xwidth + (continuousNode.position.getX() - xmin);
    }

    double calculateHeuristic(ContinuousNode n1, ContinuousNode n2) {
        double weight = 1.0;

        double d = weight * Math.sqrt(Math.pow(n1.position.getX() - n2.position.getX(), 2) + Math.pow(n1.position.getY() - n2.position.getY(), 2));
        return d;
    }

    boolean verifyNode(ContinuousNode continuousNode, boolean[][] obmap, double minx, double miny, double maxx, double maxy) {
        if (continuousNode.position.getX() < minx) {
            return false;
        } else if (continuousNode.position.getY() < miny) {
            return false;
        } else if (continuousNode.position.getX() >= maxx) {
            return false;
        } else if (continuousNode.position.getY() >= maxy) {
            return false;
        }

        if (obmap[(int) continuousNode.position.getX()][(int) continuousNode.position.getY()])
            return false;

        return true;
    }

}
