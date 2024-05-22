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
    String[] fileName = new String[]{
        "flower/flower_0",
        "grass/grass_0",
        "no_grass/nograss_0",
        "other/other_0",
        "path/path_0",
        "tree/tree_0",
        "dirt/dirt_0"
    };
    static int[][] lvlData;

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

                img = GetSpriteAtlas("tiles/" + fileName[i] + j);

                tile[i][j].img = img;
            }
            System.out.println(tile[i].length);

        }
    }

    public static int[][] ReadTextMap(String fileName) {
        lvlData = new int[18][24];
        int i = 0;

        File file = new File("D:/Information Technology/Java NetBeans/Pokemon/res/map/tilemap.txt");
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            
            String line = null;
            while(true){
                line = br.readLine();
                if(line == null){
                    break;
                }else{
                    String[] cutLine = line.split(" ");
                    for (int j = 0; j < 24; j++) {
                        lvlData[i][j] = Integer.parseInt(cutLine[j]);
                    }
                    i++;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lvlData;
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


    public void draw(Graphics2D g) {
//        BufferedImage imgMap = GetSpriteAtlas("/map/tilemap");
//        int[][] lvlData = GetLevelData(imgMap);
        lvlData = ReadTextMap("tilemap.txt");

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
