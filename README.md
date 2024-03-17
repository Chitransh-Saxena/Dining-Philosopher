# Dining Philosopher's Problem
#multithreading #java



## Description of the Problem

- Imagine 5 philosophers dining around a round table
- Each of the 5 philosopher has a bowl of noodles
- There is a fork between each 2 philosopher
    - So there are 5 forks.
- A philosopher needs 2 forks to eat the noodles and always has to pick left fork before right fork.
- The challenge is for the philosophers to eat without starving themselves



## Major Concerns in this Problem

- **Deadlock**
    - This occurs when each philosopher holds one fork, and waits indefinitely for the other.
    - This creates a `circular wait condition`
- **Starvation**
    - This occurs when one or more philosopher never gets to eat because some other philosopher always picks the fork.
    - This is similar to `thread starvation`, where a thread never gets scheduled because other thread keeps getting locks on the resource.
- **Concurrency**
    - Managing concurrent access to shared resources without causing a deadlock situation




## Why are there so many solutions

- This problem is an abstraction of real-life concurrency issues faced in multithreaded computer system designs.
- Due to above nature of the problem, it makes it a good example to explore different synchronisation and concurrency-control mechanisms.
- Each solution highlights different strategies and trade-offs in shared resources.

### Resource Allocation Strategies
- In this strategy, the allocation of resources is controlled via a mechanism.

### Deadlock Prevention
- Preventing Deadlocks via preventing circular wait conditions.

### Fairness and Avoiding Starvation
- Ensuring all threads get fair share of resources.


## Class Diagram and Package Structure

### Class Diagram
- ![Class Diagram](./Dining Philosopher.png)

### Package Structure

- Package structure that will be used, is different from traditional Spring Boot package structure which is tailored for MVC pattern.
- Pattern used here has the purpose to solve problems like Dining Philosopher's problem, and other such problems.
  - Consider the problem can be solved using various strategies. In that case, the package structure will be something like this

```lua 
    - Multithreading/
      - core
        - Philosopher.java
        - Fork.java
      - strategies
        - DiningStrategies.java
        - impl
          - ResourceAllocationStrategyImpl.java
          - DeadlockPreventionStrategyImpl.java
      - main
        - Main.java
```

- Explaining Package Structure
  - **Core**
    - Contains essential entities related to the problem
      - Like Philosopher and Fork in this case
    - It can contain both class and interface.
      - Can keep the interface and impl structure from MVC if needed
  - **Strategy**
    - This encapsulates various algorithms used to solve the problem.
    - The class can be structured in interface and related impl files if necessary.
  - **Main**
    - This is the entry point into the application
    - It interacts with classes which are essential in setting up the POJOs and get the program up and running



## Implementation Notes

- **Philosopher Interface** - Outlines basic actions of a Philosopher (eating and thinking)
- **Fork Class** - Could be used for `Lock` if necessary
- **DiningPhilosopher Abstract Class** - This implements `Philosopher` interface and uses `DiningStrategy` for the specific logic related to picking up forks and eating.
- **DiningStrategy Interface** - Each solution implements this interface, encapsulating unique strategy for each approach.
- **Main Class** - Entry Point. Classes setup and what not.


## Working towards a Solution

- Define `Philosopher` and `Fork` with minimal requirements of the behaviour
- Define `DiningPhilosopherImpl` abstract class that all specific philosopher classes will extend
- Define `DiningStrategy` and implement all the solutions(ResourceAllocation and DeadlockPrevention)
  - ResourceAllocation (ResourceHierarchy)
    - Acquiring the First Fork: When a philosopher tries to pick up the first fork (firstFork), they enter the outer synchronized block. If the fork is available (no other philosopher has locked this fork object), the current philosopher successfully acquires the lock on the firstFork object, effectively "picking up" the fork. If the fork is not available (another philosopher holds the lock), the current philosopher is blocked from entering the block, waiting until the fork becomes available.
    - Attempting to Acquire the Second Fork: Once inside the outer synchronized block, the philosopher attempts to pick up the second fork (secondFork) by entering the inner synchronized block. If the secondFork is available, the philosopher acquires the lock on the secondFork object and proceeds to "eat." If the secondFork is not available, the philosopher is blocked at the entrance of the inner synchronized block but still holds the lock on the firstFork.
    - Critical Point for Deadlock Prevention: Because the philosopher can only proceed to eat (and eventually exit the synchronized blocks, releasing the forks) if they acquire both forks, no philosopher can indefinitely hold a single fork while waiting for another. If the second fork is not available, the philosopher is blocked from eating but does not release the first fork. This might seem like it could lead to deadlock, but because all philosophers follow the same global order for picking up forks, a circular wait (which is necessary for a deadlock) cannot occur.
    - Releasing Forks: Once the philosopher finishes eating, they exit the inner synchronized block, releasing the lock on the secondFork, and then exit the outer synchronized block, releasing the lock on the firstFork. This allows other philosophers to acquire these forks.
  - DeadlockPrevention
    - 













