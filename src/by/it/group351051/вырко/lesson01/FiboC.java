package by.it.group351051.вырко.lesson01;

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
        if (n<=1) return n;
        int pisanoPeriod = findPisanoPeriod(m);
        long reducedN = n%pisanoPeriod;
        return fiboMod(reducedN,m);
    }

    private int findPisanoPeriod(int m){
        int previous = 0;
        int current = 1;
        for (int i=0; i<m*m; i++){
            int temp = (previous+current)%m;
            previous = current;
            current = temp;

            if (previous==0 && current==1){
                return i+1;
            }
        }
        return 1;
    }

    private long fiboMod(long n, int m){
        if (n<=1) return n;

        long prev = 0;
        long curr = 1;
        for (long i=2; i<=n; i++){
            long temp = (prev+curr) % m;
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}

