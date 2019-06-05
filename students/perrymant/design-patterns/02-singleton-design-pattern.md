# Singleton Design Pattern

A `singleton` is a reational pattern that ensures a class only has one instance and provides a global point of access to it.

Here's it's UML diagram:
```
+------------------------+
|       Singleton        |
+------------------------+
| -static uniqueInstance |
| -singletonData         |
+------------------------+
| +static getInstance()  |
| +singletonOperation()  |
+------------------------+
```

And here is a `singleton` class:
```java
class Service {
    private static Service service;
    String s;

    private Service() {
        s = "Some text to manipulate!";
    }

    static Service getInstance() {
        if (service != null) {
            return service;
        }
        service = new Service();
        return service;
    }
}
```

The driver class [source](https://www.geeksforgeeks.org/singleton-class-java/) might look like this, notice how there's only ever 1 `Service` instance:
```java
public class SingletonExample {
    public static void main(String[] args) {
        // instantiating Singleton class with variable y
        Service x = Service.getInstance();

        // instantiating Singleton class with variable y
        Service y = Service.getInstance();

        // instantiating Singleton class with variable z
        Service z = Service.getInstance();

        // changing variable of instance x
        x.s = (x.s).toUpperCase();

        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
        System.out.println("\n");

        // changing variable of instance z
        z.s = (z.s).toLowerCase();

        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
    }
}
```

There's a different way to write the above class, it's singleton that is thread-safe:
```java
class Service {
    private static final Service instance = new Service();
    String s;
    private Service(){
        s = "Some text!";
    }
    static Service getInstance(){
        return instance;
    }
}
```
