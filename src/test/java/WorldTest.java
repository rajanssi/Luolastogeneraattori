import asciiPanel.AsciiPanel;
import game.Character;
import game.CharacterFactory;
import game.Tile;
import game.World;
import game.WorldBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    static WorldBuilder b;
    static World w;

    @BeforeClass
    public static void setUpClass() {
        b = new WorldBuilder(100, 100);
        b.makeRooms();
        w = b.build();
    }

    @Test
    public void canBuildWorldOfCorrectSize() {
        assertEquals(100, w.getWidth());
        assertEquals(100, w.getHeight());
    }

    @Test
    public void characterIsNotPlacedInAWall() {
        Character c = new Character(w, '@', AsciiPanel.brightWhite);
        w.addAtEmptyLocation(c);
        assertTrue(w.tile(c.getX(), c.getY()).isWalkable());
    }

}
