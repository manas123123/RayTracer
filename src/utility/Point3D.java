package utility;

public class Point3D {
    public double x, y, z;

    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D p) {

        this(p.x, p.y, p.z);
    }

    public Point3D add(Point3D p) {
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public Point3D sub(Point3D p) {
        return new Point3D(x - p.x, y - p.y, z - p.z);
    }

    public double dot(Point3D p) {
        return (x * p.x + y * p.y + z * p.z);
    }

    public double dot(Vector3D v) {
        return (x * v.x + y * v.y + z * v.z);
    }
}
