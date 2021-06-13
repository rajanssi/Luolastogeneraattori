package game;

import java.awt.*;

import static utils.RandomNumberGenerator.getRandInt;

/**
 * Luokka pelin hahmojen liikkumiselle
 */
public class CharacterAi {
    private final Character character;

    CharacterAi(Character character) {
        this.character = character;
        character.setAi(this);
    }

    public void moveAround() {
        int mx = getRandInt(0, 2) - 1;
        int my = getRandInt(0, 2) - 1;

        character.moveBy(mx, my);
    }

    public void seek(Character other, World w) {
        character.setColor(Color.GREEN);
        //new Pathfinder(w, character, other);
    }

    public boolean withinReach(Character other) {

        return Math.abs(other.getX() - this.character.getX()) < 5 && Math.abs(other.getY() - this.character.getY()) < 5;
    }

}
