package by.it.group351051.stezhko.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    private static final int RED = 1;
    private static final int BLACK = 2;
    private Node root;
    private int size = 0;
    private static class Node {
        Integer key;
        String value;
        Node left;
        Node right;
        int color;

        Node(Integer key, String value, int color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public void toOppositeColor() {
            color = (color == RED ? BLACK : RED);
            left.color = (left.color == RED ? BLACK : RED);
            right.color = (right.color == RED ? BLACK : RED);
        }
        private String toStringNode() {
            return key + "=" + value;
        }
        private void treeToString(StringBuilder result) {
            if (left != null) left.treeToString(result);
            result.append(this.toStringNode());
            result.append(", ");
            if (right != null) right.treeToString(result);
        }
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            this.treeToString(result);
            result.setLength(result.length() - 2);
            result.append("}");
            return result.toString();
        }
    }
    @Override
    public Comparator<? super Integer> comparator() { return null; }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) { return null; }

    @Override
    public SortedMap<Integer, String> headMap(Integer k) { return this.headMap(root, k, new MyRbMap()); }

    @Override
    public SortedMap<Integer, String> tailMap(Integer k) { return this.tailMap(root, k, new MyRbMap()); }

    @Override
    public Integer firstKey() {
        Node n = root;
        while (n.left != null) n = n.left;
        return n.key;
    }

    @Override
    public Integer lastKey() {
        Node n = root;
        while (n.right != null) n = n.right;
        return n.key;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean containsKey(Object k) { return this.get((Integer) k) != null; }

    @Override
    public boolean containsValue(Object v) { return this.containsValue(root, v.toString()); }

    @Override
    public String get(Object key) {
        Integer k = (Integer) key;
        Node n = root;
        while (n != null) {
            int t = k.compareTo(n.key);
            if (t > 0) n = n.right;
            else if (t < 0) n = n.left;
            else return n.value;
        }
        return null;
    }

    @Override
    public String put(Integer k, String v) {
        String nv = this.get(k);
        root = this.putNode(root, k, v);
        root.color = BLACK;
        return nv;
    }

    @Override
    public String remove(Object key) {
        Integer k = (Integer) key;
        if (!this.containsKey(k)) return null;
        String v = this.get(k);
        root = this.removeNode(root, k);
        if (root != null) root.color = BLACK;
        return v;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {}

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<Integer> keySet() { return null; }

    @Override
    public Collection<String> values() { return null; }

    @Override
    public Set<Entry<Integer, String>> entrySet() { return null; }

    @Override
    public String toString() { return root == null ? "{}" : root.toString(); }
    private boolean isRedNode(Node n) { return n != null && (n.color == RED); }

    private Node rotateTreeToLeft(Node n) {
        Node nn = n.right;
        n.right = nn.left;
        nn.left = n;
        nn.color = n.color;
        n.color = RED;
        return nn;
    }

    private Node rotateTreeToRight(Node n) {
        Node nn = n.left;
        n.left = nn.right;
        nn.right = n;
        nn.color = n.color;
        n.color = RED;
        return nn;
    }

    private Node fixTree(Node n) {
        if (this.isRedNode(n.right)) n = this.rotateTreeToLeft(n);
        if (this.isRedNode(n.left) && this.isRedNode(n.left.left)) n = this.rotateTreeToRight(n);
        if (this.isRedNode(n.left) && this.isRedNode(n.right)) n.toOppositeColor();
        return n;
    }


    private Node removeMinNode(Node n) {
        if (n.left == null) return null;
        if (!this.isRedNode(n.left) && !this.isRedNode(n.left.left)) {
            n.toOppositeColor();
            if (this.isRedNode(n.right.left)) {
                n.right = this.rotateTreeToRight(n.right);
                n = this.rotateTreeToLeft(n);
                n.toOppositeColor();
            }
        }
        n.left = this.removeMinNode(n.left);
        return this.fixTree(n);
    }
    private Node removeNode(Node n, Integer k) {
        if (k.compareTo(n.key) >= 0) {
            if (this.isRedNode(n.left)) n = this.rotateTreeToRight(n);
            if (k.compareTo(n.key) == 0 && (n.right == null)) {
                size--;
                return null;
            }
            if (!this.isRedNode(n.right) && !this.isRedNode(n.right.left)) {
                n.toOppositeColor();
                if (this.isRedNode(n.left.left)) {
                    n = this.rotateTreeToRight(n);
                    n.toOppositeColor();
                }
            }
            if (k.compareTo(n.key) == 0) {
                Node t = n.right;
                while (t.left != null) t = t.left;
                n.key = t.key;
                n.value = t.value;
                n.right = this.removeMinNode(n.right);
                size--;
            } else {
                n.right = this.removeNode(n.right, k);
            }
        } else {
            if (!this.isRedNode(n.left) && !this.isRedNode(n.left.left)) {
                n.toOppositeColor();
                if (this.isRedNode(n.right.left)) {
                    n.right = this.rotateTreeToRight(n.right);
                    n = rotateTreeToLeft(n);
                    n.toOppositeColor();
                }
            }
            n.left = this.removeNode(n.left, k);
        }
        return this.fixTree(n);
    }
    private Node putNode(Node n, Integer k, String v) {
        if (n == null) {
            size++;
            return new Node(k, v, RED);
        }
        int t = k.compareTo(n.key);
        if (t > 0) n.right = this.putNode(n.right, k, v);
        else if (t < 0) n.left = this.putNode(n.left, k, v);
        else n.value = v;
        if (this.isRedNode(n.right) && !this.isRedNode(n.left)) n = rotateTreeToLeft(n);
        if (this.isRedNode(n.left) && this.isRedNode(n.left.left)) n = rotateTreeToRight(n);
        if (this.isRedNode(n.left) && this.isRedNode(n.right)) n.toOppositeColor();
        return n;
    }
    private boolean containsValue(Node n, String v) {
        return n != null && (v.equals(n.value) || this.containsValue(n.left, v) || this.containsValue(n.right, v));
    }
    private MyRbMap tailMap(Node n, Integer k, MyRbMap rbMap) {
        if (n == null) return rbMap;
        else if (n.key.compareTo(k) >= 0) {
            rbMap.put(n.key, n.value);
            this.tailMap(n.left, k, rbMap);
        }
        this.tailMap(n.right, k, rbMap);
        return rbMap;
    }
    private MyRbMap headMap(Node n, Integer k, MyRbMap rbMap) {
        if (n == null) return rbMap;
        else if (n.key.compareTo(k) < 0) {
            rbMap.put(n.key, n.value);
            this.headMap(n.right, k, rbMap);
        }
        this.headMap(n.left, k, rbMap);
        return rbMap;
    }
}
