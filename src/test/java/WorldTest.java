import game.World;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    static World w;

    @BeforeClass
    public static void setUpClass() {
        w = new World(40, 40).build();
    }

    @Test
    public void canBuildWorldOfCorrectSize() {
        assertEquals(40, w.getWidth());
        assertEquals(40, w.getHeight());
    }

    @Test
    public void canAddCharacters() {
        w.addPlayer();
        assertNotNull(w.getCharacters().get(0));
    }

    @Test
    public void canGrowWorld() {
        w.growWorld(40);
        assertEquals(80, w.getWidth());
    }
}
