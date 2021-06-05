package cavegenerator;

/**
 * Suorakulmio, joka luodaan pisteiden (x1, y1) ja (x2, y2) perusteella
 */
public class Room {
    public int  x1, y1, x2, y2;

    Room(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + w;
        this.y2 = y + h;
    }

    /**
     *
     * @return Suorakulmion keskipiste x-akselilla
     */
    int centerX() {
        return (x1 + x2) / 2;
    }

    /**
     *
     * @return Suorakulmion keskipiste y-akselilla
     */
    int centerY() {
        return (y1 + y2) / 2;
    }
}
