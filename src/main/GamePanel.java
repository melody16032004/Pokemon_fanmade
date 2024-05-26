package main;

import entity.Flower;
import entity.Player;
import entity.Sea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTING
    final int originalTileSize = 16;
    public final int scale = 2;
    public final int playerW = 14, playerH = 19;

    public final int tileSize = originalTileSize * (scale);
    public final int maxScreenCol = 18;//24
    public final int maxScreenRow = 14;//18
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTING
    public final int maxWorldCol = 28;
    public final int maxWorldRow = 36;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    Flower flower = new Flower(this);
    Sea sea = new Sea(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();

                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        sea.update();
        flower.update();
        player.update();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        sea.draw(g2);
        flower.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

}
