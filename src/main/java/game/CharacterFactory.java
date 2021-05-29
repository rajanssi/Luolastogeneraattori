package game;

import asciiPanel.AsciiPanel;

public class CharacterFactory {
    private final World world;

    public CharacterFactory(World world) {
        this.world = world;
    }

    public Character newPlayer() {
        Character player = new Character(world, '@', AsciiPanel.brightWhite);
        world.addAtEmptyLocation(player);
        return player;
    }
}

