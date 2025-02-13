package by.it.group351051.a_hancharyk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

// 4 2 6 8 2 4 9 5 8 7 5 4 7





/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {

    public void Sort(int[] arr)
    {
        int min = arr[0];
        int max = arr[0];

        for(int i = 1; i < arr.length; i++)
        {
            if(min > arr[i])
            {
                min = arr[i];
            }
            if(max < arr[i])
            {
                max = arr[i];
            }
        }

        //5 2 9 15
        //2 3 4 5 6 7 8 9 10 11 12 13 14 15
//количество элементов в массиве значений, бдует хранить склько раз встретился каждый элемент
        int[] counts = new int[max - min + 1];

        //пробегаем по массиву и считаем сколько раз встретился каждый элемент
        for(int i = 0; i < arr.length; i++)
        {
            //считаем сколько раз встретился каждый элементи добавляем 1 к количеству
            counts[arr[i] - min]++;
        }

        //текущйи индекс элемента
        int current = 0;

        for(int i = 0; i < counts.length;i++ )
        {
            //сколько раз встретился элемент со значением i
            int countValue = counts[i];
            for(int j = 0; j < countValue; j++)
            {
                arr[current++] = i + min;//доабвляем одинаковы еэлементы в массив
            }
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом

        Sort(points);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
