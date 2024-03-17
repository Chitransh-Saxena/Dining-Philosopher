package org.Multithreading.Core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private int id;
    private final Lock lock = new ReentrantLock();

    public int getId() {
        return this.id;
    }

    public Fork(int id) {
        this.id = id;
    }

    public void pickUp() {
        this.lock.lock();
    }

    public void putDown() {
        this.lock.unlock();
    }

}
