# SOFTWARE ENGINEERING IN JAVA

## Session 16 (06/11/2018)

- Optimizations to Homework code

### Notes:

This session was a reviewing of the homework code, specially the part dealing with fibonacci series:

```java
public List<Integer> getFibonacci(Integer number) {
     List<Integer> fibonacci = new ArrayList<>();
    fibonacci.add(1);
    fibonacci.add(1);
     for (int i = 2; i < number; i++) {
        fibonacci.add(i, fibonacci.get(i - 1) + fibonacci.get(i - 2));
    }
     return fibonacci;
 }
```

For that code, linear time complexity `O(n)` is used, that means that the running time will increase according to the input number.

The code was upgrade to the following:

```java
@Service
public class Calculator {

    List<BigInteger> fibonacci = new ArrayList<>();

    public List<BigInteger> getFibonacci(Integer number) {

        if (fibonacci.size() >= number){
            return fibonacci.subList(0,number);

        } else {
            if (fibonacci.size() == 0) {
                fibonacci.add(BigInteger.ONE);
                fibonacci.add(BigInteger.ONE);

                for (int i = 2; i < number; i++) {
                    fibonacci.add(i, fibonacci.get(i - 1).add(fibonacci.get(i - 2)));
                }
            }

            for (int i = fibonacci.size(); i < number; i++) {
                fibonacci.add(i, fibonacci.get(i - 1).add(fibonacci.get(i - 2)));
            }

        }

        return fibonacci;

    }
```
 When the list is empty the time complexity is `O(n)`, but in order to improve the time complexity for subsequent queries, we're using a cache. Instead of beginning the calculation from scratch the each time, we're using our previous fibonacci calculated list to speed up the answer. If the new given number is less or equal than the cached list size, we return immediately the corresponding sublist and our time complexity becomes constant `O(1)`. Conversely, if the number is larger than the size of the cached list, we calculate just the missing values, and even if the time complexity continues being `O(n)', the answer will be quicker because the intermediary steps are cached.

Also, the definition of the `List` used to store fibonacci numbers was placed in a field and the datatype of it was changed to `BigInteger` in order to avoid the integer overflow problem. Also `BigInteger.ONE` method is used when is needed to fill list with number 1.

Other change was to use `.add()` method in replace of the `+` operator to do the addition of numbers into the bucle.

Other proposed improvement in terms of space complexity is to use the same couple of variables to overrite the values each time instead to use a `List` as can be sawn in the following fragment of `Go` code:

```go
func fibonacci() func() int {
    x, y := 0, 1
    return func() int {
        x, y = y, x + y
        return x
    }
}
```

In order to reduce the time complexity other solutions using matrices can be seen [here][1], [here][2] and [here][3].

### Resources
- [The Nth Fibonacci Number in O(log N)][1]
- [The Linear Algebra View of the Fibonacci Sequence][2]
- [Geeks for Geeks - Program for Fibonacci numbers][3]
- [Big O Cheatsheet][4]
- [Big O Notation][5]

[1]: https://kukuruku.co/post/the-nth-fibonacci-number-in-olog-n/
[2]: https://medium.com/@andrew.chamberlain/the-linear-algebra-view-of-the-fibonacci-sequence-4e81f78935a3
[3]: https://www.geeksforgeeks.org/program-for-nth-fibonacci-number
[4]: http://bigocheatsheet.com/
[5]: https://www.interviewcake.com/article/java/big-o-notation-time-and-space-complexity
