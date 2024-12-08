package by.it.group351051.zaluzhny.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {
    private int[][] memo;

    int getDistanceEdinting(String one, String two) {
        memo = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return computeDistance(one, two, one.length(), two.length());
    }

    private int computeDistance(String one, String two, int lenOne, int lenTwo) {
        if (lenOne == 0) return lenTwo; // If first string is empty
        if (lenTwo == 0) return lenOne; // If second string is empty

        if (memo[lenOne][lenTwo] != -1) {
            return memo[lenOne][lenTwo];
        }

        if (one.charAt(lenOne - 1) == two.charAt(lenTwo - 1)) {
            memo[lenOne][lenTwo] = computeDistance(one, two, lenOne - 1, lenTwo - 1);
        } else {
            int insertCost = computeDistance(one, two, lenOne, lenTwo - 1);
            int removeCost = computeDistance(one, two, lenOne - 1, lenTwo);
            int replaceCost = computeDistance(one, two, lenOne - 1, lenTwo - 1);
            memo[lenOne][lenTwo] = 1 + Math.min(insertCost, Math.min(removeCost, replaceCost));
        }

        return memo[lenOne][lenTwo];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
