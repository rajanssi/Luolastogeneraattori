import game.Character;
import game.World;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    static World w;

    @BeforeClass
    public static void setUpClass() {
        w = new World(40, 40).build();
    }

    @Test
    public void canCreatePlayerCharacter() {
        Character player = w.addPlayer();
        assertNotNull(player);
    }

    @Test
    public void nonPlayerCharacterHasAi() {
        w.addEnemies();
        Character c = null;
        do {
            c = w.getCharacters().get(1);
        }
        while (c == null);
        assertNotNull(c.getAi());
    }

}
