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

    public abstract void update(boolean accelerate, boolean reverse);
    public abstract void draw(Graphics2D g2d, Component component);
}