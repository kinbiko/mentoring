# Exception handling

- An exception is an object that extends `Exception`
- An exception in Java is an object that encapsulates the details of an error.
- the use of `try/catch` blocks is useful to catch issues:
    > I'm going to
    > `TRY` this risky thing
    > and I'm going to
    > `CATCH` myself if I fall

```java
try {
    // Code for the try block goes here
    // do risky thing
}
catch (ExceptionClassName parameterName) {
    // Exception handling code goes here
    // try to recover
}
```

---

`Quiz`: Look into the difference between `RuntimeException` and `Exception` to understand why we wrap some of our exceptions in runtime exceptions

`Error` and `Exception` both extend `Throwable`. Errors cannot be handled, however there is an opportunity to handle `Exception`s.
`Error` along with `RuntimeException` & their subclasses are `unchecked exceptions`. All other `Exception` classes are `checked exceptions`.
`Checked exceptions`: They extend the `java.lang.Exception` class. A program can recover from these, and you are expected to check for these exceptions by using the `try-catch-finally` or `throw` it back to the `caller`.
`Unchecked exceptions`: They extend the `java.lang.RuntimeException`.
`RuntimeException`: a more specific Exception (this could help during debugging), and usually the result of invalid user input.
The main reason one would use exception wrapping is to prevent the code further up the call stack from having to know about every possible exception in the system.
`RuntimeException`, is reserved for exceptions that indicate incorrect use of an API.
Unchecked exceptions should be reserved for system errors which cannot/should not be recovered.

`Quiz`: find a more elegant solution for verifying that exceptions are being thrown in JUnit tests, using an @Annotation.

The following techniques exist for testing exceptions:
– "Old school" try-catch idiom
– `@Test` annotation with expected element
– JUnit `ExpectedException` rule

In JUnit, rules (@Rule) can be used as an alternative or an addition to fixture setup and cleanup methods: `@Before`, `@After`, `@BeforeClass`, and `@AfterClass`.
`ExpectedException` rule is meant for verification that code throws a specific exception. The rule must be declared as public field annotated with `@Rule` annotation:

```java
public class SimpleExpectedExceptionTest {
     @Rule
     public ExpectedException thrown = ExpectedException.none();

     @Test
     public void throwsNothing() {
         // no exception expected, none thrown: passes.
     }

     @Test
     public void throwsExceptionWithSpecificType() {
         thrown.expect(NullPointerException.class);
         throw new NullPointerException();
     }
 }
```


Remember: avoid checked exception - always wrap in (preferably your own extension of a) `RuntimeException`.
A method declaration that throws `BlahException` is a sign that you're doing something wrong!
Checked exception: caught as low as possible
Unchecked can be caught close to `main()` level.

*Pro tip: Don't ever throw exceptions in your constructor. It's a sign that the constructor is doing more than just setting up it's initial state. (e.g. making database connections).*

For tests it's OK to throws Exception always, even if it doesn't throw anything. Ideally the code you invoke doesn't throw checked exceptions, however sometimes (like this time) you have to use third-party libraries that don't agree with that philosophy.

`e.printStackTrace();` bad, `throw new RuntimeException(e)` good.

} catch (Exception e) {
    throw new subclass of RuntimeException(e);
}
