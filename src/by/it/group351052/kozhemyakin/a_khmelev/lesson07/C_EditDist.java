package by.it.group351052.kozhemyakin.a_khmelev.lesson07;

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
    Sample Output 3:
    +e,#,#,-s,#,~i,#,-c,~g,

Pояснения по обозначениям:
    - "-" (delete)   — удалить символ
    + "+" (insert)   — вставить символ
    ~ "~" (replace)  — заменить символ
    # "#" (match)    — символы совпали
*/

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();
        // dp[i][j] — минимальное расстояние редактирования
        // между первыми i символами строки one и первыми j символами строки two
        int[][] dp = new int[n + 1][m + 1];

        // Инициализация границ
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // все удаления
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // все вставки
        }

        // Подсчет DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;

                // dp[i-1][j] + 1 = удаление
                // dp[i][j-1] + 1 = вставка
                // dp[i-1][j-1] + cost = замена (или совпадение, если cost=0)
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        // Восстановление пути (предписаний) по dp
        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 || j > 0) {
            if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {
                // Удаление символа one[i-1]
                sb.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] + 1) {
                // Вставка символа two[j-1]
                sb.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else {
                // Замена или совпадение
                int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;
                if (cost == 0) {
                    // Совпадение
                    sb.insert(0, "#,");
                } else {
                    // Замена
                    sb.insert(0, "~" + two.charAt(j - 1) + ",");
                }
                i--;
                j--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);

        // В dataABC.txt предположительно три пары строк
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
