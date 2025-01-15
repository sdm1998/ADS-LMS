package by.it.group351052.kozhemyakin.a_khmelev.lesson05;

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

    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            // Компаратор для сортировки по возрастанию начала отрезка
            Segment other = (Segment) o;
            if (this.start < other.start) {
                return -1;
            } else if (this.start > other.start) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            int st = scanner.nextInt();
            int fn = scanner.nextInt();
            if (st > fn) {
                // на случай, если концы пришли в обратном порядке
                int temp = st;
                st = fn;
                fn = temp;
            }
            segments[i] = new Segment(st, fn);
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        threeWayQuickSortIterative(segments, 0, n - 1);


        for (int i = 0; i < m; i++) {
            int p = points[i];
            int idx = binarySearchFirst(segments, p);
            if (idx == -1) {
                result[i] = 0;
                continue;
            }
            int count = 0;

            // Идём влево
            int left = idx;
            while (left >= 0 && segments[left].start <= p) {
                if (segments[left].stop >= p) {
                    count++;
                }
                left--;
            }
            int right = idx + 1;
            while (right < n && segments[right].start <= p) {
                if (segments[right].stop >= p) {
                    count++;
                }
                right++;
            }

            result[i] = count;
        }

        return result;
    }

    // Элиминация хвостовой рекурсии: организуем стек вручную
    private void threeWayQuickSortIterative(Segment[] arr, int left, int right) {
        int[] stackLeft = new int[arr.length + 1];
        int[] stackRight = new int[arr.length + 1];
        int top = -1;

        top++;
        stackLeft[top] = left;
        stackRight[top] = right;

        while (top >= 0) {
            int l = stackLeft[top];
            int r = stackRight[top];
            top--;

            while (l < r) {
                // Трехпутевое разбиение
                // pivot = arr[l]
                Segment pivot = arr[l];
                int lt = l;
                int i = l;
                int gt = r;

                while (i <= gt) {
                    int cmp = arr[i].compareTo(pivot);
                    if (cmp < 0) {
                        swap(arr, lt, i);
                        lt++;
                        i++;
                    } else if (cmp > 0) {
                        swap(arr, i, gt);
                        gt--;
                    } else {
                        i++;
                    }
                }

                int leftSize = lt - l;
                int rightSize = r - gt;

                if (leftSize < rightSize) {
                    if (l < lt - 1) {
                        top++;
                        stackLeft[top] = lt;
                        stackRight[top] = r;
                        r = lt - 1;
                    } else {
                        l = gt + 1;
                    }
                } else {
                    if (gt + 1 < r) {
                        top++;
                        stackLeft[top] = l;
                        stackRight[top] = lt - 1;
                        l = gt + 1;
                    } else {
                        r = lt - 1;
                    }
                }
            }
        }
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Бинарный поиск для нахождения любого индекса idx, где segments[idx].start <= point
    // Если таких нет, вернём -1
    private int binarySearchFirst(Segment[] arr, int point) {
        int left = 0;
        int right = arr.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid].start <= point) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
