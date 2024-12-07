package by.it.group351051.a_hancharyk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
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


//считает количество инверсий(скорость О(n2));
    int countInversions(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] > arr[j]) {
                    count++;
                }
        }
        return count;

    }


    //для подсчета с помощью сортировки слиянием(скорость O(n log n);
    int counter;


    int[] mergeArr(int[] current, int[] first, int[] second, int left, int right)
    {
        int middle = left + (right - left) / 2;
        int l_cur = left;
        int r_cur = middle + 1;

        //склейка двух отсортированных массива
        for ( int i = left; i <= right; i++)
        {

            if (l_cur <= middle && r_cur <= right)
            {
                //если элемент из первого массива меньше элемента из второго,
                if (first[l_cur] < second[r_cur])
                {
                    current[i] = first[l_cur];//добавляем из перовго в наш новый массив
                    l_cur++;//увеличиваем индекс на 1 (индекс элемента первого массива)
                }
                else
                {
                    counter++;
                    current[i] = second[r_cur];//добавляем из второго в наш новый массив
                    r_cur++;//увеличиваем индекс на 1 (индекс элемента второго массива)
                }
            }
            else if (l_cur <= middle)
            {
                current[i] = first[l_cur];
                l_cur++;
            }
            else
            {
                current[i] = second[r_cur];
                r_cur++;
            }
        }
        return current;
    }

    //int[] - массив в java
    int[] merge_sort(int[] arr, int[] tempArr,  int left, int right)
    {
        if (left == right)
        {
            tempArr[left] = arr[left];
            return tempArr;
        }

        int middle = left + (right - left) / 2;

        // разделяй и сортируй
        int[] first = merge_sort(arr, tempArr, left, middle);
        int[] second= merge_sort(arr,tempArr, middle + 1, right);

        // слияние двух отсортированных половин

        int[] current;
        if(first == arr) {
            current = tempArr;
        }
        else {
            current = arr;
        }


        current = mergeArr(current,first,second, left, right);
        return current;
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
        merge_sort(a, new int[n], 0, n -1);
        int result = counter;
        //int result = countInversions(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!








        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();

        int result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
       System.out.print(result);
    }
}
