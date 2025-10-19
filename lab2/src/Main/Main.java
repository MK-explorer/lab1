package Main;

import Book.Book;
import java.io.*;
import java.util.*;

public class Main {
    private static final List<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Завантажити книги з файлу");
            System.out.println("2. Вивести всі книги");
            System.out.println("3. Вивести книги заданого автора");
            System.out.println("4. Вивести книги заданого видавництва");
            System.out.println("5. Вивести книги, видані після певного року");
            System.out.println("6. Додати нову книгу");
            System.out.println("7. Видалити книгу за ID");
            System.out.println("8. Зберегти у файл");
            System.out.println("9. Вихід");
            System.out.print("Оберіть опцію: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> loadFromFile();
                    case 2 -> showAll();
                    case 3 -> filterByAuthor();
                    case 4 -> filterByPublisher();
                    case 5 -> filterByYear();
                    case 6 -> addBook();
                    case 7 -> deleteBook();
                    case 8 -> saveToFile();
                    case 9 -> {
                        System.out.println("Вихід...");
                        System.exit(0);
                    }
                    default -> System.out.println("Невірний вибір, спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Введіть коректне число!");
                scanner.nextLine(); // Очищаємо буфер
            }
        }
    }

    private static void loadFromFile() {
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            books.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    int id = Integer.parseInt(data[0].trim());
                    String title = data[1].trim();
                    String author = data[2].trim();
                    String publisher = data[3].trim();
                    int year = Integer.parseInt(data[4].trim());
                    int pages = Integer.parseInt(data[5].trim());
                    double price = Double.parseDouble(data[6].trim());
                    books.add(new Book(id, title, author, publisher, year, pages, price));
                }
            }
            System.out.println(" Завантажено " + books.size() + " книг.");
        } catch (IOException e) {
            System.out.println(" Помилка читання файлу: " + e.getMessage());
        }
    }

    private static void showAll() {
        if (books.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        books.forEach(System.out::println);
    }

    private static void filterByAuthor() {
        System.out.print("Введіть автора: ");
        String author = scanner.nextLine();
        books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .forEach(System.out::println);
    }

    private static void filterByPublisher() {
        System.out.print("Введіть видавництво: ");
        String publisher = scanner.nextLine();
        books.stream()
                .filter(b -> b.getPublisher().equalsIgnoreCase(publisher))
                .forEach(System.out::println);
    }

    private static void filterByYear() {
        System.out.print("Введіть рік: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Споживаємо Enter
        books.stream()
                .filter(b -> b.getYear() > year)
                .forEach(System.out::println);
    }

    private static void addBook() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Споживаємо Enter
        System.out.print("Назва: ");
        String title = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        System.out.print("Видавництво: ");
        String publisher = scanner.nextLine();
        System.out.print("Рік: ");
        int year = scanner.nextInt();
        System.out.print("Кількість сторінок: ");
        int pages = scanner.nextInt();
        System.out.print("Ціна: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Споживаємо Enter

        books.add(new Book(id, title, author, publisher, year, pages, price));
        System.out.println(" Книгу додано!");
    }

    private static void deleteBook() {
        System.out.print("Введіть ID книги для видалення: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Споживаємо Enter
        boolean removed = books.removeIf(b -> b.getId() == id);
        if (removed) {
            System.out.println("Книгу видалено.");
        } else {
            System.out.println(" Книгу з таким ID не знайдено.");
        }
    }

    private static void saveToFile() {
        System.out.print("Введіть шлях для збереження файлу: ");
        String filePath = scanner.nextLine();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                pw.println(book.toCSV());
            }
            System.out.println(" Дані збережено у файл.");
        } catch (IOException e) {
            System.out.println(" Помилка запису: " + e.getMessage());
        }
    }
}

