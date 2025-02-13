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

    int Min(int a, int b ,int c)
    {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }


    int M(char a, char b)
    {
        if(a == b)
        {
            return 0;
        }
        return 1;
    }

    //по формуле из  https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    int D(int i, int j, String s1, String s2) {

        if (i == 0 && j == 0) {
            return 0;
        }
        else if(j == 0 && i > 0)
        {
            return i;
        }
        else if(i == 0 && j > 0)
        {
            return j;
        }
        else //if(j > 0 && i > 0)
        {
            int d1 = D(i, j-1, s1, s2) + 1;
            int d2 = D( i -1, j, s1, s2) + 1;
            int d3 = D(i - 1, j - 1, s1, s2) + M(s1.charAt(i-1), s2.charAt(j-1));
            return Min(d1, d2, d3);
        }

    }

    int getDistanceEdinting(String s1, String s2) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int result = D(s1.length(), s2.length(), s1, s2);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
