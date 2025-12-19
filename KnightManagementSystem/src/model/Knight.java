package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Knight implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private int level;
    private int health;
    private int strength;
    private final List<Equipment> equipment;
    private Horse horse;
    private Squire squire;

    public Knight(String name, int level, int health, int strength) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.strength = strength;
        this.equipment = new ArrayList<>();
    }

    // getters/setters
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getStrength() { return strength; }
    public List<Equipment> getEquipment() { return equipment; }
    public Horse getHorse() { return horse; }
    public Squire getSquire() { return squire; }

    public void setLevel(int level) { this.level = level; }
    public void setHealth(int health) { this.health = health; }
    public void setStrength(int strength) { this.strength = strength; }

    public void setHorse(Horse horse) { this.horse = horse; }
    public void setSquire(Squire squire) { this.squire = squire; }

    public void addEquipment(Equipment e) { equipment.add(e); }

    public boolean removeEquipment(String eqName) {
        return equipment.removeIf(e -> e.getName().equalsIgnoreCase(eqName));
    }

    public double totalWeight() {
        return equipment.stream().mapToDouble(Equipment::getWeight).sum();
    }

    public double totalPrice() {
        return equipment.stream().mapToDouble(Equipment::getPrice).sum();
    }

    public List<Equipment> findByPriceRange(double low, double high) {
        return equipment.stream()
                .filter(e -> e.getPrice() >= low && e.getPrice() <= high)
                .collect(Collectors.toList());
    }

    public void sortEquipmentByWeight() {
        equipment.sort(Comparator.comparingDouble(Equipment::getWeight));
    }

    @Override
    public String toString() {
        return String.format("Лицар %s (рівень=%d, життя=%d, сила=%d)", name, level, health, strength);
    }
}
