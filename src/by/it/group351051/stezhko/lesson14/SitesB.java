package by.it.group351051.stezhko.lesson14;

import java.util.*;

public class SitesB {
    static class DSU {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> size = new HashMap<>();
        String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                size.put(x, 1);
            }
            if (!parent.get(x).equals(x)) parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }
        void union(String[] xy) {
            String rx = find(xy[0]);
            String ry = find(xy[1]);
            if (!rx.equals(ry)) {
                if (size.get(rx) < size.get(ry)) {
                    String t = rx;
                    rx = ry;
                    ry = t;
                }
                parent.put(ry, rx);
                size.put(rx, size.get(rx) + size.get(ry));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DSU dsu = new DSU();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            dsu.union(line.split("\\+"));
        }
        Map<String, Integer> groups = new HashMap<>();
        for (String site : dsu.parent.keySet()) {
            String root = dsu.find(site);
            groups.put(root, groups.getOrDefault(root, 0) + 1);
        }
        List<Integer> result = new ArrayList<>(groups.values());
        result.sort(Collections.reverseOrder());
        for (int size : result)
            System.out.print(size + " ");
    }
}
