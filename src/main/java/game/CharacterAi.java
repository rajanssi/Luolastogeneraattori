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

    /**
     * Liiku satunnaisesti valittuun suuntaan
     */
    public void moveAround() {
        int mx = getRandInt(0, 2) - 1;
        int my = getRandInt(0, 2) - 1;

        character.moveBy(mx, my);
    }

    /**
     * Etsii reitin toisen pelihahmon luokse.
     *
     * @param other Toinen pelihahmo (tässä toteutuksessa aina pelaaja)
     * @param w Tämän hahmon pelimaailma
     */
    public void seek(Character other, World w) {
        character.setColor(Color.GREEN);
        int direction = new Pathfinder(w, character, other).getDirection();

        switch (direction) {
            case 0:
                character.moveBy(0,-1);
                break;
            case 1:
                character.moveBy(1,0);
                break;
            case 2:
                character.moveBy(0,1);
                break;
            case 3:
                character.moveBy(-1,0);
                break;
            default:
                moveAround();
                break;
        }
    }

    /**
     * Tarkistaa, onko toinen hahmo tähän hahmoon keskitetyn 10x10 neliön sisällä.
     *
     * @param other Toinen pelihahmo
     * @return Palauttaa true mikäli näin on, muuten false
     */
    public boolean withinReach(Character other) {
        return Math.abs(other.getX() - this.character.getX()) < 5 && Math.abs(other.getY() - this.character.getY()) < 5;
    }

}
