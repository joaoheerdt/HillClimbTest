import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    public Main() {
        setTitle("Hill Climb: The game");
        setResizable(false);
        TelaJogo tela = new TelaJogo();
        add(tela);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    class TelaJogo extends JPanel implements ActionListener {

        private int carroX = 10;
        private int carroY = 280;
        private int carroLargura = 300;
        private int carroAltura = 170;
        private Image imagemCarro;
        private Image imagemCenario;
        private int cenarioMovimento = 0;

        private boolean controleDireita = false;
        private boolean controleEsquerda = false;
        private Timer loop;

        public TelaJogo() {
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.blue.brighter());
            imagemCarro = new ImageIcon("resources/FuscaVermelho.png").getImage();
            imagemCenario = new ImageIcon("resources/terreno.png").getImage();

            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        controleDireita = true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        controleEsquerda = true;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        controleDireita = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        controleEsquerda = false;
                    }
                }
            });

            loop = new Timer(16, this);
            loop.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (controleDireita) {
                cenarioMovimento -= 5;
            }
            if (controleEsquerda) {
                cenarioMovimento += 5;
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int larguraCenario = 800;
            int deslocamentoVisual = cenarioMovimento % larguraCenario;

            for (int posicaoX = -larguraCenario; posicaoX < 800 + larguraCenario; posicaoX += larguraCenario) {
                g2d.drawImage(imagemCenario, posicaoX + deslocamentoVisual, 0, 800, 600, this);
            }

            g2d.drawImage(imagemCarro, carroX, carroY, carroLargura, carroAltura, this);
        }
    }
}