package PersikNaYmnichax.entities;

public enum ManualRobotOffset {
    UP_OFFSET(0, -1),
    RIGHT_OFFSET(1, 0),
    LEFT_OFFSET(-1, 0),
    DOWN_OFFSET(0, 1);
    private final int xOffset;
    private final int yOffset;

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
    ManualRobotOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
