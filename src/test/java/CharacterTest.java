import game.Character;
import game.World;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    static World w;

    @BeforeClass
    public static void setUpClass() {
        w = new World(40, 40);
    }

    @Test
    public void canCreatePlayerCharacter() {
        Character player = w.addPlayer();
        assertNotNull(player);
    }

    @Test
    public void nonPlayerCharacterHasAi() {
        w.addEnemies();
        Character c = w.getCharacters().get(0);
        assertNotNull(c.getAi());
    }

}
