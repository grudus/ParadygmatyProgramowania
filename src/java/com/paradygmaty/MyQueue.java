package com.paradygmaty;

public interface MyQueue<E> {

    public void enqueue( E x ) throws FullException;
    public void dequeue( );
    public E first( ) throws EmptyException;
    public boolean isEmpty( );
    public boolean isFull( );
}

class FullException extends Exception {}

class EmptyException extends Exception {}
