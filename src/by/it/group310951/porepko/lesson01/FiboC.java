package by.it.group310951.porepko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private int getPisanoPeriod(int m) {

        // вычисление длины периода последовательности Фибоначчи
        // по модулю заданного числа m
        int current = 0;
        int next = 1;
        int period = 0;
        while (true) {
            int oldNext = next;
            next = (current + next) % m;
            current = oldNext;
            period ++;
            if (current == 0 && next ==1) {
                return period;
            }
        }
    }

    long fasterC(long n, int m) {
        if (n == 0L) return 0L;
        else if (n == 1L) return 1L;
        else {

            // вычисляем период Пизано по заданному m
            int period = getPisanoPeriod(m);

            // вычисляем минимальный элемент последовательности Фибоначчи,
            // который даст тот же остаток, что и число n

            long result = n % period;

            // вычисляем значение найденного на предыдущем шаге элемента

            FiboB fibo = new FiboB();
            BigInteger fiboResult = fibo.fastB(Math.toIntExact(result));

            // получаем искомый остаток

            long mod = fiboResult.longValue() % m;

            return mod;
        }
    }
}



