# 3 Pillars of OOP

- Encapsulation: "Class exoskeleton"
- Inheritance: "A is-a B"
- Polymorphism: "B or C, as long as they are A"

Polymorphism: One name, many forms. Method overloading is an example of polymorphism.

# Extending classes: `abstract` classes and `interface`s

## `Abstract` classes

- keyword `abstract` is added to the class
- These are super classes that are meant to be extended by other classes (other classes can inherit from that class)
- You can't instantiate them (the construction must be done through the subclasses)
  *Subtlety: You CAN have a constructor in an abstract class, but it is only invoked if you call super(arg1, arg2, ...) in a child constructor.*
- You cannot have `abstract` methods in non-`abstract` classes
- The `abstract` methods have no braces, no implementation, and just ends in a semicolon after the method signature.
- You can think of abstract classes as being partially completed classes
- Subclasses must conform to the contracts set up by the abstract class when inheriting, or be abstract themselves
- Useful for when when you know the methods required, but you might not know how they are to be implemented
- Cannot be marked as final (as they **must** be extended to serve any purpose)
- In UML, abstract classes and abstract methods are in *italic*
- Prefer using interfaces over abstract classes where possible.

```java
public abstract class SuperClass {
    public int getNb() {
         //specify what must happen
        return 1;
     }

     public abstract int getNb2();
 }

 public class SubClass extends SuperClass {
      //you must override the implementation
      @Override
      public int getNb2() {
        return 3;
     }
 }
```

## `interface`s:

- Keyword `interface` is used instead of `class`
- Must be `public` or package-private
- You cannot create an instance of an `interface`
- Therefore, it can have no constructor
- It is a contract that when applied guarantees that all the methods it will be implemented
- A hierarchy of interfaces can be created
- A class can implement as many interfaces as needed
- Interfaces can implement any number of interfaces.
- Common, but often violated, interface naming convention: start with Can-, or end with -able
- Recommended not to make interfaces just to hold constants. Interfaces are for contracts, and state is not a contract (put the constants in a class of constants instead)
- The `public` keyword on interface methods is redundant (on the interface, not the implementing classes).

```java
public interface ExampleInterface {
    void doAction();
    String doThis(int number);
}

public class Implementation implements ExampleInterface {
    @Override
    public void doAction() {
        //specify what must happen
    }

    @Override
    public String doThis(int number) {
        //specfiy what must happen
    }
}
```

### Class of constants

```java
public class Messages {
    public static class Format {
        public static final String STRING_IS_EMPTY = "String Contents: Empty String";
        public static final String NO_RESULTS = "No results";
    }
}
// to access the contants call Messages.Format.STRING_IS_EMPTY
```

- All fields in an interface are implicitly public, static, and final - their use is redundant.
- The general (incomplete) syntax for declaring an interface is:

```java
<modifiers> interface <interface-name> {
        Constant-Declaration
        Method-Declaration
        Nested-Type-Declaration
}
```

# Implementing vs Extending

`implements` means you are using the elements of a Java Interface in your class.
`extends` means that you are creating a subclass of the base class you are extending.
- A class can only "implement" an interface.
- A class only "extends" a class.
- An interface can extend another interface.
- A class can only extend one other class.
- A class can implement several interfaces.


### Polymorphism example

```java
package inheritance;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        Animal dog = new Dog();
        cat.speak();
        dog.speak();
    }
}
```

## Casting

```java
class A {}

class B extends A{
    public void methodThatExistsOnlyOnB() {
    }
}

public class Whatev {
    public static void main(String[] args) {
        A obj = new B();
        doStuff(obj);
    }

    private static doStuff(A obj) {
        if (obj instanceof B) {
            ((B) obj).methodThatExistsOnlyOnB();
        }
    }
}
```
