package game;

import cavegenerator.BSPTree;

public class WorldBuilder {
    private int width;
    private int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public World build() {
        return new World(tiles);
    }

    public WorldBuilder makeCaves() {
        BSPTree bsp = new BSPTree(width, height, 24, 15, 6);
        tiles = bsp.generateLevel();
        return this;
    }
}
