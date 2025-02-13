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

    List<Double> calcStartTimes(double[] events, double workDuration)  {

        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта

        // сортируем события по возрастанию
        Arrays.sort(events);

        List<Double> starts = new ArrayList<>(); // список для хранения моментов старта
        double lastEndTime = -1; // время окончания последнего сеанса

        for (double event : events) {

            // если событие не покрыто предыдущим
            if (event > lastEndTime) {

                // включаем регистратор в момент начала события
                starts.add(event);

                // обновляем время окончания последнего сеанса
                lastEndTime = event + workDuration;
            }
        }

        return starts;
    }
}
