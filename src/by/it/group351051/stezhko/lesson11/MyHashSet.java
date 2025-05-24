package by.it.group351051.stezhko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private E[] data =  (E[]) new Object[0];
    @Override
    public int size() { return data.length; }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < data.length; i++) {
            result += data[i].toString();
            if (i != data.length - 1) result += ", ";
        }
        return result + "]";
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (o.equals(data[i])) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (!this.contains(e)) {
            E[] t = (E[]) new Object[data.length + 1];
            for (int i = 0; i < data.length; i++) t[i] = data[i];
            t[data.length] = e;
            data = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!this.contains(o)) return false;
        E[] t = (E[]) new Object[data.length - 1];
        boolean r = false;
        for (int i = 0, d = 0; i < data.length; i++) {
            if (d == 0 && o.equals(data[i])) {
                d = 1;
                r = true;
                continue;
            }
            t[i - d] = data[i];
        }
        data = t;
        return r;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        data =  (E[]) new Object[0];
    }
}
