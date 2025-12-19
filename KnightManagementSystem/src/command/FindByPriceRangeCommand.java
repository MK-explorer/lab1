package command;

import manager.KnightManager;
import model.Equipment;
import model.Knight;
import Util.InputUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FindByPriceRangeCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public FindByPriceRangeCommand(KnightManager manager, Scanner scanner) {
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
        double low = InputUtils.readDoubleInRange(scanner, "Найнижча ціна: ", 0, Double.MAX_VALUE);

        double high = low - 1;
        while (high < low) {
            high = InputUtils.readDoubleInRange(scanner, "Найвища ціна: ", 0, Double.MAX_VALUE);
            if (high < low) {
                System.out.println(" Найвища ціна має бути >= Найнижчу ціну. Спробуй ще.");
            }
        }

        List<Equipment> found = k.findByPriceRange(low, high);
        if (found.isEmpty()) System.out.println("Нема предметів в такому діапазоні.");
        else {
            System.out.println("Знайдено:");
            found.forEach(e -> System.out.println(" - " + e));
        }
    }
}