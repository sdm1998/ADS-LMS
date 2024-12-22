package by.it.group310951.dryhencha.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];

        // Чтение значений ступенек
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        // Массив для хранения максимальных сумм для каждой ступеньки
        int[] dp = new int[n];

        // Инициализация
        dp[0] = stairs[0]; // Начальная ступенька
        if (n > 1) {
            dp[1] = Math.max(stairs[0], stairs[1]); // Максимум для первых двух ступенек
        }

        // Заполнение массива dp
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]) + stairs[i];
        }

        // Ответ - максимальная сумма на последней ступеньке
        return dp[n-1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}
