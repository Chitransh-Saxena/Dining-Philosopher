package Strategy;

import org.Multithreading.Core.Fork;
import org.Multithreading.Strategy.DiningStrategy;
import org.Multithreading.Strategy.impl.DeadlockPreventionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlockPreventionStrategyTest {

    private Fork leftFork;
    private Fork rightFork;
    private DiningStrategy strategy;
    private boolean eatingCondition;


    @BeforeEach
    void setup() {
        leftFork = new Fork(1);
        rightFork = new Fork(2);
        strategy = new DeadlockPreventionStrategy(4);
        eatingCondition = false;
    }


    @Test
    void philosopherCanEat() {
        strategy.dine(1,leftFork,rightFork);
        assertTrue(eatingCondition);
    }


}
