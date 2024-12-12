package by.it.group351051.samsonova.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;   // Стоимость предмета
        int weight; // Вес предмета

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            // Сравниваем стоимость за единицу веса (по убыванию)
            double thisValuePerWeight = (double) this.cost / this.weight;
            double otherValuePerWeight = (double) o.cost / o.weight;
            return Double.compare(otherValuePerWeight, thisValuePerWeight);
        }
    }

    /**
     * метод для вычисления максимальной стоимости рюкзака
     *
     * @param source файл с данными о предметах
     * @return максимальная стоимость рюкзака
     * @throws FileNotFoundException если файл не найден
     */

    public double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);

        //количество предметов и вместимость рюкзака
        int n = input.nextInt();
        int W = input.nextInt();

        //массив предметов
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        //все предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        //сортировка предметов по стоимости
        Arrays.sort(items);

        double result = 0; //общая стоимость
        int remainingCapacity = W; //остаток, в который можно поместить предметы

        //заполним
        for (Item item : items) {
            if (remainingCapacity == 0) {
                break; //выход
            }

            //если предмет целиком помещается в рюкзак
            if (item.weight <= remainingCapacity) {
                result += item.cost; //добавим стоимость предмета
                remainingCapacity -= item.weight; //-- оставшуюся часть
                System.out.printf("Добавлен полностью: %s\n", item);
            } else {
                //частично
                double fraction = (double) remainingCapacity / item.weight; //часть предмета
                result += item.cost * fraction; //+ частичн. стоимость
                System.out.printf("Добавлена часть предмета: %s, доля: %.2f\n", item, fraction);
                remainingCapacity = 0; //рюкзак заполнен
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)\n", costFinal, finishTime - startTime);
    }
}
