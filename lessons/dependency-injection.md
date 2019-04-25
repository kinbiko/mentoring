# Dependency Injection

Dependency injection is a technique for decoupling a class from its dependencies. One of the great benefits of dependency injection is that it makes unit testing a class much easier.

## Example - no dependency injection

Suppose we have the following contrived class:

```java
class MyClass {
    private MyDependency myDependency = new MyDependency()

    void doStuff() {
        DependencyResult res = myDependency.performKeyTask();
        doStuff(res);
    }

    private void doStuff(DependencyResult res) {
        //...
    }
}

class MyDependency{
    public DependencyResult performKeyTask(){
        //do whatever...
        return res;
    }
}
```

Notice how `MyClass` creates the MyDependency it needs to use. You can identify this by the `new` keyword. This is exactly the coupling we wish to break apart. It turns out that `MyClass` doesn't really need a `new MyDependency` - it just needs a class that's got a `performKeyTask()` method that returns a `DepdendencyResult`.

## Example - Take advantage of interfaces to loosen the coupling

```java
interface MyDependency {
    DependencyResult performKeyTask();
}

class MyClass {
    private final MyDependency myDependency;

    MyClass(MyDependency myDependency) {
        this.myDependency = myDependency;
    }

    void doStuff() {
        DependencyResult res = myDependency.performKeyTask();
        doStuff(res);
    }

    private void doStuff(DependencyResult res) {
        //...
    }
}

class DefaultMyDependency implements MyDependency {
    @Override
    public DependencyResult performKeyTask(){
        //do whatever...
        return res;
    }
}
```

### Key points:

- We use the general name for the interface, and prefix the implementation class with `Default`. This is a convention you might see in code elsewhere, and the convention I recommend using. Other common patterns you might find (but please don't use) are
  - `IMyDepdendency` for the interface and the `MyDependency` for the implementation.
  - `MyDependency` for the interface and `MyDependencyImpl` for the implementation.
- The `new` keyword went away and we now request the `MyDependency` in our constructor. Since you cannot create a class without calling the constructor whoever uses the `MyClass` will have to provide an implementation of `MyDependency`.
- Nothing changed in `doStuff()`.
- Not pictured: Any classes that previously depended on `MyClass` will now have to pass a `new DefaultMyDependency()` to the `MyClass` constructor.
- The code is now more complex. We probably don't want to introduce dependency all over the place, just because.

## The benefits of dependency injection

Since `MyClass` no longer depends upon the `DefaultMyDependency` implementation anymore, but only the contract (interface), we can change `DefaultMyDependency` without needing to even recompile `MyClass`. This was not the case before as `MyClass` depended directly on `DefaultMyDependency`. To help see just how powerful this is imagine if the interface `MyDependency` is not in the same codebase but in a framework somewhere, that does most of the heavy lifting, but just needs one piece of custom information from `performKeyTask` to do a bunch of really useful magic. This doesn't really have anything to do with dependency injection but I'm hoping it shows how powerful interfaces can be.

Dependency injection allows us to put any other `MyDependency` implementation in `MyClass` without any other changes to the codebase. In fact we could create as many `MyClass` instances as we wanted to, and have different behaviours depending on which `MyDependency` implementation we pass in. That being said, you're probably better off using generics if you need to create too many of these different instances.

Dependency injection comes in really handy when you need to mock out something in a test. Suppose you wanted to test `MyClass`, but the `MyDependency` instance that usually gets passed in is really annoying to deal with.
Generally annoying things to deal with in tests:
- intentionally random behaviour.
- makes a database connection.
- makes a request to a third party service
- does too much stuff, e.g. returns a very complex object where you only care about bits of it.
In these cases it's often useful to create a very simple implementation of your dependency as a private inner class in the test class. This way you control exactly what the dependency does for the purposes of the test.

It is also useful to mock out dependencies when the target class calls `void` methods on it, and you want to verify that it's being called and what it's being called with.

## Not all classes are dependencies

Strings, primitives, and most implementations in the standard Java library are not generally dependencies. Additionally, POJOs don't tend to be dependencies either, mainly because POJOs don't tend to be kept as fields (apart from in other POJOs). Classes that do stuff and return POJOs are fair game however.

## Real world example:

In [this example](https://github.com/JStonham/budjen/pull/10/files) I've used dependency injection for the `TransactionData` and the `Logger` classes.
