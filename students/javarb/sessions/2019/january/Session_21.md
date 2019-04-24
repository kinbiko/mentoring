# SOFTWARE ENGINEERING IN JAVA

## Session 21 (08/01/2019)

- Corrections to fibonacci algorithm.
- Implementing Logger and tests for it.

### Notes

Some improves where done to the code in fibonacci in order to make it easier to read.

#### Logger

In order to implement log capability we created the following interface:

```java
public interface Logger {
    void log(String message);
}
```

And after we implement that interface inside a DefaultLogger class:
```java
@Component
public class DefaultLogger implements Logger { ... }
```

Notice that `@Component` Spring annotation was used in order we can use the Logger inside our project classes. Hence, for example in the `Calculator` class we are injecting our Logger interface:

```java
@Service
public class Calculator {
    private Logger logger;

    Calculator (Logger logger) {
        this.logger = logger;
    }
    ...
}
```

If we not define our Logger implementation as `@Component`, Spring doesn't know where the class that satisfies that interface is.

A thing to highlight is the fact that we are injecting the interface but not the particular class implementation which we define as `DefaultLogger`, however, Spring choose it automatically since its the only one that exists in our project for the `Logger` interface.

**Note:** In tests we have another implementation for `Logger` interface but as we aren't using Spring at all there, will not be conflicts with having multiple interface implementation for our project, in such a case we should to specify which implementation we are using.

For more about dependency injection see [here][4]

### Homework

For the activities flow track we're using projects and kanban method embeeded into GitHub's interface.

- [x] Find why Travis was failing in build the Session

  **A/** this was because we were trying to use Logger inside Calculator class but Spring could not recognize this as a bean, so we had to add the `@Component` annotation to DefaultLogger Logger's implementation. See more above.

- [x] Implement more common log components: Timestamp, IP address, Thread ID (test it). Show these in every log call


### Resources

- [Timestamp][1]
- [Get IP info][2]
- [Get thread ID][3]
- [Dependency Injection][4]

[1]: https://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
[2]: https://crunchify.com/how-to-get-server-ip-address-and-hostname-in-java/
[3]: https://stackoverflow.com/questions/3294293/how-to-get-thread-id-from-a-thread-pool
[4]: https://kinbiko.gitbook.io/learning-se/dependency-injection
