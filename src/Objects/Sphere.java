package Objects;


import utility.Point3D;


public class Sphere {
    public Point3D center;
    public double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public String toString() {
        return "center: " + center.toString() + " radius: " + radius;
    }
}
