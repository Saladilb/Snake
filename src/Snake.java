import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements Draw, KeyListener {
    private int timeOutOfRepaint = 500;
    private Point endOfTail = new Point();
    private boolean over = false;
    private boolean pressed = false;
    private Point pointApple = new Point();
    private ArrayList <Point> partsOfSnake = new ArrayList<Point>();
    private int dimention = 1;
    private boolean appleEaten = false;
    private int score = 0;

    Snake() {
        setBasePoints();
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        setNavigation(g);
        for (Point item: partsOfSnake) {
            g.fillRect(item.x, item.y, 25, 25);
        }
        pressed = false;
        try {
            Thread.sleep(timeOutOfRepaint);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void isOver(Graphics g) {
        if (partsOfSnake.get(0).x >480 || partsOfSnake.get(0).y > 480 || partsOfSnake.get(0).x < 0 || partsOfSnake.get(0).y < 0  ) {
            over = true;
        }
        for (int i = 1; i < partsOfSnake.size(); i ++ ) {
            Point headSnake = partsOfSnake.get(0);
            if ((headSnake.x == partsOfSnake.get(i).x && headSnake.y == partsOfSnake.get(i).y) || (headSnake.x == endOfTail.x && headSnake.y == endOfTail.y)) {
                over = true;
            }
        }
    }

    private void setBasePoints() {
        partsOfSnake.add( new Point(250,250));
        partsOfSnake.add( new Point(250,275));
    }

    private void setNavigation(Graphics g) {
        endOfTail.x = partsOfSnake.get(partsOfSnake.size() - 1).x;
        endOfTail.y = partsOfSnake.get(partsOfSnake.size() - 1).y;
        for (int i = partsOfSnake.size() - 1; i > 0; i--) {
            partsOfSnake.get(i).x = partsOfSnake.get(i - 1).x;
            partsOfSnake.get(i).y = partsOfSnake.get(i - 1).y;
        }
        if (dimention == 0) {
            dimention = 0;
            partsOfSnake.get(0).x = partsOfSnake.get(0).x - 25;
        }
        if (dimention == 1) {
            dimention = 1;
            partsOfSnake.get(0).y = partsOfSnake.get(0).y - 25;
        }
        if (dimention == 2) {
            dimention = 2;
            partsOfSnake.get(0).x = partsOfSnake.get(0).x + 25;
        }
        if (dimention == 3) {
            dimention = 3;
            partsOfSnake.get(0).y = partsOfSnake.get(0).y + 25;
        }
        isOver(g);
        isMoreSnake();
    }

    private void isMoreSnake() {
        if (partsOfSnake.get(0).x == pointApple.x && partsOfSnake.get(0).y == pointApple.y) {
            Point moreSnake = new Point(endOfTail.x, endOfTail.y);
            partsOfSnake.add(moreSnake);
            appleEaten = true;
            timeOutOfRepaint -= 15;
            score += 1000 / timeOutOfRepaint;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressed) {
            int keyCode = e.getKeyCode();
            if (keyCode == 37 && (dimention == 1 || dimention == 3)) {
                dimention = 0;
            }
            if (keyCode == 38 && (dimention == 0 || dimention == 2)) {
                dimention = 1;
            }
            if (keyCode == 39 && (dimention == 1 || dimention == 3)) {
                dimention = 2;
            }
            if (keyCode == 40 && (dimention == 0 || dimention == 2)) {
                dimention = 3;
            }
            pressed = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void setPointApple(Point pointApple) {
        this.pointApple = pointApple;
    }

    public boolean getOver() {
        return over;
    }

    public void setAppleEaten(Boolean eaten) {
        this.appleEaten = eaten;
    }

    public boolean getAppleEaten() {
        return appleEaten;
    }

    public int getScore() {
        return score;
    }
}
