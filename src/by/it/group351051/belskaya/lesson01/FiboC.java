package by.it.group351051.belskaya.lesson01;

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

        int pizanoPeriod = pizanoPeriod(m);

        long nModPeriod = n % pizanoPeriod;

        return fiboMod(nModPeriod, m);
    }

    private int pizanoPeriod(int m) {
        int a = 0, b = 1, p = 0;

        for (int i = 0; i <= m * m; i++) {
            int c = (a + b) % m;
            a = b;
            b = c;

            if (a == 0 && b == 1) {
                p = i + 1;
                break;
            }
        }
        return p;
    }

    private long fiboMod(long n, int m) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prev = 0, cur = 1, next;

        for (long i = 2; i <= n; i++) {
            next = (prev + cur) % m;
            prev = cur;
            cur = next;
        }

        return cur;
    }
}

