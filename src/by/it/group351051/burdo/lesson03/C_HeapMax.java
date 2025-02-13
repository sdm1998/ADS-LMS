package by.it.group351051.burdo.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        private List<Long> heap = new ArrayList<>();

        private void swap(int i, int j) {
            long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        int siftDown(int i) { //просеивание вверх O(logn)
            int heapSize = heap.size();
            while (2 * i + 1 < heapSize) { // пока существует хотя бы один ребенок
                int left = 2 * i + 1;  // левый ребенок
                int right = 2 * i + 2; // правый ребенок
                int j = left;

                // если правый ребенок существует и меньше левого
                if (right < heapSize && heap.get(right) < heap.get(left)) {
                    j = right;
                }

                // если родитель уже меньше или равен минимальному ребенку, выходим
                if (heap.get(i) <= heap.get(j)) {
                    break;
                }

                // меняем местами родителя с минимальным ребенком
                swap(i, j);
                i = j; // продолжаем просеивание вниз

            }
            return i;
        }

        int siftUp(int i) { //просеивание вниз O(logn)
            while (i > 0 && heap.get(i) < heap.get((i - 1) / 2)) { // пока не достигли корня и текущий элемент меньше родителя
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2; // поднимаем элемент вверх
            }
            return i;
        }

        void insert(Long value) { //вставка
            heap.add(value); // добавляем значение в конец
            int i = heap.size() - 1;
            siftUp(i); //поднимаем это значение, чтобы дерево тало опять сбалансировнным
        }
        Long extractMax() { //извлечение и удаление максимума
            if (heap.isEmpty()) {
                Long result = null;
            }
            long result = heap.removeLast();
            siftDown(0);
            return result;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                //System.out.println(maxValue);
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
