package PersikNaYmnichax.entities;

import java.awt.Point;
import java.util.Random;

public class Target {
    public volatile int positionX;
    public volatile int positionY;

    public Target(int x, int y) {
        positionX = x;
        positionY = y;
    }
    public void changeTargetPosition(int width, int height) {
        Random random = new Random();
        positionX = random.nextInt(width / 10) * 10;
        positionY = random.nextInt(height / 10) * 10;
    }
}
