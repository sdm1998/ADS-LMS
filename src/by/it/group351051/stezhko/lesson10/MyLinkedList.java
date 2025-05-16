package by.it.group351051.stezhko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    private E[] data =  (E[]) new Object[0];
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < data.length; i++) {
            result += data[i].toString();
            if (i != data.length - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    @Override
    public boolean add(E element) {
        E[] temp = (E[]) new Object[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        temp[data.length] = element;
        data = temp;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= data.length) return null;
        E[] temp = (E[]) new Object[data.length - 1];
        E removedEl = null;
        for (int i = 0, d = 0; i < data.length; i++) {
            if (d == 0 && i == index) {
                d = 1;
                removedEl = data[i];
                continue;
            }
            temp[i - d] = data[i];
        }
        data = temp;
        return removedEl;
    }
    public boolean remove(Object o) {
        return this.remove(this.indexOf(o)) == null ? false : true;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (o.equals(data[i])) return i;
        }
        return -1;
    }
    @Override
    public int size() {
        return data.length;
    }

    @Override
    public void addFirst(E element) {
        E[] temp = (E[]) new Object[data.length + 1];
        temp[0] = element;
        for (int i = 0; i < data.length; i++) {
            temp[i + 1] = data[i];
        }
        data = temp;
    }
    @Override
    public void addLast(E element) {
        this.add(element);
    }

    @Override
    public E element() {
        return this.size() > 0 ? data[0] : null;
    }

    @Override
    public E getFirst() {
        return this.size() > 0 ? data[0] : null;
    }

    @Override
    public E getLast() {
        int len = this.size();
        return len > 0 ? data[len - 1] : null;
    }

    @Override
    public E poll() {
        return this.pollFirst();
    }

    @Override
    public E pollFirst() {
        int len = this.size();
        if (len == 0) return null;
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Object[len - 1];
        E removedEl = data[0];
        for (int i = 1; i < len; i++) {
            temp[i - 1] = data[i];
        }
        data = temp;
        return removedEl;
    }

    @Override
    public E pollLast() {
        int len = this.size();
        if (len == 0) return null;
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Object[len - 1];
        E removedEl = data[len - 1];
        for (int i = 0; i < len - 1; i++) {
            temp[i] = data[i];
        }
        data = temp;
        return removedEl;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
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
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean offerFirst(E element) {
        return false;
    }

    @Override
    public boolean offerLast(E element) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }
    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }
}
