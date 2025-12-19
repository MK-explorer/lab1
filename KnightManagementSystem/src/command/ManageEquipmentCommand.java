package command;

import manager.KnightManager;
import model.Knight;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class ManageEquipmentCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public ManageEquipmentCommand(KnightManager manager, Scanner scanner) {
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
        while (true) {
            System.out.println("\nМеню спорядження для " + k.getName());
            System.out.println("1) Список обладнання");
            System.out.println("2) Вилучити обладнання за іменем");
            System.out.println("3) Повернутись");
            int c = InputUtils.readIntInRange(scanner, "Вибір: ", 1, 3);
            if (c == 1) {
                if (k.getEquipment().isEmpty()) System.out.println("Нема спорядження.");
                else k.getEquipment().forEach(e -> System.out.println(" - " + e));
            } else if (c == 2) {
                String en = InputUtils.readNonEmptyString(scanner, "Введіть назву обладнання щоб видалити: ");
                boolean removed = k.removeEquipment(en);
                System.out.println(removed ? "Забрано." : "Не знайдено.");
            } else {
                break;
            }
        }
    }
}
