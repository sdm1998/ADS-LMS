package by.it.group310951.porepko.lesson04;

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
        int result = countInversions(a, 0, n - 1); // Подсчёт инверсий
        return result;
    }

    // рекурсия для подсчёта инверсий
    private int countInversions(int[] a, int left, int right) {
        int inversions = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inversions += countInversions(a, left, mid); // инверсии в левой части
            inversions += countInversions(a, mid + 1, right); // инверсии в правой части
            inversions += mergeCount(a, left, mid, right); // инверсии при слиянии
        }
        return inversions;
    }

    // Метод для слияния двух половин и подсчёта инверсий
    private int mergeCount(int[] a, int left, int mid, int right) {

        int[] temp = new int[right - left + 1]; // создаем временный массив
        int i = left; // индекс для левой части
        int j = mid + 1; // индекс для правой части
        int k = 0; // индекс для временного массива
        int inversions = 0; // счётчик инверсий

        // слияние двух частей
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                inversions += (mid - i + 1);
            }
        }

        // копирование оставшихся элементов левой части
        while (i <= mid) {
            temp[k++] = a[i++];
        }

        // копирование оставшихся элементов правой части
        while (j <= right) {
            temp[k++] = a[j++];
        }

        // копирование временного массива в основной
        for (i = left, k = 0; i <= right; i++, k++) {
            a[i] = temp[k];
        }

        return inversions;
    }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}
