package com.company.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
        Node newNode = new Node();
        head = newNode;
        tail = newNode.next;
    }

    public LinkedList(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    @Override
    public void addFirst(T value) {
        Node newNode = new Node(value);
        if(size() == 0){
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        Node newNode = new Node(value);
        if(size() == 0){
            tail = newNode;
            head = newNode;
        }else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T value) {
        if(index < 0 || index > size()){
            throw new NoSuchElementException("No such element");
        }
        if(index == 0){
            addFirst(value);
            return;
        }
        if(index == size){
            addLast(value);
            return;
        }
        Node current = head;
        for(int i = 0; i < index;i++){
            current = current.next;
        }
        Node newNode = new Node(value);
        Node prevNode = current.prev;

        newNode.next = current;
        newNode.prev = prevNode;

        if(prevNode != null){
            prevNode.next = newNode;
        }
        current.prev = newNode;
        size++;

    }

    @Override
    public T getFirst() {
        if(size() == 0){
            throw new NoSuchElementException("No such element");
        }
        return head.value;
    }

    @Override
    public T getLast() {
        if(size() == 0){
            throw new NoSuchElementException("No such element");
        }
        return tail.value;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > size()){
            throw new NoSuchElementException("No such element");
        }
        if(index == 0){
            return getFirst();
        }
        if(index == size){
            return getLast();
        }
        Node current = head;
        for(int i = 0; i < index;i++){
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int indexOf(T value) {
        if(size() == 0){
            return -1;
        }
        Node current = head;
        int neededIndex=-1;
        for(int i = 0; i < size;i++){
            if(current.value == value){
                neededIndex = i;
                break;
            }
            current = current.next;
        }

        return neededIndex;
    }

    @Override
    public T removeFirst() {
        if(size() == 0){
            throw new NoSuchElementException("No such element");
        }
        Node firstNode = new Node(head.value);
        head = head.next;
        size--;
        return firstNode.value;

    }

    @Override
    public T removeLast() {
        if(size() == 0){
            throw new NoSuchElementException("No such element");
        }
        Node lastNode = new Node(tail.value);
        tail = tail.prev;
        size--;
        return lastNode.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
        }
        Node() {
        }
    }
}
