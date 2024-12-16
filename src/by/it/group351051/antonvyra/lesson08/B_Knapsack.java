package by.it.group351051.antonvyra.lesson08;

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

    int getMaxWeight(InputStream stream) {
        // Read input
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt(); // Maximum weight capacity of the knapsack
        int n = scanner.nextInt(); // Number of gold weights
        int[] gold = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // Initialize DP table
        int[][] dp = new int[n + 1][W + 1];

        // Solve the problem using dynamic programming
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                dp[i][w] = dp[i - 1][w]; // Exclude the current item
                if (gold[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - gold[i - 1]] + gold[i - 1]);
                }
            }
        }

        // The maximum weight that can be carried is in dp[n][W]
        return dp[n][W];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
