package command;

import manager.KnightManager;
import model.Knight;
import Util.InputUtils;

import java.util.Optional;
import java.util.Scanner;

public class SelectKnightCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    // This command simply lets user choose and prints chosen
    public SelectKnightCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int idx = InputUtils.chooseKnightIndex(scanner, manager.getKnights());
        if (idx < 0) return;
        Optional<Knight> opt = manager.getByIndex(idx);
        if (opt.isPresent()) {
            System.out.println("Обрано: " + opt.get());
        } else {
            System.out.println("Помилка вибору.");
        }
    }
}
