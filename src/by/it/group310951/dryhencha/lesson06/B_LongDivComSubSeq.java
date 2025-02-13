package by.it.group310951.dryhencha.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // Читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Массив для хранения длины наибольшей кратной подпоследовательности
        int[] dp = new int[n];

        // Инициализация массива dp
        for (int i = 0; i < n; i++) {
            dp[i] = 1;  // Каждое число само по себе — это подпоследовательность длины 1
        }

        // Динамическое программирование для вычисления наибольшей кратной подпоследовательности
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] % m[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Ответ - максимальная длина кратной подпоследовательности
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }
}
