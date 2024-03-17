package Core;

import org.Multithreading.Core.Fork;
import org.Multithreading.Core.Philosopher;
import org.Multithreading.Core.ResourceAllocationPhilosopher;
import org.Multithreading.Strategy.DiningStrategy;
import org.Multithreading.Strategy.impl.ResourceAllocationImpl;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhilosopherTest {

    @Test
    public void testPhilosopherEatUsingResourceAllocation() {

        Fork leftFork = new Fork(1);
        Fork rightFork = new Fork(2);
        DiningStrategy diningStrategy = new ResourceAllocationImpl();
        Philosopher philosopher = new ResourceAllocationPhilosopher(1, leftFork, rightFork, diningStrategy);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(philosopher::eat);

        boolean exceptionThrown = false;

        try {
            // This will throw a TimeoutException since the philosopher eats indefinitely
            future.get(7, TimeUnit.SECONDS);
        } catch(TimeoutException e1) {
            System.out.println("Timeout Exception thrown, meaning thread was indeed running indefinitely");
            exceptionThrown = true;
            future.cancel(true); // This attempts to stop the thread
        } catch (InterruptedException | ExecutionException e) {
            // Test passes if TimeoutException is thrown, indicating the philosopher was indeed eating.
            future.cancel(true); // This attempts to stop the thread
        } finally {
            executor.shutdownNow();
        }

        // Assert that a TimeoutException was indeed thrown, indicating that the philosopher was eating indefinitely
        assertTrue(exceptionThrown, "TimeoutException was expected to be thrown, indicating the philosopher was eating indefinitely.");

    }
}
