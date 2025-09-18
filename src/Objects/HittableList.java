package Objects;

import utility.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class HittableList<T extends Hittable> {

    private List<T> list;

    public HittableList(List<T> list) {
        this.list = list;
    }

    public HittableList() {
        this.list = new ArrayList<T>();
    }

    public void add(T t) {
        list.add(t);
    }

    public void clear() {
        list.clear();
    }

    public boolean hit(Ray ray, float tmin, float tmax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitted = false;
        float t = 0;

        for (T item : list) {
            if (item.hit(ray, tmin, tmax, tempRec)) {
                hitted = true;
                tmax = tempRec.t;
                rec.t = tempRec.t;
                rec.p = tempRec.p;
                rec.n = tempRec.n;

            }
        }
        return hitted;
    }
}
