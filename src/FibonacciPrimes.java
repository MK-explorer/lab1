import java.util.Scanner;

/**
 * Клас, який зберігає номер та значення числа Фібоначчі
 * та дозволяє перевірити його на простоту потім геттери.
 */
class SequenceNumber {
    private int index;
    private long value;

    public SequenceNumber(int index, long value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public long getValue() {
        return value;
    }

    public boolean isPrime() {
        if (value < 2) return false;
        for (long i = 2; i * i <= value; i++) {
            if (value % i == 0) return false;
        }
        return true;
    }
}
/**
Юзаємо сканер щоб зчитувати і виводити дані.


 */
public class FibonacciPrimes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість чисел Фібоначчі: ");
        int N = scanner.nextInt();

       if (N <= 0) {
           System.out.println("N має бути більше 0.");
           return;
        }

        SequenceNumber[] numbers = new SequenceNumber[N];
        long a = 1, b = 1;

        for (int i = 0; i < N; i++) {
            long value = (i < 2) ? 1 : a + b;
            if (i >= 2) {
                a = b;
                b = value;
            }
            numbers[i] = new SequenceNumber(i + 1, value);
        }

/**
Лічильник для prime чисел
 */
        int primeCount = 0;
        System.out.println("Перші " + N + " чисел Фібоначчі:");
        for (SequenceNumber num : numbers) {
            boolean prime = num.isPrime();
            if (prime) primeCount++;
            System.out.printf("Індекс %d: %d -> %s\n",
                    num.getIndex(), num.getValue(), prime ? "Просте" : "Не просте");
        }

        System.out.println("Кількість простих чисел: " + primeCount);
    }
}
