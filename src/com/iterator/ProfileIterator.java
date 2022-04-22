package com.iterator;

/**
 * A interface comum a todos os iteradores.
 */
public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();
}
