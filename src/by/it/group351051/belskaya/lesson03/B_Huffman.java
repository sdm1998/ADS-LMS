package by.it.group351051.belskaya.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine();

        Map<String, Character> codes = new HashMap<>();

        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            char letter = parts[0].charAt(0);
            String code = parts[1];
            codes.put(code, letter);
        }

        String encoded = scanner.nextLine();

        if (encoded.length() != length) {
            throw new IllegalArgumentException("Длина закодированной строки не совпадает с указанной длиной!");
        }

        StringBuilder currentCode = new StringBuilder();

        for (char item : encoded.toCharArray()) {
            currentCode.append(item);

            if (codes.containsKey(currentCode.toString())) {
                result.append(codes.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group351051/belskaya/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}
