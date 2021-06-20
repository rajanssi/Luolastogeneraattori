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

    Tile(char symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public boolean isWalkable() {
        return this != WALL && this != ROOMWALL && this != BOUNDS;
    }
}

