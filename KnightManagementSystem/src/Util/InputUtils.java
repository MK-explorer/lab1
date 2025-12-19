package Util;

import model.Knight;

import java.util.List;
import java.util.Scanner;

public class InputUtils {

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Невірне число, спробуй ще раз.");
            }
        }
    }

    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int v = readInt(scanner, prompt);
            if (v >= min && v <= max) return v;
            System.out.printf("Число має бути від %d до %d. Спробуй ще.\n", min, max);
        }
    }

    public static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат числа (наприклад 12.5). Спробуй ще.");
            }
        }
    }

    public static double readDoubleInRange(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            double v = readDouble(scanner, prompt);
            if (v >= min && v <= max) return v;
            System.out.printf("Число має бути від %.2f до %.2f. Спробуй ще.\n", min, max);
        }
    }

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Рядок не може бути пустим. Спробуй ще.");
        }
    }

    public static int chooseKnightIndex(Scanner scanner, List<Knight> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список лицарів порожній.");
            return -1;
        }
        System.out.println("Список лицарів:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, list.get(i));
        }
        // User enters 1-based index, we convert to 0-based
        return readIntInRange(scanner, "Оберіть номер лицаря: ", 1, list.size()) - 1;
    }
}