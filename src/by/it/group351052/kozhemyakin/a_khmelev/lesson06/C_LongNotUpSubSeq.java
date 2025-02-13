package by.it.group351052.kozhemyakin.a_khmelev.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозрастающая подпоследовательность

Дано:
    целое число 1<=n<=1E5
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n
    и саму подпоследовательность (индексы i[1]<i[2]<…<i[k]),
    для которой A[i[j]] >= A[i[j+1]].

    В первой строке выводится k,
    во второй строке - индексы найденной подпоследовательности.

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ (эффективно, чтобы уложиться в n log n).

Sample Input:
5
5 3 4 4 2

Sample Output:
4
1 3 4 5
*/

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // d будет хранить "хвосты" подпоследовательностей разной длины
        // idx будет хранить индексы элементов из arr, соответствующих хвостам
        List<Integer> d = new ArrayList<>();
        List<Integer> idx = new ArrayList<>();

        // prev будет хранить ссылки на предыдущий элемент в восстановлении ответа
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            // поиск места для arr[i] в d
            // нам нужна невозрастающая подпоследовательность: arr[j] >= arr[i]
            // значит в d ищем первый элемент, который < arr[i], чтобы заменить его
            // (аналог LIS, только меняем знак сравнения)
            int left = 0;
            int right = d.size() - 1;
            int pos = d.size();
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (d.get(mid) >= arr[i]) {
                    // если d[mid] >= arr[i], то идём вправо
                    left = mid + 1;
                } else {
                    pos = mid;
                    right = mid - 1;
                }
            }

            // Если pos равен длине d, расширяем d
            if (pos == d.size()) {
                d.add(arr[i]);
                idx.add(i);
            } else {
                d.set(pos, arr[i]);
                idx.set(pos, i);
            }

            // prev:
            if (pos > 0) {
                prev[i] = idx.get(pos - 1);
            } else {
                prev[i] = -1;
            }
        }

        // длина невозрастающей подпоследовательности
        int length = d.size();

        // восстанавливаем индексы
        // последний элемент — это idx.get(length - 1)
        int[] answer = new int[length];
        int p = idx.get(length - 1);
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = p + 1;
            p = prev[p];
        }

        // вывод результата
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.print(answer[i] + (i + 1 < length ? " " : ""));
        }
        System.out.println();

        return length;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        instance.getNotUpSeqSize(stream);
    }
}
