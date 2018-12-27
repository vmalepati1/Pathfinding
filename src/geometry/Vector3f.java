package geometry;

public class Vector3f {

    private double x, y, z;

    public Vector3f() {
        set(0, 0, 0);
    }

    public Vector3f(Vector3f vector) {
        set(vector.x, vector.y, vector.z);
    }

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3f setX(double x) {
        this.x = x;
        return this;
    }

    public Vector3f setY(double y) {
        this.y = y;
        return this;
    }

    public Vector3f setZ(double z) {
        this.z = z;
        return this;
    }

    public boolean equals(Object object) {
        if(!(object instanceof Vector3f)) return false;
        Vector3f vec = (Vector3f) object;
        if(vec.getX() == this.getX() && vec.getY() == this.getY() && vec.getZ() == this.getZ()) return true;
        return false;
    }

}
