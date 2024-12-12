package by.it.group351051.samsonova.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; //импор arrays

public class B_Sheduler {
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();

        // Массив событий с временем начала и окончания
        Event[] events = {
                new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };


        List<Event> starts = instance.calcStartTimes(events, 0, 10);


        System.out.println("Оптимальный график занятий:");
        System.out.println(starts);
    }

    /**
     * Метод для расчета максимального количества непересекающихся событий.
     *
     * @param events массив событий
     * @param from   начальное время интервала
     * @param to     конечное время интервала
     * @return список выбранных событий
     */

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();

        //сортируем события по времени окончания (жадный подход)
        Arrays.sort(events, (Event a, Event b) -> Integer.compare(a.stop, b.stop));

        //отладочная информация: выводим отсортированные события
        System.out.println("Отсортированные события:");
        for (Event event : events) {
            System.out.println(event);
        }

        int lastEndTime = from; //врремя окончания последнего добавленного события

        //проходим по всем событиям
        for (Event event : events) {
            //если событие начинается после или одновременно с окончанием предыдущего
            //и заканчивается в пределах допустимого интервала
            if (event.start >= lastEndTime && event.stop <= to) {
                result.add(event);  //добавляем событие в результат
                lastEndTime = event.stop;  //обновляем время окончания
                System.out.println("Добавлено событие: " + event);
            } else {
                System.out.println("Пропущено событие: " + event);
            }
        }

        return result; //возвращаем итоговый список
    }
}
