package main;

import camera.*;
import utility.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class Driver {
    public static void main(String[] args) {


        //Image Parameters
        double aspectRatio = 16.0 / 9.0;
        int height = 1080;

        //Camera Parameters
        double viewport_height = 2.0;
        double viewport_dis = 1.0;
        Point3D eye = new Point3D(0, 0, 0);
        Normal normal = new Normal(0, 0, -1);

        //Initialize Camera
        Camera cam1 = new Camera(eye, normal, viewport_height, viewport_dis, height, aspectRatio);

        //Render Image
        File image = new File("image.png");
        cam1.render(image);
    }

}
