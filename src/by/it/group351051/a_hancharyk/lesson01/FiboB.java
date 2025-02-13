package by.it.group351051.a_hancharyk.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    public BigInteger getFibonacciByIndexRecursive(int n, BigInteger[] memo) {
        if (n == 0 || n == 1) {
            memo[n] = BigInteger.valueOf(n);
            return memo[n];
        }
        if (memo[n] == null) {
            BigInteger res = getFibonacciByIndexRecursive(n - 2, memo).add(getFibonacciByIndexRecursive(n - 1, memo));
            memo[n] = res;
        }
        return memo[n];
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] cache = new BigInteger[2];
        cache[0] = BigInteger.ZERO;
        cache[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            cache[i%2] = cache[0].add(cache[1]);
            //При i=2 устареет 0-й элемент
            //При i=3 в 0 будет свежий элемент (обновили его на предыдущей итерации), а в 1 -- ещё старый
            //При i=4 последним элементом мы обновляли cache[1], значит ненужное старьё сейчас в cache[0]
            //Интуитивно понятно, что так будет продолжаться и дальше
        }

            return  cache[n%2];
    }

}

