package by.it.group351051.burdo.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возрастающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]]больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/

/*
LIS (Longest Increasing Subsequence)
Принципы динамического программирования:

Разбиение задачи на подзадачи: длины подпоследовательностей для каждого элемента массива.
Использование результатов предыдущих подзадач: lisLength[i] обновляется на основе значений lisLength[j].
Хранение промежуточных результатов: массив lisLength.
Оптимизация перебора: проверяются только элементы, удовлетворяющие условию делимости.
 */
public class A_LIS {

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // массив для хранения длин LIS
        // lisLength[i] хранит длину наибольшей возрастающей подпоследовательности, которая заканчивается на элементе A[i]
        int[] lisLength = new int[n];
        // изначально все элементы LIS имеют длину 1
        for (int i = 0; i < n; i++) {
            lisLength[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[j] < m[i] && lisLength[i] < lisLength[j] + 1) {
                    //  m[j] < m[i] значит, что мы можем продолжить возрастающую подпоследовательность.
                    // lisLength[i] < lisLength[j] + 1это значит, что текущая LIS длина для m[i] увеличится.

                    lisLength[i] = lisLength[j] + 1;
                }
            }
        }

        // ищем максимальную длину LIS
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, lisLength[i]);
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
