package Book;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int pages;
    private double price;

    // Конструктор
    public Book(int id, String title, String author, String publisher, int year, int pages, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }

    // Геттери
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    // Метод для форматування книги у CSV
    public String toCSV() {
        return id + "," + title + "," + author + "," + publisher + "," + year + "," + pages + "," + price;
    }

    // Вивід книги у зрозумілому форматі
    @Override
    public String toString() {
        return id + ": " + title + " (" + author + ", " + publisher + ", " + year + ", " + pages + " сторінок, " + price + " грн)";
    }
}
