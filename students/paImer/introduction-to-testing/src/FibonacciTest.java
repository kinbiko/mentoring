import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {
    @Test
    public void testFibonacci() {
        checkFib(-1, 0);
        checkFib(1, 1);
        checkFib(2, 3);
        checkFib(3, 4);
        checkFib(5, 5);
    }

    private void checkFib(int expected, int i) {
        assertEquals(expected, new Fibonacci().fib(i));
    }
}
