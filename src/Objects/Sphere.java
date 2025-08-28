package Objects;


import utility.Point3D;
import utility.Ray;
import utility.Vector3D;


public class Sphere implements Hittable {
    public Point3D center;
    public double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean hit(Ray ray) {
        Vector3D d = ray.direction;
        Point3D center = this.center;
        Vector3D oc = ray.origin.sub(center).toVector();
        double r = this.radius;

        double a = d.dot(d);
        double b = 2.0 * (d.dot(oc));
        double c = oc.dot(oc) - r * r;
        double disc = b * b - 4 * a * c;

        return disc >= 0;
    }

    public Point3D getIntersection(Ray ray) {
        return null;
    }


    public String toString() {
        return "center: " + center.toString() + " radius: " + radius;
    }
}
