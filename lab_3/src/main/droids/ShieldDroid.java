package main.droids;

public class ShieldDroid extends Droid {
    private int shield; // захисний щит

    public ShieldDroid(String name) {
        super(name, 200, 10); // базове здоров'я та шкода
        this.shield = 20;      // початковий щит
    }

    @Override
    public void takeDamage(int dmg) {
        int dmgAfterShield = dmg - shield; // щит зменшує шкоду
        if (dmgAfterShield < 0) dmgAfterShield = 0;
        super.takeDamage(dmgAfterShield);
        System.out.println(ConsoleColors.CYAN + name + " використовує щит! Отримано " + dmgAfterShield + " dmg." + ConsoleColors.RESET);
    }

    public void rechargeShield() {
        shield += 10; // поповнення щита
        System.out.println(ConsoleColors.BLUE + name + " заряджає щит на 10 HP!" + ConsoleColors.RESET);
    }
}
