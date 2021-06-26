package cavegame.cavegenerator;

import cavegame.game.Tile;
import cavegame.utils.ArrayList;

import static cavegame.utils.RandomNumberGenerator.*;

/**
 * BSP-puu tietorakenne, joka sisältää mm. algoritmin tason luomiselle.
 */
public class BSPTree {
    private final ArrayList<Leaf> leaves;
    private final int maxLeafSize;
    private final int mapWidth;
    private final int mapHeight;
    private final ArrayList<Room> rooms;
    private final Tile[][] level;
    int roomMaxSize;
    int roomMinSize;

    /**
     * BSP puun konstruktori, joka ottaa parametreina luotavan alueen koon, puun lehtien maksimikoon ja luotavien huoneiden
     * maksimi- ja minimikoon.
     *
     * @param mapWidth Luotavan alueen leveys
     * @param mapHeight Luotavan alueen korkeus
     * @param maxLeafSize Puun lehtisolmun maksimikoko
     * @param roomMaxSize Luotavien huoneiden maksimikoko
     * @param roomMinSize Luotavien huoneiden minimikoko
     */
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
                level[i][j] = Tile.BOUNDS;
            }
        }
    }

    /**
     * Jakaa BSP-puun lehtisolmut kahtia, jos solmu on liian suuri (tai 20% todennäköisyydellä muissa tapauksissa).
     */
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

    /**
     * Palauttaa luodun tason kaksiulotteisena taulukkona.
     * @return Luotu taso kaksiulotteisena ruudukkona.
     */
    public Tile[][] getLevel() {
        return level;
    }

    /**
     * Palauttaa algoritmin luomat huoneet.
     * @return Algoritmin luomat huoneet.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }
}

