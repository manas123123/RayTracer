package Objects;

import camera.Camera;

import java.io.File;
import java.util.ArrayList;

public class Scene {
    ArrayList<Camera> cameras;
    HittableList<Hittable> objects;

    public Scene() {
        cameras = new ArrayList<>();
        objects = new HittableList<>();
    }

    public Scene(HittableList<Hittable> objects) {
        this.objects = objects;
    }

    public Scene(Camera camera) {
        this.cameras.add(camera);
    }

    public Scene(Camera camera, HittableList<Hittable> objects) {
        cameras = new ArrayList<>();
        cameras.add(camera);
        this.objects = objects;
    }

    public void addObj(Hittable hittable) {
        objects.add(hittable);
    }

    public void render() {
        for (int i = 0; i < cameras.size(); i++) {
            File image = new File("Image" + i + ".png");
            cameras.get(i).render(image, objects);
        }


    }

    public void addCamera(Camera camera) {
        cameras.add(camera);
    }
}
