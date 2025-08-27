package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class Driver {
    public static void main(String[] args) {
        long start = System.nanoTime();

        Random random = new Random();
        int width = 1980;
        int height = 1080;

        File image = new File("image.png");
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, random.nextInt());
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", image);
        } catch (Exception e) {
            System.out.println("Error writing image");
        }
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start) / 1000000000.0F + "ms");
    }

}
