package by.it.group310951.oktysyuk.lesson01;

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
        long periodPisano = periodPisano(m);

        //уменьшение n с помощью периода
        n = n % periodPisano;

        //n-ое число Фибоначчи по модулю m
        return fibMod(n, m);
    }

    long periodPisano(int m) {
        long prev = 0, curr = 1;
        for (long i = 0; i < m * m; i++) {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            //период начинается с 0, 1
            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }
        return 0;
    }

    long fibMod(long n, int m) {
        long prev = 0, curr = 1;
        for (long i = 2; i <= n; i++) {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;
        }
        return curr;
    }

}

