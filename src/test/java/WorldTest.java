import game.World;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    static World w;

    @BeforeClass
    public static void setUpClass() {
        w = new World(40, 40);
    }

    @Test
    public void canBuildWorldOfCorrectSize() {
        assertEquals(100, w.getWidth());
        assertEquals(100, w.getHeight());
    }

    @Test
    public void canAddCharacters() {
        w.addEnemies();
        assertEquals(1, w.getCharacters().size());
    }

    @Test
    public void canGrowWorld() {
        w.growWorld(40);
        assertEquals(80, w.getWidth());
    }
}
