package Objects;

import utility.Point3D;
import utility.Ray;

public interface Hittable {
    public boolean hit(Ray ray);

    public Point3D getIntersection(Ray ray);
}
