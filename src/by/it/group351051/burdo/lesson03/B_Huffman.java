package by.it.group351051.burdo.lesson03;

import by.it.a_khmelev.lesson02.C_GreedyKnapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        System.out.println(scanner);
        System.out.println(count);
        System.out.println(length);

        Map<Character, String> codesMap = new HashMap<>();
        // регулярное выражение для поиска символов и значений
        Pattern pattern = Pattern.compile("([a-zA-Z]):\\s*(\\S+)");
        for (int i = 0; i <= count; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                char key = matcher.group(1).charAt(0);  // получаем букву (ключ)
                String value = matcher.group(2);  // получаем значение
                // Добавляем в словарь
                codesMap.put(key, value);
            }
        }
        String encodedString = scanner.nextLine().trim();
        System.out.println(encodedString);
        while (encodedString.length() > 0) {
            for (char symbol : codesMap.keySet()) {
                if (encodedString.startsWith(codesMap.get(symbol))) { // если стока начинается с кода проверяемого символа
                    result.append(symbol);
                    // добавляем символ к ответу и подрезаем начало стоки
                    encodedString = encodedString.substring(codesMap.get(symbol).length());
                }
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //abacabad
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group351051/burdo/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }

}
