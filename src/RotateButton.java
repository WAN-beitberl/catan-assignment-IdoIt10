import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

class RotateButton extends JButton {
    private double radians;

    public RotateButton(String text, double radians) {
        super(text);
        this.radians = radians;
    }

    public void UpdateRotateButton(double radians) {
        this.radians = radians;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(radians, getWidth() / 2.0, getHeight() / 2.0);
        super.paintComponent(g2d);
        g2d.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(-radians, getWidth() / 2.0, getHeight() / 2.0);
        Shape transformedShape = transform.createTransformedShape(new Rectangle(0, 0, getWidth(), getHeight()));
        return transformedShape.contains(x, y);
    }

    @Override
    public Border getBorder() {
        Border originalBorder = super.getBorder();
        return new RotatedBorder(originalBorder, radians);
    }
}