package main;

import Objects.Hittable;
import Objects.HittableList;
import Objects.Scene;
import Objects.Sphere;
import camera.*;
import utility.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class Driver {
    public static void main(String[] args) {


        //Image Parameters
        double aspectRatio = 16.0 / 9.0;
        int height = 1080;

        //Initialize Camera
        double viewport_height = 2.0;
        double viewport_dis = 1.0;
        Point3D eye = new Point3D(0, 0, 0);
        Normal normal = new Normal(0, 0, -1);
        Camera cam1 = new Camera(eye, normal, viewport_height, viewport_dis, height, aspectRatio);

        double viewport_height2 = 2.0;
        double viewport_dis2 = 1.0;
        Point3D eye2 = new Point3D(0, 0, 1);
        Normal normal2 = new Normal(0, 0, -1);
        Camera cam2 = new Camera(eye2, normal2, viewport_height2, viewport_dis2, height, aspectRatio);


        //Initialize Object List
        Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1.0), 0.5);
        Sphere sphere2 = new Sphere(new Point3D(1.0, 0.0, -1.0), 0.5);
        Sphere sphere3 = new Sphere(new Point3D(-1.0, 0.0, -1.0), 0.5);
        Sphere sphere4 = new Sphere(new Point3D(0, -100.5, -1), 100);
        HittableList<Hittable> objs = new HittableList<>();


        //Initialize Scene
        Scene scene = new Scene();
        scene.addObj(sphere);

        scene.addObj(sphere4);
        scene.addCamera(cam1);
        //scene.addCamera(cam2);


        //Render Image
        File image = new File("image.png");
        scene.render();
    }

}
