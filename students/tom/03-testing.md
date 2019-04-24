# Unit testing

### TDD cycle

> red, green, refactor

1. Red: Write no more test code than is necessary for it to fail, or fail to compile.
1. Green: Write no more production code than is necessary for it to pass.
1. Refactor: Clean up after yourself.

After creating a class, use (cmd + shift + T) to create a test class. So far I have been using JUnit4 for testing.
*An initial test will look like this:*
```java

import org.junit.Test;

public class <<TestName>> {
    @Test
    public void testName() {
        // Test code goes here
    }
}
```

*Important: tests must be public void*

### The JUnit `Assert` class provides the following static methods:

| Method            | Function                                                              |
|-------------------|-----------------------------------------------------------------------|
| assertEquals      | Asserts that two objects (or primitives) are equal.                   |
| assertTrue        | Asserts that a statement is true.                                     |
| assertFalse       | Asserts that a statement is false.                                    |
| assertNull        | Asserts that an object reference is null.                             |
| assertNotNull     | Asserts that an object reference is not null.                         |
|  *less common assertions below*                                                           |
| assertSame        | Asserts that two object references point to the same instance.        |
| assertNotSame     | Asserts that two object references do not point to the same instance. |
| assertThat*       | Asserts that an object matches the given conditions.                  |
| assertArrayEquals | Asserts that two arrays have the same items.                          |

\* `assertThat` is fairly common, and allows you to do most of the other forms of assertions, but the way you word your assertions tend to be more verbose.


A good convention is to name a test class with in the form `givenX_doY`:
```java
@Test
public void givenNullObject_NoResults() {
    Assert.assertEquals(<<expected>>, <<actual>>);
}
```

Name the instance of the class you want to test `target`:
```java
<<Object type in class to test>> target = new <<Object type in class to test>>();
```
*For example:*

```java
@Test
public void canWriteAndGetJsonPath() {
    JsonPath target = new JsonPath();
    target.setJsonPath("number.value");
    String result = target.getJsonPath();
    Assertions.assertEquals("number.value", result);
}
```

*Make sure your tests are sufficiently thorough and accurate before you try and modify production code!
Trying to get the code to pass a bad test will drive you crazy!
In the following example, I'm trying to find the unique element in a array of type double. Notice that the answer should be `3.0`, but that the test asserts that it should be `1.0`!*

```java
 @Test
public void FindUniqueElementTest() {
    assertEquals(1.0, FindUniqueElement.finder(new double[]{0.0, 0.0, 3.0, 0.0}), 0.0000000000001 );
}
```
Refactor after you have a passing test.

> *Refactoring = amplify clarity of intent while minimizing duplication.*
  - Lasse Koskela

## Different Levels of Testing

1. Unit testing:
2. Component testing:
3. Integration testing:
4. System testing:
