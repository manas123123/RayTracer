package utility;

public class Vector3D {
    public double x, y, z;

    public Vector3D() {
        this(0, 0, 0);
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector3D v) {
        this(v.x, v.y, v.z);
    }

    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    public Vector3D sub(Vector3D v) {
        return new Vector3D(x - v.x, y - v.y, z - v.z);
    }

    public double dot(Vector3D v) {
        return (x * v.x + y * v.y + z * v.z);
    }

    public double dot(Point3D v) {
        return (x * v.x + y * v.y + z * v.z);
    }

    public double dot(Normal normal) {
        return (x * normal.x + y * normal.y + z * normal.z);
    }

    public void normalize() {
        double magnitude = Math.sqrt(x * x + y * y + z * z);
        if (magnitude != 0.0) {
            x /= magnitude;
            y /= magnitude;
            z /= magnitude;
        }
    }
}
