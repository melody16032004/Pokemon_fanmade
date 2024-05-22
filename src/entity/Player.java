package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    private BufferedImage[][] anim;
    private BufferedImage[] animFlower;
    private FileInputStream fi;
    private TileManager tileManager;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 370;
        y = 80;
        speed = 2f;
        direction = 0;
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
    
    

    public void getPlayerImage() {
        anim = new BufferedImage[4][4];

        for (int i = 0; i < anim.length; i++) {
            for (int j = 0; j < anim.length; j++) {
                switch (i) {
                    case 0 ->
                        anim[i][j] = GetSpriteAtlas("player/down/down_0" + j);
                    case 1 ->
                        anim[i][j] = GetSpriteAtlas("player/up/up_0" + j);
                    case 2 ->
                        anim[i][j] = GetSpriteAtlas("player/left/left_0" + j);
                    case 3 ->
                        anim[i][j] = GetSpriteAtlas("player/right/right_0" + j);
                }
            }
        }
    }
    
    public void animationTick(){
        spriteCount++;
            if (spriteCount > 10) {
                switch (spriteNum) {
                    case 0 ->
                        spriteNum = 1;
                    case 1 ->
                        spriteNum = 2;
                    case 2 ->
                        spriteNum = 3;
                    case 3 ->
                        spriteNum = 0;
                    default -> {
                    }
                }
                spriteCount = 0;
            }
    }

    public void update() {
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {

            if (keyHandler.up) {
                direction = UP;
                if (keyHandler.speedRun) {
                    y -= speed * 2f;
                } else {
                    y -= speed;
                }
            } else if (keyHandler.down) {
                direction = DOWN;
                if (keyHandler.speedRun) {
                    y += speed * 2f;
                } else {
                    y += speed;
                }
            } else if (keyHandler.right) {
                direction = RIGHT;
                if (keyHandler.speedRun) {
                    x += speed * 2f;
                } else {
                    x += speed;
                }
            } else if (keyHandler.left) {
                direction = LEFT;
                if (keyHandler.speedRun) {
                    x -= speed * 2f;
                } else {
                    x -= speed;
                }
            }

            animationTick();
        }
    }

    public void draw(Graphics2D g) {

        BufferedImage img = null;

        switch (direction) {
            case DOWN ->
                img = anim[DOWN][spriteNum];
            case UP ->
                img = anim[UP][spriteNum];
            case LEFT ->
                img = anim[LEFT][spriteNum];
            case RIGHT ->
                img = anim[RIGHT][spriteNum];
        }
        g.drawImage(img, x, y, gamePanel.playerW * gamePanel.scale, gamePanel.playerH * gamePanel.scale, null);
        
        
        
    }

}
