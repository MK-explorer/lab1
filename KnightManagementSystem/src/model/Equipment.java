package model;

import java.io.Serializable;

public abstract class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final double weight;
    private final double price;
    private final EquipmentType type;

    public Equipment(String name, double weight, double price, EquipmentType type) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.type = type;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public double getPrice() { return price; }
    public EquipmentType getType() { return type; }

    @Override
    public String toString() {
        return String.format("%s (тип=%s, вага=%.2f, ціна=%.2f)", name, type, weight, price);
    }
}
