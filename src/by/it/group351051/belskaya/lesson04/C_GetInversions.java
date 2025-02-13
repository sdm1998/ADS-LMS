package by.it.group351051.belskaya.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        result = mergeSortAndCountInversions(a, 0, n - 1);
        return result;
    }

    // Рекурсивная функция для сортировки слиянием и подсчета инверсий
    private int mergeSortAndCountInversions(int[] a, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            int leftInversions = mergeSortAndCountInversions(a, left, mid);
            int rightInversions = mergeSortAndCountInversions(a, mid + 1, right);
            int splitInversions = mergeAndCount(a, left, mid, right);
            return leftInversions + rightInversions + splitInversions;
        }
        return 0;
    }

    // Функция для слияния двух отсортированных массивов и подсчета инверсий
    private int mergeAndCount(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, count = 0;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                count += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        System.arraycopy(temp, 0, a, left, temp.length);
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/itgroup351051/belskaya/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
