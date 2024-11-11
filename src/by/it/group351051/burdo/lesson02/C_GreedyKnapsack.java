package by.it.group351051.burdo.lesson02;
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
            if (this.weight == 0) {
                return 0;
            } else if (o.weight == 0) { // если предмет невесомый, но дорогой - такой предмет в приоритете
                return 1;
            }
            else if (this.cost/this.weight < o.cost/o.weight) {
                return 1;
            }
            return 0;
        }
    }

    private static void kiloPriceBubbleSort(Item[] items) {
        // сортировка по приоритету стоимости килограмма веса
        int n = items.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Сравниваем start и end времени двух событий
                if (items[j].compareTo(items[i]) == 1) {
                    // Меняем местами два предмета
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        kiloPriceBubbleSort(items);
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        double result = 0;
        double gainedW = 0;
        // так как груз отсортирован от дорого за килограмм до дешевого,
        // то пройдемся циклом добавляя в рюкзак в начале груз подороже
        for (int i = 0; i < items.length; i++) {
            double leftKilos = W - gainedW; // вычисляем свободное место
            if (leftKilos == 0) {
                break; // если места нет - останавливаем цикл
            } else if (items[i].weight < leftKilos) {
                // если веса хватает - добавляем груз целиком
                gainedW += items[i].weight;
                result += items[i].cost;
            } else {
                // если веса не хватает - добавляем кусок груза
                double leftKiloPercent = leftKilos / items[i].weight;
                gainedW += leftKilos;
                result += items[i].cost * leftKiloPercent;
            }

        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group351051/burdo/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}