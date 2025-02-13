package by.it.group351051.a_hancharyk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

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


        ArrayList<Long> fib= new ArrayList<>();

        fib.add(0L);
        fib.add(1L);
        long t = 0;

        // for (long long j = 2; j < n; ++j) {
        for (long j = (long)2; j < m*m+1; ++j) {

            long temp = fib.get((int)(j - (long)1));
           long temp1 = fib.get((int)(j - (long)2));
            fib.add( (temp + temp1) % m);
            t++;//t = t + 1
            if ((fib.get((int)j) == 1) && (fib.get((int)(j - 1)) ==0)) break;
        }

       return  fib.get((int)(n % t)) ;


        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        //return 0L;
    }


}

