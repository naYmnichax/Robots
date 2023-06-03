package PersikNaYmnichax.entities;

import PersikNaYmnichax.logic.MathTransformations;

public class Robot {
    public volatile double positionX;
    public volatile double positionY;
    public volatile double direction = 0;
    private double angle = 0;
    private static final double duration = 10;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;

    public Robot(double x, double y) {
        positionX = x;
        positionY = y;
    }

    private double getAngularVelocity(ManualRobot target) {
        double angleToTarget = MathTransformations.angleTo(
                positionX, positionY, target.getTail().x, target.getTail().y
        );
        double newAngle = MathTransformations.asNormalizedRadians(angleToTarget - direction);
        angle = newAngle;
        if (Math.abs(direction - angleToTarget) < 10e-7) {
            return direction;
        } else if (direction >= Math.PI) {
            if (direction - Math.PI < angleToTarget && angleToTarget < direction)
                return -maxAngularVelocity;
            else
                return maxAngularVelocity;
        } else {
            if (direction < angleToTarget && angleToTarget < direction + Math.PI)
                return maxAngularVelocity;
            else
                return -maxAngularVelocity;
        }
    }

    public void moveRobot(ManualRobot target, int width, int height) {
        double angularVelocity = MathTransformations.applyLimits(
                getAngularVelocity(target), -maxAngularVelocity, maxAngularVelocity);
        double newDirection = MathTransformations.asNormalizedRadians(
                direction + Math.min(angle, angularVelocity) * duration
        );
        direction = newDirection;
        double newX = positionX + maxVelocity * duration * Math.cos(direction);
        double newY = positionY + maxVelocity * duration * Math.sin(direction);
        positionX = newX;
        positionY = newY;
        checkBorders(width, height);
    }

    public boolean isNeedMove(Target target) {
        double distance = MathTransformations.distance(
                positionX, positionY, target.positionX, target.positionY);
        return (distance >= 0.5);
    }

    public void checkBorders(double width, double height) {
        double newX = MathTransformations.applyLimits(positionX, 0, width);
        double newY = MathTransformations.applyLimits(positionY, 0, height);
        positionX = newX;
        positionY = newY;
    }
}
