package by.it.group351052.porepko.lesson01;

import java.util.List;
import java.util.ArrayList;

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
    public long fasterC(long n, int m) {
        if (n <= 1) return n;

        List<Long> pisanoPeriod = getPisanoPeriod(m);
        int periodLength = pisanoPeriod.size();

        long remainder = n % periodLength;

        return pisanoPeriod.get((int) remainder);
    }

    private List<Long> getPisanoPeriod(int m) {
        List<Long> pisanoPeriod = new ArrayList<>();
        pisanoPeriod.add(0L);
        pisanoPeriod.add(1L);

        for (int i = 2; i < m * m; i++) {
            long next = (pisanoPeriod.get(i - 1) + pisanoPeriod.get(i - 2)) % m;
            if (next == 1 && pisanoPeriod.get(i - 1) == 0) {
                pisanoPeriod.remove(pisanoPeriod.size() - 1);
                break;
            }
            pisanoPeriod.add(next);
        }

        return pisanoPeriod;
    }
    public static void main(String[] args) {
        FiboC fibo = new FiboC();

        int n = 10;
        int m = 5;
        System.out.printf("getFibonacciModul(%d, %d)=%d \n\t time=%d \n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }
}







