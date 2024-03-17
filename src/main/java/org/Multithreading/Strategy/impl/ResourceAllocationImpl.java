package org.Multithreading.Strategy.impl;

import org.Multithreading.Core.Fork;
import org.Multithreading.Strategy.DiningStrategy;

public class ResourceAllocationImpl implements DiningStrategy {


    /*
    - Here’s a step-by-step explanation of how this prevents a philosopher from holding onto one fork indefinitely:

        - Acquiring the First Fork: When a philosopher tries to pick up the first fork (firstFork), they enter the outer synchronized block. If the fork is available (no other philosopher has locked this fork object), the current philosopher successfully acquires the lock on the firstFork object, effectively "picking up" the fork. If the fork is not available (another philosopher holds the lock), the current philosopher is blocked from entering the block, waiting until the fork becomes available.
        - Attempting to Acquire the Second Fork: Once inside the outer synchronized block, the philosopher attempts to pick up the second fork (secondFork) by entering the inner synchronized block. If the secondFork is available, the philosopher acquires the lock on the secondFork object and proceeds to "eat." If the secondFork is not available, the philosopher is blocked at the entrance of the inner synchronized block but still holds the lock on the firstFork.
        - Critical Point for Deadlock Prevention: Because the philosopher can only proceed to eat (and eventually exit the synchronized blocks, releasing the forks) if they acquire both forks, no philosopher can indefinitely hold a single fork while waiting for another. If the second fork is not available, the philosopher is blocked from eating but does not release the first fork. This might seem like it could lead to deadlock, but because all philosophers follow the same global order for picking up forks, a circular wait (which is necessary for a deadlock) cannot occur.
        - Releasing Forks: Once the philosopher finishes eating, they exit the inner synchronized block, releasing the lock on the secondFork, and then exit the outer synchronized block, releasing the lock on the firstFork. This allows other philosophers to acquire these forks.
     */

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
