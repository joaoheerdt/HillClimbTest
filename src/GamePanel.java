import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import vehicle.Vehicle;
import vehicle.Fusca;
import map.GameMap;

public class GamePanel extends JPanel implements ActionListener {

    private Vehicle activeVehicle;
    private GameMap activeMap;
    private boolean accelerate = false;
    private boolean reverse = false;
    private Timer loop;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);

        this.activeVehicle = new Fusca();
        this.activeMap = new GameMap("src/assets/map/terreno.png");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    accelerate = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    reverse = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    accelerate = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    reverse = false;
                }
            }
        });

        loop = new Timer(16, this);
        loop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        activeMap.update(accelerate, reverse);
        activeVehicle.update(accelerate, reverse);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        activeMap.draw(g2d, this);
        activeVehicle.draw(g2d, this);
    }
}