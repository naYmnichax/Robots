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

    private double getAngularVelocity(Robot robot, Target target) {
        double angleToTarget = MathTransformations.angleTo(
                robot.positionX, robot.positionY, target.positionX, target.positionY
        );
        if (angleToTarget > robot.direction) {
            return maxAngularVelocity;
        }
        if (angleToTarget < robot.direction) {
            return -maxAngularVelocity;
        }
        return 0;
    }

    public void moveRobot(Robot robot, Target target, double width, double height) {
        double angularVelocity = MathTransformations.applyLimits(
                getAngularVelocity(robot, target), -maxAngularVelocity, maxAngularVelocity);
        double newX = robot.positionX + maxVelocity / angularVelocity *
                (Math.sin(robot.direction + angularVelocity * duration) -
                        Math.sin(robot.direction));
        if (!Double.isFinite(newX)) {
            newX = robot.positionX + maxVelocity * duration * Math.cos(robot.direction);
        }
        double newY = robot.positionY - maxVelocity / angularVelocity *
                (Math.cos(robot.direction + angularVelocity * duration) -
                        Math.cos(robot.direction));
        if (!Double.isFinite(newY)) {
            newY = robot.positionY + maxVelocity * duration * Math.sin(robot.direction);
        }
        double newDirection = MathTransformations.asNormalizedRadians(
                robot.direction + angularVelocity * duration
        );

        robot.positionX = newX;
        robot.positionY = newY;

        if (newX <= 1 | newY <= 1 | Math.abs(newX - width) <= 1 | Math.abs(newY - height) <= 1) {
            newDirection = (newDirection - Math.PI) % 2 * Math.PI;
        }
        robot.direction = newDirection;
    }

    public boolean isNeedMove(Robot robot, Target target) {
        double distance = MathTransformations.distance(
                robot.positionX, robot.positionY, target.positionX, target.positionY);
        return (distance >= 0.5);
    }
}
