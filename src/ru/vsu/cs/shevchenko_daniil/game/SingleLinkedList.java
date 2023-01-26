package ru.vsu.cs.shevchenko_daniil.game;

import java.util.Iterator;
import java.util.LinkedList;

public class SingleLinkedList<T> implements Iterable {

    private Node head;
    private Node tail;
    private int size;


    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T data = currentNode.data;
                currentNode = currentNode.next;
                return data;
            }
        };
    }

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void remove(T data) {
        Node currentNode = head;
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.data == data) {

                if (currentNode == head) {
                    head = currentNode.next;
                } else if (currentNode == tail) {
                    tail = previousNode;
                    previousNode.next = null;
                } else {
                    previousNode.next = currentNode.next;
                }
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        size--;
    }

    public void print() {
        if (head != null) {
            System.out.println(head.data + " ");
        }

        Node currentNode = head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
            System.out.println(currentNode.data + " ");
        }
    }

    public int getSize(){
        return size;
    }

    public T getLast(){
        return tail.data;
    }

    public T getFirst(){
        return head.data;
    }

    public T getNext(T data) {
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.data == data) {
                if (currentNode.next != null) {
                    return currentNode.next.data;
                } else {
                    return head.data;
                }
            }

            currentNode = currentNode.next;
        }
        return null;
    }

    public T get(int index) {
        Node currentNode = head;
        int temp = 0;

        while (temp < index) {
           currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    public void swap() {
        Node tempNode = head;
        head = tail;
        tail = tempNode;
    }

}
