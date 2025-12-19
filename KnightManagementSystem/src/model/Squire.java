package model;

import java.io.Serializable;

public class Squire implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int loyaltyLevel; // 0..100

    public Squire(String name, int loyaltyLevel) {
        this.name = name;
        this.loyaltyLevel = loyaltyLevel;
    }

    public String getName() { return name; }
    public int getLoyaltyLevel() { return loyaltyLevel; }

    @Override
    public String toString() {
        return String.format("%s (вірність=%d)", name, loyaltyLevel);
    }
}
