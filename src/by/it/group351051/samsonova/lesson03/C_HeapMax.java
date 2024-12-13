package by.it.group351051.samsonova.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftUp(int i) { 
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(i) <= heap.get(parent)) {
                    break;
                }
                swap(i, parent);
                i = parent;
            }
            return i;
        }

        int siftDown(int i) {
            int size = heap.size();
            while (2 * i + 1 < size) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int largest = left;

                if (right < size && heap.get(right) > heap.get(left)) {
                    largest = right;
                }

                if (heap.get(i) >= heap.get(largest)) {
                    break;
                }

                swap(i, largest);
                i = largest;
            }
            return i;
        }

        void insert(Long value) { // Вставка
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            if (heap.isEmpty()) {
                return null;
            }

            Long result = heap.get(0);
            Long last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, last);
                siftDown(0);
            }

            return result;
        }

        private void swap(int i, int j) {
            Long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

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
            } else if (s.startsWith("Insert")) {
                String[] parts = s.split(" ");
                if (parts[0].equalsIgnoreCase("Insert")) {
                    heap.insert(Long.parseLong(parts[1]));
                    i++;
                }
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
