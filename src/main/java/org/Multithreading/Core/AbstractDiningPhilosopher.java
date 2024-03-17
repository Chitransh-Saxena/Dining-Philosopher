package org.Multithreading.Core;

import org.Multithreading.Strategy.DiningStrategy;

public abstract class AbstractDiningPhilosopher implements Philosopher {

    protected int id;
    protected Fork leftFork;
    protected Fork rightFork;
    protected DiningStrategy diningStrategy;

    public AbstractDiningPhilosopher(int id, Fork leftFork, Fork rightFork, DiningStrategy diningStrategy) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.diningStrategy = diningStrategy;
    }

    @Override
    public void run() {
        while(true) {
            think();
            eat();
        }
    }

    @Override
    public abstract void eat();

    @Override
    public abstract void think();

}
