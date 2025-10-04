package Objects;


import utility.Color;
import utility.Normal;
import utility.Point3D;
import utility.Ray;
import utility.*;

import java.awt.*;


public class Sphere extends Hittable {
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

    public boolean hit(Ray ray, double tmin, double tmax, HitRecord rec) {

        Vector3D d = ray.direction;
        Point3D center = this.center;
        Vector3D oc = ray.origin.sub(center).toVector();
        float r = (float) this.radius;

        float a = (float) d.dot(d);
        float b = (float) (2.0 * (d.dot(oc)));
        double c = oc.dot(oc) - r * r;
        double disc = b * b - 4 * a * c;

        if (disc < 0)
            return false;

        float root = (float) ((-b - Math.sqrt(disc)) / (2.0 * a));
        float root2 = (float) ((-b + Math.sqrt(disc)) / (2.0 * a));


        float x = root < root2 ? root : root2;
        if (x <= tmin || x >= tmax) {
            x = root > root2 ? root : root2;
            if (x <= tmin || x >= tmax)
                return false;
        }


        rec.t = x;
        rec.p = ray.at(x);
        rec.n = rec.p.sub(center).toNormal();
        // Remove the redundant rec.n.normalize() since toNormal() already normalizes

        return true;
    }

    public float getT(Ray ray) {
        Vector3D d = ray.direction;
        Point3D center = this.center;
        Vector3D oc = ray.origin.sub(center).toVector();
        float r = (float) this.radius;

        float a = (float) d.dot(d);
        float b = (float) (2.0 * (d.dot(oc)));
        double c = oc.dot(oc) - r * r;
        double disc = b * b - 4 * a * c;

        if (disc < 0)
            return -1.0f;
        else
            return (float) ((-b - Math.sqrt(disc)) / (2.0 * a));

    }

    public String toString() {
        return "center: " + center.toString() + " radius: " + radius;
    }
}
