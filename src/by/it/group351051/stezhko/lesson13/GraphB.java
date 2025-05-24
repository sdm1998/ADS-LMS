package by.it.group351051.stezhko.lesson13;

import java.util.*;

public class GraphB {
    private static boolean recursionCycle(String vertex, Map<String, List<String>> graph, Set<String> visited, Set<String> recursionStack) {
        if (recursionStack.contains(vertex)) return true;
        if (visited.contains(vertex)) return false;
        visited.add(vertex);
        recursionStack.add(vertex);
        for (String vertexTo : graph.getOrDefault(vertex, new ArrayList<>()))
            if (recursionCycle(vertexTo, graph, visited, recursionStack)) return true;
        recursionStack.remove(vertex);
        return false;
    }
    private static String hasCycle(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();
        for (String v : graph.keySet())
            if (recursionCycle(v, graph, visited, recursionStack)) return "yes";
        return "no";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> graph = new HashMap<>();
        for (String edge : input.split(", ")) {
            String[] t = edge.split(" -> ");
            String vertexFrom = t[0];
            String vertexTo = t[1];
            graph.putIfAbsent(vertexFrom, new ArrayList<>());
            graph.get(vertexFrom).add(vertexTo);
        }
        System.out.println(hasCycle(graph));
    }
}
