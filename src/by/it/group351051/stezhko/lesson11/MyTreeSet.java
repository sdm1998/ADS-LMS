package by.it.group351051.stezhko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private E[] data =  (E[]) new Object[0];
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < data.length; i++) {
            result += data[i] == null ? "null" : data[i].toString();
            if (i != data.length - 1) result += ", ";
        }
        return result + "]";
    }
    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
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
    private int binarySearch(Object o) {
        int min = 0;
        int max = data.length - 1;
        while (min <= max) {
            int mid = (int)Math.floor((min + max) / 2);
            Comparable<? super E> val = (Comparable<? super E>) data[mid];
            int c = val.compareTo((E) o);
            if (c < 0)
                min = mid + 1;
            else if (c > 0)
                max = mid - 1;
            else
                return mid;
        }
        return -(min + 1);
    }
    @Override
    public boolean add(E e) {
        if (this.contains(e)) return false;
        int ix = this.binarySearch(e);
        if (ix < 0)
            ix = -(ix + 1);
        int len = data.length + 1;
        E[] t = (E[]) new Object[len];
        for (int i = 0, d = 0; i < len; i++) {
            if (i == ix) {
                d = 1;
                t[i] = e;
            } else t[i] = data[i - d];
        }
        data = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int ix = this.binarySearch((E)o);
        if (ix < 0) return false;
        E[] t = (E[]) new Object[data.length - 1];
        for (int i = 0, d = 0; i < data.length; i++) {
            if (i == ix) {
                d = 1;
                continue;
            }
            t[i - d] = data[i];
        }
        data = t;
        return true;
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
        for(int i = 0; i < t.length; i++)
            if (this.add((E)t[i])) a = true;
        return a;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean r = false;
        for (int i = 0; i < data.length; i++) {
            if (!c.contains((E) data[i])) {
                this.remove(data[i]);
                i--;
                r = true;
            }
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
    public void clear() { data =  (E[]) new Object[0]; }
}
