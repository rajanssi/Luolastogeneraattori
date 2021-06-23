import org.junit.Before;
import org.junit.Test;
import utils.ArrayList;

import static org.junit.Assert.*;

public class UtilsTest {
    static ArrayList a;

    @Before
    public void setUp() {
        a = new ArrayList();
    }

    @Test
    public void listGrowsDynamically() {
        for (int i = 0; i < 100; i++) {
            a.add(3);
        }
        assertEquals(100, a.size());
    }

    @Test
    public void canRemoveItemsFromList() {
        for (int i = 0; i < 100; i++) {
            a.add(i);
        }

        a.remove(20);

        assertEquals(19, a.get(19));
        assertEquals(21, a.get(20));
    }

    @Test
    public void canRemoveItemsWithoutIndex() {
        Object o = new Object();
        a.add(o);
        a.remove(o);

        assertEquals(0, a.size());

        a.remove(o);
        assertEquals(0, a.size());
    }

    @Test
    public void canModifyExistingItem() {
        a.add(3);
        a.set(0, 9);
        assertEquals(9, a.get(0));

    }
}
