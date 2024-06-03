package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import tile.TileManager;

public class Tree {

    private GamePanel gamePanel;
    private BufferedImage[] animFlower;
    private TileManager tileManager;
    private int[][] lvlData;

    public Tree(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

//        getFlowerImage();
        lvlData = tileManager.ReadTextMap("tilemap.txt");
        tileManager = new TileManager(gamePanel);
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
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = (int) (worldX - gamePanel.player.worldX + gamePanel.player.screenX);
                int screenY = (int) (worldY - gamePanel.player.worldY + gamePanel.player.screenY);
                int value = lvlData[i][j];

                if (tileManager.check(worldX, worldY)){
                    if (value == 210) {
                        //draw tree
                        g.drawImage(tileManager.tile[value / 30][value % 30].img, screenX, screenY - 2*16 - 26, 60, 90, null);
                        //continue;
                    }
                }
            }
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = (int) (worldX - gamePanel.player.worldX + gamePanel.player.screenX);
                int screenY = (int) (worldY - gamePanel.player.worldY + gamePanel.player.screenY);
                int value = lvlData[i][j];

                if (tileManager.check(worldX, worldY)) {
                    //draw building
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
