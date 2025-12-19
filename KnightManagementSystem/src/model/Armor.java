package model;

public class Armor extends Equipment {
    private static final long serialVersionUID = 1L;
    private final int defense;

    public Armor(String name, double weight, double price, int defense) {
        super(name, weight, price, EquipmentType.ARMOR);
        this.defense = defense;
    }

    public int getDefense() { return defense; }

    @Override
    public String toString() {
        return super.toString() + String.format(", захист=%d", defense);
    }
}
