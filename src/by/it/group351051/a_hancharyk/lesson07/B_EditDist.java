package by.it.group351051.a_hancharyk.lesson07;

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
   //Минимальный их трех значений
    int Min(int a, int b ,int c)
    {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }

//проверка отсуствия изменений
    int M(char a, char b)
    {
        if(a == b)
        {
            return 0;
        }
        return 1;
    }

    //D(i,j) по формуле //по формуле из  https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    int D(int d[][], int i, int j, String s1, String s2)
    {
        if (i == 0 && j == 0) {
            return  0;
        } else if (j == 0 && i > 0) {
            return i;
        } else if (i == 0 && j > 0) {
            return j;
        }
        else {
            int m = M(s1.charAt(i - 1), s2.charAt(j - 1));
            int d1 = d[i][j - 1] + 1;
            int d2 = d[i - 1][j] + 1;
            int d3 = d[i - 1][j - 1] + m;

            return Min(d1, d2, d3);
        }
    }

    int getDistanceEdinting(String s1, String s2) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //для создания доп столбца и строки с 0 как в таблице
        int[][] d = new int[s1.length() + 1][s2.length() + 1];

        //перебор строк с номерами i
        for (int i = 0; i <= s1.length(); i++) {
            //перебор столбцов с номерами j
            for (int j = 0; j <= s2.length(); j++) {
                d[i][j] = D(d, i, j, s1, s2);
            }

        }
        return d[s1.length()][s2.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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