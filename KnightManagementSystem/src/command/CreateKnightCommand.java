package command;

import manager.KnightManager;
import model.Knight;
import Util.InputUtils;

import java.util.Scanner;

public class CreateKnightCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public CreateKnightCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        String name = InputUtils.readNonEmptyString(scanner, "Ім'я лицаря: ");

        //  ПЕРЕВІРКА НА ДУБЛІКАТИ
        if (manager.findByName(name).isPresent()) {
            System.out.println(" Лицар з таким іменем уже існує!");
            return;
        }

        int lvl = InputUtils.readIntInRange(scanner, "Рівень (ціле число >=1): ", 1, Integer.MAX_VALUE);
        int hp = InputUtils.readIntInRange(scanner, "Здоров'я (ціле число >=1): ", 1, Integer.MAX_VALUE);
        int str = InputUtils.readIntInRange(scanner, "Сила (ціле число >=0): ", 0, Integer.MAX_VALUE);

        Knight k = new Knight(name, lvl, hp, str);
        manager.addKnight(k);
        System.out.println(" Створено: " + k);
    }
}
