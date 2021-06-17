package cavegenerator;

import game.Tile;
import java.util.ArrayList;

import static utils.RandomNumberGenerator.*;

/**
 * BSP-puu tietorakenne, joka sisältää mm. algoritmin tason luomiselle
 */
public class BSPTree {
    private final int maxLeafSize;
    private final int mapWidth;
    private final int mapHeight;
    private final ArrayList<Leaf> leaves;
    private final Tile[][] level;
    private final ArrayList<Room> rooms;
    int roomMaxSize;
    int roomMinSize;

    public BSPTree(int mapWidth, int mapHeight, int maxLeafSize, int roomMaxSize, int roomMinSize) {
        this.maxLeafSize = maxLeafSize;
        this.roomMaxSize = roomMaxSize;
        this.roomMinSize = roomMinSize;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.leaves = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.level = new Tile[mapWidth][mapHeight];

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                getLevel()[i][j] = Tile.BOUNDS;
            }
        }
    }

    private void splitLeaves() {
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
    }

    /**
     * Rakentaa binary space partitioning algoritmilla pelin luolastotason.
     *
     * @return Palauttaa kaksiuloitteisen taulukon joka sisältää informaation pelin tason ruuduista
     */
    public Tile[][] generateLevel() {
        Leaf rootLeaf = new Leaf(0, 0, mapWidth, mapHeight);
        leaves.add(rootLeaf);

        splitLeaves();

        rootLeaf.createRooms(this);
        return getLevel();
    }

    public Tile[][] getLevel() {
        return level;
    }

    // TODO: Oma ArrayList toteutus tähän - muokkaa World luokan for-silmukoita
    public ArrayList<Room> getRooms() {
        return rooms;
    }

}

