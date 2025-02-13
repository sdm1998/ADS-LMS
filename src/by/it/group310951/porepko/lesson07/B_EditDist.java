package by.it.group310951.porepko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Objects;
import java.util.Arrays;

/*
Задача на программирование: расстояние Левенштейна

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

        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        if (Objects.equals(one, two)) {
            return 0;
        }
        // задаем матрицу для вычисления расстояния редактирования
        int[][] editingMatrix = new int[two.length() + 1][one.length() + 1];

        // заполняем первую строку
        for (int i = 1; i <= one.length(); i++) {
            editingMatrix[0][i] = i;
        }
        // заполняем первый столбец
        for (int i = 1; i <= two.length(); i++) {
            editingMatrix[i][0] = i;
        }
        // заполняем все остальные строки
        for (int i = 1; i < editingMatrix.length; i++) {
            for (int j = 1; j < editingMatrix[i].length; j++) {

                // если символы не равны, то инкриминируем значение изменения
                int m = one.charAt(j - 1) == two.charAt(i - 1) ? 0 : 1;
                int[] sequence = {
                        editingMatrix[i - 1][j] + 1, // вставка
                        editingMatrix[i][j - 1] + 1, // удаление
                        editingMatrix[i - 1][j - 1] + m,  // замена
                };

                // находим минимальное значение
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
            if (i > 0) {
                System.out.print(two.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}