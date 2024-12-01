package by.it.group351051.burdo.lesson05;

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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            } else {
                //если концы отрезков пришли в обратном порядке
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start > o.start) {
                // если текущий старт позже сравниваемого
                return 1;
            } else if (this.start == o.start && this.stop - this.start < o.stop - o.start) {
                // если старты совпадают, но текущее событие короче сравниваемого
                return 1;
            }
            return 0;
        }
    }

    private static void quickSort(Segment[] segments, int low, int high) {
        if (low < high) {
            // делим список на две части
            int mid = partition(segments, low, high);

            // рекурсивно сортируем слева
            quickSort(segments, low, mid - 1);
            // рекурсивно сортируем справа
            quickSort(segments, mid + 1, high);
        }
    }

    private static void swap(Segment[] segments, int i, int j) {
        // меняем местами элементы
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    private static int partition(Segment[] arr, int low, int high) {
        // выбираем средний элемент в качестве опорного
        int middle = low + (high - low) / 2;
        Segment pivot = arr[middle];
        // перемещаем опорный элемент в конец диапазона
        swap(arr, middle, high);
        // индекс меньших элементов
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // сравниваем текущий элемент с опорным
            if (arr[j].compareTo(pivot) == 0) {
                //System.out.printf("segment start %d > pivot start %d\n", arr[j].start, pivot.start);
                i++;
                swap(arr, i, j);
                //} else {
                //System.out.printf("segment start %d <= pivot start %d\n", arr[j].start, pivot.start);
            }
        }
        // перемещаем опорный элемент на своё место (между меньшими и большими)
        swap(arr, i + 1, high);

        // возвращаем индекс, на котором теперь находится опорный элемент
        return i + 1;
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        // сортировка событий алгоритмом быстрой сортировки
        quickSort(segments, 0, n-1);

        // проходимся по списку точек и считаем сколько раз встретили в событиях
        for (int i = 0; i < m; i++) {
            int pointOccurance = 0;
            for (int j = 0; j < n; j++) {
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop) {
                    pointOccurance += 1;
                }
            }
            result[i] = pointOccurance;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("sorted segments %d %d\n", segments[i].start, segments[i].stop);
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataA.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
