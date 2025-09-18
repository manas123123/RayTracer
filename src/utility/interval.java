package utility;

public class interval {
    public float min, max;

    public interval(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public interval() {
        this(0, 0);
    }

    public interval(interval interval) {
        this(interval.min, interval.max);
    }

    public float size() {
        return max - min;
    }

    public boolean contains(float value) {
        return value >= min && value <= max;
    }

    public boolean surrounds(float value) {
        return value > min && value < max;

    }
}

