package command;

import manager.KnightManager;
import model.Knight;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class CalculateTotalValueCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public CalculateTotalValueCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int idx = InputUtils.chooseKnightIndex(scanner, manager.getKnights());
        if (idx < 0) return;
        Optional<Knight> opt = manager.getByIndex(idx);
        if (opt.isEmpty()) {
            System.out.println("Нема такого лицаря.");
            return;
        }
        Knight k = opt.get();
        System.out.printf("Загальна сума спорядження для %s = %.2f\n", k.getName(), k.totalPrice());
    }
}
