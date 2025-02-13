package by.it.group351051.belskaya.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    private void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);

            merge(a, left, mid, right);
        }
    }

    private void merge(int[] a, int left, int mid, int right) {
        int sizeLeftArr = mid - left + 1;
        int sizeRightArr = right - mid;

        int[] leftArray = new int[sizeLeftArr];
        int[] rightArray = new int[sizeRightArr];

        // Копируем данные в временные массивы
        System.arraycopy(a, left, leftArray, 0, sizeLeftArr);
        for (int j = 0; j < sizeRightArr; ++j) {
            rightArray[j] = a[mid + 1 + j];
        }

        // Индексы для слияния
        int i = 0, j = 0;
        int k = left;
        while (i < sizeLeftArr && j < sizeRightArr) {
            if (leftArray[i] <= rightArray[j]) {
                a[k] = leftArray[i];
                i++;
            } else {
                a[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы левого подмассива, если есть
        while (i < sizeLeftArr) {
            a[k] = leftArray[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы правого подмассива, если есть
        while (j < sizeRightArr) {
            a[k] = rightArray[j];
            j++;
            k++;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        mergeSort(a, 0, n - 1);
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/itgroup351051/belskaya/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
