package Objects;

import utility.Point3D;
import utility.Ray;

public abstract class Hittable {
    public abstract boolean hit(Ray ray);

    public abstract boolean hit(Ray ray, double tmin, double tmax, HitRecord rec);
    public abstract float getT(Ray ray);
}
