package cavegenerator;

class Rect {
    int x1, y1, x2, y2;

    Rect(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + w;
        this.y2 = y + h;
    }

    int centerX() {
        return (x1 + x2) / 2;
    }

    int centerY() {
        return (y1 + y2) / 2;
    }
}
