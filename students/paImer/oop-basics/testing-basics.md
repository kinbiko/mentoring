# Testing Basics

## What is testing?

When we software test a piece of code, we write a piece of software that executes another piece of software.
The idea behind testing is to *test* your code works how you would expect, given a known set of results, and help a  developer verify that the logic of portion of their program is correct.

**Unit testing** is testing broken down into *units*.
These can intuitively be thought of as the smallest testable part of an application.
A unit test targets a small fragment of code, e.g., a method or a class.

## JUnit

These notes will cover the basic functionality of JUnit.
There are various versions of JUnit, with JUnit 4 and JUnit 5 being the most popular.
While there are differences between the two, the methods described here apply to JUnit 4.

## Writing a test

The identifier `@Test` is given before a method to identify it as a test.
To import this identifier, we use `import org.junit.Test`.

A test method should:

- Have both `public` and `void` modifiers
- Take no input values

Under the test method, you use an *assertion* or an *assertion statement* to check for expected results against actual results.
This can be imported with `import org.junit.Assert`.

An example of a simple test for a program that checks if a number is perfect (proper divisors add up to the number itself) or not:

```java
// Import test identifier and assert method
import org.junit.Test;
import org.junit.Assert;

// Write the actual test method and decide what to check for
public class PerfectTest {
    @Test // Test identifier - testPerfect() is our test.
    public void testPerfect() {
        // Here we check the a few known perfect numbers
        Assert.assertEquals(true, new Perfect().perfect(1));
        Assert.assertEquals(true, new Perfect().perfect(6));
        Assert.assertEquals(true, new Perfect().perfect(496));
        // And a few that are not perfect.
        Assert.assertEquals(false, new Perfect().perfect(2));
        Assert.assertEquals(false, new Perfect().perfect(10));
    }
}

// The class we will run our tests on.
class Perfect {
    private int sum = 1;

    boolean perfect(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i + (n / i);
            }
        }
        return sum == n;
    }
}
```

Note: There are a few ways we can tidy this test code up:

```java
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PerfectTest {
    @Test
    public void testPerfect() {
        checkPerfect(1);
        checkPerfect(6);
        checkPerfect(496);
    }

    private void checkPerfect(int num) {
        assertTrue(new Perfect().perfect(num));
    }

    @Test
    public void testNotPerfect() {
        checkNotPerfect(2);
        checkNotPerfect(10);
    }

    private void checkNotPerfect(int num) {
        assertFalse(new Perfect().perfect(num));
    }
}
```

As we are dealing with a boolean, there are only two possible results (`true`, `false`).
Here I've gone ahead and split the test into two separate tests - one that tests if `true`, and the other tests if `false`.
