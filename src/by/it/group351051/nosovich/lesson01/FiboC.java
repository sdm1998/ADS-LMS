package by.it.group351051.nosovich.lesson01;

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
        int n = 7704;
        int m = 485;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        // Нахождение периода Пизано
        long pisanoPeriod = pisanoPeriodLength(m);
        // Нахождение остатка от деления n на длину периода для сокращения вычислений
        n = n % pisanoPeriod;
        // Нахождение n-го числа Фибоначчи по модулю m
        return fibonacciMod(n, m);
    }

    private long pisanoPeriodLength(int m) {
        long previous = 0;
        long current = 1;
        for (long i = 0; i < m * m; i++) {
            long temp = current; // Временное = текущее
            current = (previous + current) % m; //Текущее равно остаток от деления (пред. + тек.) на m
            previous = temp; // Предыдущее = временное (текущее до расчетов)

            // Период заканчивается, когда значение предыдущего = 0 и текущего = 1
            // В случае с m = 485 при достижении current 486 previous = 0, а current = 1
            if (previous == 0 && current == 1) {
                return i + 1;
            }
        }
        return -1; // Не должно быть достигнуто
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
