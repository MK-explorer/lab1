package main.game;

import main.droids.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Droid> droids = new ArrayList<>();
    private static String lastBattle = "";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n МЕНЮ");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Додати предмет дроїду");
            System.out.println("4. Бій 1 на 1");
            System.out.println("5. Бій команда на команду");
            System.out.println("6. Зберегти бій у файл");
            System.out.println("7. Відтворити бій");
            System.out.println("8. Вихід");
            System.out.print("Ваш вибір: ");

            int ch = getIntInput(1, 8);

            switch (ch) {
                case 1 -> createDroid();
                case 2 -> showDroids();
                case 3 -> addItemToDroid();
                case 4 -> fightOneOnOne();
                case 5 -> fightTeamVsTeam();
                case 6 -> BattleLog.save(lastBattle);
                case 7 -> BattleLog.read();
                case 8 -> {
                    System.out.println("Вихід...");
                    return;
                }
            }
        }
    }

    private static void createDroid() {
        String name = getStringInput("Введіть ім'я дроїда: ");

        System.out.println("Тип дроїда: 1 - Battle, 2 - Shield");
        int type = getIntInput(1, 2);

        Droid droid = (type == 1) ? new BattleDroid(name) : new ShieldDroid(name);
        droids.add(droid);
        System.out.println(" Створено: " + droid);
    }

    private static void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("️ Список дроїдів порожній ");
        } else {
            System.out.println("Список дроїдів");
            for (int i = 0; i < droids.size(); i++) {
                System.out.println(i + ". " + droids.get(i));
            }
        }
    }

    private static void addItemToDroid() {
        if (droids.isEmpty()) {
            System.out.println(" Поки немає дроїдів!");
            return;
        }

        showDroids();
        System.out.print("Оберіть дроїда: ");
        int i = getIntInput(0, droids.size() - 1);

        String name = getStringInput("Назва предмета: ");
        System.out.print("Бонус до атаки: ");
        int dmg = getIntInput(0, Integer.MAX_VALUE);

        Item item = new Item(name, dmg);
        droids.get(i).addItem(item);
        System.out.println(" Предмет додано до " + droids.get(i).getName());
    }

    private static void fightOneOnOne() {
        if (droids.size() < 2) {
            System.out.println(" Потрібно хоча б два дроїди!");
            return;
        }

        showDroids();
        System.out.print("Оберіть 1-го дроїда: ");
        int i1 = getIntInput(0, droids.size() - 1);
        System.out.print("Оберіть 2-го дроїда: ");
        int i2 = getIntInput(0, droids.size() - 1);

        if (i1 == i2) {
            System.out.println(" Не можна обрати одного дроїда двічі!");
            return;
        }

        Battle battle = new Battle();
        battle.fightOneOnOne(droids.get(i1), droids.get(i2));
        lastBattle = battle.getLog();
    }

    //  командний бій
    private static void fightTeamVsTeam() {
        if (droids.size() < 4) {
            System.out.println("️ Потрібно хоча б 4 дроїди (по 2 на команду)!");
            return;
        }

        showDroids();
        System.out.print("Скільки дроїдів у кожній команді? ");
        int teamSize = getIntInput(1, droids.size() / 2);

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("\n Команда 1 ");
        for (int i = 0; i < teamSize; i++) {
            System.out.print("Оберіть дроїда #" + (i + 1) + ": ");
            team1.add(droids.get(getIntInput(0, droids.size() - 1)));
        }

        System.out.println("\n Команда 2 ");
        for (int i = 0; i < teamSize; i++) {
            System.out.print("Оберіть дроїда #" + (i + 1) + ": ");
            team2.add(droids.get(getIntInput(0, droids.size() - 1)));
        }

        Battle battle = new Battle();
        battle.fightTeamVsTeam(team1, team2);
        team1.forEach(Droid::restoreHealth);
        team2.forEach(Droid::restoreHealth);
        lastBattle = battle.getLog();
    }

    //  Безпечне зчитування числа в межах
    private static int getIntInput(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine(); // очищення буфера
                if (num >= min && num <= max) return num;
                else System.out.print("Введіть число від " + min + " до " + max + ": ");
            } else {
                System.out.print("️ Це не число! Введіть число: ");
                scanner.nextLine(); // очищення неправильного вводу
            }
        }
    }

    //  Безпечне зчитування непорожнього тексту
    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println(" Введення не може бути порожнім!");
        }
    }
}
