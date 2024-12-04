package by.it.group351051.burdo.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
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
/*
Расстояние Левенштейна (редакционное расстояние, дистанция редактирования) — метрика, измеряющая по модулю разность между
двумя последовательностями символов. Она определяется как минимальное количество односимвольных операций (а именно вставки,
удаления, замены), необходимых для превращения одной последовательности символов в другую.

https://habr.com/ru/articles/676858/ - подробно и с картинками.
Итак, вычислим расстояние между двумя строками методом Вагнера — Фишера:
составим матрицу D и каждый её элемент вычислим по рекуррентной формуле
*/

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //  одна из строк пустая
        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        if (Objects.equals(one, two)) {
            return 0;
        }
        // задаем матрицу для вычисления расстояния редактирования
        int[][] editingMatrix = new int[two.length()+1][one.length()+1];

        // заполняем первую строку (худший случай с максимальным расстоянием Левенштейна)
        for (int i = 1; i <= one.length(); i++) {
            editingMatrix[0][i] = i;
        }
        // заполняем первый столбец (худший случай с максимальным расстоянием Левенштейна)
        for (int i = 1; i <= two.length(); i++) {
            editingMatrix[i][0] = i;
        }
        // заполняем остальные строки
        for (int i = 1; i < editingMatrix.length; i++) {
            for (int j = 1; j < editingMatrix[i].length; j++) {
                // если символы не равны, то инкриминируем значение изменения
                int m = one.charAt(j-1)==two.charAt(i-1) ? 0 : 1;
                int[] sequence = {
                        editingMatrix[i-1][j] + 1, // вставка
                        editingMatrix[i][j-1] + 1, // удаление
                        editingMatrix[i-1][j-1] + m,  // замена
                };

                // находим минимальное значение через поток
                int min = Arrays.stream(sequence).min().getAsInt();
                editingMatrix[i][j] = min;

            }
        }

        // выводим матрицу на экран
        System.out.print("    ");
        for (int i = 0; i < one.length(); i++) {
            System.out.print(one.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < editingMatrix.length; i++) {
            if (i>0) {
                System.out.print(two.charAt(i-1) + " ");
            } else {System.out.print("  ");}
            for (int j = 0; j < editingMatrix[i].length; j++) {
                System.out.print(editingMatrix[i][j] + " ");
            }
            System.out.println();
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return editingMatrix[two.length()][one.length()];
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}