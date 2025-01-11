package by.it.group351052.kozhemyakin.a_khmelev.lesson01;

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
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int pisanoPeriod = getPisanoPeriod(m);


        n = n % pisanoPeriod;


        return getFibonacciMod(n, m);
    }
    private int getPisanoPeriod(int m) {
        int previous = 0;
        int current = 1;

        for (int i = 0; i < m * m; i++) {
            int temp = (previous + current) % m;
            previous = current;
            current = temp;

            // Период Пизано начинается с 0, 1
            if (previous == 0 && current == 1) {
                return i + 1;
            }
        }
        return 0;
    }

    private long getFibonacciMod(long n, int m) {
        if (n <= 1) {
            return n;
        }

        long previous = 0;
        long current = 1;

        for (long i = 2; i <= n; i++) {
            long temp = (previous + current) % m;
            previous = current;
            current = temp;
        }
        return current;


    }


}

