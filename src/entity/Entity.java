
package entity;

import java.awt.Rectangle;


public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public int direction;
    public static final int DOWN = 0, UP = 1, LEFT = 2, RIGHT = 3;
    
    public int spriteCount = 0;
    public int spriteNum = 0;
    public int spriteCountBot = 0;
    public  int spriteNumBot = 1;
    public Rectangle rect;
    public boolean collisionOn = false;
    
}
