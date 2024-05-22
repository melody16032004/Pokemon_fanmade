package tile;

import entity.Flower;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import static utils.Constants.TileConstants.*;

public class TileManager {

    GamePanel gamePanel;
    Flower flower;
    public Tile[][] tile;
    Random rand = new Random();
    String[] fileName = new String[]{
        "flower/flower_0",
        "grass/grass_0",
        "no_grass/nograss_0",
        "other/other_0",
        "path/path_0",
        "tree/tree_0",
        "dirt/dirt_0"
    };
    int[][] tmp = new int[fileName.length][9];

    public BufferedImage getTile(int x, int y) {
        return tile[x][y].img;
    }

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[fileName.length][9];

        geteTileImage();
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

    public void geteTileImage() {
        BufferedImage img = null;

        for (int i = 0; i < fileName.length; i++) {
            for (int j = 0; j < GetTilesAmount(i); j++) {
                tile[i][j] = new Tile();

//                if (i == 4 && j < 10) {
//                    img = GetSpriteAtlas("tiles/" + fileName[i] + "0" + j);
//                } else if (i == 4 && j >= 10) {
//                    img = GetSpriteAtlas("tiles/" + fileName[i] + j);
//                } else {
//                    img = GetSpriteAtlas("tiles/" + fileName[i] + j);
//                }
                img = GetSpriteAtlas("tiles/" + fileName[i] + j);

                tile[i][j].img = img;
            }
            System.out.println(tile[i].length);

        }
    }

//    public static BufferedImage GetUnitTileMap(int x, int y) {
//        BufferedImage imgUnit = tile[x][y].img;
//        return imgUnit;
//    }

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
    
    public static void loadMapData(){
        InputStream fi;
        try{
            
//            fi = getClass().getResourceAsStream("/map/tilemap.txt");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage imgMap = GetSpriteAtlas("/map/tilemap");
        int[][] lvlData = GetLevelData(imgMap);

        for (int i = 0; i < lvlData.length; i++) {
            for (int j = 0; j < lvlData[i].length; j++) {
                int value = lvlData[i][j];
                if (value == 45) {
                    g.drawImage(tile[value / 9][value % 9].img, gamePanel.tileSize * j, gamePanel.tileSize * i - 28 * 2, 64, 90, null);
                } else if (value != 5) {
                    g.drawImage(tile[value / 9][value % 9].img, gamePanel.tileSize * j, gamePanel.tileSize * i, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }

//        g.drawImage(tile[6][0].img, gamePanel.tileSize * 10, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[18 / 9][18 % 9].img, gamePanel.tileSize * 10, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[0 / 9][0 % 9].img, gamePanel.tileSize * 10 + 40, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[1 / 9][0 % 9].img, gamePanel.tileSize * 10 + 80, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[2 / 9][0 % 9].img, gamePanel.tileSize * 10 + 120, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[3 / 9][0 % 9].img, gamePanel.tileSize * 10 + 160, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);
//        g.drawImage(tile[4 / 9][0 % 9].img, gamePanel.tileSize * 10+ 200, gamePanel.tileSize * 10, gamePanel.tileSize, gamePanel.tileSize, null);

    }

}
