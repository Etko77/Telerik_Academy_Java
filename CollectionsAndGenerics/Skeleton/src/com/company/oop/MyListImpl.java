package com.company.oop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements MyList<T> {


    private final int CAPACITY = 4;
    private int size;
    private T[] data;
    public MyListImpl() {
        data = (T[]) new Object [CAPACITY];
        size = 0;
    }
    public MyListImpl(int num) {
        data = (T[]) new Object [num];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        data[size++] =element;

    }
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length + 1);
        }
    }


    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public int indexOf(T element) {

        for (int i = 0; i < size;i++) {
            if (element.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size-1; i >= 0;i--) {
            if (element.equals(data[i])){
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(T element) {
        for (T elem:
             data) {
            if(elem == null && element == null){
                return true;
            }
            if(elem != null && element.equals(elem)){
                return true;
            }

        }
        return false;
    }

    @Override
    public void removeAt(int index) {
        if(index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = index; i<size-1;i++){
            data[i] = data[i + 1];
        }
        data[--size]=null;


    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if(index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    @Override
    public void clear() {
        for(int i = size - 1; i>=0 ;i--){
            data[i]=null;
            size--;
        }
    }

    @Override
    public void swap(int from, int to) {
        T elem = data[from];
        data[from] = data[to];
        data[to] = elem;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i != size - 1) System.out.print(", ");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;
            @Override
            public boolean hasNext() {
                return current<size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[current++];
            }
        };
    }
}
