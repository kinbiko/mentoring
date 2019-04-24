import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void testFizzBuzz() {
        check("FizzBuzz", 0);
        check("1", 1);
        check("2", 2);
        check("Fizz", 3);
        check("Buzz", 5);
        check("Fizz", 6);
        check("Buzz", 10);
        check("FizzBuzz", 15);
    }

    private void check(String expected, int i) {
        assertEquals(expected, new FizzBuzz().fizzBuzz(i));
    }
}
