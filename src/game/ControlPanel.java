package game;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
    public ControlPanel() {
        super();

        JButton button = new JButton();
        add(button);
    }
    public void paintComponent(Graphics g) { super.paintComponent(g); }
}