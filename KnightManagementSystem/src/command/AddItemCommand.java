package command;

import manager.KnightManager;
import model.*;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class AddItemCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public AddItemCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int idx = InputUtils.chooseKnightIndex(scanner, manager.getKnights());
        if (idx < 0) return;
        Optional<Knight> opt = manager.getByIndex(idx);
        if (opt.isEmpty()) {
            System.out.println("Лицар не знайдений.");
            return;
        }
        Knight knight = opt.get();

        System.out.println("Тип спорядження: 1) Зброя 2) Броня 3) Інше");
        int t = InputUtils.readIntInRange(scanner, "Вибір: ", 1, 3);
        String name = InputUtils.readNonEmptyString(scanner, "Назва предмета: ");
        double weight = InputUtils.readDoubleInRange(scanner, "Вага (kg): ", 0.01, 10000);
        double price = InputUtils.readDoubleInRange(scanner, "Ціна: ", 0.01, 1000000);

        switch (t) {
            case 1 -> {
                int dmg = InputUtils.readIntInRange(scanner, "Шкода (damage, int): ", 0, Integer.MAX_VALUE);
                Weapon w = new Weapon(name, weight, price, dmg);
                knight.addEquipment(w);
            }
            case 2 -> {
                int def = InputUtils.readIntInRange(scanner, "Захист (defense, int): ", 0, Integer.MAX_VALUE);
                Armor a = new Armor(name, weight, price, def);
                knight.addEquipment(a);
            }
            default -> {
                OtherEquipment e = new OtherEquipment(name, weight, price);
                knight.addEquipment(e);
            }
        }
        System.out.println("Предмет додано.");
    }
}