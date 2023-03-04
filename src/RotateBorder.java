import javax.swing.border.Border;
import java.awt.*;

class RotatedBorder implements Border {
    private final Border border;
    private double radians;

    public RotatedBorder(Border border, double radians) {
        this.border = border;
        this.radians = radians;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(radians, x + width / 2.0, y + height / 2.0);
        border.paintBorder(c, g2d, x, y, width, height);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return border.getBorderInsets(c);
    }

    @Override
    public boolean isBorderOpaque() {
        return border.isBorderOpaque();
    }
}