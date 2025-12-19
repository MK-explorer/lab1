package test;

import manager.KnightManager;
import model.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightManagerTest {

    private KnightManager manager;

    @BeforeEach
    public void setUp() {
        manager = new KnightManager();
    }

    @Test
    public void testAddSingleKnight() {
        Knight knight = new Knight("Arthur", 5, 100, 20);
        manager.addKnight(knight);

        assertEquals(1, manager.size());
    }

    @Test
    public void testAddMultipleKnights() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));
        manager.addKnight(new Knight("Lancelot", 6, 110, 22));
        manager.addKnight(new Knight("Gawain", 4, 90, 18));

        assertEquals(3, manager.size());
    }

    @Test
    public void testFindByNameFound() {
        Knight knight = new Knight("Arthur", 5, 100, 20);
        manager.addKnight(knight);

        var found = manager.findByName("Arthur");
        assertTrue(found.isPresent());
        assertEquals("Arthur", found.get().getName());
    }

    @Test
    public void testFindByNameNotFound() {
        var found = manager.findByName("NonExistent");
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindByNameCaseInsensitive() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));

        var found = manager.findByName("arthur");
        assertTrue(found.isPresent());
    }

    @Test
    public void testGetByIndexValid() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));

        var found = manager.getByIndex(0);
        assertTrue(found.isPresent());
        assertEquals("Arthur", found.get().getName());
    }

    @Test
    public void testGetByIndexInvalid() {
        var found = manager.getByIndex(5);
        assertFalse(found.isPresent());
    }

    @Test
    public void testGetByIndexNegative() {
        var found = manager.getByIndex(-1);
        assertFalse(found.isPresent());
    }

    @Test
    public void testRemoveByIndex() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));
        manager.addKnight(new Knight("Lancelot", 6, 110, 22));

        manager.removeByIndex(0);
        assertEquals(1, manager.size());
        assertEquals("Lancelot", manager.getByIndex(0).get().getName());
    }

    @Test
    public void testRemoveByIndexInvalid() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));
        manager.removeByIndex(5);
        assertEquals(1, manager.size());
    }

    @Test
    public void testClear() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));
        manager.addKnight(new Knight("Lancelot", 6, 110, 22));

        manager.clear();
        assertEquals(0, manager.size());
    }

    @Test
    public void testGetKnightsUnmodifiable() {
        manager.addKnight(new Knight("Arthur", 5, 100, 20));
        var list = manager.getKnights();

        assertThrows(UnsupportedOperationException.class, () -> list.add(new Knight("New", 1, 1, 1)));
    }
}