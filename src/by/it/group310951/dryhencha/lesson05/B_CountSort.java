package by.it.group310951.dryhencha.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class  B_CountSort {


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
            System.out.println(points[i]);
        }
        // в качестве стартовых минимального и максимального
        // значений задаем граничные значения по условию задачи
        int min = 10000;
        int max = 1;
        // определяем минимальный и максимальный элемент
        for (int i = 0; i < n; i++) {
            if (points[i] < min) {
                min = points[i];
            }
            if (points[i] > max) {
                max = points[i];
            }
        }
        System.out.printf("Минимальный элемент %d\n", min);
        System.out.printf("Максимальный элемент %d\n", max);
        System.out.printf("Число элементов в счетчике %d\n", max-min+1);

        // задаем массив-счетчик, рассчитанный на диапазон чисел от минимального до максимального
        int[] counter = new int[max-min+1];

        for (int i=0; i < points.length; i++) {
            // инкрементируем элмент счетчика, ответственный за текущее число в сортируемом массиве
            counter[points[i]-min] ++;
        }

        int pointsIndex = 0;
        // проходимся по всему диапазону чисел, сохраненному в массие-счетчике
        for (int i=0; i < counter.length; i++) {
            // для каждого из чисел вставляем его солько раз, каково значение индекса в счетчике
            for (int j=0; j < counter[i]; j++) {
                points[pointsIndex] = i + min;
                pointsIndex++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}