import javax.swing.*;
import java.awt.*;

public class BackImg {

    public JFrame frame;
    JButton button;
    public BackImg() {
        frame = new JFrame();
        button = new JButton("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("Catan.png");
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // create a JPanel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("Catan.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
        };

        // set the layout of the panel to null to allow absolute positioning of the button
        panel.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();

        // set the button's size to 200 x 50 pixels
        Dimension buttonSize = new Dimension(80, 30);
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);

        // add the button to the panel using the GridBagConstraints
        // add the button to the panel using the GridBagConstraints
        panel.add(button, gbc);
        // add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);

        if (button.isSelected()){

        }
        frame.removeAll();
        frame.add(new Board());
        frame.pack();
        frame.setVisible(false);

        frame.setVisible(true);
    }
}