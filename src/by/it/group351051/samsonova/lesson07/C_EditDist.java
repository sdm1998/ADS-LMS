package by.it.group351051.samsonova.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();
        int[][] dp = new int[m + 1][n + 1];

        char[][] ops = new char[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
            ops[i][0] = '-';
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
            ops[0][j] = '+';
        }

        //заполнение массива
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    ops[i][j] = '#';  // Совпадение
                } else {
                    int delete = dp[i - 1][j] + 1;
                    int insert = dp[i][j - 1] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    //минимум из трех
                    dp[i][j] = Math.min(Math.min(delete, insert), replace);

                    //запись операции
                    if (dp[i][j] == delete) {
                        ops[i][j] = '-';
                    } else if (dp[i][j] == insert) {
                        ops[i][j] = '+';
                    } else {
                        ops[i][j] = '~';
                    }
                }
            }
        }

        //восстановление последовательности операций
        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        while (i > 0 || j > 0) {
            if (ops[i][j] == '#') {
                result.insert(0, "#,");
                i--;
                j--;
            } else if (ops[i][j] == '-') {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            } else if (ops[i][j] == '+') {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else if (ops[i][j] == '~') {
                result.insert(0, "~" + two.charAt(j - 1) + ",");
                i--;
                j--;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        
        while (scanner.hasNextLine()) {
            String one = scanner.nextLine();
            String two = scanner.nextLine();
            System.out.println(instance.getDistanceEdinting(one, two));
        }
    }
}