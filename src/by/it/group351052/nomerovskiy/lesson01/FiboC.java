package by.it.group351052.nomerovskiy.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {

        //возврат значений при слишком низких n
        if (n == 0) return 0;
        if (n == 1) return 1;

        final int piLimit = m*6; // предел периода Пизано
        long previous = 0;
        long current = 1;
        int pi_m = 0; //период Пизано
        long temp;

        // нахождение периода Пизано
        for (int i = 0; i < piLimit; i++) {
            temp = current;
            current = (previous + current) % m;
            previous = temp;

            // период всегда начинается с 0, 1
            if (previous == 0 && current == 1) {
                pi_m = i + 1; // цикл начинался с 0
                break;
            }
        }

        // приводим n к индексу в пределах периода Пизано
        n %= pi_m;

        // если после сокращения n = 0, возвращаем 0
        if (n <= 1) return n;

        // вычисляем n-е число Фибоначчи по модулю m
        previous = 0;
        current = 1;
        for (long i = 0; i < n - 1; i++) {
            temp = current;
            current = (previous + current) % m;
            previous = temp;
        }

        return current;
    }
}