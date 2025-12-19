package command;

import manager.KnightManager;
import model.Horse;
import model.Knight;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class ManageHorseCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public ManageHorseCommand(KnightManager manager, Scanner scanner) {
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
        Knight k = opt.get();
        System.out.println("1) Призначити коня  2) Вилучити коня  3) Оглянути коня");
        int c = InputUtils.readIntInRange(scanner, "Вибір: ", 1, 3);
        if (c == 1) {
            String name = InputUtils.readNonEmptyString(scanner, "Ім'я коня: ");
            double speed = InputUtils.readDouble(scanner, "Швидкість: ");
            double carry = InputUtils.readDouble(scanner, "Місткість коня (kg): ");
            k.setHorse(new Horse(name, speed, carry));
            System.out.println("Коня призначено.");
        } else if (c == 2) {
            k.setHorse(null);
            System.out.println("Коня вилучено.");
        } else {
            System.out.println("Кінь: " + (k.getHorse() == null ? "нема" : k.getHorse()));
        }
    }
}
