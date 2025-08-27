package utility;

public class Ray {
    public Point3D origin;
    public Vector3D direction;

    public Ray() {
        origin = new Point3D();
        direction = new Vector3D();
    }

    public Ray(Point3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(Ray ray) {
        this.origin = ray.origin;
        this.direction = ray.direction;
    }

    public Point3D at(float t) {
        return origin.add(direction.mul(t));

    }
}
