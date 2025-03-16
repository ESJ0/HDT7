import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias para la clase BST_BTS.
 */
public class BST_BTSTest {

    private BST_BTS<Integer, String> bst;

    @Before
    public void setUp() {
        bst = new BST_BTS<>();
    }

    @Test
    public void testAddAndSearch() {

        bst.add(5, "Manzana");
        bst.add(3, "Banana");

        assertEquals("Manzana", bst.search(5));
        assertEquals("Banana", bst.search(3));
    }

    @Test
    public void testSearchInEmptyTree() {
        assertNull(bst.search(5));
    }

    @Test
    public void testAddSingleElement() {
        bst.add(10, "Kiwi");
        assertEquals("Kiwi", bst.search(10));
    }
}