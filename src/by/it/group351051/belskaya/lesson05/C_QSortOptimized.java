package by.it.group351051.belskaya.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {
    // отрезок
    private static class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
            // тут вообще-то лучше доделать конструктор на случай если
            // концы отрезков придут в обратном порядке
            if (start > stop) {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            // компаратор отрезков по началу
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.stop, o.stop);
        }
    }

    // быстрая сортировка для массива отрезков с элиминацией хвостовой рекурсии
    private static void quickSort(Segment[] array, int low, int high) {
        while (low < high) {
            int pi = partition(array, low, high);
            // рекурсивный вызов для меньшей части
            if (pi - low < high - pi) {
                quickSort(array, low, pi - 1);
                low = pi + 1;
            } else {
                quickSort(array, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    // функция разделения для быстрой сортировки
    private static int partition(Segment[] array, int low, int high) {
        Segment pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                Segment temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Segment temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    private static int binarySearch(Segment[] array, int low, int high, int point) {
        int result = high; // индекс для возврата, если ничего не найдено
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid].start > point) {
                high = mid;
            } else {
                result = mid;
                low = mid + 1;
            }
        }
        return result;
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        // число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        // число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // читаем сами отрезки
        for (int i = 0; i < n; i++) {
            // читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        // читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // сортируем отрезки по началу с использованием быстрой сортировки
        quickSort(segments, 0, n - 1);

        // для каждой точки подсчитываем количество отрезков, в которые она попадает
        for (int i = 0; i < m; i++) {
            int count = 0;
            int index = binarySearch(segments, 0, n, points[i]);
            while (index < n && segments[index].start <= points[i]) {
                if (segments[index].stop >= points[i]) {
                    count++;
                }
                index++;
            }
            result[i] = count;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/belskaya/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
