package by.it.group351051.belsky.lesson04;

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
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!


        // Массив для временного хранения
        int[] temp = new int[n];
        // Подсчитываем инверсии с помощью модификации сортировки слиянием
        return mergeSortAndCount(a, temp, 0, n - 1);



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    // Метод сортировки слиянием с подсчетом инверсий
    private int mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            // Рекурсивно сортируем левую и правую части массива
            count += mergeSortAndCount(array, temp, left, mid);
            count += mergeSortAndCount(array, temp, mid + 1, right);

            // Сливаем отсортированные части и подсчитываем инверсии
            count += mergeAndCount(array, temp, left, mid, right);
        }
        return count;
    }

    // Метод слияния двух отсортированных частей массива с подсчетом инверсий
    private int mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        int invCount = 0;

        // Слияние двух отсортированных частей
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                invCount += (mid - i + 1);  // Все элементы с индексами от i до mid являются инверсиями с array[j]
            }
        }

        // Копируем оставшиеся элементы левой части
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // Копируем оставшиеся элементы правой части
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // Копируем отсортированный массив обратно в исходный
        System.arraycopy(temp, left, array, left, right - left + 1);

        return invCount;
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
