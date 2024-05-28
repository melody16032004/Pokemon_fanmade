package main;

import entity.Entity;
import static entity.Entity.*;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.rect.x;
        System.out.println(entityLeftWorldX);
        int entityRightWorldX = entity.worldX + entity.rect.x + entity.rect.width;
//        System.out.println(entityRightWorldX);
        int entityTopWorldY = entity.worldY + entity.rect.y;
//        System.out.println(entityTopWorldY);
        int entityBottomWorldY = entity.worldY + entity.rect.y + entity.rect.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        System.out.println("entityLeftCol : " + entityLeftCol);
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityLeftCol][entityTopRow];
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityTopRow[2] : " + entityTopRow);
                System.out.println("tileNum1 : " + tileNum1);
                tileNum2 = gamePanel.tileManager.lvlData[entityRightCol][entityTopRow];
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        && gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityLeftCol][entityBottomRow];
                System.out.println("entityLeftCol[1] : " + entityLeftCol);
                System.out.println("entityBottomRow[2] : " + entityBottomRow);
                System.out.println("tileNum1 : " + tileNum1);
                tileNum2 = gamePanel.tileManager.lvlData[entityRightCol][entityBottomRow];
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        && gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityLeftCol][entityTopRow];
//                System.out.println("entityLeftCol[1] : " + entityLeftCol);
//                System.out.println("entityTopRow[2] : " + entityTopRow);
//                System.out.println("tileNum1 : " + tileNum1);
                tileNum2 = gamePanel.tileManager.lvlData[entityLeftCol][entityBottomRow];
                
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        && gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.lvlData[entityRightCol][entityRightCol];
                tileNum2 = gamePanel.tileManager.lvlData[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1 / 30][tileNum1 % 30].collision
                        || gamePanel.tileManager.tile[tileNum2 / 30][tileNum2 % 30].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
