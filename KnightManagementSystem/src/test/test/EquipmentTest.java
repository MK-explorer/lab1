package test;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipmentTest {

    @Test
    public void testWeaponCreation() {
        Weapon sword = new Weapon("Sword", 2.5, 100, 25);

        assertEquals("Sword", sword.getName());
        assertEquals(2.5, sword.getWeight());
        assertEquals(100, sword.getPrice());
        assertEquals(25, sword.getDamage());
        assertEquals(EquipmentType.WEAPON, sword.getType());
    }

    @Test
    public void testWeaponToString() {
        Weapon sword = new Weapon("Sword", 2.5, 100, 25);
        assertTrue(sword.toString().contains("Sword"));
        assertTrue(sword.toString().contains("25"));
    }

    @Test
    public void testArmorCreation() {
        Armor armor = new Armor("Steel Armor", 15.0, 500, 10);

        assertEquals("Steel Armor", armor.getName());
        assertEquals(15.0, armor.getWeight());
        assertEquals(500, armor.getPrice());
        assertEquals(10, armor.getDefense());
        assertEquals(EquipmentType.ARMOR, armor.getType());
    }

    @Test
    public void testArmorToString() {
        Armor armor = new Armor("Steel Armor", 15.0, 500, 10);
        assertTrue(armor.toString().contains("Steel Armor"));
        assertTrue(armor.toString().contains("10"));
    }

    @Test
    public void testOtherEquipmentCreation() {
        OtherEquipment shield = new OtherEquipment("Shield", 5.0, 80);

        assertEquals("Shield", shield.getName());
        assertEquals(5.0, shield.getWeight());
        assertEquals(80, shield.getPrice());
        assertEquals(EquipmentType.OTHER, shield.getType());
    }

    @Test
    public void testHorseCreation() {
        Horse horse = new Horse("Bucephalus", 50.0, 200.0);

        assertEquals("Bucephalus", horse.getName());
        assertEquals(50.0, horse.getSpeed());
        assertEquals(200.0, horse.getCarryCapacity());
    }

    @Test
    public void testSquireCreation() {
        Squire squire = new Squire("Tom", 85);

        assertEquals("Tom", squire.getName());
        assertEquals(85, squire.getLoyaltyLevel());
    }
}

