package game;

import java.awt.Color;

import static utils.RandomNumberGenerator.getRandInt;

/**
 * Sisältää oleelliset tiedot pelissä käytettävästä tasosta, kuten tason korkeus ja leveys ruuduissa sekä kaikki tason ruudut.
 */
public class World {
    private final Tile[][] tiles;
    private final int width;
    private final int height;

    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
    }

    public Tile tile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.BOUNDS;
        } else {
            return tiles[x][y];
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getSymbol(int x, int y) {
        return tile(x, y).getSymbol();
    }

    public Color getColor(int x, int y) {
        return tile(x, y).getColor();
    }

    public void addAtEmptyLocation(Character character) {
        int x;
        int y;

        do {
            x = getRandInt(0, width);
            y = getRandInt(0, height);
        }
        while (!tile(x, y).isWalkable());

        character.setX(x);
        character.setY(y);
    }

}
