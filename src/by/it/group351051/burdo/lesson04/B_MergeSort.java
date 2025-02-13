package by.it.group351051.burdo.lesson04;

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
    public static void merge(int[] array, int startIndex, int centerIndex, int endIndex) {
        // задаем смещение для левой и правой половины
        int firstShift = 0;
        int secondShift = 0;
        // временный массив для хранения элементов
        int[] result = new int[endIndex - startIndex];

        while (startIndex + firstShift < centerIndex && centerIndex + secondShift < endIndex) {
            // если крайний элемент левой половины меньше крайнего элемента правой половины
            if (array[startIndex + firstShift] < array[centerIndex + secondShift]) {
                //  добавляем в результирующий массив крайнее значение с левой половины и добавляем сдвиг
                result[firstShift + secondShift] = array[startIndex + firstShift];
                firstShift += 1;
            } else {
                //  добавляем в результирующий массив крайнее значение с правой половины и добавляем сдвиг
                result[firstShift + secondShift] = array[centerIndex + secondShift];
                secondShift += 1;
            }
        }
        // добавляем оставшиеся (если есть) элементы из левой половины в результат
        while (startIndex + firstShift < centerIndex) {
            result[firstShift + secondShift] = array[startIndex + firstShift];
            firstShift += 1;
        }
        // добавляем оставшиеся (если есть) элементы из правой половины в результат
        while (centerIndex + secondShift < endIndex) {
            result[firstShift + secondShift] = array[centerIndex + secondShift];
            secondShift += 1;
        }
        // копируем отстортированный участок в результирующий массив
        for (int i = 0; i < firstShift + secondShift; i++) {
            array[startIndex + i] = result[i];
        }

    }

    public static void mergeSortRecursive(int[] array, int startIndex, int endIndex) {
        if (startIndex + 1 == endIndex) {
            return; // если отрезок состоит из одного элемента - выходим
        }
        int centerIndex = (startIndex + endIndex) / 2;
        mergeSortRecursive(array, startIndex, centerIndex); // сортируем правую часть
        mergeSortRecursive(array, centerIndex, endIndex); // сортируем левую часть
        merge(array, startIndex, centerIndex, endIndex); // вызываем сортировку слиянием для выбранной секции алгоритма

    }

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
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        mergeSortRecursive(a, 0, n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
