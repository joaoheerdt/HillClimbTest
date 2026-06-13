import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import vehicle.*;
import map.GameMap;

public class GamePanel extends JPanel implements ActionListener {
    private Vehicle activeVehicle;
    private GameMap activeMap;
    private boolean isAccelerating = false;
    private boolean isBraking = false;
    private Timer loop;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        this.activeVehicle = new Fusca();
        this.activeMap = new GameMap("src/assets/map/terreno.png");

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) isAccelerating = true;
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S) isBraking = true;

                if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_4) {
                    activeVehicle.changeGear(e.getKeyCode() - KeyEvent.VK_0);
                }
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) isAccelerating = false;
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S) isBraking = false;
            }
        });

        loop = new Timer(16, this);
        loop.start();
    }

    public void actionPerformed(ActionEvent e) {
        activeVehicle.updatePhysics(isAccelerating, isBraking);
        activeMap.update(activeVehicle.getCurrentSpeed());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        activeMap.draw(g2d, this);
        activeVehicle.draw(g2d, this);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));

        int currentGear = activeVehicle.getCurrentGear();
        double currentRpm = activeVehicle.getCurrentRpm();
        double visualSpeed = activeVehicle.getCurrentSpeed() * 1.65;

        g2d.drawString("Gear: " + (currentGear == 0 ? "N" : currentGear), 20, 30);
        g2d.drawString(String.format("RPM: %.0f", currentRpm), 20, 55);
        g2d.drawString(String.format("Speed: %.0f km/h", visualSpeed), 20, 80);
    }
}