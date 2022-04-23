package com.iterator.iterators;

import com.iterator.profile.Profile;

/**
 * A interface comum a todos os iteradores.
 */
public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();
}
