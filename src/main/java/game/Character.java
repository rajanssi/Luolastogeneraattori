package game;

import java.awt.*;

/**
 * Määrittelee pelihahmo-olion ja mm. sen sijainnin pelimaailmassa, tekoälyn ja ulkonäön
 */
public class Character {
    private World world;
    private final char symbol;
    private Color color;
    private CharacterAi ai;
    private final int maxHp;
    private int hp;
    private int x;
    private int y;

    public Character(World world, char symbol, Color color) {
        this.world = world;
        this.symbol = symbol;
        this.color = color;

        if (symbol == '@') {
            maxHp = 5;
        } else {
            maxHp = 3;
        }
        hp = maxHp;
    }

    public char getSymbol() {
        return symbol;
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

    public void setAi(CharacterAi ai) {
        this.ai = ai;
    }

    public CharacterAi getAi() {
        return ai;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return this.hp;
    }

    /**
     * Liikkuu annettujen parametrien mukaiseen ruutuun. Mikäli ruudussa on toinen hahmo, hyökkää sitä vastaan.
     * @param mx
     * @param my
     */
    public void moveBy(int mx, int my) {
        Character o = world.getCharacter(x+mx, y+my);

        if (o == null || o == this) {
            onEnter(getX() + mx, getY() + my, world.tile(getX() + mx, getY() + my));
        } else {
            attack(o);
        }

    }

    /**
     * Tarkistaa, voiko ruutuun siirtyä.
     * @param x
     * @param y
     * @param tile
     */
    public void onEnter(int x, int y, Tile tile) {
        if (tile == Tile.DOOR && this.ai != null) {
            return;
        }

        if (tile.isWalkable()) {
            setX(x);
            setY(y);
        }
    }

    /**
     * Hyökkää toista pelihahmoa vastan ja vähentää tämän elinvoimaa.
     * @param o Toinen pelihahmo
     */
    public void attack(Character o) {
        o.setHp(o.getHp() - 1);
    }

    /**
     * Päivittää pelimaailman tilan tällä hahomlle
     * @param w Tämän hahmon pelimaailma
     */
    public void updateWorld(World w) {
        this.world = w;
    }
}
