package main;

import entity.Entity;
import static entity.Entity.*;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.worldX + entity.rect.x);
        System.out.println(entityLeftWorldX);
        int entityRightWorldX = (int) (entity.worldX + entity.rect.x + entity.rect.width);
//        System.out.println(entityRightWorldX);
        int entityTopWorldY = (int) (entity.worldY + entity.rect.y);
//        System.out.println(entityTopWorldY);
        int entityBottomWorldY = (int) (entity.worldY + entity.rect.y + entity.rect.height);

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.lvlData[entityTopRow][entityRightCol];
                System.out.println("UP");
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityTopRow[2] : " + entityTopRow);
                System.out.println("tileNum1 : " + tileNum1);
                System.out.println("tileNum2 : " + tileNum2);
                System.out.println("");
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        || gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.lvlData[entityBottomRow][entityRightCol];
                System.out.println("DOWN");
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityBottomRow[2] : " + entityBottomRow);
                System.out.println("tileNum1 : " + tileNum1);
                System.out.println("tileNum2 : " + tileNum2);
                System.out.println("");
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        || gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.lvlData[entityBottomRow][entityLeftCol];
                System.out.println("LEFT:");
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityTopRow[2] : " + entityTopRow);
                System.out.println("tileNum1 : " + tileNum1);
                System.out.println("tileNum2 : " + tileNum2);
                System.out.println("");
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        || gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.lvlData[entityBottomRow][entityRightCol];
                System.out.println("RIGHT:");
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityTopRow[2] : " + entityTopRow);
                System.out.println("tileNum1 : " + tileNum1);
                System.out.println("tileNum2 : " + tileNum2);
                System.out.println("");
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        || gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
