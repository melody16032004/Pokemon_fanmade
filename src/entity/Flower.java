package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import tile.TileManager;

public class Flower extends Entity {

    private GamePanel gamePanel;
    private BufferedImage[] animFlower;
    private TileManager tileManager;
    private int[][] lvlData;

    public Flower(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        getFlowerImage();
        lvlData = tileManager.ReadTextMap("tilemap.txt");
        tileManager = new TileManager(gamePanel);
    }

    public void getFlowerImage() {
        animFlower = new BufferedImage[5];

        for (int i = 0; i < animFlower.length; i++) {
            animFlower[i] = GetSpriteAtlas("/tiles/flower/flower_0" + i);
        }
    }

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        FileInputStream fi;

        try {
            fi = new FileInputStream("res/" + fileName + ".png");

            try {
                img = ImageIO.read(fi);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return img;
    }

    public void animationTick() {
        spriteCount++;
        if (spriteCount > 13) {
            switch (spriteNum) {
                case 0 ->
                    spriteNum = 1;
                case 1 ->
                    spriteNum = 2;
                case 2 ->
                    spriteNum = 3;
                case 3 ->
                    spriteNum = 4;
                case 4 ->
                    spriteNum = 0;
                default -> {
                }
            }
            spriteCount = 0;
        }
    }
    public void update() {
        animationTick();
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                int value = lvlData[i][j];

                if (tileManager.check(worldX, worldY));
                {
                    if (value == 150) {
                        g.drawImage(animFlower[spriteNum], screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    }
                }
            }
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                int value = lvlData[i][j];

                if (tileManager.check(worldX, worldY)) {
                    if (value == 360) {                                                                      //256 144
                        g.drawImage(tileManager.tile[value / 30][value % 30].img, screenX - 24, screenY, 112 + 64, 72 + 57, null);
                    } else if (value == 361) {
                        g.drawImage(tileManager.tile[value / 30][value % 30].img, screenX - 14, screenY, 80 + 64, 72 + 57, null);
                    }
                }
            }
        }
    }
}
