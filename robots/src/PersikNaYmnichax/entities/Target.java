package PersikNaYmnichax.entities;

import java.awt.Point;

public class Target {
    public volatile int positionX = 150;
    public volatile int positionY = 100;

    public void setTargetPosition(Point p) {
        positionX = p.x;
        positionY = p.y;
    }
}
