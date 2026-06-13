package vehicle;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Vehicle {
    protected int x, y, width, height, wheelSize;
    protected Image bodyImage, wheelImage;
    protected double speed, currentRpm, maxRpm, baseTorque, mass;
    protected int currentGear, maxSpeed;
    protected double[] gearRatios;
    protected double wheelAngle;

    public Vehicle(int x, int y, int width, int height, int wheelSize, String bodyPath, String wheelPath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wheelSize = wheelSize;
        this.bodyImage = new ImageIcon(bodyPath).getImage();
        this.wheelImage = new ImageIcon(wheelPath).getImage();
        this.wheelAngle = 0;
    }

    public abstract void updatePhysics(boolean isAccelerating, boolean isBraking);
    public abstract void draw(Graphics2D g2d, Component component);

    public void changeGear(int newGear) {
        if (newGear >= 0 && newGear < gearRatios.length) {
            double oldRatio = (currentGear == 0) ? 1.0 : gearRatios[currentGear];
            double newRatio = (newGear == 0) ? 1.0 : gearRatios[newGear];

            this.currentRpm *= (newRatio / oldRatio);
            this.currentGear = newGear;
        }
    }

    public double getTorqueFactor() {
        if (currentRpm < 2000.00) return 0.5;
        if (currentRpm > (maxRpm - 500.00)) return 0.6;
        return 1.0;
    }

    public double getCurrentSpeed() { return this.speed; }
    public double getCurrentRpm() { return this.currentRpm; }
    public int getCurrentGear() { return this.currentGear; }
}