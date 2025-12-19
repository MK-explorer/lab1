package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private Knight knight;

    @BeforeEach
    public void setUp() {
        knight = new Knight("Arthur", 5, 100, 20);
    }

    @Test
    public void testKnightCreation() {
        assertEquals("Arthur", knight.getName());
        assertEquals(5, knight.getLevel());
        assertEquals(100, knight.getHealth());
        assertEquals(20, knight.getStrength());
    }

    @Test
    public void testAddEquipment() {
        Weapon sword = new Weapon("Sword", 2.5, 100, 25);
        knight.addEquipment(sword);
        assertEquals(1, knight.getEquipment().size());
    }

    @Test
    public void testAddMultipleEquipment() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        knight.addEquipment(new Armor("Armor", 15.0, 500, 10));
        assertEquals(2, knight.getEquipment().size());
    }

    @Test
    public void testRemoveEquipment() {
        Weapon sword = new Weapon("Sword", 2.5, 100, 25);
        knight.addEquipment(sword);
        boolean removed = knight.removeEquipment("Sword");
        assertTrue(removed);
        assertEquals(0, knight.getEquipment().size());
    }

    @Test
    public void testRemoveNonExistentEquipment() {
        boolean removed = knight.removeEquipment("NonExistent");
        assertFalse(removed);
    }

    @Test
    public void testTotalWeightEmpty() {
        assertEquals(0.0, knight.totalWeight());
    }

    @Test
    public void testTotalWeightSingleItem() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        assertEquals(2.5, knight.totalWeight());
    }

    @Test
    public void testTotalWeightMultipleItems() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        knight.addEquipment(new Armor("Armor", 15.0, 500, 10));
        knight.addEquipment(new OtherEquipment("Shield", 5.0, 80));
        assertEquals(22.5, knight.totalWeight());
    }

    @Test
    public void testTotalPriceEmpty() {
        assertEquals(0.0, knight.totalPrice());
    }

    @Test
    public void testTotalPriceSingleItem() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        assertEquals(100.0, knight.totalPrice());
    }

    @Test
    public void testTotalPriceMultipleItems() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        knight.addEquipment(new Armor("Armor", 15.0, 500, 10));
        assertEquals(600.0, knight.totalPrice());
    }

    @Test
    public void testSetHorse() {
        Horse horse = new Horse("Bucephalus", 50, 200);
        knight.setHorse(horse);
        assertNotNull(knight.getHorse());
        assertEquals("Bucephalus", knight.getHorse().getName());
    }

    @Test
    public void testRemoveHorse() {
        knight.setHorse(new Horse("Bucephalus", 50, 200));
        knight.setHorse(null);
        assertNull(knight.getHorse());
    }

    @Test
    public void testSetSquire() {
        Squire squire = new Squire("Tom", 85);
        knight.setSquire(squire);
        assertNotNull(knight.getSquire());
        assertEquals("Tom", knight.getSquire().getName());
        assertEquals(85, knight.getSquire().getLoyaltyLevel());
    }

    @Test
    public void testRemoveSquire() {
        knight.setSquire(new Squire("Tom", 85));
        knight.setSquire(null);
        assertNull(knight.getSquire());
    }

    @Test
    public void testFindByPriceRangeEmpty() {
        var found = knight.findByPriceRange(100, 200);
        assertTrue(found.isEmpty());
    }

    @Test
    public void testFindByPriceRangeOneItem() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        var found = knight.findByPriceRange(50, 150);
        assertEquals(1, found.size());
    }

    @Test
    public void testFindByPriceRangeMultipleItems() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        knight.addEquipment(new Armor("Armor", 15.0, 500, 10));
        knight.addEquipment(new Weapon("Dagger", 1.0, 50, 10));

        var found = knight.findByPriceRange(40, 150);
        assertEquals(2, found.size());
    }

    @Test
    public void testFindByPriceRangeNoMatches() {
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));
        var found = knight.findByPriceRange(200, 300);
        assertTrue(found.isEmpty());
    }

    @Test
    public void testSortByWeight() {
        knight.addEquipment(new Armor("Armor", 15.0, 500, 10));
        knight.addEquipment(new Weapon("Dagger", 1.0, 50, 10));
        knight.addEquipment(new Weapon("Sword", 2.5, 100, 25));

        knight.sortEquipmentByWeight();

        assertEquals(1.0, knight.getEquipment().get(0).getWeight());
        assertEquals(2.5, knight.getEquipment().get(1).getWeight());
        assertEquals(15.0, knight.getEquipment().get(2).getWeight());
    }

    @Test
    public void testSetLevel() {
        knight.setLevel(10);
        assertEquals(10, knight.getLevel());
    }

    @Test
    public void testSetHealth() {
        knight.setHealth(150);
        assertEquals(150, knight.getHealth());
    }

    @Test
    public void testSetStrength() {
        knight.setStrength(30);
        assertEquals(30, knight.getStrength());
    }
}
