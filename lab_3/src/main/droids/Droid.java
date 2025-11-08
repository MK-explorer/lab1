package main.droids;

import java.util.*;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth; //  зберігаємо максимум для відновлення HP
    protected int damage;
    protected List<Item> inventory = new ArrayList<>();

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health; //  запам'ятовуємо початкове HP
        this.damage = damage;
    }

    public void addItem(Item item) {
        inventory.add(item);
        damage += item.getAttackBoost();
        System.out.println(ConsoleColors.YELLOW + name + " отримав предмет: " + item + ConsoleColors.RESET);
    }

    public void attack(Droid enemy) {
        int dmg = damage;
        if (Math.random() < 0.2) { // шанс критичного удару
            dmg *= 2;
            System.out.println(ConsoleColors.RED + "⚡ Критичний удар!" + ConsoleColors.RESET);
        }
        enemy.takeDamage(dmg);
        System.out.println(name + " ⚔ атакує " + enemy.name + " на " + dmg + " dmg!");
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    // метод для повного відновлення HP
    public void restoreHealth() {
        this.health = this.maxHealth;
    }

    @Override
    public String toString() {
        return name + " [HP=" + health + "/" + maxHealth + ", DMG=" + damage + "]";
    }
}

