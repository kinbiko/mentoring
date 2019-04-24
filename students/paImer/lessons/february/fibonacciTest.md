# TDD in practice

Here we will write a program that calculates the nth number of the fibonacci sequence.

We take `F(1) == 1`, `F(2) == 1`, and the define subsequent terms as given by the formula:

![Fib](http://latex2png.com/output//latex_47583871677f0f2fcbe228a749710c61.png)

## The first test

We should check if the class `Fibonacci` exists, and that we can instantiate it.

### Red I

```java
import org.junit.Test;

public class FibTest {
    @Test
    public void testFib() {
        new Fib();
    }
}
```

As there is no such class, we have our first failing test.

### Green I

Create a class called `Fib`.

```java
class Fib {
}
```

### Refactor I

Nothing much to do here.

### Red II

Now everything compiles, we need to add another failing step.
Let's check if there is a method contained in `Fib`, and try and pass in an integer.

```java
import org.junit.Test;

public class FibTest {
    @Test
    public void testFib() {
        new Fib().fib(1);
    }
}
```

No such method exists.

### Green II

```java
class Fib {
    int fib(int n) {
        return 0;
    }
}
```

Success - the program compiles.

### Refactor II

Nothing to do here.

### Red III

After importing the necessary JUnit packages, let's assert that inputting anything less than `1` gives `-1`.

```java
import org.junit.Assert;
import org.junit.Test;

public class FibTest {
    @Test
    public void testFib() {
        Assert.assertEquals(-1, new Fib().fib(0));
    }
}
```

We haven't written any implementation code yet, so this will fail.

### Green III

Let's add the most *naive* implementation code possible that will pass our first test - a method that *always* returns `-1`.
To begin with, we will write this class in the same file initially, with the intent to extract it to a separate file when we're finished.

```java
class Fib {
    int fib(int n) {
        return -1;
    }
}
```

### Refactor III

This does the job, and is already fairly simple, so let's skip this step for now (but only after careful consideration)

### Red IV

Time to make another failing test.
We have already discussed that, as per our definition, `F(1)` should return `1`.

Here we will add another assertion (not an entire test) for brevity.
It is preferable to write different tests when testing different inputs, due to the way JUnit works - a assertion will prevent any of the assertions within the same test from running, depriving you of potentially useful information.
Multiple tests removes this concern.

```java
Assert.assertEquals(1, new Fib().fib(1));
```

### Green IV

It might make sense here to split up our inputs into two camps: those less than 1, and those greater than or equal to 1.
As we are dealing only with integers, this covers all possible inputs.

```java
class Fib {
    int fib(int n) {
        if (n < 1) {
            return -1;
        }
        return 1;
    }
}
```

All tests  pass!

### Refactor IV

Let's statically import `Assert.assertEquals` and extract a method called `checkFib()` from `testFib()` to improve readability:

```java
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FibTest {
    @Test
    public void testFib() {
        checkFib(-1, 0);
        checkFib(1, 1);
    }

    private void checkFib(int expected, int n) {
        assertEquals(expected, new FibTest().fib(n));
    }
}
```

### Red V

Now we have the two most basic test cases down, it's time to start making our tests a little more case-specific.

If we try the next logical step `checkFib(1, 2)`, we notice that this assertion passes.
This is not a failing test, and therefore we ignore it and look for the next most degenerate case that results in a failure.

Indeed, `checkFib(2, 3)` does exactly this.

### Green V

One simple case that passes:

```java
class Fib {
    int fib(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return 2;
    }
}
```

### Refactor V

Having two inputs return the same value can be tided up a bit.
Let's use the `||` operator to fix this.

```java
class Fib {
    int fib(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return 2;
    }
}
```

### Red VI

Let's check `F(4) = 3` with `checkFib(3, 4)`.

### Green VI

```java
class Fib {
    int fib(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return n - 1;
    }
}
```

Success!

### Refactor VI

Nothing necessarily needed to be done here.

### Red VII

Let's check `F(5) = 5` with `checkFib(5, 5)`.

### Green VII

Given the problem at hand and a bit of insight, let's go with the following:

```java
class Fib {
    int fib(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
```

It passes!

### Refactor VII

Now we check if we can optimise for readability.
As it turns out, there's nothing particularly mind-blowing that can be done from here, and the solution we've arrived at is pretty good!

### Conclusion

After trying a number of other assertions, we can see that after seven cycles of Red-Green-Refactor we've actually arrived at our solution!