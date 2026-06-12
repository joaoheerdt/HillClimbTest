package map;
import java.awt.*;
import javax.swing.ImageIcon;

    public class GameMap {

        private Image mapImage;
        private int mapMovement;
        private int mapWidth;

        public GameMap(String imagePath) {
            this.mapImage = new ImageIcon(imagePath).getImage();
            this.mapMovement = 0;
            this.mapWidth = 800;
        }

        public void update(boolean accelerate, boolean reverse) {
            if (accelerate) {
                mapMovement -= 5;
            }
            if (reverse) {
                mapMovement += 5;
            }
        }

        public void draw(Graphics2D g2d, Component component) {
            int visualOffset = mapMovement % mapWidth;

            for (int posX = -mapWidth; posX < 800 + mapWidth; posX += mapWidth) {
                g2d.drawImage(mapImage, posX + visualOffset, 0, 800, 600, component);
            }
        }
    }

