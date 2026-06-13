package vehicle;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Fusca extends Vehicle {

    public Fusca() {
        super(50, 280, 300, 150, 67, "src/assets/vehicles/FuscaVermelho.png", "src/assets/vehicles/RodaFusca.png");
        this.maxRpm = 5000.0;
        this.baseTorque = 150.0;
        this.mass = 800.0;
        this.gearRatios = new double[]{1.0, 3.8, 2.0, 1.2, 0.9};
        this.currentGear = 1;
        this.currentRpm = 1000.0;
    }

    @Override
    public void updatePhysics(boolean isAccelerating, boolean isBraking) {
        double gearRatio = (currentGear == 0) ? 1.0 : gearRatios[currentGear];

        if (isAccelerating) {
            double gainRpm = ((baseTorque * 40 * gearRatio) / mass) * getTorqueFactor();
            this.currentRpm += gainRpm;
        } else if (isBraking) {
            this.currentRpm -= 80;
        } else {
            this.currentRpm -= 25;
        }

        if (currentRpm < 1000.0) this.currentRpm = 1000.0;
        if (currentRpm > maxRpm) this.currentRpm = maxRpm;

        if (this.currentRpm <= 1000.0 && !isAccelerating) {
            this.speed = 0;
        } else {
            this.speed = (this.currentRpm / gearRatio) * 0.015;
        }

        this.wheelAngle += this.speed * 0.04;
    }

    @Override
    public void draw(Graphics2D g2d, Component component) {
        g2d.drawImage(bodyImage, x, y, width, height, component);

        int backWheelX = x + 40;
        int frontWheelX = x + 200;
        int wheelY = y + 80;


        AffineTransform oldTransform = g2d.getTransform();

        g2d.translate(backWheelX + wheelSize / 2.0, wheelY + wheelSize / 2.0);
        g2d.rotate(this.wheelAngle);
        g2d.drawImage(wheelImage, -wheelSize / 2, -wheelSize / 2, wheelSize, wheelSize, component);
        g2d.setTransform(oldTransform);

        g2d.translate(frontWheelX + wheelSize / 2.0, wheelY + wheelSize / 2.0);
        g2d.rotate(this.wheelAngle);
        g2d.drawImage(wheelImage, -wheelSize / 2, -wheelSize / 2, wheelSize, wheelSize, component);
        g2d.setTransform(oldTransform);
    }
}