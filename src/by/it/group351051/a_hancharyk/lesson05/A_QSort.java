package by.it.group351051.a_hancharyk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    7 10
    0 5
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;//начало отрезка
        int stop;//конец отрезка

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке

            //если левая гранциа больше правой
            if (this.start > this.stop) {
                //меняем местами

                int temp = this.start;
                this.start = this.stop;
                this.stop = temp;
            }
        }

//функция, которая сравнивает текущий объект segment с объектом o(любой интервал)4
        //0 если текщуий больше, иначе 0
    @Override
    public int compareTo(Segment o) {
        if (this.start > o.start) {//если начало текущего отрезка больше o
            return 1;
        }
        if (this.start == o.start && this.stop > o.stop) { //если начало первого и второго равны, тогда сравниваем концы отрезков
            return 1;
        }
        return 0;
    }
}
    //рекурсивная сростровка
    void quickSort(Segment[] arr, int left, int right)
    {


        int l = left;//временная левая граница
        int r = right;//временная правая граница

        Segment p = arr[left];///опорный элемент

        while(left < right)//цикл до тех пор, пока правая граница меьнше левой
        {
            //двигаемся по массиву, пока элемент на границе больше опорного элемента
            while(((arr[right].compareTo(p)) == 1) && (left < right))
            {
                right--;//смешаем границу влево
            }
            //если не дошли до края левой границы,
            if(left != right)
            {
                //левая граница равна элементу, до которого мы дошли
                arr[left] = arr[right];
                left++;
            }
            //идем от левой границы до тех пор, пока текущий меньше или равен опорному
            while(((arr[left].compareTo(p)) == 0) && (left < right))
            {
                left++;//смещаем левую границу вправо
            }
            // //если не дошли до края прввой границы,
            if(left != right)
            {
                //правая граница равна текущему элементу, до которого дошли
                arr[right] = arr[left];
                right--;
            }

        }

        arr[left] = p;
        int index = left;
        left = l;
        right = r;
        if(left < index)
        {
            quickSort(arr, left, index);
        }
        if(right > index)
        {
            quickSort(arr, index + 1, right);
        }
    }

    //поиск количества отрезков, которым принадлежат события
    public int[] findCount(Segment[] segments, int[] points)
    {
        int[] result=new int[points.length];
        //Цикл по всем событиям
        for(int i = 0; i < points.length; i++)
        {
            int point = points[i];// время текущего события

            //Проверяем все интервалы времемени, когда включались камеры
            for(int j = 0; j < segments.length; j++)
            {
                //если начало отрезка <= времени совьтия и конец больше или равен
                if(segments[j].start <= point && segments[j].stop >= point)
                {
                    result[i]++;//значит событие входит в отрезок
                }
                //если время больше соыбтия, случилось после текущих отрезков
                if(point > segments[j].stop)
                {
                    break;
                }
            }
        }
return result;
    }
    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }

        System.out.println("Точки до сортировки: ");
        for(int i = 0; i < segments.length; i++)
        {
            System.out.println(segments[i].start + " " + segments[i].stop);
        }
        //отсортировали
        quickSort(segments, 0, segments.length - 1);


        System.out.println("Точки после сортировки: ");
        for(int i = 0; i < segments.length; i++)
        {
            System.out.println(segments[i].start + " " + segments[i].stop);
        }

        //сам алгоритм
        int[] result = findCount(segments, points);
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
