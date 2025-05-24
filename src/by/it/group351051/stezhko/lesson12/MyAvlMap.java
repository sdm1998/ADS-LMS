package by.it.group351051.stezhko.lesson12;

import java.util.*;

public class MyAvlMap implements Map<Integer, String> {
    private Node root;
    private int size;
    private static class Node {
        int height = 1;
        Integer key;
        String value;
        Node left;
        Node right;
        Node(Integer key, String value) {
            this.key = key;
            this.value = value;
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
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean containsKey(Object key) { return this.findNode(root, (Integer) key) != null; }

    @Override
    public boolean containsValue(Object value) { return false; }

    @Override
    public String get(Object key) {
        Node n = this.findNode(root, (Integer) key);
        return n == null ? null : n.value;
    }

    @Override
    public String put(Integer key, String value) {
        Node n = this.findNode(root, key);
        if (n != null) {
            String v = n.value;
            n.value = value;
            return v;
        }
        root = this.addNode(root, key, value);
        size++;
        return null;
    }

    @Override
    public String remove(Object key) {
        Integer k = (Integer) key;
        Node n = this.findNode(root, k);
        if (n == null) return null;
        root = this.removeNode(root, k);
        size--;
        return n.value;
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
    public String toString() {
        return root == null ? "{}" : root.toString();
    }

    private Node addNode(Node n, int k, String v) {
        if (n == null) return new Node(k, v);
        if (k < n.key) n.left = this.addNode(n.left, k, v);
        else if (k > n.key) n.right = this.addNode(n.right, k, v);
        else {
            n.value = v;
            return n;
        }
        n.height = Math.max(this.evalHeight(n.left), this.evalHeight(n.right)) + 1;
        return this.fixTree(n);
    }

    private Node fixTree(Node n) {
        int cmp = (n == null ? 0 : this.evalHeight(n.left) - this.evalHeight(n.right));
        if (cmp < -1) {
            cmp = (n == null ? 0 : this.evalHeight(n.right.left) - this.evalHeight(n.right.right));
            if (cmp <= 0) return this.rotateTreeToLeft(n);
            else {
                n.right = this.rotateTreeToRight(n.right);
                return this.rotateTreeToLeft(n);
            }
        } else if (cmp > 1) {
            cmp = (n == null ? 0 : this.evalHeight(n.left.left) - this.evalHeight(n.left.right));
            if (cmp >= 0) return this.rotateTreeToRight(n);
            else {
                n.left = this.rotateTreeToLeft(n.left);
                return this.rotateTreeToRight(n);
            }
        } else {
            return n;
        }
    }

    private Node rotateTreeToLeft(Node nl) {
        Node t = nl.right;
        Node nnl = t.left;
        t.left = nl;
        nl.right = nnl;
        nl.height = Math.max(this.evalHeight(nl.left), this.evalHeight(nl.right)) + 1;
        t.height = Math.max(this.evalHeight(t.left), this.evalHeight(t.right)) + 1;
        return t;
    }

    private Node rotateTreeToRight(Node nr) {
        Node t = nr.left;
        Node nnr = t.right;
        t.right = nr;
        nr.left = nnr;
        nr.height = Math.max(this.evalHeight(nr.left), this.evalHeight(nr.right)) + 1;
        t.height = Math.max(this.evalHeight(t.left), this.evalHeight(t.right)) + 1;
        return t;
    }
    private Node removeNode(Node n, int k) {
        if (n == null) return null;
        if (k < n.key) n.left = this.removeNode(n.left, k);
        else if (k > n.key) n.right = this.removeNode(n.right, k);
        else {
            if (n.left == null || n.right == null)
                n = (n.left == null ? n.right : n.left);
            else {
                Node t = this.findMin(n.right);
                n.key = t.key;
                n.value = t.value;
                n.right = this.removeNode(n.right, t.key);
            }
        }
        if (n == null) return null;
        n.height = Math.max(this.evalHeight(n.left), this.evalHeight(n.right)) + 1;
        return this.fixTree(n);
    }

    private Node findMin(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }
    private Node findNode(Node n, int k) {
        if (n == null) return null;
        if (k < n.key) return this.findNode(n.left, k);
        else if (k > n.key) return this.findNode(n.right, k);
        else return n;
    }
    private int evalHeight(Node node) { return (node == null) ? 0 : node.height; }
}
