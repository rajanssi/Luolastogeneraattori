package game;

import java.awt.*;

/**
 * Määrittelee pelihahmo-olion ja mm. sen sijainnin pelimaailmassa, tekoälyn ja ulkonäön
 */
public class Character {
    private World world;
    private final char symbol;
    private final Color color;
    private CharacterAi ai;
    private int x;
    private int y;

    public Character(World world, char symbol, Color color) {
        this.world = world;
        this.symbol = symbol;
        this.color = color;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public void moveBy(int mx, int my) {
        this.onEnter(getX() + mx, getY() + my, world.tile(getX() + mx, getY() + my));
    }

    public void onEnter(int x, int y, Tile tile) {
        if (tile.isWalkable()) {
            setX(x);
            setY(y);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updateWorld(World w) {
        this.world = w;
    }

    public void setAi(CharacterAi ai) {
        this.ai = ai;
    }

    public CharacterAi getAi() {
        return ai;
    }
}
