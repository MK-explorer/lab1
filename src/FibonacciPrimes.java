

import java.util.Scanner;

/**
 * Клас, який зберігає індекс і значення числа Фібоначчі
 * та дозволяє перевірити, чи є число простим.
 */
   class SequenceNumber {
    public int index;
    public long value;

    /**
     * Створює об’єкт fibonacci.SequenceNumber із заданим індексом і значенням числа Фібоначчі.
     * @param index індекс числа Фібоначчі (починаючи з 1)
     * @param value значення числа Фібоначчі
     */
    public SequenceNumber(int index, long value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Повертає індекс числа Фібоначчі.
     * @return індекс числа
     */
    public int getIndex() {
        return index;
    }

    /**
     * Повертає значення числа Фібоначчі.
     * @return значення числа
     */
    public long getValue() {
        return value;
    }

    /**
     * Перевіряє, чи є число Фібоначчі простим.
     * @return true, якщо число просте, false в іншому випадку
     */
    public boolean isPrime() {
        if (value < 2) return false;
        for (long i = 2; i * i <= value; i++) {
            if (value % i == 0) return false;
        }
        return true;
    }
}

/**
 * Програма для визначення простих чисел серед перших N чисел Фібоначчі.
 * Зчитує кількість чисел від користувача через командний рядок або клавіатуру
 * та виводить результати перевірки на простоту.
 */
public class FibonacciPrimes {
    /**
     * Головний метод програми для обчислення чисел Фібоначчі та перевірки їх на простоту.
     * @param args аргументи командного рядка, де args[0] може містити кількість чисел N
     */
    public static void main(String[] args) {
        int N;
        Scanner scanner = new Scanner(System.in);

        // Перевірка, чи передано аргумент командного рядка
        if (args.length > 0) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат аргументу командного рядка. Введіть ціле число через клавіатуру:");
                System.out.print("Введіть кількість чисел Фібоначчі: ");
                N = scanner.nextInt();
            }
        } else {
            System.out.print("Введіть кількість чисел Фібоначчі: ");
            N = scanner.nextInt();
        }

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

        int primeCount = 0;
        System.out.println("Перші " + N + " чисел Фібоначчі:");
        for (SequenceNumber num : numbers) {
            boolean prime = num.isPrime();
            if (prime) primeCount++;
            System.out.printf("Індекс %d: %d -> %s\n", num.getIndex(), num.getValue(), prime ? "Просте" : "Не просте");
        }

        System.out.println("Кількість простих чисел: " + primeCount);
    }
}