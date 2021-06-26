import cavegame.cavegenerator.Room;
import cavegame.game.Character;
import cavegame.game.World;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CharacterAiTest {
    World w;

    @Before
    public void setUp() {
        w = new World(40, 40, 5).build();
    }

    @Test
    public void characterReactsToNearByCharacter() {
        Character player = w.addPlayer();
        Character c = new Character(w, 'X', Color.WHITE);
        w.addAtEmptyLocation(c, w.rooms.get(0));
        c.setAi();
        Room r = w.rooms.get(0);

        player.setX(r.centerX() + 1);
        player.setY(r.centerY());

        c.setX(r.centerX() - 1);
        c.setY(r.centerY());

        w.updateWorld();
        w.updateWorld();

        assertTrue(c.getAi().withinReach(player));
    }

    public void characterMovesTowardsPlayer() {
        Character player = w.addPlayer();
        Character c = new Character(w, 'X', Color.WHITE);
        w.addAtEmptyLocation(c, w.rooms.get(0));
        c.setAi();
        Room r = w.rooms.get(0);

        player.setX(r.centerX() + 1);
        player.setY(r.centerY());

        c.setX(r.centerX() - 1);
        c.setY(r.centerY());

        w.updateWorld();
        w.updateWorld();

        assertEquals(c.getX(), r.centerX());
    }

    @Test
    public void characterWillAttackNearbyPlayer() {
        Character player = w.addPlayer();
        Character c = new Character(w, 'X', Color.WHITE);
        w.addAtEmptyLocation(c, w.rooms.get(0));
        c.setAi();

        c.setX(player.getX() + 1);
        c.setY(player.getY());

        int hp = player.getHp();

        w.updateWorld();
        w.updateWorld();

        assertNotEquals(hp, player.getHp());
    }

}
