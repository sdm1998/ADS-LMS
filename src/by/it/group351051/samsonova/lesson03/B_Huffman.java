package by.it.group351051.samsonova.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class B_Huffman {

    class Node {
        boolean isLeaf;
        char symbol;
        Node left, right;

        Node(boolean isLeaf, char symbol) {
            this.isLeaf = isLeaf;
            this.symbol = symbol;
        }
    }

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(file);

        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine(); //переход на след. строку

        Map<String, Character> codeToChar = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] pair = line.split(": ", 2);
            codeToChar.put(pair[1], pair[0].charAt(0));
        }

        String encodedString = scanner.next();
        scanner.close();

        Node root = buildTree(codeToChar);

        Node current = root;
        for (char bit : encodedString.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.isLeaf) {
                result.append(current.symbol);
                current = root;
            }
        }

        return result.toString();
    }

    private Node buildTree(Map<String, Character> codeToChar) {
        Node root = new Node(false, ' ');

        for (Map.Entry<String, Character> entry : codeToChar.entrySet()) {
            Node current = root;
            for (char bit : entry.getKey().toCharArray()) {
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new Node(false, ' ');
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(false, ' ');
                    }
                    current = current.right;
                }
            }
            current.isLeaf = true;
            current.symbol = entry.getValue();
        }

        return root;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group351051/samsonova/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}

