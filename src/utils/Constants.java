package utils;

public class Constants {

    public static class TileConstants {

        public static final int NONE = 0;
        public static final int NO_GRASS = 1;
        public static final int GRASS = 2;
        public static final int OBSTRUCTIONS = 3;
        public static final int SAND = 4;
        public static final int FLOWER = 5;
        public static final int GATE = 6;
        public static final int TREE = 7;
        public static final int SEA = 8;
        public static final int STILL_WATER = 9;
        public static final int SIGN = 10;
        public static final int GREEN = 11;
        public static final int BUILDING = 12;
        public static final int WHITE = 13;

        public static int GetTilesAmount(int tiles) {
            switch (tiles) {
                case NONE -> {
                    return 0;
                }
                case NO_GRASS -> {
                    return 4;
                }
                case GRASS -> {
                    return 1;
                }
                case OBSTRUCTIONS -> {
                    return 9;
                }
                case SAND -> {
                    return 13;
                }
                case FLOWER -> {
                    return 5;
                }
                case GATE -> {
                    return 5;
                }
                case TREE -> {
                    return 3;
                }
                case SEA -> {
                    return 16;
                }
                case STILL_WATER -> {
                    return 9;
                }
                case SIGN -> {
                    return 3;
                }
                case GREEN -> {
                    return 14;
                }
                case BUILDING -> {
                    return 2;
                }
                case WHITE -> {
                    return 9;
                }
                default -> {
                    return 0;
                }
            }
        }

    }
}
