package by.it.group351051.antonvyra.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

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
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    // отрезок
    private class Segment implements Comparable<Segment> {
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

    private class Event implements Comparable<Event> {
        int time;
        int type; // -1 = начало отрезка, 0 = точка, 1 = конец отрезка
        int index; // индекс точки для результата

        Event(int time, int type, int index) {
            this.time = time;
            this.type = type;
            this.index = index;
        }

        @Override
        public int compareTo(Event o) {
            // Если времена совпадают, сортируем по типу события
            if (this.time == o.time) {
                return Integer.compare(this.type, o.type);
            }
            return Integer.compare(this.time, o.time);
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Читаем число отрезков и точек
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Инициализируем список событий
        List<Event> events = new ArrayList<>();

        // Читаем отрезки и добавляем их события
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            events.add(new Event(start, -1, -1)); // Начало отрезка
            events.add(new Event(stop, 1, -1));  // Конец отрезка
        }

        // Читаем точки и добавляем их как события
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int point = scanner.nextInt();
            events.add(new Event(point, 0, i)); // Точка
        }

        // Сортируем события
        Collections.sort(events);

        // Sweep line для подсчета количества камер
        int activeSegments = 0;
        for (Event event : events) {
            if (event.type == -1) {
                // Начало отрезка
                activeSegments++;
            } else if (event.type == 1) {
                // Конец отрезка
                activeSegments--;
            } else if (event.type == 0) {
                // Точка
                result[event.index] = activeSegments;
            }
        }

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
