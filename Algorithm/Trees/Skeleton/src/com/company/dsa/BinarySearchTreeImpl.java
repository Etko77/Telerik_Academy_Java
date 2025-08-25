package com.company.dsa;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E value;
    private BinarySearchTreeImpl<E> left;
    private BinarySearchTreeImpl<E> right;

    public BinarySearchTreeImpl(E value) {
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public E getRootValue() {
        return value;
    }

    @Override
    public BinarySearchTree<E> getLeftTree() {
        return left;
    }

    @Override
    public BinarySearchTree<E> getRightTree() {
        return right;
    }

    @Override
    public void insert(E value) {
        if(left == null && value.compareTo(this.value)<=0){
            left = new BinarySearchTreeImpl<>(value);
            return;
        }
        if(right == null && value.compareTo(this.value)>0){
            right = new BinarySearchTreeImpl<>(value);
            return;
        }
        if(value.compareTo(this.value)<=0){
            left.insert(value);
        }else{
            right.insert(value);
        }


    }

    @Override
    public boolean search(E value) {
        if(value.equals(this.value)){
            return true;
        }
        if(value.compareTo(this.value)< 0 && left != null){
            return left.search(value);
        }
        if(value.compareTo(this.value)> 0 && right != null){
            return right.search(value);
        }
        return false;
    }

    @Override
    public List<E> inOrder() {
        List<E> listOfElements = new ArrayList<>();
        return inOrderWithParam(listOfElements);
    }
    public List<E>inOrderWithParam(List<E> elems){
        if(left != null){
            left.inOrderWithParam(elems);
        }
        elems.add(this.value);
        if(right != null){
            right.inOrderWithParam(elems);
        }
        return elems;
    }

    @Override
    public List<E> postOrder() {
        List<E> listOfElements = new ArrayList<>();
        return postOrderWithParam(listOfElements);
    }
    public List<E>postOrderWithParam(List<E> elems){
        if(left != null){
            left.postOrderWithParam(elems);
        }
        if(right != null){
            right.postOrderWithParam(elems);
        }
        elems.add(this.value);
        return elems;
    }

    @Override
    public List<E> preOrder() {
        List<E> listOfElements = new ArrayList<>();
        return preOrderWithParam(listOfElements);
    }
    public List<E>preOrderWithParam(List<E> elems){
        elems.add(this.value);
        if(left != null){
            left.preOrderWithParam(elems);
        }
        if(right != null){
            right.preOrderWithParam(elems);
        }
        return elems;
    }


    @Override
    public List<E> bfs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int height() {
        if(left == null){
            return -1;
        }
        if(right == null){
            return 0;
        }
        return left.height() + 1 + right.height();

    }

    // Advanced task: implement remove method. To test, uncomment the commented tests in BinaryTreeImplTests
//    @Override
//    public boolean remove(E value) {
//        throw new UnsupportedOperationException();
//    }
}
