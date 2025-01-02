package by.it.group310951.porepko.lesson01;

import java.math.BigInteger;
import java.util.Objects;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n < 2) return n;

        return calc(n - 1) + calc (n -2);
    }





        private BigInteger calcBigInt(BigInteger n) {
            // вариант без ограничения на размер числа, в котором код совпадает с мат.определением чисел Фибоначчи
            //время O(2^n)

            if (Objects.equals(n, BigInteger.ZERO)) return BigInteger.ZERO;
            if (Objects.equals(n, BigInteger.ONE)) return BigInteger.ONE;

            return calcBigInt(n.subtract(BigInteger.ONE)).add(calcBigInt(n.subtract(BigInteger.TWO)));
        }

    BigInteger slowA(Integer n) {
        FiboA fibo = new FiboA();

        return fibo.calcBigInt(BigInteger.valueOf(n));
    }

}

