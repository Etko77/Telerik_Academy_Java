package com.company.oop;

import java.util.Arrays;

public class MyListImpl<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 5;

    private T[] elements;
    private int size;

    public MyListImpl() {
        this(DEFAULT_CAPACITY);
    }

    public MyListImpl(int initialCapacity) {
        elements = createArray(initialCapacity);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public void add(T element) {
        if (size == elements.length) {
            resize();
        }

        elements[size] = element;
        size++;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean remove(T element) {
        int i = indexOf(element);
        if (i == -1) {
            return false;
        }

        removeAt(i);
        return true;
    }

    @Override
    public void removeAt(int index) {
        validateIndex(index);

        size--;

        System.arraycopy(
                elements,
                index + 1,
                elements,
                index,
                size - index);
    }

    private T[] createArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    private void resize() {
        this.elements = Arrays.copyOf(this.elements, size() * 2);
    }

    private void validateIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
