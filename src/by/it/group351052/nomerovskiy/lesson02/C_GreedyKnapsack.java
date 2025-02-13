package by.it.group351052.nomerovskiy.lesson02;
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
            // Сортировка по стоимости на единицу веса (по убыванию)
            double thisCostPerWeight = (double) this.cost / this.weight;
            double otherCostPerWeight = (double) o.cost / o.weight;
            return Double.compare(otherCostPerWeight, thisCostPerWeight);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      // сколько предметов в файле
        int W = input.nextInt();      // какой вес у рюкзака
        Item[] items = new Item[n];   // получим список предметов
        for (int i = 0; i < n; i++) { // создаем каждый предмет с помощью конструктора
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        // покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        // реализуем алгоритм жадного сбора рюкзака
        double result = 0;  // это будет итоговая стоимость вещей в рюкзаке
        Arrays.sort(items); // сортируем предметы по стоимости на единицу веса

        for (Item item : items) {
            if (W <= 0) break; // если рюкзак уже полон, выходим

            if (item.weight <= W) {
                // если предмет помещается полностью
                W -= item.weight;
                result += item.cost;
            } else {
                // если предмет не помещается полностью, то берем его часть
                result += item.cost * ((double) W / item.weight);
                W = 0;  // рюкзак больше не вмещает ничего
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmel/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}