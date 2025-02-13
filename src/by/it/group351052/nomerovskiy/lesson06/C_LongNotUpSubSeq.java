package by.it.group351052.nomerovskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        // читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // массив dp будет хранить длину наибольшей невозрастающей подпоследовательности,
        // заканчивающейся на каждом элементе
        int[] dp = new int[n];
        // массив parent для восстановления индексов
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // минимальная длина подпоследовательности для каждого элемента - 1
            parent[i] = -1; // инициализация родителей
        }

        // динамическое программирование
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }

        // находим максимальную длину невозрастающей подпоследовательности
        int maxLength = 0;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        // восстанавливаем индексы подпоследовательности
        ArrayList<Integer> indices = new ArrayList<>();
        while (maxIndex != -1) {
            indices.add(maxIndex + 1);  // добавляем индекс (сдвигаем на 1, так как индексы начинаются с 1)
            maxIndex = parent[maxIndex];
        }

        // выводим результат
        Collections.reverse(indices);  // разворачиваем, так как мы восстанавливаем индексы с конца
        System.out.println(maxLength);  // длина подпоследовательности
        for (int index : indices) {
            System.out.print(index + " ");  // индексы
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