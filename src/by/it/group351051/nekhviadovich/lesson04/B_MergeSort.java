package by.it.group351051.nekhviadovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = mergeSort(a);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    int[] mergeSort(int[] arr) {//это основной рекурсивный метод
        if (arr.length<2) {
            return arr;
        }
        int middlePoint = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, middlePoint);
        int[] rightArr = Arrays.copyOfRange(arr, middlePoint, arr.length);

        leftArr = mergeSort(leftArr);
        rightArr = mergeSort(rightArr);



        return merge(leftArr, rightArr);
    }

    private static int[] merge(int[] left, int[] right) {
        int n = 0, m = 0, k = 0; //тут счетчики
        int[] result = new int[left.length + right.length];

        // сравнение элементов и добавление в итоговый массив
        while (n < left.length && m < right.length) {
            if (left[n] <= right[m]) {
                result[k] = left[n];
                n++;
            } else {
                result[k] = right[m];
                m++;
            }
            k++;
        }

        // добавляем оставшиеся элементы из левого массива, если есть
        while (n < left.length) {
            result[k] = left[n];
            n++;
            k++;
        }

        // оставшиеся элементы из правого массива, если есть
        while (m < right.length) {
            result[k] = right[m];
            m++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
