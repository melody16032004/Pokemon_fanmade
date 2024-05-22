package utils;

public class Constants {

    public static class TileConstants {

        public static final int FLOWER = 0;
        public static final int GRASS = 1;
        public static final int NO_GRASS = 2;
        public static final int OTHER = 3;
        public static final int PATH = 4;
        public static final int TREE = 5;
        public static final int DIRT = 6;

        public static int GetTilesAmount(int tiles) {
            switch (tiles) {
                case FLOWER -> {return 5;}
                case GRASS -> {return 1;}
                case NO_GRASS -> {return 4;}
                case OTHER -> {return 5;}
                case PATH -> {return 9;}
                case TREE -> {return 1;}
                case DIRT -> {return 4;}
                default -> {return 0;}
            }
        }

    }
}
