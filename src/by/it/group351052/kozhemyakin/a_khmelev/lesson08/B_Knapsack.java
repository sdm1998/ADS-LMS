package by.it.group351052.kozhemyakin.a_khmelev.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9
*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();  // вместимость рюкзака
        int n = scanner.nextInt();  // число золотых слитков
        int[] gold = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // dp[j] будет хранить максимальный вес, который можно набрать при вместимости j
        // каждый слиток можно использовать не более одного раза
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            int weight = gold[i];
            // идём от W вниз до weight, чтобы не использовать один предмет более одного раза
            for (int j = W; j >= weight; j--) {
                int candidate = dp[j - weight] + weight;
                if (candidate > dp[j]) {
                    dp[j] = candidate;
                }
            }
        }

        int result = dp[W];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
