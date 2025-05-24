package by.it.group351051.stezhko.lesson14;

import java.util.*;

public class StatesHanoiTowerC {
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

    static void hanoi(int n, char from, char to, char aux, DSU dsu, int[] heights, int step, List<Integer> maxHeights) {
        if (n == 1) {
            heights[from - 'A']--;
            heights[to - 'A']++;
            int maxHeight = Math.max(Math.max(heights[0], heights[1]), heights[2]);
            maxHeights.add(maxHeight);
            return;
        }
        hanoi(n - 1, from, aux, to, dsu, heights, step, maxHeights);
        heights[from - 'A']--;
        heights[to - 'A']++;
        int maxHeight = Math.max(Math.max(heights[0], heights[1]), heights[2]);
        maxHeights.add(maxHeight);
        hanoi(n - 1, aux, to, from, dsu, heights, step + 1, maxHeights);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        DSU dsu = new DSU((int) Math.pow(2, N));
        int[] heights = new int[3];
        heights[0] = N;
        List<Integer> maxHeights = new ArrayList<>();
        hanoi(N, 'A', 'B', 'C', dsu, heights, 1, maxHeights);
        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < maxHeights.size(); i++) {
            int h = maxHeights.get(i);
            groups.put(h, groups.getOrDefault(h, 0) + 1);
        }
        List<Integer> result = new ArrayList<>(groups.values());
        Collections.sort(result);
        for (int size : result)
            System.out.print(size + " ");
    }
}
