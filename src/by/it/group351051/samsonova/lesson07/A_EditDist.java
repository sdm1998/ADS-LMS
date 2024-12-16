package by.it.group351051.samsonova.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_EditDist {

    int getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();

        int[][] dp = new int[n + 1][m + 1];

        //баз случаи
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        //основной алгоритм
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Символы совпадают
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]), // Удаление или вставка
                            dp[i - 1][j - 1] // Замена
                    ) + 1;
                }
            }
        }

        //возвращаем рез из ячейки
        return dp[n][m];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);

        // Выводим результаты для всех пар строк из файла
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine())); // Пара 1
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine())); // Пара 2
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine())); // Пара 3
    }
}
