package command;

import manager.KnightManager;
import model.Equipment;
import model.Knight;
import Util.InputUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ViewKnightStatsCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public ViewKnightStatsCommand(KnightManager manager, Scanner scanner) {
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
        System.out.println(k);
        System.out.println("Кінь: " + (k.getHorse() == null ? "Нема" : k.getHorse()));
        System.out.println("Зброєносець: " + (k.getSquire() == null ? "Нема" : k.getSquire()));
        List<Equipment> eq = k.getEquipment();
        System.out.println("Спорядження:");
        if (eq.isEmpty()) {
            System.out.println("  (нема спорядження)");
        } else {
            for (Equipment e : eq) {
                System.out.println("  - " + e);
            }
        }
        System.out.printf("Загальна вага: %.2f kg\n", k.totalWeight());
        System.out.printf("Загальна сума: %.2f\n", k.totalPrice());
    }
}
