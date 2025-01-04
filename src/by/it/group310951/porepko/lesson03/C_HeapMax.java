package by.it.group310951.porepko.lesson03;

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
        private List<Long> heap = new ArrayList<>();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        // просеивание вниз
        int siftDown(int i) {
            int size = heap.size();
            while (true) {
                int leftChild = 2 * i + 1; // левый потомок
                int rightChild = 2 * i + 2; // правый потомок
                int largest = i; // индекс наибольшего элемента

                // сравниваем с левым потомком
                if (leftChild < size && heap.get(leftChild) > heap.get(largest)) {
                    largest = leftChild;
                }

                // сравниваем с правым потомком
                if (rightChild < size && heap.get(rightChild) > heap.get(largest)) {
                    largest = rightChild;
                }

                // если наибольший элемент — текущий, свойство кучи выполнено
                if (largest == i) {
                    break;
                }

                // меняем местами с наибольшим потомком
                swap(i, largest);
                i = largest; // переходим к потомку
            }
            return i;
        }

        // просеивание вверх
        int siftUp(int i) {
            while (i > 0) {
                int parentIndex = (i - 1) / 2; // индекс родителя
                if (heap.get(i) <= heap.get(parentIndex)) {
                    break;
                }
                swap(i, parentIndex); // меняем местами с родителем
                i = parentIndex; // переходим к родителю
            }
            return i;
        }

        // вставка элемента в кучу
        void insert(Long value) {
            heap.add(value); // добавляем элемент в конец массива
            siftUp(heap.size() - 1);
        }

        // извлечение и удаление максимального элемента
        Long extractMax() {
            if (heap.isEmpty()) {
                return null; // если куча пуста, возвращаем null
            }

            Long max = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                siftDown(0);
            }

            return max;
        }

        // обмен элементов в куче
        private void swap(int i, int j) {
            Long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    // процедура для чтения данных из файла
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);

        Integer count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) {
                    maxValue = res;
                }
                System.out.println(res);
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert")) {
                    heap.insert(Long.parseLong(p[1]));
                }
                i++;
            }
        }

        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}