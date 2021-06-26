import cavegame.cavegenerator.BSPTree;
import cavegame.game.Tile;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class BSPTest {
    static BSPTree bsp;

    @BeforeClass
    public static void setUpClass() {
        bsp = new BSPTree(100, 100, 20, 12, 6);
    }

    @Test
    public void generatedLevelsAreCorrectSize() {
        Tile[][] level = bsp.generateLevel();
        assertEquals(100, level.length);
        assertEquals(100, level[0].length);
    }

    @Test
    public void canGenerateSmallLevels() {
        bsp = new BSPTree(10, 10, 10, 10, 9);
        Tile[][] level = bsp.generateLevel();
        assertEquals(10, level.length);
        assertEquals(10, level[0].length);
    }


    public void canGenerateLargeLevels() {
        bsp = new BSPTree(10000, 10000, 24, 12, 6);
        Tile[][] level = bsp.generateLevel();
        assertEquals(10000, level.length);
        assertEquals(10000, level[0].length);
    }
}
