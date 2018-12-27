package continuous;

import geometry.Vector2i;

public class ContinuousNode {

    public Vector2i position;
    public double cost;
    public int pind;

    public ContinuousNode(Vector2i position, double cost, int pind) {
        this.position = position;
        this.cost = cost;
        this.pind = pind;
    }

}
