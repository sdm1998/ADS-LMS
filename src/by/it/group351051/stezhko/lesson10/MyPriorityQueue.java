package by.it.group351051.stezhko.lesson10;

import java.util.Queue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class MyPriorityQueue<E> implements Queue<E> {
    private E[] data = (E[]) new Object[50];
    private int size = 0;

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            result += data[i].toString();
            if (i != size - 1) result += ", ";
        }
        return result + "]";
    }
    @Override
    public int size() { return size; }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }
    @Override
    public boolean add(E e) {
        if (size >= data.length) {
            int s = data.length * 2;
            E[] t = (E[]) new Object[s];
            System.arraycopy(data, 0, t, 0, size);
            data = t;
        }
        data[size] = e;
        this.siftUp(size);
        size++;
        return true;
    }
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++)
            if (Objects.equals(data[i], o)) {
                this.removeByIndex(i);
                return true;
            }
        return false;
    }
    @Override
    public E remove() { return this.isEmpty() ? null : this.poll(); }
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (Objects.equals(data[i], o)) return true;
        return false;
    }

    @Override
    public boolean offer(E element) { return this.add(element); }
    @Override
    public E poll() {
        if (this.isEmpty()) return null;
        E e = data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        this.siftDown(0);
        return e;
    }
    @Override
    public E peek() { return this.element(); }
    @Override
    public E element() { return this.isEmpty() ? null : data[0]; }
    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] col = c.toArray();
        for(int i = 0; i < col.length; i++)
            if(!this.contains(col[i])) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] col = c.toArray();
        boolean a = false;
        for(int i = 0; i < col.length; i++)
            if (this.add((E)col[i])) a = true;
        return a;
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        E[] t = (E[]) new Object[data.length];
        int ns = 0;
        boolean c = false;
        for (int i = 0; i < size; i++)
            if (!col.contains(data[i])) {
                t[ns] = data[i];
                ns++;
            }
        if (ns != size) {
            data = t;
            size = ns;
            for (int i = (size / 2) - 1; i >= 0; i--) this.siftDown(i);
            c = true;
        }
        return c;
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        E[] t = (E[]) new Object[data.length];
        int ns = 0;
        boolean c = false;
        for (int i = 0; i < size; i++)
            if (col.contains(data[i])) {
                t[ns] = data[i];
                ns++;
            }
        if (ns != size) {
            data = t;
            size = ns;
            for (int i = (size / 2) - 1; i >= 0; i--) this.siftDown(i);
            c = true;
        }
        return c;
    }

    @Override
    public Iterator<E> iterator() { return null; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return null; }

    private void siftDown(int index) {
        while (true) {
            int c1 = 2 * index + 1;
            int c2 = 2 * index + 2;
            int mi = index;
            if (c1 < size && this.compareObjects(data[c1], data[mi]) < 0) mi = c1;
            if (c2 < size && this.compareObjects(data[c2], data[mi]) < 0) mi = c2;
            if (mi == index) break;
            E t = data[index];
            data[index] = data[mi];
            data[mi] = t;
            index = mi;
        }
    }
    private void siftUp(int index) {
        while (index > 0) {
            int pi = (index - 1) / 2;
            if (this.compareObjects(data[index], data[pi]) >= 0) break;
            E t = data[index];
            data[index] = data[pi];
            data[pi] = t;
            index = pi;
        }
    }
    private int compareObjects(E a, E b) {
        if (a == null && b == null) return 0;
        else if (a == null) return -1;
        else if (b == null) return 1;
        else return ((Comparable<? super E>) a).compareTo(b);
    }
    private void removeByIndex(int index) {
        if (index < 0 || index >= size) return;
        data[index] = data[size - 1];
        data[size - 1] = null;
        size--;
        if (index > 0 && this.compareObjects(data[index], data[(index - 1) / 2]) < 0) this.siftUp(index);
        else this.siftDown(index);
    }
}