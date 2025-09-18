package camera;

import Objects.*;
import utility.Normal;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

import java.awt.*;

public class Camera {
    public Point3D position;
    public Normal normal;
    public double viewport_height;
    public double viewport_width;
    public double viewport_dis;
    public Vector3D viewport_x;
    public Vector3D viewport_y;
    public Vector3D delta_x;
    public Vector3D delta_y;

    public Camera() {
        position = new Point3D();
        normal = new Normal();
        viewport_height = 0.0;
        viewport_width = 0.0;
        viewport_dis = 0.0;
    }

    public Camera(Point3D position, Normal normal, double viewport_height, double viewport_width, double viewport_dis) {
        this.position = new Point3D(position);
        this.normal = new Normal(normal);
        this.viewport_height = viewport_height;
        this.viewport_width = viewport_width;
        this.viewport_dis = viewport_dis;

    }

    public void writeDeltas(int height, int width) {
        delta_x = new Vector3D(viewport_x.div(width));
        delta_y = new Vector3D(viewport_y.div(height));
    }

    public Color getPixelAt(int x, int y, int image_width, int image_height) {
        Point3D viewport_upper_left = new Point3D(position.add(normal).sub(viewport_y.mul(0.5f)).sub(viewport_x.mul(0.5f)));
        Point3D pixel00 = viewport_upper_left.add(delta_x.mul(0.5f).add(delta_y.mul(0.5f)));
        Point3D pixelCenter = pixel00.add(delta_x.mul(x)).add(delta_y.mul(y));
        Ray ray = new Ray(position, new Vector3D(pixelCenter.x - position.x, pixelCenter.y - position.y, pixelCenter.z - position.z));

        float r = 0.5f + 0.5f * y / image_height;
        float g = 0.7f + 0.3f * y / image_height;

        Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1.0), 0.5, new Color(1.0f, 0, 0));
        Sphere sphere2 = new Sphere(new Point3D(1.0, 0.0, -1.0), 0.5, new Color(0, 0, 1.0f));
        Sphere sphere3 = new Sphere(new Point3D(-1.0, 0.0, -1.0), 0.5, new Color(0, 1.0f, 0));


        HittableList<Hittable> scene = new HittableList<>();
        scene.add(sphere);
        scene.add(sphere2);
        scene.add(sphere3);

        HitRecord rec = new HitRecord();

        if (scene.hit(ray, 0, 1000, rec)) {
            Color c = rec.color;
            float red = c.getRed() / 256f;
            float green = c.getGreen() / 256f;
            float blue = c.getBlue() / 256f;


            Normal light = new Normal(-1, -1, -1);
            light.normalize();
            float a = (float) rec.n.dot(light.mul(-1.0f));
            a = a < 0 ? 0 : a;
            return new Color((float) red * a, (float) green * a, (float) blue * a);
        }

        return new Color(r, g, 1.0f);
    }


}
