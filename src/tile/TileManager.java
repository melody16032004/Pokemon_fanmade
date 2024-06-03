package tile;

import entity.Flower;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;
import static utils.Constants.TileConstants.*;

public class TileManager {

    GamePanel gamePanel;
    public Tile[][] tile;
    public int[] tileCanMove;
    public boolean[] canMove;
    String[] fileName = new String[]{
        "none",
        "tiles/no_grass/nograss_",
        "tiles/grass/grass_",
        "tiles/obstructions/obstructions_",
        "tiles/path/sand/sand_",
        "tiles/flower/flower_",
        "tiles/gate/gate_",
        "tiles/tree/tree_",
        "tiles/sea/sea_",
        "tiles/still water/still water_",
        "tiles/sign/sign_",
        "tiles/path/green/pathgreen_",
        "building/building_",
        "tiles/path/white/white_"
    };
    public int[][] lvlData;

    public BufferedImage getTile(int x, int y) {
        return tile[x][y].img;
    }

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[fileName.length][30];
//        lvlData = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        getTileCanMove();
//        int count = 0;
//        for (int i = 0; i < tileCanMove.length; i++) {
//            if(tileCanMove[i] != 0)
//                count++;
//            else
//                break;
//        }
//        System.out.println("length: " + count);
        CanMoveHere();
    }
    public void checkMove(){
        for (int i = 0; i < lvlData.length; i++) {
//            for
        }
    }
    public void CanMoveHere() {
        canMove = new boolean[tileCanMove[tileCanMove.length - 1] + 1];
        for (int i = 0; i < canMove.length; i++) {
            canMove[i] = false;
        }
        for (int i = 0; i < tileCanMove.length; i++) {
            canMove[tileCanMove[i]] = true;
        }
    }

    public void getTileCanMove() {
        tileCanMove = new int[43];
        int i = -1;

        File file = new File("D:/Information Technology/Java NetBeans/Pokemon/res/map/tileCanMove.txt");
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);

            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] cutLine = line.split("-");
//                    i++;
                    if (i == tileCanMove.length) {
                        break;
                    }


                    int index1 = Integer.parseInt(cutLine[0]);
                    int index2 = Integer.parseInt(cutLine[1]);
                    for (int j = index1; j <= index2; j++) {
                        i++;
                        tileCanMove[i] = j;
                        
                    }
//                    for (int j = 0; j < tileCanMove.length; j++) {
//                        if(tileCanMove[j] != 0)
//                            System.out.println(tileCanMove[j]);
//                        else
//                            break;
//                    }
                    
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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

    public void getTileImage() {
        BufferedImage img = null;

        for (int i = 0; i < fileName.length; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < GetTilesAmount(i); j++) {
                tile[i][j] = new Tile();

                if (j < 10) {
                    img = GetSpriteAtlas(fileName[i] + "0" + j);
                } else {
                    img = GetSpriteAtlas(fileName[i] + j);
                }

                tile[i][j].img = img;
                switch (i){
                    case 0:
                    case 3:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                        tile[i][j].collision = true;
                }
//                if (i == 6) {
//                    tile[i][j].collision = true;
//                }
                switch(i){
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                    case 11:
                    case 13:
                        tile[i][j].canMoveHere = true;
                }
            }
//            System.out.println(tile[i].length);
        }
    }

    public static int[][] ReadTextMap(String fileName) {
        int[][] lvlData = new int[141][42];
//        lvlData = new int[50][50];
        int i = -1;

        File file = new File("D:/Information Technology/Java NetBeans/Pokemon/res/map/" + fileName);
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);

            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] cutLine = line.split("\t");
                    i++;
                    if (i == lvlData.length) {
                        break;
                    }
                    for (int j = 0; j < cutLine.length; j++) {
//                        System.out.println(j);
                        lvlData[i][j] = Integer.parseInt(cutLine[j]);
                    }

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lvlData;
    }

//    public static int[][] GetLevelData(BufferedImage img) {
//        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
//
//        for (int j = 0; j < img.getHeight(); j++) {
//            for (int i = 0; i < img.getWidth(); i++) {
//                Color color = new Color(img.getRGB(i, j));
//                int value = color.getRed();
//
//                if (value >= 63) {
//                    value = 0;
//                }
//                lvlData[j][i] = value;
//            }
//        }
//
//        return lvlData;
//    }
    public void draw(Graphics2D g) {
        lvlData = ReadTextMap("tilemap.txt");

        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int worldX = j * gamePanel.tileSize;
                int worldY = i * gamePanel.tileSize;
                int screenX = (int) (worldX - gamePanel.player.worldX + gamePanel.player.screenX);
                int screenY = (int) (worldY - gamePanel.player.worldY + gamePanel.player.screenY);
                int value = lvlData[i][j];

//                System.out.println(check(worldX, worldY));
                if (check(worldX, worldY)) {
                    if (value == 0 || value == 210) {
                        continue;
                    }
                    else if (value != 0 && value != 210 && value != 242) {
                        //draw anything left
                        g.drawImage(tile[value / 30][value % 30].img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    }
                }

//                g.drawImage(tile[value / 30][value % 30].img, gamePanel.tileSize * j, gamePanel.tileSize * i, gamePanel.tileSize, gamePanel.tileSize, null);
            }
//            break;
        }
//        g.drawImage(tile[6][0].img, gamePanel.tileSize * 10, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[18 / 9][18 % 9].img, gamePanel.tileSize * 10, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[0 / 9][0 % 9].img, gamePanel.tileSize * 10 + 40, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[1 / 9][0 % 9].img, gamePanel.tileSize * 10 + 80, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[2 / 9][0 % 9].img, gamePanel.tileSize * 10 + 120, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[3 / 9][0 % 9].img, gamePanel.tileSize * 10 + 160, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[4 / 9][0 % 9].img, gamePanel.tileSize * 10+ 200, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public boolean check(int worldX, int worldY) {
        return worldX + (gamePanel.tileSize * 4) > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - (gamePanel.tileSize * 4) < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + (gamePanel.tileSize * 4) > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - (gamePanel.tileSize * 4) < gamePanel.player.worldY + gamePanel.player.screenY;
    }
}
