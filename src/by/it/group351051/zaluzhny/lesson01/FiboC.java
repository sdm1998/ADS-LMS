package by.it.group351051.zaluzhny.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        // 1. Находим период Пизано для m
        long pisanoPeriod = pisanoPeriod(m);

        // 2. Вычисляем n по модулю длины периода
        n = n % pisanoPeriod;

        // 3. Вычисляем n-е число Фибоначчи по модулю m
        return fibonacciMod(n, m);
    }

    // Функция для нахождения периода Пизано
    private long pisanoPeriod(int m) {
        int previous = 0;
        int current = 1;

        for (int i = 0; i < m * m; i++) {
            int temp = current;
            current = (previous + current) % m;
            previous = temp;

            // Период всегда начинается с 0, 1
            if (previous == 0 && current == 1) {
                return i + 1; // Период найден
            }
        }

        return 0; // Это не должно произойти
    }

    private long fibonacciMod(long n, int m) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long previous = 0;
        long current = 1;

        for (long i = 2; i <= n; i++) {
            long temp = current;
            current = (previous + current) % m;
            previous = temp;
        }

        return current;
    }
}

