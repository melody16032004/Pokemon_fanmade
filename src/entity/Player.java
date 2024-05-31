package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    private BufferedImage[][] anim;
    public final int screenX, screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        rect = new Rectangle();
        rect.x = 0;
        rect.y = 3;
        rect.width = 14;
        rect.height = 16;
        

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 16;
        worldY = gamePanel.tileSize * 16;
        speed = 4;
        direction = 0;
        //370 80
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
                        anim[i][j] = GetSpriteAtlas("player/movement/down/down_0" + j);
                    case 1 ->
                        anim[i][j] = GetSpriteAtlas("player/movement/up/up_0" + j);
                    case 2 ->
                        anim[i][j] = GetSpriteAtlas("player/movement/left/left_0" + j);
                    case 3 ->
                        anim[i][j] = GetSpriteAtlas("player/movement/right/right_0" + j);
                }
            }
        }
    }

    public void animationTick() {
        spriteCount++;
        if (spriteCount > 7) {
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

            } else if (keyHandler.down) {
                direction = DOWN;

            } else if (keyHandler.right) {
                direction = RIGHT;

            } else if (keyHandler.left) {
                direction = LEFT;

            }

            collisionOn = false;
//            gamePanel.collisionChecker.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case UP -> {
                        if (keyHandler.speedRun) {
                            worldY -= speed * 2f;
                        } else {
                            worldY -= 2;
                        }
                    }

                    case DOWN -> {
                        if (keyHandler.speedRun) {
                            worldY += speed * 2f;
                        } else {
                            worldY += 2;
                        }
                    }

                    case LEFT -> {
                        if (keyHandler.speedRun) {
                            worldX -= speed * 1f;
                        } else {
                            worldX -= speed / 2;
                        }
                    }

                    case RIGHT -> {
                        if (keyHandler.speedRun) {
                            worldX += speed * 1f;
                        } else {
                            worldX += speed / 2;
                        }
                    }

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
        g.drawImage(img, screenX, screenY, gamePanel.playerW * gamePanel.scale, gamePanel.playerH * gamePanel.scale, null);

    }

}
