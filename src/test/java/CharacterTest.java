import cavegame.game.Character;
import cavegame.game.World;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    static World w;

    @BeforeClass
    public static void setUpClass() {
        w = new World(40, 40, 20).build();
    }

    @Test
    public void canCreatePlayerCharacter() {
        Character player = w.addPlayer();
        assertNotNull(player);
    }


    @Test
    public void canMoveCharacter() {
        Character player = w.addPlayer();

        int x, y;
        x = player.getX();
        y = player.getY();

        if (w.getTile(x + 1, y).isWalkable()) {
            player.moveBy(1, 0);
            assertEquals(x + 1, player.getX());
            player.moveBy(-1, 0);
        }
        if (w.getTile(x, y + 1).isWalkable()) {
            player.moveBy(0, 1);
            assertEquals(y + 1, player.getY());
            player.moveBy(0, -1);
        }
        if (w.getTile(x + 1, y + 1).isWalkable()) {
            player.moveBy(1, 1);
            assertEquals(x + 1, player.getX());
            assertEquals(y + 1, player.getY());
            player.moveBy(-1, -1);
        }
    }
}
