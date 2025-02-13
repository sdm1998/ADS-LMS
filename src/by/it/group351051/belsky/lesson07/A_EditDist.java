package by.it.group351051.belsky.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        Map<String, Integer> memo = new HashMap<>();
        return editDistance(one, two, one.length(), two.length(), memo);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    // Рекурсивный метод для вычисления расстояния редактирования
    private int editDistance(String one, String two, int lenOne, int lenTwo, Map<String, Integer> memo) {
        // Если одна из строк пустая, вернуть длину другой строки
        if (lenOne == 0) return lenTwo;
        if (lenTwo == 0) return lenOne;

        // Формируем ключ для хранения результата в мапе
        String key = lenOne + "-" + lenTwo;
        // Если результат уже вычислен, возвращаем его
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Если последние символы равны, просто продолжаем
        if (one.charAt(lenOne - 1) == two.charAt(lenTwo - 1)) {
            memo.put(key, editDistance(one, two, lenOne - 1, lenTwo - 1, memo));
        } else {
            // Рассчитываем минимальные операции (вставка, удаление, замена)
            int insert = editDistance(one, two, lenOne, lenTwo - 1, memo); // Вставка
            int delete = editDistance(one, two, lenOne - 1, lenTwo, memo); // Удаление
            int replace = editDistance(one, two, lenOne - 1, lenTwo - 1, memo); // Замена

            // Находим минимальное количество операций и сохраняем в мапу
            memo.put(key, 1 + Math.min(insert, Math.min(delete, replace)));
        }
        return memo.get(key);
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
