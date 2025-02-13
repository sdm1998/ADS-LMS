package by.it.group351051.a_hancharyk.lesson03;

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
        //тут запишите ваше решение.
        //Будет мало? Ну тогда можете его собрать как Generic и/или использовать в варианте B
        private List<Long> heap = new ArrayList<>();

        void swapNode(int num1,int num2)
        {
            Long buf = heap.get(num1);
            heap.set(num1, heap.get(num2));
            heap.set(num2,buf);
        }
        int maxNode(int i)
        {
            int left = i*2+1;
            int right = i*2+2;

            //если правая и левая границы(гидексы массива) меньше размера массива
            if (left < heap.size() && right < heap.size())
            {
                //элемент на индексе left больше элемента с индексом right -> то возвращаем  левую, иначе правую, (индексы)
                if((heap.get(left) > heap.get(right)) == true)
                {
                    return left;
                }
                else
                {
                    return right;
                }

            }
            else{
                if (left < heap.size())//если левая меньше
                    return left;
                else{
                    if (right < heap.size())//если правая граница(индекс) меньше размера
                        return right;
                    else
                        return i;//если равен самому узлу
                }
            }
        }

        int siftDown(int i) { //просеивание вверх
            //двигаемя до тех пор, пока текущий элемент меньше узла с максимальным значением
            while (heap.get(i) < heap.get(maxNode(i)))
                swapNode(i,maxNode(i));
            return i;
        }

        int siftUp(int i) { //просеивание вниз
            int midl = (i - 1)/2;//центральный элемент дерева
            //двигаемся с середины до текущего до тех пор пока
            if (heap.get(midl) <= heap.get(i))
                swapNode(midl,i);
            return i;
        }


        void insert(Long value) { //вставка
            heap.add(value);//добавляете элемент в конец массива
            siftUp(heap.size() - 1);//смещаем его на нужную позицию
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = heap.get(0);//получениеи значение самого первого элемента
            heap.set(0, heap.get(heap.size() - 1));//на позицию 0 ставим последний элемент
            heap.remove(heap.size() - 1);//последний удалили
            siftDown(0);
            return result;//вернули макс
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1


    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {//
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                System.out.println();
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
