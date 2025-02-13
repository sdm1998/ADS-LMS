package by.it.group351051.burdo.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
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
        // массив для хранения длин LIS
        // lisLength[i] хранит длину наибольшей возрастающей подпоследовательности, которая заканчивается на элементе A[i]
        int[] lisLength = new int[n];
        // изначально все элементы LIS имеют длину 1
        for (int i = 0; i < n; i++) {
            lisLength[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            int lastDividableIndex = 0;
            for (int j = 0; j < i; j++) {
                if (m[i] % m[j]  == 0 && lisLength[i] < lisLength[j] + 1) {
                    //  m[j] % m[i] значит, что мы можем продолжить возрастающую подпоследовательность.
                    // lisLength[i] < lisLength[j] + 1 это значит, что текущая длина для m[i] увеличится.

                    lisLength[i] = lisLength[j] + 1;
                }
            }
        }
        int result = 0;
        // ищем максимальную длину кратной подпоследовательности
        for (int i = 0; i < n; i++) {
            result = Math.max(result, lisLength[i]);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}