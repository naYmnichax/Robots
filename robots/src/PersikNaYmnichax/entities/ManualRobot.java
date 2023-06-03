package PersikNaYmnichax.entities;

import PersikNaYmnichax.logic.MathTransformations;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class ManualRobot {
    private static final int segmentSize = 10;
    public ArrayList<Segment> segments;
    public double direction;
    private boolean grow;
    private volatile int xOffset;
    private volatile int yOffset;

    public ManualRobot(double direction) {
        this.direction = direction;
        segments = new ArrayList<>();
        grow = false;

        for (int i = 0; i < 3; i++) {
            int x = (i + 1) * segmentSize;
            int y = 150;
            Segment segment = new Segment(x, y);
            segments.add(segment);
        }
    }

    public void update(Target target, int width, int height) {
        checkDirection();
        reachedTarget(target, width, height);
        boolean firstTime = true;
        for (Segment segment: segments) {
            if (firstTime) {
                segment.x += xOffset;
                segment.y += yOffset;
                firstTime = false;
            }

        }
    }
    public void changeDirection(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' -> direction = 0;
            case 'd' -> direction += ManualRobotDirection.RIGHT.getDirection();
            case 'a' -> direction += ManualRobotDirection.LEFT.getDirection();
        }
    }

    public void reachedTarget(Target target, int width, int height) {
        if (getHead().x == target.positionX && getHead().y == target.positionY) {
            grow = true;
            target.changeTargetPosition(width, height);
        }
        if (grow) {
            int x = getTail().x;
            int y = getTail().y;
            Segment tempSegment = new Segment(x, y);
            segments.add(tempSegment);
            grow = false;
        }
    }

    public Segment getHead() {
        return segments.get(0);
    }

    public Segment getTail() {
        return segments.get(segments.size() - 1);
    }

    private void checkDirection() {
        double quotient = direction / MathTransformations.asNormalizedRadians(Math.PI);
        if (quotient > 0){
            if (MathTransformations.round(quotient) / 2 == 0) {
                xOffset = ManualRobotOffset.LEFT_OFFSET.getXOffset();
                yOffset = ManualRobotOffset.LEFT_OFFSET.getYOffset();
            } else if (MathTransformations.round(quotient) / 3 == 0) {
                xOffset = ManualRobotOffset.UP_OFFSET.getXOffset();
                yOffset = ManualRobotOffset.UP_OFFSET.getYOffset();
            } else {
                xOffset = ManualRobotOffset.DOWN_OFFSET.getXOffset();
                yOffset = ManualRobotOffset.DOWN_OFFSET.getYOffset();
            }
        } else {
            xOffset = ManualRobotOffset.RIGHT_OFFSET.getXOffset();
            yOffset = ManualRobotOffset.RIGHT_OFFSET.getYOffset();
        }
    }
}
