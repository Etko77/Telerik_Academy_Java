package com.company.oop;

public interface MyList<T> {

    int size();

    int capacity();

    void add(T element);

    T get(int index);

    int indexOf(T element);

    boolean remove(T element);

    void removeAt(int index);
}
