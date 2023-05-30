package PersikNaYmnichax.logic;

public class MathTransformations {
    public static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    public static double applyLimits(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    public static double asNormalizedRadians(double angle) {
        return (angle % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public static int round(double value) {
        return (int) (value + 0.5);
    }
}
