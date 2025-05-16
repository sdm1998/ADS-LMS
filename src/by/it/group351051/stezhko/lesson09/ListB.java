package by.it.group351051.stezhko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {

    private E[] data =  (E[]) new Object[0];
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
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
    public boolean add(E e) {
        E[] temp = (E[]) new Object[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        temp[data.length] = e;
        data = temp;
        return true;
    }

    @Override
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

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= data.length) return;
        E[] temp = (E[]) new Object[data.length + 1];
        for (int i = 0, d = 0; i < data.length; i++) {
            if (i == index) {
                d = 1;
                temp[i] = element;
            }
            temp[i + d] = data[i];
        }
        data = temp;
    }

    @Override
    public boolean remove(Object o) {
        E[] temp = (E[]) new Object[data.length - 1];
        if (!this.contains(o)) return false;
        boolean removed = false;
        for (int i = 0, d = 0; i < data.length; i++) {
            if (d == 0 && o.equals(data[i])) {
                d = 1;
                removed = true;
                continue;
            }
            temp[i - d] = data[i];
        }
        data = temp;
        return removed;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= data.length) return null;
        E oldEl = data[index];
        data[index] = element;
        return oldEl;
    }


    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }


    @Override
    public void clear() {
        data =  (E[]) new Object[0];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (o.equals(data[i])) return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= data.length) return null;
        return data[index];
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (o.equals(data[i])) return i;
        }
        return -1;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
