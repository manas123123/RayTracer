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
        long start = System.nanoTime();

        //Image Parameters
        double aspectRatio = 16.0 / 9.0;
        int height = 720;
        int width = (int) (((double) (height)) * (aspectRatio));

        //Camera Parameters
        double viewport_height = 2.0;
        double viewport_width = viewport_height * ((double) width / height);
        double viewport_dis = 1.0;
        Camera cam1 = new Camera(new Point3D(0, 0, 0), new Normal(0, 0, -1), viewport_height, viewport_width, 1.0);
        cam1.viewport_x = new Vector3D(viewport_width, 0, 0);
        cam1.viewport_y = new Vector3D(0, -viewport_height, 0);
        cam1.writeDeltas(height, width);




        File image = new File("image.png");
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, cam1.getPixelAt(x, y, height, width).getRGB());
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", image);
        } catch (Exception e) {
            System.out.println("Error writing image");
        }
        long end = System.nanoTime();

        System.out.print("Render finished! ");
        System.out.println("Time taken: " + (end - start) / 1000000000.0F + "ms");
    }

}
