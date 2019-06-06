# Builder Pattern

> "The builder pattern moves the construction logic for an object outside the class to instantiate. Making this move might be useful for several reasons. You might simply want to reduce the size of a class that has many methods. You may also want to allow step-by-step construction of a target object. This occurs when you acquire the parameters for a constructor gradually, as happens with parsers and may happen with a user interface." - S.J. Metsker: Design Patterns Java Workbook

- Separates object construction from its representation.
- One way of implementing the builder pattern is to have a POJO with a nested `public static class <name of class>Builder` inside it.
- This internal `<name of class>Builder` class has setter methods on the outer class' fields, and in each method returns `this`.
- The internal `<name of class>Builder` class crucially has a `build()` method that returns the outer class object.

The UML diagram shows the components and relationship of the different objects involved:
```
+--------------+      +--------------+
|   Director   |<-----| «interface»  |
+--------------+      |   Builder    |
| +construct() |      +--------------+
|              |      | +buildPart() |
+--------------+      |              |
                      +--------------+
                             ^
                             |
                             |
                    +------------------+
                    | ConcreteBuilder  |
                    +------------------+
                    | +buildPart()     |
                    | +getResult()     |
                    |                  |
                    +------------------+
```

- In the example below, the POJO is `TcpFlow` and the nested builder is `TcpFlowBuilder`.
```java
public class TcpFlow {
    private String foo = "my default";
    private String bar;

    private TcpFlow() {
    }

    public static TcpFlowBuilder builder() {
        return new TcpFlowBuilder();
    }

    private void setBar(final String arg) {
        this.bar = arg;
    }

    private void setFoo(final String arg) {
        this.foo = arg;
    }

    public static class TcpFlowBuilder {
        private TcpFlow tcpFlow;

        TcpFlowBuilder setFoo(final String arg) {
            tcpFlow.setFoo(arg);
            return this;
        }

        TcpFlowBuilder setBar(final String arg) {
            tcpFlow.setBar(arg);
            return this;
        }

        TcpFlow build() {
            return tcpFlow;
        }
    }

}
```

Here's a class that makes use of the builder
```java
class SomethingThatUsesTCPFlow {
    void someMethod() {
        TcpFlow.builder()
                .setFoo("arg")
                .setBar("arg")
                .build();
    }
}
```
