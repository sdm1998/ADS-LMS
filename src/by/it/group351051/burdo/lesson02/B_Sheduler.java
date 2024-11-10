package by.it.group351051.burdo.lesson02;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };


        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    private static void bubbleSort(Event[] events) {
        int n = events.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Сравниваем start и end времени двух событий
                if (events[j].start > events[j + 1].start || (events[j].start == events[j + 1].start && events[j].stop > events[j + 1].stop)) {
                    // Меняем местами два события
                    Event temp = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = temp;
                }
            }
        }
    }

    private static boolean isNotInArray(List<Event> events, Event event) {
        // проверка на наличие ивента в списке
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).start == event.start && events.get(i).stop == event.stop) {
                return false;
            }
        }
        return true;
    }


    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        bubbleSort(events); // сортируем пришедшие ивенты
        List<Event> result;
        result = new ArrayList<>();
        result.add(events[0]);
        int lastStop = events[0].stop;

        for (int i = 0; i < events.length; i++) {
            //System.out.println(events[i]);
            int startStopDelta = 1000000000; // задаем для дельты между концом и началом события очень большое значение
            Event nextEvent = null;
            for (int j = 0; j < events.length; j++) {

                // проверяем не выходит ли событие за временные рамки
                if (events[i].start <= from || events[i].stop >= to || events[j].start <= from || events[j].stop >= to) {
                    continue;
                }

                int currentDelta = events[j].start - events[i].stop; // вычисляем дельту,
                // чтобы следующим ивентом сделать ближайший (с минимальной дельтой)
                if (events[j].start >= lastStop && currentDelta < startStopDelta && isNotInArray(result, events[j])) {
                    // следующим ивентом будет тот, начало которого после окончания предыдущего (с минимальной дельтой),
                    startStopDelta = currentDelta;
                    nextEvent = events[j];
                    lastStop = nextEvent.stop;
                }
            }
            if (nextEvent != null) {
                result.add(nextEvent);
            }
        }
       return result; //вернем итог
    }
}