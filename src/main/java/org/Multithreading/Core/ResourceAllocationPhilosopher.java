package org.Multithreading.Core;

import org.Multithreading.Strategy.DiningStrategy;

public class ResourceAllocationPhilosopher extends AbstractDiningPhilosopher {

    public ResourceAllocationPhilosopher(int id, Fork leftFork, Fork rightFork, DiningStrategy diningStrategy) {
        super(id, leftFork, rightFork, diningStrategy);
    }

    @Override
    public void eat() {
        diningStrategy.dine(this.id, this.leftFork, this.rightFork);
    }

    @Override
    public void think() {
        System.out.println("Philosopher - " + this.id + " is thinking.");
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("Exception occurred - " + e.getMessage()) ;
        }
    }
}
