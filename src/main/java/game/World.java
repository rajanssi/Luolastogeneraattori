package game;

import asciiPanel.AsciiPanel;
import cavegenerator.BSPTree;
import cavegenerator.Room;

import java.awt.Color;
import java.util.ArrayList;

import static cavegenerator.FloorGenerator.connectTrees;
import static utils.RandomNumberGenerator.getRandInt;

/**
 * Määrittelee oleelliset tiedot pelissä käytettävästä tasosta, kuten tason korkeus ja leveys ruuduissa sekä kaikki
 * tason ruudut. Pitää yllä myös tietoa pelimaailman hahmoista ja huoneista. Sisältää metodeita pelimaailman
 * päivitykseen ja uusien maailmojen luomiseen.
 */
public class World {
    private int width;
    private int height;
    private Tile[][] tiles;
    private BSPTree bsp;
    private Character player;
    private final ArrayList<Room> rooms;
    private final ArrayList<Character> characters;
    private int turn;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.rooms = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.bsp = new BSPTree(width, height, 24, 12, 8);
        this.turn = 1;
        this.player = null;
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

    public Tile getTile(int x, int y) {
        return tile(x, y);
    }

    public Character getCharacter(int x, int y) {
        for (Character c : characters) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Character> getCharacters() {
        return this.characters;
    }

    public void addAtEmptyLocation(Character character, Room r) {
        int x;
        int y;

        do {
            x = Math.abs(getRandInt(r.x1, r.x2));
            y = getRandInt(r.y1, r.y2);
        }
        while (!tile(x, y).isWalkable());

        character.setX(x);
        character.setY(y);
        characters.add(character);
    }

    public Character addPlayer() {
        Room r = rooms.get(0);
        this.player = new Character(this, '@', AsciiPanel.brightWhite);
        addAtEmptyLocation(player, r);
        return player;
    }

    public void addEnemies() {
        for (Room r : bsp.getRooms()) {
            if (getRandInt(0, 5) == 1) {
                Character c = new Character(this, 'X', AsciiPanel.brightWhite);
                new CharacterAi(c);
                addAtEmptyLocation(c, r);
            }
        }
    }

    public void updateWorld() {
        for (Character c : characters) {
            if (c.getSymbol() != '@') {
                if (c.getHp() < 1) {
                    characters.remove(c);
                    break;
                }
                if (c.getAi().withinReach(player) && turn % 2 == 0) {
                    c.getAi().seek(player, this);
                } else if (turn % 2 == 0) {
                    c.getAi().moveAround();
                }
            }
        }
        turn++;
    }

    public void growWorld(int horizontalGrowth) {
        Room r1 = bsp.getRooms().get(bsp.getRooms().size() - 1);
        this.bsp = new BSPTree(horizontalGrowth, height, 24, 12, 8);
        var newTiles = bsp.generateLevel();
        var temp = new Tile[width + horizontalGrowth][height];
        for (int i = 0; i < width + horizontalGrowth; i++) {
            for (int j = 0; j < height; j++) {
                if (i < width) {
                    temp[i][j] = tiles[i][j];
                } else {
                    temp[i][j] = newTiles[i - width][j];
                }
            }
        }
        tiles = temp;

        for (Room r : bsp.getRooms()) {
            r.x1 += width;
            r.x2 += width;
        }

        Room r2 = bsp.getRooms().get(0);
        connectTrees(r1, r2, tiles);
        this.width += horizontalGrowth;
        addEnemies();
    }

    public World build() {
        this.tiles = bsp.generateLevel();
        for (Room r : bsp.getRooms()) {
            rooms.add(r);
        }
        addEnemies();
        return this;
    }
}
