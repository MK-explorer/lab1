package main.droids;

public class Item {
    private String name;
    private int attackBoost;

    public Item(String name, int attackBoost) {
        this.name = name;
        this.attackBoost = attackBoost;
    }

    public int getAttackBoost() {
        return attackBoost;
    }


    @Override
    public String toString() {
        return name + " [+" + attackBoost + " dmg,)";
    }
}
