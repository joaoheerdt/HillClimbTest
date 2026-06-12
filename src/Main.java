import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Hill Climb: The game");
        setResizable(false);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}