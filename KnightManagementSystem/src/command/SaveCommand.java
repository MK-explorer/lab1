package command;

import manager.FileManager;
import manager.KnightManager;

import java.util.Scanner;

public class SaveCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public SaveCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть шлях файлу щоб зберегти (or leave empty to auto-generate in ./data/): ");
        String path = scanner.nextLine().trim();
        try {
            if (path.isEmpty()) {
                String saved = FileManager.saveKnightsToRandomFile(manager.getKnights());
                System.out.println("Saved to " + saved);
            } else {
                FileManager.saveKnightsToFile(manager.getKnights(), path);
                System.out.println("Saved to " + path);
            }
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
