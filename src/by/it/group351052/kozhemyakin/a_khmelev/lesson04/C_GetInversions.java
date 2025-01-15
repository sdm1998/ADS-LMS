package by.it.group351052.kozhemyakin.a_khmelev.lesson04;

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

Sample Input:
5
2 3 9 2 9
Sample Output:
2
*/

public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Используем модифицированный merge sort для подсчета инверсий
        int result = mergeSortAndCount(a, 0, n - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    // Реализация сортировки слиянием и параллельного подсчета инверсий
    private int mergeSortAndCount(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int inversionsLeft = mergeSortAndCount(arr, left, mid);
        int inversionsRight = mergeSortAndCount(arr, mid + 1, right);
        int inversionsMerge = mergeAndCount(arr, left, mid, right);
        return inversionsLeft + inversionsRight + inversionsMerge;
    }

    // Слияние двух отсортированных частей и подсчет "перекрестных" инверсий
    private int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;       // указатель левой половины
        int j = mid + 1;    // указатель правой половины
        int k = 0;          // указатель во временном массиве
        int inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // все элементы с i до mid больше arr[j], значит инверсии
                inversions += (mid - i + 1);
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // копируем обратно в исходный массив
        for (int t = 0; t < temp.length; t++) {
            arr[left + t] = temp[t];
        }
        return inversions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}
