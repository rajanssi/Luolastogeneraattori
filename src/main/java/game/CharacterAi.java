package game;

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

}
