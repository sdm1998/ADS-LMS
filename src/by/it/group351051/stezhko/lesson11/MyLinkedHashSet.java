package by.it.group351051.stezhko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private int size = 0;
    private Node head = null;
    private Node tail = null;
    private static class Node<E> {
        E value;
        Node<E> prev = null;
        Node<E> next = null;

        Node(E e) {
            this.value = e;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        Node t = head;
        while(t != null) {
            result += t.value.toString();
            if (t.next != null) result += ", ";
            t = t.next;
        }
        return result + "]";
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean contains(Object o) {
        Node t = head;
        while(t != null) {
            if (o.equals(t.value)) return true;
            t = t.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() { return null; }

    @Override
    public Object[] toArray() { return new Object[0]; }

    @Override
    public <T> T[] toArray(T[] a) { return null; }

    @Override
    public boolean add(E e) {
        if (this.contains(e)) return false;
        Node node = new Node(e);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node t = head;
        while(t != null) {
            if (o.equals(t.value)) {
                size--;
                if (size == 0) this.clear();
                else {
                    Node prev = t.prev;
                    Node next = t.next;
                    if (prev != null) prev.next = next;
                    else {
                        next.prev = null;
                        head = next;
                    }
                    if (next != null) next.prev = prev;
                    else {
                        prev.next = null;
                        tail = prev;
                    }
                }
                return true;
            }
            t = t.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] t = c.toArray();
        for(int i = 0; i < t.length; i++) {
            if(!this.contains(t[i])) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] t = c.toArray();
        boolean a = false;
        for(int i = 0; i < t.length; i++) {
            this.add((E)t[i]);
            a = true;
        }
        return a;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean r = false;
        Node t = head;
        while(t != null) {
            if (!c.contains(t.value)) {
                this.remove(t.value);
                r = true;
            }
            t = t.next;
        }
        return r;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] t = c.toArray();
        boolean r = false;
        for(int i = 0; i < t.length; i++)
            if (this.remove((E)t[i])) r = true;
        return r;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
