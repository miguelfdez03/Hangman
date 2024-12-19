package ahorcado;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameFigure extends JPanel {

    private int errorCount = 0;

    public GameFigure() {
        setSize(600, 300);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        // Draw base of the gallows
        g.fillRect(0, 10, 10, 350);
        g.fillRect(10, 10, 150, 10);
        g.drawLine(150, 10, 150, 50);

        switch (errorCount) {
            case 1 -> g.drawOval(125, 50, 50, 50); // Head
            case 2 -> {
                g.drawOval(125, 50, 50, 50);
                g.drawLine(150, 100, 150, 160); // Body
            }
            case 3 -> {
                g.drawOval(125, 50, 50, 50);
                g.drawLine(150, 100, 150, 160);
                g.drawLine(150, 100, 100, 130); // Left arm
            }
            case 4 -> {
                g.drawOval(125, 50, 50, 50);
                g.drawLine(150, 100, 150, 160);
                g.drawLine(150, 100, 100, 130);
                g.drawLine(150, 100, 200, 130); // Right arm
            }
            case 5 -> {
                g.drawOval(125, 50, 50, 50);
                g.drawLine(150, 100, 150, 160);
                g.drawLine(150, 100, 100, 130);
                g.drawLine(150, 100, 200, 130);
                g.drawLine(150, 160, 100, 200); // Left leg
            }
            case 6 -> {
                g.drawOval(125, 50, 50, 50);
                g.drawLine(150, 100, 150, 160);
                g.drawLine(150, 100, 100, 130);
                g.drawLine(150, 100, 200, 130);
                g.drawLine(150, 160, 100, 200);
                g.drawLine(150, 160, 200, 200); // Right leg
            }
            default -> {}
        }
    }

    // Increment error count and repaint
    public void setErrorCount(int count) {
        this.errorCount = count;
        this.repaint();
    }
}
