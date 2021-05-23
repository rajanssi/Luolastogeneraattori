package cavegenerator;

public class Main {

    public static void main(String[] args) {
        final int MAP_WIDTH = 100;
        final int MAP_HEIGHT = 50;

        BSPTree t = new BSPTree(MAP_WIDTH, MAP_HEIGHT, 24, 15, 6);
        int[][] level = t.generateLevel();

        UserInterface ui = new UserInterface(MAP_WIDTH, MAP_HEIGHT);
        for (int x = 0; x < MAP_WIDTH - 1; x++) {
            for (int y = 0; y < MAP_HEIGHT - 1; y++) {
                if (level[x][y] == 1) ui.addRoom(1, 1, x, y);
            }
        }
    }
}


