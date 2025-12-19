package model;

import java.io.Serializable;

public class Horse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final double speed;
    private final double carryCapacity;

    public Horse(String name, double speed, double carryCapacity) {
        this.name = name;
        this.speed = speed;
        this.carryCapacity = carryCapacity;
    }

    public String getName() { return name; }
    public double getSpeed() { return speed; }
    public double getCarryCapacity() { return carryCapacity; }

    @Override
    public String toString() {
        return String.format("%s (Швидкість=%.2f, місткість коня=%.2fkg)", name, speed, carryCapacity);
    }
}
