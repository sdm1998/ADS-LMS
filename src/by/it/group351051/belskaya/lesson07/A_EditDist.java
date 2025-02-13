package by.it.group351051.belskaya.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        return editDistance(one, two, one.length(), two.length());
    }

    private int editDistance(String one, String two, int i, int j) {
        // Базовые случаи
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }

        // Если последние символы совпадают, игнорируем их
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
            return editDistance(one, two, i - 1, j - 1);
        } else {
            // Минимальная из трех операций: вставка, удаление, замена
            return 1 + Math.min(
                    editDistance(one, two, i, j - 1), // Вставка
                    Math.min(
                            editDistance(one, two, i - 1, j), // Удаление
                            editDistance(one, two, i - 1, j - 1) // Замена
                    )
            );
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/belskaya/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
