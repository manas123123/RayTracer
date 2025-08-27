package utility;

public class Normal {
    public double x, y, z;

    public Normal() {
        this(0.0, 0.0, 0.0);
    }

    public Normal(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal(Normal normal) {
        this(normal.x, normal.y, normal.z);
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
