package game;

import cavegenerator.BSPTree;

/**
 * Luo uuden pelimaailman annettujen parametrien ja BSP algoritmin generoimien ruutujen perusteella
 */
public class WorldBuilder {
    private final int width;
    private final int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public World build() {
        return new World(tiles);
    }

    // TODO: BSP-puulle annettavat arvot parametreina?
    public WorldBuilder makeCaves() {
        BSPTree bsp = new BSPTree(width, height, 24, 12, 8);
        tiles = bsp.generateLevel();
        return this;
    }
}
