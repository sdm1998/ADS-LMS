package by.it.group310951.dryhencha.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // Читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Массив для хранения длины наибольшей невозрастающей подпоследовательности
        int[] dp = new int[n];
        // Массив для восстановления индексов
        int[] prev = new int[n];

        // Инициализация массива dp и prev
        for (int i = 0; i < n; i++) {
            dp[i] = 1;  // Каждое число само по себе — это подпоследовательность длины 1
            prev[i] = -1; // Указываем, что нет предыдущего элемента
        }

        // Динамическое программирование для вычисления наибольшей невозрастающей подпоследовательности
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;  // Запоминаем индекс предыдущего элемента
                }
            }
        }

        // Находим максимальную длину
        int maxLength = 0;
        int lastIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        // Восстанавливаем индексы для вывода
        int[] resultIndices = new int[maxLength];
        int index = maxLength - 1;
        while (lastIndex != -1) {
            resultIndices[index--] = lastIndex + 1; // Добавляем индекс, начиная с 1
            lastIndex = prev[lastIndex]; // Переходим к предыдущему элементу
        }

        // Выводим результат
        System.out.println(maxLength);
        for (int i = 0; i < maxLength; i++) {
            System.out.print(resultIndices[i] + " ");
        }
        return maxLength;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}
