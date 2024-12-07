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

    int[][] getArrD(String s1, String s2)
    {

        int[][] d = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            //перебор столбцов с номерами j
            for (int j = 0; j <= s2.length(); j++) {
                d[i][j] = D(d, i, j, s1, s2);
            }

        }
        return d;
    }

    String getDistanceEdinting(String s1, String s2) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] d = getArrD(s1, s2);



        int i = s1.length(), j = s2.length();
        StringBuilder result = new StringBuilder();

        while (i > 0 && j > 0) {
            //если буквы совпадают
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                result.append("#,");
                i--;
                j--;

            }
            //замена буквы s2.charAt(j - 1)
            else if (d[i][j] == d[i - 1][j - 1] + 1) {
                result.append("~").append(s2.charAt(j - 1)).append(",");
                i--;
                j--;
            }
            //вставка в вторую стр
            else if (d[i][j] == d[i][j - 1] + 1) {
                result.append("+").append(s2.charAt(j - 1)).append(",");
                j--;
            }
            //удаление из вторрой строки
            else {
                result.append("-").append(s1.charAt(i - 1)).append(",");
                i--;
            }
        }

        //все что осталось удаляем из первой
        while (i > 0) {
            result.append("-").append(s1.charAt(i - 1)).append(",");
            i--;
        }
//все что соатлось добавляем к второй
        while (j > 0) {
            result.append("+").append(s2.charAt(j - 1)).append(",");
            j--;
        }


        return getReverse(result.toString().split(","));
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    //перевернуть массив операций
    public String getReverse(String[] arr)
    {

        //цикл до половины
        for(int i = 0; i < arr.length / 2; i++)
        {
            //меняем с разных сторонн
            String temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }

        return String.join(",", arr) + ",";


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