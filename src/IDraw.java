import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IDraw implements Draw {
    private boolean over = false;
    private Snake snake;
    private CanvasSnake canvasSnake;
    private Apple apple;
    private ArrayList <Draw> objectsForDraw = new ArrayList<Draw>();
    public void run() {
        canvasSnake =  new CanvasSnake();
        snake = new Snake();
        canvasSnake.setResizable(false);
        canvasSnake.setLocation(500,150);
        canvasSnake.addKeyListener(snake);
        apple = new Apple();
        objectsForDraw.add(apple);
        objectsForDraw.add(snake);
        objectsForDraw.add(canvasSnake);

        while (!snake.getOver()) {
            draw(canvasSnake.getCp().getGraphics());
        }
        over = true;
        showMassegeNewGame();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(12,12,12));
        for (int i = 0; i < 550; i += 25) {
            g.drawLine(0,i,550,i);
        }
        for (int i = 0; i < 550; i += 25) {
            g.drawLine(i,0,i,550);
        }
        for (Draw anObjectsForDraw : objectsForDraw) {
            anObjectsForDraw.draw(g);
        }
        snake.setPointApple(apple.getApple());
        if (snake.getAppleEaten() == true) {
            apple.setEaten(snake.getAppleEaten());
            snake.setAppleEaten(false);
        }
    }

    private void showMassegeNewGame() {

        canvasSnake.setLayout(new FlowLayout());
        JButton confirmAndReturnToGame = new JButton("Вы набрали " + snake.getScore() + " очков ");
        canvasSnake.add(confirmAndReturnToGame);
        confirmAndReturnToGame.setSize(400,200);
        confirmAndReturnToGame.setLocation(0,50);
        while (over == true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
