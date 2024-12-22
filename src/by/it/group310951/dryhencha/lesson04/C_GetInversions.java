package by.it.group310951.dryhencha.lesson04;

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
    public static int merge(int[] array, int startIndex, int centerIndex, int endIndex) {
        // задаем смещение для левой и правой половины
        int firstShift = 0;
        int secondShift = 0;
        int inversions = 0;
        // временный массив для хранения элементов
        int[] result = new int[endIndex - startIndex];

        while (startIndex + firstShift < centerIndex && centerIndex + secondShift < endIndex) {
            // если крайний элемент левой половины меньше крайнего элемента правой половины
            if (array[startIndex + firstShift] <= array[centerIndex + secondShift]) {
                //  добавляем в результирующий массив крайнее значение с левой половины и добавляем сдвиг
                result[firstShift + secondShift] = array[startIndex + firstShift];
                firstShift += 1;
            } else {
                //  добавляем в результирующий массив крайнее значение с правой половины и добавляем сдвиг
                result[firstShift + secondShift] = array[centerIndex + secondShift];
                //System.out.printf("detected %d > %d\n",  array[startIndex + firstShift], array[centerIndex + secondShift]);
                secondShift += 1;
                // увеличиваем счетчик инверсий
                inversions += 1;
                //System.out.printf("inversions %d\n", inversions);

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
        // копируем отсортированный участок в результирующий массив
        for (int i = 0; i < firstShift + secondShift; i++) {
            array[startIndex + i] = result[i];
        }
        return inversions;

    }

    public static int mergeSortRecursive(int[] array, int inversions, int startIndex, int endIndex) {
        if (startIndex + 1 == endIndex) {
            //System.out.printf("inversions %d\n", inversions);
            return inversions; // если отрезок состоит из одного элемента - выходим
        }
        int centerIndex = (startIndex + endIndex) / 2;
        int newInversions = mergeSortRecursive(array, inversions, startIndex, centerIndex); // сортируем правую часть
        inversions += newInversions - inversions;
        newInversions = mergeSortRecursive(array, inversions, centerIndex, endIndex); // сортируем левую часть
        inversions += newInversions - inversions;
        inversions += merge(array, startIndex, centerIndex, endIndex); // вызываем сортировку слиянием для выбранной секции алгоритма
        //System.out.printf("inversions! %d\n", inversions);
        return inversions;
    }

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
        int result = 0;

        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        // подсчет инверсий на базе сортировки слиянием, реализованной в предыдущем задании
        // если элемент из правой части меньше элемента из левой части - присутствует инверсия
        result = mergeSortRecursive(a, result, 0, n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson04/dataC.txt");
        //InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson04/dataСTest.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}