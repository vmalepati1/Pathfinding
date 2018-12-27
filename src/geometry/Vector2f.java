package geometry;

public class Vector2f {

    private double x, y;

    public Vector2f() {
        set(0, 0);
    }

    public Vector2f(Vector2f vector) {
        set(vector.x, vector.y);
    }

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2f setX(double x) {
        this.x = x;
        return this;
    }

    public Vector2f setY(double y) {
        this.y = y;
        return this;
    }

    public boolean equals(Object object) {
        if(!(object instanceof Vector2f)) return false;
        Vector2f vec = (Vector2f) object;
        if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
        return false;
    }

}
