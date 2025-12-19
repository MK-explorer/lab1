package command;

import manager.KnightManager;
import model.Knight;
import model.Squire;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class ManageSquireCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public ManageSquireCommand(KnightManager manager, Scanner scanner) {
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
        System.out.println("1) Призначити зброєносця  2) Вилучити зброєносця  3) Оглянути зброєносця");
        int c = InputUtils.readIntInRange(scanner, "Вибір: ", 1, 3);
        if (c == 1) {
            String name = InputUtils.readNonEmptyString(scanner, "Ім'я зброєносця: ");
            int loyalty = InputUtils.readIntInRange(scanner, "Вірність (0-100): ", 0, 100);
            k.setSquire(new Squire(name, loyalty));
            System.out.println("Зброєносця призначено.");
        } else if (c == 2) {
            k.setSquire(null);
            System.out.println("Зброєносця видалено.");
        } else {
            System.out.println("Зброєносець: " + (k.getSquire() == null ? "нема" : k.getSquire()));
        }
    }
}
