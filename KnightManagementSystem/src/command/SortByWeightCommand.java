
package command;

import manager.KnightManager;
import model.Equipment;
import model.Knight;
import Util.InputUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SortByWeightCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public SortByWeightCommand(KnightManager manager, Scanner scanner) {
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

        //  перевірка чи є спорядження
        List<Equipment> equipment = k.getEquipment();
        if (equipment.isEmpty()) {
            System.out.println(" У лицаря " + k.getName() + " немає спорядження для сортування.");
            return;
        }

        System.out.println("\n ДО СОРТУВАННЯ:");
        equipment.forEach(e -> System.out.printf("  - %s (вага: %.2f kg)\n", e.getName(), e.getWeight()));

        // Сортуємо
        k.sortEquipmentByWeight();

        System.out.println("\n ПІСЛЯ СОРТУВАННЯ (від легких до важких):");
        equipment.forEach(e -> System.out.printf("  - %s (вага: %.2f kg)\n", e.getName(), e.getWeight()));
    }
}