package PersikNaYmnichax.entities;

import java.awt.Point;

public class Target {
    public volatile int positionX;
    public volatile int positionY;

    public Target(int x, int y) {
        positionX = x;
        positionY = y;
    }
    public void setTargetPosition(Point p) {
        positionX = p.x;
        positionY = p.y;
    }
}
