package com.tntcpu.algs4.chap1_basis.example.chap1;


import java.util.Iterator;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-03
 */
public class E010_Stack<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}