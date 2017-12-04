package com.paradygmaty;

import java.util.ArrayList;
import java.util.List;


public class CircularQueue<E> implements MyQueue<E> {
    private int size;
    private int readIndex;
    private int writeIndex;
    private List<E> arr;

    public CircularQueue(int size) {
        this.size = size + 1;
        arr = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) arr.add(null);
    }

    public void enqueue(E x) throws FullException {
        if (isFull())
            throw new FullException();
        arr.set(writeIndex, x);
        writeIndex = (writeIndex + 1) % size;
    }

    public void dequeue() {
        if (!isEmpty()) {
            arr.set(readIndex, null);
            readIndex = (readIndex + 1) % size;
        }
    }

    public E first() throws EmptyException {
        if (isEmpty())
            throw new EmptyException();
        return arr.get(readIndex);
    }

    public boolean isEmpty() {
        return readIndex == writeIndex;
    }

    public boolean isFull() {
        return (writeIndex + 1) % size == readIndex;
    }
}

