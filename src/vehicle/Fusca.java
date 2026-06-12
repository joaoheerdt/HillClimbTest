package vehicle;

import java.awt.*;

public class Fusca extends Vehicle {

    public Fusca() {
        super(10, 280, 350, 170, 80, "src/assets/vehicles/FuscaVermelho.png", "src/assets/vehicles/RodaFusca.png", 100.0, 0.5);
    }



    @Override
    public void draw(Graphics2D g2d, Component component) {
        double wheelRadius = wheelSize / 2.0;
        int rearWheelX = x + 45;
        int rearWheelY = y + 90;
        int frontWheelX = x + 230;
        int frontWheelY = y + 90;

        g2d.drawImage(bodyImage, x, y, width, height, component);

        Graphics2D gRearWheel = (Graphics2D) g2d.create();
        gRearWheel.rotate(wheelAngle, rearWheelX + wheelRadius, rearWheelY + wheelRadius);
        gRearWheel.drawImage(wheelImage, rearWheelX, rearWheelY, wheelSize, wheelSize, component);
        gRearWheel.dispose();

        Graphics2D gFrontWheel = (Graphics2D) g2d.create();
        gFrontWheel.rotate(wheelAngle, frontWheelX + wheelRadius, frontWheelY + wheelRadius);
        gFrontWheel.drawImage(wheelImage, frontWheelX, frontWheelY, wheelSize, wheelSize, component);
        gFrontWheel.dispose();
    }
}