package by.it.group351051.stezhko.lesson14;

import java.util.*;

public class PointsA {
    static class DSU {
        int[] parent;
        int[] size;
        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                if (size[rx] < size[ry]) {
                    int temp = rx;
                    rx = ry;
                    ry = temp;
                }
                parent[ry] = rx;
                size[rx] += size[ry];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distance = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] points = new int[n][3];
        for (int i = 0; i < n; i++) {
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();
            points[i][2] = scanner.nextInt();
        }
        DSU dsu = new DSU(n);
        for (int i = 0, d2 = distance * distance; i < n; i++) {
            int ax = points[i][0];
            int ay = points[i][1];
            int az = points[i][2];
            for (int j = i + 1; j < n; j++) {
                int bx = points[j][0];
                int by = points[j][1];
                int bz = points[j][2];
                if (Math.pow(ax - bx, 2) + Math.pow(ay - by, 2) + Math.pow(az - bz, 2) < d2)
                    dsu.union(i, j);
            }
        }
        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            groups.put(root, groups.getOrDefault(root, 0) + 1);
        }
        List<Integer> result = new ArrayList<>(groups.values());
        result.sort(Collections.reverseOrder());
        for (int size : result)
            System.out.print(size + " ");
    }
}
