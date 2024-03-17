package org.Multithreading.Strategy.impl;

import org.Multithreading.Core.Fork;
import org.Multithreading.Strategy.DiningStrategy;

public class ResourceAllocationImpl implements DiningStrategy {


    @Override
    public void dine(int philosopherId, Fork leftFork, Fork rightFork) {

        Fork firstFork = leftFork.getId() < rightFork.getId() ? leftFork : rightFork;
        Fork secondFork = leftFork.getId() < rightFork.getId() ? rightFork : leftFork;

        synchronized (firstFork) {
            synchronized (secondFork) {
                System.out.println("Philosopher - " + philosopherId + " is eating");
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    System.out.println("Error occured - " + e.getMessage());
                }
            }
        }
    }
}
