package by.it.group351051.zaluzhny.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;
        double valuePerWeight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.valuePerWeight = (double) cost / weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    ", valuePerWeight=" + valuePerWeight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            return Double.compare(o.valuePerWeight, this.valuePerWeight);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      // количество предметов
        int W = input.nextInt();      // максимальный вес рюкзака
        Item[] items = new Item[n];   // массив предметов

        for (int i = 0; i < n; i++) { // создаём каждый предмет
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        // Сортируем предметы по отношению стоимости к весу
        Arrays.sort(items);

        double totalValue = 0; // Общая стоимость предметов в рюкзаке
        for (Item item : items) {
            if (W <= 0) break; // Если рюкзак полон, выходим из цикла

            if (item.weight <= W) {
                // Если предмет можно полностью положить в рюкзак
                totalValue += item.cost; // Добавляем его стоимость
                W -= item.weight; // Уменьшаем оставшийся вес
            } else {
                // Если предмет больше оставшегося места, берем часть
                totalValue += item.valuePerWeight * W; // Добавляем часть стоимости
                W = 0; // Рюкзак больше не вмещает ничего
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %.2f\n", totalValue);
        return totalValue; // Возвращаем итоговую стоимость
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %.2f (время %d мс)", costFinal, finishTime - startTime);
    }
}
