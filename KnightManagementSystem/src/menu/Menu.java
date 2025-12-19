
package menu;

import command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, Command> commands = new HashMap<>();
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void register(int id, Command cmd) {
        commands.put(id, cmd);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Виберіть опцію: ");

            String line = scanner.nextLine().trim();


            if (line.isEmpty()) {
                System.out.println(" Введи число від 1 до 15.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(" Невірне число. Спробуй ще.");
                continue;
            }

            if (choice < 1 || choice > 15) {
                System.out.println(" Вибір має бути від 1 до 15.");
                continue;
            }

            if (choice == 15) {
                System.out.println(" Вихід з програми!");
                running = false;
                break;
            }

            Command cmd = commands.get(choice);
            if (cmd != null) {
                cmd.execute();
            } else {
                System.out.println(" Нема такої опції.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n Knight Management System");
        System.out.println("1. Створити лицаря");
        System.out.println("2. Список лицарів");
        System.out.println("3. Вибрати лицаря (суто для дисплею)");
        System.out.println("4. Подивитися характеристики лицаря");
        System.out.println("5. Додати предмет до лицаря");
        System.out.println("6. Керування спорядженням (список/видалити)");
        System.out.println("7. Сортувати спорядження лицарів за вагою");
        System.out.println("8. Знайти предмет за ціновим діапазоном");
        System.out.println("9. Керування конем");
        System.out.println("10. Керування зброєносцем");
        System.out.println("11. Підсумувати загальну ціну спорядження");
        System.out.println("12. Підсумувати загальну вагу лицаря");
        System.out.println("13. Зберегти до файлу (auto-random name or specify path)");
        System.out.println("14. Завантажити з файлу (specify path)");
        System.out.println("15. Вихід");
        System.out.println("====================================");
    }
}