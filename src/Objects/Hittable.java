package Objects;

import utility.Point3D;
import utility.Ray;

public abstract class Hittable {
    public abstract boolean hit(Ray ray);

    public abstract float getT(Ray ray);
}
