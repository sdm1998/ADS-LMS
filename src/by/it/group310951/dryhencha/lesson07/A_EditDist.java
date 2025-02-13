package by.it.group310951.dryhencha.lesson07;

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
/*
Расстояние Левенштейна (редакционное расстояние, дистанция редактирования) — метрика, измеряющая по модулю разность между
двумя последовательностями символов. Она определяется как минимальное количество односимвольных операций (а именно вставки,
удаления, замены), необходимых для превращения одной последовательности символов в другую. В общем случае, операциям,
используемым в этом преобразовании,
можно назначить разные цены. Широко используется в теории информации и компьютерной лингвистике.
*/
public class A_EditDist {

    int getDistanceEdinting(String one, String two) {
        return calculateLevenshteinDistance(one, two, one.length(), two.length());
    }

    // рекурсивная функция для вычисления расстояния Левенштейна
    private int calculateLevenshteinDistance(String one, String two, int m, int n) {
        //  одна из строк пустая
        if (m == 0) return n;
        if (n == 0) return m;

        // если последние символы одинаковые, пропускаем их
        if (one.charAt(m - 1) == two.charAt(n - 1)) {
            return calculateLevenshteinDistance(one, two, m - 1, n - 1);
        }

        // если последние символы разные, вычисляем минимальную стоимость трех операций:
        int insert = calculateLevenshteinDistance(one, two, m, n - 1);    // вставка
        int remove = calculateLevenshteinDistance(one, two, m - 1, n);   // удаление
        int replace = calculateLevenshteinDistance(one, two, m - 1, n - 1); // замена

        // возвращаем минимальное из них + 1 (за текущую операцию)
        return 1 + Math.min(insert, Math.min(remove, replace));
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}