package org.Multithreading.Strategy.impl;

import org.Multithreading.Core.Fork;
import org.Multithreading.Strategy.DiningStrategy;

import java.util.concurrent.Semaphore;

public class DeadlockPreventionStrategy implements DiningStrategy {

    private final Semaphore semaphore;

    public DeadlockPreventionStrategy(int limit) {
        semaphore = new Semaphore(limit);
    }

    @Override
    public void dine(int philosopherId, Fork leftFork, Fork rightFork) {

        try {
            semaphore.acquire();
            leftFork.pickUp();
            rightFork.pickUp();

            System.out.println("Philosopher - " + philosopherId + " is eating");
            Thread.sleep(5000);

            rightFork.putDown();
            leftFork.putDown();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
