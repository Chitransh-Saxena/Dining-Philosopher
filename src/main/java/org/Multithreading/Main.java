package org.Multithreading;

import org.Multithreading.Core.Fork;
import org.Multithreading.Core.Philosopher;
import org.Multithreading.Core.ResourceAllocationPhilosopher;
import org.Multithreading.Strategy.DiningStrategy;
import org.Multithreading.Strategy.impl.DeadlockPreventionStrategy;
import org.Multithreading.Strategy.impl.ResourceAllocationImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int PHILOSOPHER_COUNT = 5;
        Fork[] forks = new Fork[PHILOSOPHER_COUNT];
        Thread[] philosophers = new Thread[PHILOSOPHER_COUNT];

        DiningStrategy strategy = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose from below options");
        System.out.println("Press 1 for ResourceHierarchy Solution, 2 for DeadlockPrevention Solution");

        String option = sc.nextLine();

        switch(option) {
            case "1" :
                strategy = new ResourceAllocationImpl();
                break;

            case "2" :
                strategy = new DeadlockPreventionStrategy(PHILOSOPHER_COUNT - 1);
                break;

            default:
                break;
        }



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