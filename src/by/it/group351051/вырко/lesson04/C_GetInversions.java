package by.it.group351051.вырко.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Размер массива
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Подсчет инверсий с использованием модифицированной сортировки слиянием
        return mergeSortAndCount(a, 0, n - 1);
    }

    // Метод для рекурсивной сортировки и подсчета инверсий
    private int mergeSortAndCount(int[] array, int left, int right) {
        int count = 0;

        if (left < right) {
            // Находим середину массива
            int mid = (left + right) / 2;

            // Считаем инверсии в левой и правой частях
            count += mergeSortAndCount(array, left, mid);
            count += mergeSortAndCount(array, mid + 1, right);

            // Считаем инверсии при слиянии
            count += mergeAndCount(array, left, mid, right);
        }

        return count;
    }

    // Метод для слияния двух отсортированных частей и подсчета инверсий
    private int mergeAndCount(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Создаем временные массивы для левой и правой половин
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Индексы для прохода по массивам
        int i = 0, j = 0, k = left;
        int swaps = 0;

        // Слияние двух массивов
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
                // Все оставшиеся элементы в leftArray[i..n1-1] больше, чем rightArray[j]
                swaps += (n1 - i);
            }
        }

        // Копируем оставшиеся элементы из левой части
        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        // Копируем оставшиеся элементы из правой части
        while (j < n2) {
            array[k++] = rightArray[j++];
        }

        return swaps;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
