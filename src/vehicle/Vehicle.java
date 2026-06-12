package vehicle;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Vehicle {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image bodyImage;
    protected Image wheelImage;
    protected int wheelSize;
    protected double wheelAngle;

    protected double currentSpeed;
    protected double maxSpeed;
    protected double power;

    public Vehicle(int x, int y, int width, int height, int wheelSize, String bodyPath, String wheelPath, double maxSpeed, double power) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wheelSize = wheelSize;
        this.bodyImage = new ImageIcon(bodyPath).getImage();
        this.wheelImage = new ImageIcon(wheelPath).getImage();
        this.wheelAngle = 0;
        this.currentSpeed = 0.0;
        this.maxSpeed = maxSpeed;
        this.power = power;
    }

    public void update(boolean accelerate, boolean reverse) {
        if (accelerate) {
            currentSpeed += power;
            if (currentSpeed > maxSpeed) {
                currentSpeed = maxSpeed;
            }
        }
        else if (reverse) {
            currentSpeed -= power;
            if (currentSpeed < -maxSpeed * 0.5) {
                currentSpeed = -maxSpeed * 0.5;
            }
        }
        else {
            if (currentSpeed > 0) {
                currentSpeed -= 0.1;
                if (currentSpeed < 0) currentSpeed = 0;
            } else if (currentSpeed < 0) {
                currentSpeed += 0.1;
                if (currentSpeed > 0) currentSpeed = 0;
            }
        }

        wheelAngle += currentSpeed * 0.02;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public abstract void draw(Graphics2D g2d, Component component);
}