import javax.swing.*;
import java.awt.*;

public class CanvasSnake extends JFrame implements Draw{
    private JPanel cp;
    private Color colorBackground = (Color.getHSBColor(52,4,222));
    CanvasSnake() {
        cp = new JPanel();
        setSize(515, 535);
        setVisible(true);
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(cp);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.setColor(colorBackground);
        g.fillRect(0,0,500,500);
    }

    public JPanel getCp() {
        return cp;
    }
}
