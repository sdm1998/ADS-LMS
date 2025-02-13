package by.it.group351051.nekhviadovich.lesson07;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m = two.length();

        // Матрица для хранения расстояний
        int[][] dp = new int[n + 1][m + 1];

        // Инициализация базового случая
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // Удаление всех символов строки `one`
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // Вставка всех символов строки `two`
        }

        // Заполнение матрицы
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Символы совпадают
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j], // Удаление
                            Math.min(
                                    dp[i][j - 1], // Вставка
                                    dp[i - 1][j - 1] // Замена
                            )
                    );
                }
            }
        }

        // Расстояние Левенштейна между полными строками

        int result = dp[n][m];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}