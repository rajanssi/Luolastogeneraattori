package cavegenerator;

import game.Tile;

import java.util.ArrayList;

import static utils.RandomNumberGenerator.*;

/**
 * BSP-puu tietorakenne, joka sisältää mm. algoritmin tason luomiselle
 */
// TODO: Tunneloinnit eri luokkaan/pakettiin, koska ne eivät varsinaisesti ole osa algoritmia?
public class BSPTree {
    private int maxLeafSize;
    private int mapWidth;
    private int mapHeight;
    private Tile[][] level;
    int roomMaxSize;
    int roomMinSize;

    public BSPTree(int mapWidth, int mapHeight, int maxLeafSize, int roomMaxSize, int roomMinSize) {
        this.maxLeafSize = maxLeafSize;
        this.roomMaxSize = roomMaxSize;
        this.roomMinSize = roomMinSize;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        level = new Tile[mapWidth][mapHeight];

        // TODO: Tason alustus alustavasti näin (voi olla, että tämä on ihan riittävän hyvä tapa)
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                level[i][j] = Tile.BOUNDS;
            }
        }
    }

    /**
     * Rakentaa binary space partitioning algoritmilla pelin luolastotason.
     *
     * @return Palauttaa kaksiuloitteisen taulukon joka sisältää informaation pelin tason ruuduista
     */
    public Tile[][] generateLevel() {
        // TODO: Oma ArrayList toteutus tähän
        ArrayList<Leaf> leaves = new ArrayList<>();
        Leaf rootLeaf = new Leaf(0, 0, mapWidth, mapHeight);
        leaves.add(rootLeaf);
        boolean splitSuccessfully = true;
        while (splitSuccessfully) {
            splitSuccessfully = false;

            int j = leaves.size();
            for (int i = 0; i < j; i++) {
                Leaf l = leaves.get(i);
                if (l.child1 == null && l.child2 == null) {
                    if (l.width > maxLeafSize || l.height > maxLeafSize || getRandInt(0, 5) > 4) {
                        if (l.splitLeaf()) {
                            leaves.add(l.child1);
                            leaves.add(l.child2);
                            splitSuccessfully = true;
                            j = leaves.size();
                        }
                    }
                }
            }
        }

        rootLeaf.createRooms(this);
        return level;
    }

    public void createRoom(Rect room) {
        for (int x = room.x1 + 1; x < room.x2; x++) {
            for (int y = room.y1 + 1; y < room.y2; y++) {
                level[x][y] = Tile.FLOOR;
                if (x == room.x1 + 1 || x == room.x2 - 1) {
                    level[x][y] = Tile.WALL;
                }
                if (y == room.y1 + 1 || y == room.y2 - 1) {
                    level[x][y] = Tile.WALL;
                }
            }
        }
    }

    public void createHall(Rect room1, Rect room2) {
        int x1 = room1.centerX();
        int y1 = room1.centerY();
        int x2 = room2.centerX();
        int y2 = room2.centerY();

        if (getRandInt(0, 1) == 1) {
            createVerTunnel(y1, y2, x2);
            createHorTunnel(x1, x2, y1);
        } else {
            createHorTunnel(x1, x2, y2);
            createVerTunnel(y1, y2, x1);
        }
    }

    private void createHorTunnel(int x1, int x2, int y) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            if (level[x][y - 1] != Tile.FLOOR) {
                level[x][y - 1] = Tile.WALL;
            }
            if (level[x][y + 1] != Tile.FLOOR) {
                level[x][y + 1] = Tile.WALL;
            }
            level[x][y] = Tile.FLOOR;
        }
    }

    private void createVerTunnel(int y1, int y2, int x) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            if (level[x - 1][y] != Tile.FLOOR) {
                level[x - 1][y] = Tile.WALL;
            }
            if (level[x + 1][y] != Tile.FLOOR) {
                level[x + 1][y] = Tile.WALL;
            }
            level[x][y] = Tile.FLOOR;
        }
    }
}

