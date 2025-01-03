package by.it.group310951.porepko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!



        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();

        }

        // тут ваше решение (реализуйте сортировку слиянием)

        // вызов функции сортировки
        mergeSort(a, 0, n - 1);


        return a;
    }

    // реализация сортировки слиянием
    void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // сортировка  правой и левой частей
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // слияние частей
            merge(array, left, middle, right);
        }
    }

    void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] Left = new int[n1];
        int[] Right = new int[n2];

        // копирование данных во временные массивы
        for (int i = 0; i < n1; i++) {
            Left[i] = array[left + i];
        }
        for (int i = 0; i < n2; i++) {
            Right[i] = array[middle + 1 + i];
        }

        // индексы текущих элементов для Left, Right и основного массива
        int i = 0, j = 0, k = left;

        // cлияние временных массивов в основной
        while (i < n1 && j < n2) {
            if (Left[i] <= Right[j]) {
                array[k] = Left[i];
                i++;
            } else {
                array[k] = Right[j];
                j++;
            }
            k++;
        }

        // копируем оставшиеся элементы Left, если есть
        while (i < n1) {
            array[k] = Left[i];
            i++;
            k++;
        }

        // копируем оставшиеся элементы Right, если есть
        while (j < n2) {
            array[k] = Right[j];
            j++;
            k++;
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result=instance.getMergeSort(stream);

        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
