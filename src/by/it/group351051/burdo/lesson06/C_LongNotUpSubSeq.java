package by.it.group351051.burdo.lesson06;

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
        // массив для отслеживания предшественников каждого элемента в последовательности
        // (позволяет восстановить индексы результата).
        int[] prev = new int[n];
        // массив, где хранятся индексы элементов, заканчивающих подпоследовательности различной длины.
        int[] indices = new int[n];
        int length = 0; // длина наибольшей подпоследовательности

        // заполняем массивы
        for (int i = 0; i < n; i++) {
            prev[i] = -1; // изначально нет предшественников

            // если текущий элемент m[i] меньше или равен какому-то элементу из indices,
            // его можно использовать для обновления конца соответствующей подпоследовательности

            // если текущий элемент больше всех элементов в текущей последовательности концов,
            // он начинает новую подпоследовательность большей длины
            int pos = binarySearch(indices, m[i], length, m);

            // если позиция pos > 0, то обновляем массив prev.
            // указываем, что предшественником элемента m[i] будет элемент, стоящий в позиции pos - 1
            if (pos > 0) {
                prev[i] = indices[pos - 1];
            }

            //  текущий элемент становится концом подпоследовательности длиной pos + 1
            indices[pos] = i; //сохраняем индекс текущего элемента

            // если новая длина превышает текущую, увеличиваем её
            if (pos == length) {
                length++;
            }
        }

        // восстановление индексов
        int[] resultIndices = new int[length];
        int index = indices[length - 1];
        //  начинаем восстановление с последнего элемента найденной подпоследовательности
        for (int i = length - 1; i >= 0; i--) {
            resultIndices[i] = index + 1; // индексы начинаются с 1
            index = prev[index];
        }

        // выводим результат
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.print(resultIndices[i] + " ");
        }

        return length;
    }

    // бинарный поиск для нахождения позиции элемента в indexes с учетом длины последовательности
    private int binarySearch(int[] indexes, int value, int length, int[] m) {
        int low = 0, high = length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (m[indexes[mid]] >= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson06/dataCTest.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
    }

}