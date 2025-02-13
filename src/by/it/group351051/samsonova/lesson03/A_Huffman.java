package by.it.group351051.samsonova.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {


    abstract class Node implements Comparable<Node> {
        private final int frequency;

        abstract void fillCodes(String code);

        private Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.frequency, o.frequency);
        }

        public int getFrequency() {
            return frequency;
        }
    }

    //внутренний узел дерева
    private class InternalNode extends Node {
        Node left;
        Node right;

        InternalNode(Node left, Node right) {
            super(left.getFrequency() + right.getFrequency());
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }
    }

    //лситовой узел дерева
    private class LeafNode extends Node {
        char symbol;

        LeafNode(int frequency, char symbol) {
            super(frequency);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    private static Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        //читаем строку из файла
        Scanner scanner = new Scanner(file);
        String s = scanner.next();
        scanner.close();

        //расчет чистоты символов
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        //очередь с приор.
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new LeafNode(entry.getValue(), entry.getKey()));
        }

        //huffman tree
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.offer(new InternalNode(left, right));
        }

        //коды
        Node root = priorityQueue.poll();
        if (root != null) {
            root.fillCodes("");
        }

        //кодируем исходную строку
        StringBuilder encodedString = new StringBuilder();
        for (char c : s.toCharArray()) {
            encodedString.append(codes.get(c));
        }

        //вывод результатов
        System.out.printf("%d %d\n", codes.size(), encodedString.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(encodedString);

        return encodedString.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File file = new File(root + "by/it/group351051/samsonova/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(file);
    }
}


