package utility;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class Color {
    public float r, g, b;
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
    }

    public void add(Color color) {
        this.r += color.r;
        this.g += color.g;
        this.b += color.b;
    }

    public void sub(Color color) {
        this.r -= color.r;
        this.g -= color.g;
        this.b -= color.b;
    }

    public void divide(int scalar) {
        this.r /= scalar;
        this.g /= scalar;
        this.b /= scalar;
    }

    public void multiply(Color color) {
        this.r *= color.r;
        this.g *= color.g;
        this.b *= color.b;
    }

    public Color toInt() {
        return(new Color(this.r, this.g, this.b));
    }


}
