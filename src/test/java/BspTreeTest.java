import cavegenerator.BSPTree;
import game.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class BspTreeTest {
    static BSPTree bsp;

    @BeforeClass
    public static void setUpClass() {
        bsp = new BSPTree(100, 100, 10, 10, 9);
    }

    @Test
    public void generatedLevelsAreCorrectSize() {
        Tile[][] level = bsp.generateLevel();
        assertEquals(100, level.length);
        assertEquals(100, level[0].length);
    }


}
