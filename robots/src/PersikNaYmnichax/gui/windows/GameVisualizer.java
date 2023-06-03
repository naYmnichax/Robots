package PersikNaYmnichax.gui.windows;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import PersikNaYmnichax.entities.*;
import PersikNaYmnichax.entities.Robot;
import PersikNaYmnichax.logic.MathTransformations;

public class GameVisualizer extends JPanel {
    private final Robot robot = new Robot(100, 100);
    ManualRobot ourRobot = new ManualRobot(0);
    private final Target target = new Target(400, 150);

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameVisualizer() {
        Timer timer = initTimer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 10);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                ourRobot.changeDirection(e);
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    protected void onModelUpdateEvent() {
        int width = this.getSize().width;
        int height = this.getSize().height;
        if (!robot.isNeedMove(target)) {
            return;
        }
        robot.moveRobot(ourRobot, width, height);
        ourRobot.update(target, width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRobot(g2d, MathTransformations.round(robot.positionX), MathTransformations.round(robot.positionY), robot.direction);
        drawOurRobot(g2d, ourRobot);
        drawTarget(g2d);
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, int robotCenterX, int robotCenterY, double direction) {
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
    }
    private void drawOurRobot(Graphics2D g, ManualRobot robot) {
        boolean flagFirstPartBody = true;
        for (Segment segment: robot.segments) {
            AffineTransform t = AffineTransform.getRotateInstance(robot.direction, segment.x, segment.y);
            g.setTransform(t);
            g.setColor(Color.GREEN);
            fillOval(g, segment.x, segment.y, 10, 10);
            g.setColor(Color.BLACK);
            drawOval(g, segment.x, segment.y, 10, 10);
            if (flagFirstPartBody) {
                g.setColor(Color.WHITE);
                fillOval(g, segment.x + 10, segment.y, 5, 5);
                g.setColor(Color.BLACK);
                drawOval(g, segment.x + 10, segment.y, 5, 5);
                flagFirstPartBody = false;
            }
        }
    }

    private void drawTarget(Graphics2D g) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, target.positionX, target.positionY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, target.positionX, target.positionY, 5, 5);
    }
}
