package game;

import java.awt.Color;
import asciiPanel.AsciiPanel;

/**
 * Määrittelee ruudut, joista pelimaailma koostuu.
 */
public enum Tile {
    FLOOR((char) 250, AsciiPanel.white),
    WALL('#', AsciiPanel.white),
    ROOMWALL('#', AsciiPanel.white),
    BOUNDS('x', AsciiPanel.brightBlack),
    DOOR('D', AsciiPanel.white);

    private final char symbol;
    private final Color color;
    private boolean visible;

    Tile(char symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
        this.visible = false;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getColor() {
        if (visible && symbol != 'x') {
            return color;
        }
        return Color.DARK_GRAY;
    }

    public void setVisible(boolean b) {
        this.visible = b;
    }

    public boolean isVisible() {
        return this.visible;
    }


    public boolean isWalkable() {
        return this != WALL && this != ROOMWALL && this != BOUNDS;
    }
}

