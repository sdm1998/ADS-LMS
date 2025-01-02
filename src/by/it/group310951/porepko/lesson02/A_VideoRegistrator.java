package by.it.group310951.porepko.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args)  {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестированиb

    List<Double> calcStartTimes(double[] events, double workDuration)  {

        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;


        //hint: сортировка Arrays.sort обеспечит скорость алгоритма
        //C*(n log n) + C1*n = O(n log n)

        Arrays.sort(events);
        result = new ArrayList<>();
        result.add(events[0]);

        // время старта видеокамеры
        double lastEvent = events[0];

        for (int i = 1; i < events.length; i++) {
            //System.out.printf("A_VideoRegistrator event = %f\n", events[i]);
            //System.out.printf("A_VideoRegistrator lastEvent + workDuration = %f\n", lastEvent + workDuration);
            if (events[i] > lastEvent + workDuration) {  //вычислим момент окончания работы видеокамеры

                result.add(events[i]);
                lastEvent = events[i];
            }
        }


        return result;                        //вернем итог
    }
}
