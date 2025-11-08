package main.game;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BattleLog {

    // збереження логу у новий файл
    public static void save(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println(" Немає бою для збереження!");
            return;
        }

        try {
            // створюємо унікальну назву файлу за поточним часом
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "battle_" + timestamp + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(text);
            }

            System.out.println(" Бій збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println(" Помилка запису файлу: " + e.getMessage());
        }
    }

    // відтворення бою (вибір файлу зі списку)
    public static void read() {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) ->
                name.startsWith("battle_") && name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println(" Немає збережених боїв!");
            return;
        }

        System.out.println("\n Доступні збережені бої ===");
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + ": " + files[i].getName());
        }

        System.out.print("Оберіть номер бою для відтворення: ");
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(" Невірне введення!");
            return;
        }

        if (choice < 0 || choice >= files.length) {
            System.out.println(" Невірний номер файлу!");
            return;
        }

        File selectedFile = files[choice];
        System.out.println("\n ВІДТВОРЕННЯ БОЮ: " + selectedFile.getName() + " ===\n");

        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(250); // легка затримка для "ефекту відтворення"
            }
        } catch (IOException e) {
            System.out.println(" Помилка зчитування файлу: " + e.getMessage());
        } catch (InterruptedException ignored) {}
    }
}
