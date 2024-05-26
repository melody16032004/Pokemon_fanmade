package entity;

import static entity.Flower.GetSpriteAtlas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import tile.TileManager;

public class Sea extends Entity {

    private GamePanel gamePanel;
    private BufferedImage[] animSea;
    private TileManager tileManager;
    private int[][] lvlData;

    public Sea(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        getSeaImage();
        lvlData = tileManager.ReadTextMap("tilemap.txt");
        tileManager = new TileManager(gamePanel);
    }

    public void getSeaImage() {
        animSea = new BufferedImage[16];

        for (int i = 0; i < animSea.length; i++) {
            if (i < 10) {
                animSea[i] = GetSpriteAtlas("/tiles/sea/sea_0" + i);
            } else {
                animSea[i] = GetSpriteAtlas("/tiles/sea/sea_" + i);
            }
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

    public void animationTickTop() {
        spriteCount++;
        if (spriteCount > 13) {
            switch (spriteNum) {
                case 0 ->
                    spriteNum = 2;
                case 2 ->
                    spriteNum = 4;
                case 4 ->
                    spriteNum = 6;
                case 6 ->
                    spriteNum = 8;
                case 8 ->
                    spriteNum = 10;
                case 10 ->
                    spriteNum = 12;
                case 12 ->
                    spriteNum = 14;
                case 14 ->
                    spriteNum = 0;
                default -> {
                }
            }
            spriteCount = 0;
        }
    }

    public void animationTickBot() {
        spriteCountBot++;
        if (spriteCountBot > 13) {
            switch (spriteNumBot) {
                case 1 ->
                    spriteNumBot = 3;
                case 3 ->
                    spriteNumBot = 5;
                case 5 ->
                    spriteNumBot = 7;
                case 7 ->
                    spriteNumBot = 9;
                case 9 ->
                    spriteNumBot = 11;
                case 11 ->
                    spriteNumBot = 13;
                case 13 ->
                    spriteNumBot = 15;
                case 15 ->
                    spriteNumBot = 1;
                default -> {
                }
            }
            spriteCountBot = 0;
        }
    }

    public void update() {
        animationTickTop();
        animationTickBot();
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                int value = lvlData[i][j];

                if (tileManager.check(worldX, worldY)) {

                    if (value == 240) {
                        g.drawImage(animSea[spriteNum], screenX, screenY, 96, 48, null);
                    } else if (value == 241) {
                        g.drawImage(animSea[spriteNumBot], screenX, screenY, 96, 48, null);
                    }
                }
            }

        }

    }
}
