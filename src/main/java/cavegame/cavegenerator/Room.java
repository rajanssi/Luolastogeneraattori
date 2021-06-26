package cavegame.cavegenerator;

import java.util.Objects;

/**
 * Suorakulmio, joka luodaan pisteiden (x1, y1) ja (x2, y2) perusteella
 */
public class Room {
    public int  x1, y1, x2, y2;

    /**
     * Konstruktori, joka ottaa huoneen sijainnin tasossa xy-akselilla ja huoneen leveyden (w) ja korkeuden (h).
     * @param x Huoneen sijainti x-akselilla.
     * @param y Huoneen sijainti y-akselilla.
     * @param w Huoneen leveys.
     * @param h Huoneen korkeus.
     */
    Room(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + w;
        this.y2 = y + h;
    }

    /**
     * @return Huoneen keskipiste x-akselilla.
     */
    public int centerX() {
        return (x1 + x2) / 2;
    }

    /**
     * @return Huoneen keskipiste y-akselilla.
     */
    public int centerY() {
        return (y1 + y2) / 2;
    }
}
