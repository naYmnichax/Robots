package PersikNaYmnichax.entities;

public enum ManualRobotDirection {
    LEFT(4.71),
    RIGHT(1.57);
    private final double direction;

    ManualRobotDirection(double direction) {
        this.direction = direction;
    }

    public double getDirection() {
        return direction;
    }
}
