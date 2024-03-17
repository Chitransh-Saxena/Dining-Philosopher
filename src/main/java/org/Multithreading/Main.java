package org.Multithreading;

import org.Multithreading.Core.Fork;
import org.Multithreading.Core.Philosopher;
import org.Multithreading.Core.ResourceAllocationPhilosopher;
import org.Multithreading.Strategy.DiningStrategy;
import org.Multithreading.Strategy.impl.ResourceAllocationImpl;

public class Main {
    public static void main(String[] args) {

        final int PHILOSOPHER_COUNT = 5;
        Fork[] forks = new Fork[PHILOSOPHER_COUNT];
        Thread[] philosophers = new Thread[PHILOSOPHER_COUNT];

        DiningStrategy strategy = new ResourceAllocationImpl();

        for(int i = 0; i< PHILOSOPHER_COUNT; i++) {
            forks[i] = new Fork(i+1);
        }

        for(int i = 0; i < PHILOSOPHER_COUNT; i++) {
            ResourceAllocationPhilosopher philosopher = new ResourceAllocationPhilosopher(i + 1,
                    forks[i],
                    forks[(i+1) % PHILOSOPHER_COUNT],
                    strategy);
            philosophers[i] = new Thread(philosopher);
            philosophers[i].start();
        }
    }
}