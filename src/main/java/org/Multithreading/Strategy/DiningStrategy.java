package org.Multithreading.Strategy;

import org.Multithreading.Core.Fork;

public interface DiningStrategy {

    void dine(int philosopherId, Fork leftFork, Fork rightFork);
}
