package by.it.group351051.antonvyra.lesson05;

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

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment other) {
            if (this.start != other.start) {
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(this.stop, other.stop);
        }
    }

    private void quickSort(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] pivots = partition(segments, left, right);
            quickSort(segments, left, pivots[0] - 1);
            left = pivots[1] + 1; // Tail recursion elimination
        }
    }

    private int[] partition(Segment[] segments, int left, int right) {
        Segment pivot = segments[left];
        int lt = left, gt = right, i = left + 1;
        while (i <= gt) {
            if (segments[i].compareTo(pivot) < 0) {
                swap(segments, lt++, i++);
            } else if (segments[i].compareTo(pivot) > 0) {
                swap(segments, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    private int binarySearch(Segment[] segments, int point) {
        int low = 0, high = segments.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (segments[mid].start > point) {
                high = mid - 1;
            } else if (segments[mid].stop < point) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // сортировка отрезков
        quickSort(segments, 0, n - 1);

        // проверка каждой точки
        for (int i = 0; i < m; i++) {
            int point = points[i];
            int idx = binarySearch(segments, point);
            if (idx != -1) {
                // Count all segments containing the point
                int count = 0;
                for (int j = idx; j >= 0 && segments[j].start <= point && segments[j].stop >= point; j--) {
                    count++;
                }
                for (int j = idx + 1; j < n && segments[j].start <= point && segments[j].stop >= point; j++) {
                    count++;
                }
                result[i] = count;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
