package by.it.group351052.kozhemyakin.a_khmelev.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.
//
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
//
//        Sample Output 1:
//        a
//
//
//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111
//
//        Sample Output 2:
//        abacabad

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();


        Map<String, Character> decodeMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            // Пример строки:  a: 0
            // Считаем всю строку целиком:
            String line = scanner.nextLine().trim();
            // Если строка пустая (например, после считывания числа), повторим чтение:
            if (line.isEmpty()) {
                line = scanner.nextLine().trim();
            }
            // Разделяем по ": " — [a, 0]
            String[] parts = line.split(": ");
            char letter = parts[0].charAt(0);
            String code = parts[1];
            decodeMap.put(code, letter);
        }

        // Считываем закодированную строку:
        String encoded = scanner.next();

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            temp.append(encoded.charAt(i));
            if (decodeMap.containsKey(temp.toString())) {
                result.append(decodeMap.get(temp.toString()));
                temp.setLength(0);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}
