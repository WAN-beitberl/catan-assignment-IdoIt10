import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BuildSettlement extends JFrame implements ActionListener {
    private JButton[] buttons;
    private int selectedIndex;

    public BuildSettlement() {
        super("Build Settlement");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(300, 300));
        buttons = new JButton[25];

        // Create buttons for each intersection on the game board
        for (int i = 0; i < 25; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(5, 5));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel);
        setVisible(true);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Set the selected index to the button that was clicked
        for (int i = 0; i < 25; i++) {
            if (e.getSource() == buttons[i]) {
                selectedIndex = i;
                break;
            }
        }
        dispose();
    }
}