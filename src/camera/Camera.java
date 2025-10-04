package camera;

import Objects.*;
import utility.Normal;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static utility.Normal.normalize;

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
    int height, width;


    public Camera() {
        position = new Point3D();
        normal = new Normal();
        viewport_height = 0.0;
        viewport_width = 0.0;
        viewport_dis = 0.0;
        viewport_x = new Vector3D();
        viewport_y = new Vector3D();
        delta_x = new Vector3D();
        delta_y = new Vector3D();
        height = 0;
        width = 0;
    }

    public Camera(Point3D position, Normal normal, double viewport_height, double viewport_dis, int height, double aspectRatio) {
        this.height = height;
        this.width = (int) (((double) (height)) * (aspectRatio));

        this.position = new Point3D(position);
        this.normal = new Normal(normal);
        this.viewport_height = viewport_height;
        this.viewport_width = viewport_height * ((double) width / height);
        this.viewport_dis = viewport_dis;

        viewport_x = new Vector3D(viewport_width, 0, 0);
        viewport_y = new Vector3D(0, -viewport_height, 0);

        writeDeltas(height, width);

    }

    public void writeDeltas(int height, int width) {
        delta_x = new Vector3D(viewport_x.div(width));
        delta_y = new Vector3D(viewport_y.div(height));
    }

    public Color getPixelAt(int x, int y, HittableList<Hittable> scene) {
        Point3D viewport_upper_left = new Point3D(position.add(normalize(normal)).sub(viewport_y.mul(0.5f)).sub(viewport_x.mul(0.5f)));
        Point3D pixel00 = viewport_upper_left.add(delta_x.mul(0.5f).add(delta_y.mul(0.5f)));
        Point3D pixelCenter = pixel00.add(delta_x.mul(x)).add(delta_y.mul(y));

        int samples = 64;
        int maxDepth = 12;


        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;

        for (int i = 0; i < samples; i++) {
            float randx = (float) (Math.random() - 0.5);
            float randy = (float) (Math.random() - 0.5);

            Point3D pc = pixelCenter.add(delta_x.mul(randx)).add(delta_y.mul(randy));
            Ray ray = new Ray(position, new Vector3D(pc.x - position.x, pc.y - position.y, pc.z - position.z));

            float r = 0.5f + 0.5f * y / height;
            float g = 0.7f + 0.3f * y / height;


            HitRecord rec = new HitRecord();
            float[] colorRGB = new float[3];

            if (rayColor(ray, colorRGB, maxDepth, scene)) {
                red += colorRGB[0];
                green += colorRGB[1];
                blue += colorRGB[2];
            } else {
                red += r;
                green += g;
                blue += 0.99f;
            }

//            if (scene.hit(ray, 0, 1000000000, rec)) {
//
//
//                Normal light = new Normal(-1, -1, -1);
//                light.normalize();
//                float a = (float) rec.n.dot(light.mul(-1.0f));
//                a = a < 0 ? 0 : a;
//
//                red += (float) (rec.n.x + 1) * 0.5f;
//                green += (float) (rec.n.y + 1) * 0.5f;
//                blue += (float) (rec.n.z + 1) * 0.5f;
//            } else { // if it does not hit anything
//
//                blue += 1.0f;
//            }
        }

        red /= samples;
        green /= samples;
        blue /= samples;
        return new Color(red, green, blue);
    }

    public boolean rayColor(Ray ray, float[] colorRGB, int maxDepth, HittableList<Hittable> scene) {

        HitRecord rec = new HitRecord();
        if (maxDepth == 0) {
            colorRGB[0] = 0.0f;
            colorRGB[1] = 0.0f;
            colorRGB[2] = 0.0f;
            return true;

        } else if (scene.hit(ray, 0.00001f, 1000000000, rec)) {
            Ray ray2 = getReflectedRay(rec);

            rayColor(ray2, colorRGB, maxDepth - 1, scene);
            // get reflection
            float absorbance = 0.5f;
            colorRGB[0] *= 1.0 - absorbance;
            colorRGB[1] *= 1.0 - absorbance;
            colorRGB[2] *= 1.0 - absorbance;
            return true;
        } else {
            colorRGB[0] = 0.5f;
            colorRGB[1] = 0.7f;
            colorRGB[2] = 1.0f;
            return false;
        }
    }

    public Ray getReflectedRay(HitRecord rec) {
        // Generate random point in unit sphere using rejection sampling
        Vector3D randomVec;
        do {
            randomVec = new Vector3D(
                    Math.random() * 2 - 1,  // Random in [-1, 1]
                    Math.random() * 2 - 1,  // Random in [-1, 1]
                    Math.random() * 2 - 1   // Random in [-1, 1]
            );
        } while (randomVec.dot(randomVec) > 1.0);  // Reject if outside unit sphere

        // Lambertian diffuse: normal + random vector
        Vector3D normalVec = new Vector3D(rec.n.x, rec.n.y, rec.n.z);
        Vector3D scatterDirection = normalVec.add(randomVec);

        // Normalize the scatter direction
        scatterDirection.normalize();

        return new Ray(rec.p, scatterDirection);
    }

    public void render(File image, HittableList<Hittable> objs) {
        long start = System.nanoTime();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, getPixelAt(x, y, objs).getRGB());
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", image);
        } catch (Exception e) {
            System.out.println("Error writing image");
        }

        long end = System.nanoTime();
        System.out.print("Render finished! ");
        System.out.println("Time taken: " + (end - start) / 1000000000.0F + "s");
    }

    public boolean hit(Ray ray, float tmin, float tmax, HitRecord rec) {
        return false;
    }

}
