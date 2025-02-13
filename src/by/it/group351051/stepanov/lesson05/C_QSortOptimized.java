package by.it.group351051.stepanov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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

    // Отрезок
    private static class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (start > stop) {
                int temp = start;
                start = stop;
                stop = temp;
            }
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Число отрезков и точек
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Segment[] segments = new Segment[n];
        int[] points = new int[m];
        int[] result = new int[m];

        // Читаем отрезки
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        // Читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем отрезки
        Arrays.sort(segments);

        // Обрабатываем каждую точку
        for (int i = 0; i < m; i++) {
            result[i] = countSegmentsForPoint(segments, points[i]);
        }

        return result;
    }

    private int countSegmentsForPoint(Segment[] segments, int point) {
        int count = 0;
        int left = 0, right = segments.length - 1;

        // Бинарный поиск первого подходящего отрезка
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (segments[mid].start <= point && segments[mid].stop >= point) {
                count++;

                // Проверяем соседние элементы
                int temp = mid;
                while (--temp >= 0 && segments[temp].start <= point && segments[temp].stop >= point) {
                    count++;
                }

                temp = mid;
                while (++temp < segments.length && segments[temp].start <= point && segments[temp].stop >= point) {
                    count++;
                }

                break;
            } else if (segments[mid].start > point) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return count;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/stepanov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
