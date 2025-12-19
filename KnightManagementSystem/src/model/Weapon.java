package model;

public class Weapon extends Equipment {
    private static final long serialVersionUID = 1L;
    private final int damage;

    public Weapon(String name, double weight, double price, int damage) {
        super(name, weight, price, EquipmentType.WEAPON);
        this.damage = damage;
    }

    public int getDamage() { return damage; }

    @Override
    public String toString() {
        return super.toString() + String.format(", шкода=%d", damage);
    }
}
