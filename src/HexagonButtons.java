import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

public class HexagonButtons extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int HEX_RADIUS = 50;

    public HexagonButtons() {
        setLayout(null);
        int[] xCoords = new int[6];
        int[] yCoords = new int[6];
        for (int i = 0; i < 6; i++) {
            xCoords[i] = (int) (HEX_RADIUS * Math.cos(Math.PI / 3.0 * i + Math.PI / 6.0));
            yCoords[i] = (int) (HEX_RADIUS * Math.sin(Math.PI / 3.0 * i + Math.PI / 6.0));
        }
        int numSides = 6;
        int buttonWidth = HEX_RADIUS / 3;
        int buttonHeight = (int) Math.sqrt(Math.pow(HEX_RADIUS, 2) - Math.pow(buttonWidth / 2, 2));
        int offset = (HEX_RADIUS - buttonHeight) / 2;
        for (int i = 0; i < numSides; i++) {
            int startX = xCoords[i];
            int startY = yCoords[i];
            int endX = xCoords[(i + 1) % 6];
            int endY = yCoords[(i + 1) % 6];
            int centerX = (startX + endX) / 2;
            int centerY = (startY + endY) / 2;
            int buttonX = centerX - buttonWidth / 2;
            int buttonY = centerY - buttonHeight / 2 + offset;

            JButton button = new JButton("") {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D)g;
                    AffineTransform originalTransform = g2.getTransform();
                    AffineTransform rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(45), getWidth()/2, getHeight()/2);
                    g2.setTransform(rotationTransform);
                    super.paintComponent(g);
                    g2.setTransform(originalTransform);
                }
            };

            button.setBackground(Color.WHITE);
            button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            add(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        int[] xCoords = new int[6];
        int[] yCoords = new int[6];
        for (int i = 0; i < 6; i++) {
            xCoords[i] = (int) (HEX_RADIUS * Math.cos(Math.PI / 3.0 * i + Math.PI / 6.0));
            yCoords[i] = (int) (HEX_RADIUS * Math.sin(Math.PI / 3.0 * i + Math.PI / 6.0));
        }
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.rotate(Math.PI / 2);
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 6; i++) {
            int startX = xCoords[i];
            int startY = yCoords[i];
            int endX = xCoords[(i + 1) % 6];
            int endY = yCoords[(i + 1) % 6];
            g2d.drawLine(startX, startY, endX, endY);
        }
        g2d.setTransform(old);
    }

}