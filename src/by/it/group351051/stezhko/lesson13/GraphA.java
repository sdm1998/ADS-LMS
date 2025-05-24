package by.it.group351051.stezhko.lesson13;

import java.util.*;

public class GraphA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (String edge : input.split(", ")) {
            String[] t = edge.split(" -> ");
            String vertexFrom = t[0];
            String vertexTo = t[1];
            graph.putIfAbsent(vertexFrom, new ArrayList<>());
            graph.get(vertexFrom).add(vertexTo);
            inDegree.putIfAbsent(vertexFrom, 0);
            inDegree.put(vertexTo, inDegree.getOrDefault(vertexTo, 0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<>();
        for (String key : graph.keySet())
            if (inDegree.get(key) == 0) queue.add(key);
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String vertexFrom = queue.poll();
            result.add(vertexFrom);
            if (graph.containsKey(vertexFrom)) {
                for (String vertexTo : graph.get(vertexFrom)) {
                    inDegree.put(vertexTo, inDegree.get(vertexTo) - 1);
                    if (inDegree.get(vertexTo) == 0) queue.add(vertexTo);
                }
            }
        }
        System.out.println(String.join(" ", result));
    }
}
