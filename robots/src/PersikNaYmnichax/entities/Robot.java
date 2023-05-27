package PersikNaYmnichax.entities;

import PersikNaYmnichax.logic.MathTransformations;

public class Robot {
    public volatile double positionX;
    public volatile double positionY;
    public volatile double direction = 0;
    private static final double duration = 10;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;

    public Robot(double x, double y) {
        positionX = x;
        positionY = y;
    }

    private double getAngularVelocity(Target target) {
        double angleToTarget = MathTransformations.angleTo(
                positionX, positionY, target.positionX, target.positionY
        );
        if (angleToTarget > direction) {
            return maxAngularVelocity;
        }
        if (angleToTarget < direction) {
            return -maxAngularVelocity;
        }
        return 0;
    }

    public void moveRobot(Target target, int width, int height) {
        double angularVelocity = MathTransformations.applyLimits(
                getAngularVelocity(target), -maxAngularVelocity, maxAngularVelocity);
        double newX = positionX + maxVelocity / angularVelocity *
                (Math.sin(direction + angularVelocity * duration) -
                        Math.sin(direction));
        if (!Double.isFinite(newX)) {
            newX = positionX + maxVelocity * duration * Math.cos(direction);
        }
        double newY = positionY - maxVelocity / angularVelocity *
                (Math.cos(direction + angularVelocity * duration) -
                        Math.cos(direction));
        if (!Double.isFinite(newY)) {
            newY = positionY + maxVelocity * duration * Math.sin(direction);
        }
        positionX = MathTransformations.applyLimits(newX, 0, width);
        positionY = MathTransformations.applyLimits(newY, 0, height);
        double newDirection = MathTransformations.asNormalizedRadians(
                direction + angularVelocity * duration + bounceAngle(newX, newY)
        );
        direction = newDirection;
    }

    private double bounceAngle(double robotNewX, double robotNewY) {
        if (Double.compare(robotNewX, positionX) != 0) {
            return direction - Math.PI;
        } else if (Double.compare(robotNewY, positionY) != 0) {
            return direction - Math.PI / 2;
        }
        return 0;
    }

    public boolean isNeedMove(Target target) {
        double distance = MathTransformations.distance(
                positionX, positionY, target.positionX, target.positionY);
        return (distance >= 0.5);
    }
}
