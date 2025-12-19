
package command;

import manager.FileManager;
import manager.KnightManager;
import Util.InputUtils;

import java.util.List;
import java.util.Scanner;

import model.Knight;

public class LoadCommand implements Command {
    private final KnightManager manager;
    private final Scanner scanner;

    public LoadCommand(KnightManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть назву файлу (або шлях) щоб завантажити (або залиште порожнім щоб скасувати): ");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) {
            System.out.println("Завантаження скасовано.");
            return;
        }

        // Якщо шлях не містить /, то додаємо data/
        if (!path.contains("/") && !path.contains("\\")) {
            path = "data/" + path;
        }

        try {
            List<Knight> loaded = FileManager.loadKnightsFromFile(path);


            System.out.println("1) Видалити старих");
            System.out.println("2) Додати до вже існуючих");
            int choice = InputUtils.readIntInRange(scanner, "Вибір: ", 1, 2);

            if (choice == 1) {
                manager.clear();
                System.out.println("🗑Старих лицарів вилучено.");
            } else {
                System.out.println("Додаємо до існуючих лицарів.");
            }

            for (Knight k : loaded) manager.addKnight(k);
            System.out.println(" Завантажено " + loaded.size() + "лицарів з" + path);
            System.out.println("Загальна кількість лицарів: " + manager.getKnights().size());

        } catch (Exception e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
    }
}