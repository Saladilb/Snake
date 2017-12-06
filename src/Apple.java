
import java.awt.*;
import java.util.Random;

public class Apple implements Draw {
    private Point apple;
    private Random random = new Random();
    private boolean eaten = false;

    Apple () {
        addNewApple();
    }

    private void addNewApple() {
        apple = new Point();
        apple.x = random.nextInt(480);
        apple.y = random.nextInt(480);
        apple.x -= apple.x%25 ;
        apple.y -= apple.y%25 ;
    }

    public Point getApple() {return apple; }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        if (eaten) {
            addNewApple();
            eaten = false;
        }
        g.fillRect(apple.x,apple.y,25,25);
    }

    public void setEaten(Boolean eaten) {
        this.eaten = eaten;
    }
}
