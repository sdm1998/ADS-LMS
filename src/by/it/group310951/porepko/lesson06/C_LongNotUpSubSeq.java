package by.it.group310951.porepko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }


        //тут реализуйте логику задачи методами динамического программирования

        // массив для хранения длины наибольшей невозрастающей подпоследовательности
        int[] dp1 = new int[n];

        // массив для восстановления индексов
        int[] pred = new int[n];

        // инициализация массива dp и pred
        for (int i = 0; i < n; i++) {
            dp1[i] = 1;  // каждое число - подпоследовательность длины равной 1
            pred[i] = -1; // предыдущего элемента нет
        }

        // динамическое программирование для вычисления наибольшей невозрастающей подпоследовательности
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j] && dp1[i] < dp1[j] + 1) {
                    dp1[i] = dp1[j] + 1;
                    pred[i] = j;  // индекс предыдущего элемента
                }
            }
        }

        // найдем максимальную длину
        int maxLength = 0;
        int lastIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp1[i] > maxLength) {
                maxLength = dp1[i];
                lastIndex = i;
            }
        }

        // восстанавливаем индексы
        int[] resultIndices = new int[maxLength];
        int index = maxLength - 1;
        while (lastIndex != -1) {
            resultIndices[index--] = lastIndex + 1; // добавляем индекс, начиная с 1
            lastIndex = pred[lastIndex]; // переходим к предыдущему элементу
        }

        // выводим результат
        System.out.println(maxLength);
        for (int i = 0; i < maxLength; i++) {
            System.out.print(resultIndices[i] + " ");
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

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