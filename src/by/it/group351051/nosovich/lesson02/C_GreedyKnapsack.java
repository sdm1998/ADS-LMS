package by.it.group351051.nosovich.lesson02;
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
            double thisValue = (double) this.cost / this.weight;
            double otherValue = (double) o.cost / o.weight;
            // Сравнение соотношения стоимости предметов к их весу
            // и нахождение предмета с наибольшим значением
            int compare = Double.compare(otherValue, thisValue);
            return compare; // Сортировка
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
        // Сортирвка предметов по убыванию значения,
        // полученного в результат деления стоимости на вес
        Arrays.sort(items);

        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0; // Исходная стоимость
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.

        for (Item item:items) {
            // Если рюкзак полон, выход из цикла
            if (W == 0) break;
            // Если вес предмета меньше, чем помещает рюкзак
            if (item.weight <= W) {
                // К стоимости рюкзака прибавляется стоимость предмета
                result += item.cost;
                // Из вместимости рюкзака по весу отнимается вес предмета
                W-= item.weight;
            }
            // Если вес предмета больше, чем помещает рюкзак
            else {
                // К стоимости рюкзака добавляется стоимость той части предмета,
                // которая по весу входит в лимит рюкзака
                result += item.cost * ((double) W / item.weight);
                // Рюкзак заполнен, цикл прерывается
                W = 0;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}