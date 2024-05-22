package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Flower extends Entity {

    private GamePanel gamePanel;
    private BufferedImage[] animFlower;

    public Flower(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        getFlowerImage();
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

    
    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();

                if (value >= 63) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }

        return lvlData;
    }
    
    
    
    public void update() {
        animationTick();
    }

    public void draw(Graphics2D g) {

        BufferedImage imgMap = GetSpriteAtlas("/map/tilemap");
        int[][] lvlData = GetLevelData(imgMap);

        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int value = lvlData[i][j];
                if (value == 0) {
                    g.drawImage(animFlower[spriteNum], gamePanel.tileSize * j, gamePanel.tileSize * i, gamePanel.tileSize, gamePanel.tileSize, null);
                } 
            }
        }
        
    }
}
