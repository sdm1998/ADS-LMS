package by.it.group310951.dryhencha.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt(); // вместимость рюкзака
        int n = scanner.nextInt(); // количество слитков
        int[] gold = new int[n];

        // Чтение весов слитков
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // Массив для хранения максимального веса для каждой вместимости
        int[] dp = new int[W + 1];

        // Динамическое программирование
        for (int i = 0; i < n; i++) {
            // Обновляем массив dp для всех возможных весов рюкзака от W до gold[i]
            for (int j = W; j >= gold[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - gold[i]] + gold[i]);
            }
        }

        // Ответ - максимальный вес, который можно унести в рюкзаке
        return dp[W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
