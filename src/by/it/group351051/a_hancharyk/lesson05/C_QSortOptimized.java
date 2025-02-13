package by.it.group351051.a_hancharyk.lesson05;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //если левая гранциа больше правой
            if (this.start > this.stop) {
                //меняем местами

                int temp = this.start;
                this.start = this.stop;
                this.stop = temp;
            }

        }

        @Override
        public int compareTo(Object o) {

            Segment obj = (Segment)o;
            if (this.start > obj.start) {//если начало текущего отрезка больше o
                return 1;
            }
            if (this.start == obj.start && this.stop > obj.stop) { //если начало первого и второго равны, тогда сравниваем концы отрезков
                return 1;
            }
            return 0;

        }
    }




   void quickSort(Segment[] arr, int left, int right){
        Segment p = arr[(left + right) / 2];
        int l = left;
        int index = right;

        while(l < index)
        {
            while (arr[l].compareTo(p) == 0)
            {
                ++l;
            }
            while(arr[index].compareTo(p) == 1)
            {
                --index;
            }
            if(l <= index)
            {
                Segment temp = arr[l];
               arr[l] = arr[index];
                arr[index] = temp;
                ++l;
                --index;
            }
        }
        if(left < index) {
            quickSort(arr, left, index);
        }
        if(l < right) {
            quickSort(arr, l, right);
        }

    }

    int binaryFind(int left, int right, int value) {


        int index = -1;//индекс искомого элемента

        //пока индекс левого меньше правого
        while (left <= right) {
            int midl = (right + left) / 2;//находим центр массива

            if (midl > value) {//если центральный больше искомого значения, значит элемент наодится слева, нужно менять правую границу(она будет равна
                right = midl -1;
            } else if (midl< value) {//если центральный меньше искомого значения, значит элемент справа
                left = midl + 1 ;
            } else if (midl == value) {// если центральный равен саммоу значению
                index = midl;//значит сохраняем в переменную index
                break;
            }
        }
        return index;
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
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }


        quickSort(segments,0,n-1);
        int k= 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (binaryFind(segments[j].start, segments[j].stop, points[i]) != -1) {
                    result[i]++;
                    break;
                }
            }
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
