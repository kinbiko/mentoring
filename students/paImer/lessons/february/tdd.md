# Test-Driven Development (TDD)

> Testing: A practice
> Test-driven development: A discipline

Red → Green → Refactor → Repeat

## Red

The starting phase of the Red-Green-Refactor cycle.
This is where the process always begins.

Create a test that tests the implementation of a feature, and will fail if expectations are not met.
Think about what you want to develop.
You should write a test that informs the implementation of a feature of what you are aiming to create.
The test will only pass when its expectations are met.

For example, create a program `flip()` that reverses a given string, and test whether the following holds:

```
Input: "String"
Output: "gnirtS"
```

Of course the test fails - we haven't actually written anything to implement yet.

## Green

Now that we have a failing test, we should write as little sensible code as possible to make the test pass.
Here we worry about simply making our test pass, and worry less about other factors. (See: [Refactor](#Refactor))

`flip()` should accept a string, and output the input string in reverse.

```java
public static String flip(String s) {
    String t = "";
    int len = s.length();
    for (int i = len - 1; i <= 0; i--) {
        t += s.charAt(i);
    }
    return t;
}
```

```
flip("String") = "gnirtS"
```

Here we have a passing test.

## Refactor

Once we have a passing test, it is important to look carefully at what we have written, and decide if any tweaks are necessary.

What exactly is a *tweak*?
Well, in this case we are talking specifically about steps to take to improve the code's readability.

Perhaps in this case, renaming the variables to actually mean something would be a good idea.
(N.B. in terms of readability, this is never a bad idea!)

```java
public static String flip(String in) {
    String out = "";
    int stringLength = in.length();
    for (int i = stringLength - 1; i <= 0; i--) {
        out += in.charAt(i);
    }
    return out;
}
```

Now it *looks* better, but can we make it *behave* any better?

For small strings and minor implementations of this code, the above method should suffice.
However, for extremely long strings, using a string builder can help preserve memory; here, we purposefully omit one for the sake of readability.

```java
public static String flip(String stringIn) {
    StringBuilder stringOut = new StringBuilder();
    int stringLength = stringIn.length();
    for (int i = stringLength - 1; i <= 0; i--) {
        stringOut.append(stringIn.charAt(i));
    }
    return stringOut.toString();
}
```

Here we have a test that looks great, and behaves how we'd expect.
As we have already written code to test the program, make sure all the tests pass.

When refactoring, ask yourself the following questions:

- Does my test suite offer you enough confidence to allow me to reliably refactor my code?
- Can I reduce duplication in my tests or implementation code?
- Can I make my implementation code more descriptive?

## Conclusion

TDD is a discipline; it helps you write code that's very maintainable, and keeps you predictably productive in the future if you maintain the discipline.
TDD may help implement a more robust solution than you may have otherwise been able to write.