import cavegame.cavegenerator.Room;
import cavegame.game.World;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static cavegame.utils.RandomNumberGenerator.*;

public class WorldTest {
    World w;

    @Before
    public void setUp() {
        w = new World(40, 40, 12).build();
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

    @Test
    public void roomsAreConnected() {
        boolean[][] seen = new boolean[w.getWidth()][w.getHeight()];
        dfs(seen, w.rooms.get(0).centerX(), w.rooms.get(0).centerY());

        int index = getRandInt(1, w.rooms.size() - 1);
        Room r = w.rooms.get(index);
        assertTrue(seen[r.centerX()][r.centerY()]);
    }
    
    public void roomsAreConnectedAfterGrowingWorld() {
        boolean[][] seen = new boolean[w.getWidth()][w.getHeight()];
        dfs(seen, w.rooms.get(0).centerX(), w.rooms.get(0).centerY());

        int index = getRandInt(1, w.rooms.size());
        Room r = w.rooms.get(index);
        assertTrue(seen[r.centerX()][r.centerY()]);

        w.growWorld(50);

        seen = new boolean[w.getWidth()][w.getHeight()];
        dfs(seen, w.rooms.get(0).centerX(), w.rooms.get(0).centerY());
        r = w.rooms.get(w.rooms.size() - 1);
        assertTrue(seen[r.centerX()][r.centerY()]);
    }

    private void dfs(boolean[][] seen, int x, int y) {
        if (seen[x][y]) {
            return;
        }
        seen[x][y] = true;
        if (w.getTile(x + 1, y).isWalkable()) {
            dfs(seen, x + 1, y);
        }
        if (w.getTile(x - 1, y).isWalkable()) {
            dfs(seen, x - 1, y);
        }
        if (w.getTile(x, y + 1).isWalkable()) {
            dfs(seen, x, y + 1);
        }
        if (w.getTile(x, y - 1).isWalkable()) {
            dfs(seen, x, y - 1);
        }
    }
}
