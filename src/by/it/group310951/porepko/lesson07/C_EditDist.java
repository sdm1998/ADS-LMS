package by.it.group310951.porepko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        //  одна из строк пустая
        if (one.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (char c : two.toCharArray()) {
                result.append("+").append(c).append(",");
            }
            return result.toString();
        }
        if (two.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (char c : one.toCharArray()) {
                result.append("-").append(c).append(",");
            }
            return result.toString();
        }


        // матрица для вычисления расстояния редактирования
        int[][] editingMatrix = new int[two.length()+1][one.length()+1];

        // матрица для вычисления действий редактирования
        char[][] actionsMatrix = new char[two.length()+1][one.length()+1];

        // заполняем первую строку матрицы
        for (int i = 1; i <= one.length(); i++) {
            editingMatrix[0][i] = i;
            actionsMatrix[0][i] = '+';
        }
        // заполняем первый столбец матрицы
        for (int i = 1; i <= two.length(); i++) {
            editingMatrix[i][0] = i;
            actionsMatrix[i][0] = '+';
        }
        // заполняем остальные строки
        for (int i = 1; i < editingMatrix.length; i++) {
            for (int j = 1; j < editingMatrix[i].length; j++) {

                int cost = one.charAt(j-1)==two.charAt(i-1) ? 0 : 1;
                // возможные операции
                int insert = editingMatrix[i - 1][j] + 1; //вставка
                int replace = editingMatrix[i - 1][j - 1] + cost; //замена
                int delete = editingMatrix[i][j - 1] + 1; // удаление


                int min = Arrays.stream(new int[]{insert, delete, replace}).min().getAsInt();
                editingMatrix[i][j] = min;

                // определяем действие
                if (cost == 0) {
                    actionsMatrix[i][j] = '#';
                } if (min == replace) {
                    actionsMatrix[i][j] = '~';
                } else if (min == insert) {
                    actionsMatrix[i][j] = '+';
                } else {
                    actionsMatrix[i][j] = '-';
                }

                // заполняем матрицу действий
                if (cost==0) {
                    actionsMatrix[i][j] = '#';
                } else if (editingMatrix[i-1][j] + 1 == min) {
                    actionsMatrix[i][j] = '+';
                } else if (editingMatrix[i][j-1] + 1 == min) {
                    actionsMatrix[i][j] = '-';
                } else if (editingMatrix[i-1][j-1] + cost == min) {
                    actionsMatrix[i][j] = '~';
                }

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

        System.out.print("    ");
        for (int i = 0; i < one.length(); i++) {
            System.out.print(one.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < actionsMatrix.length; i++) {
            if (i>0) {
                System.out.print(two.charAt(i-1) + " ");
            } else {System.out.print("  ");}
            for (int j = 0; j < actionsMatrix[i].length; j++) {
                System.out.print(actionsMatrix[i][j] + " ");
            }
            System.out.println();
        }
        // восстановление пути c конца матрицы
        StringBuilder result = new StringBuilder();
        int i = two.length(), j = one.length();
        while (i > 0 && j > 0) {
            char action = actionsMatrix[i][j];
            if (action == '#') {
                result.insert(0, "#,");
                i--;
                j--;
            } else if (action == '~') {
                result.insert(0, "~" + two.charAt(i - 1) + ",");
                i--;
                j--;
            } else if (action == '+') {
                result.insert(0, "+" + two.charAt(i - 1) + ",");
                i--;
            } else {
                result.insert(0, "-" + one.charAt(j - 1) + ",");
                j--;
            }
        }

        // если остались символы в строке two
        while (i > 0) {
            result.insert(0, "+" + two.charAt(i - 1) + ",");
            i--;
        }

        // если остались символы в строке one
        while (j > 0) {
            result.insert(0, "-" + one.charAt(j - 1) + ",");
            j--;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}