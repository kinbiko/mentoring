# What is a class?

Simply put, a class is a blueprint/mould that defines what an object can do and know.
This blueprint provides initial values for its member variables, and implementations of member functions or methods.

These notes will cover *three* (of many) types common in object-oriented programming, namely:

- [`Abstract`](#Abstract)
- [`Interface`](#Interface)
- [`Enum`](#Enum)

## Abstract

An abstract class is a class that is declared *abstract*.
It cannot be *instantiated*, that is, you cannot create an object of an abstract class.

An abstract class is used by creating an inheriting subclass that *can* be instantiated.

### Why use an abstract class?

An abstract class does a few things for any inheriting subclass:

1. Defines both methods which can and can't be used by the inheriting subclass.
1. Defines *abstract methods* which the inheriting subclass must implement.

```java
abstract class Animal {
    public abstract void makeNoise();
}

public class Bird extends Animal {
    public void makeNoise() {
        System.out.println("Chirp, chirp");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal bird = new Bird();
        bird.makeNoise();
    }
}
```

```
Output:
Chirp, chirp
```

Here, each animal must have a sound, and by making this method abstract we have made it compulsory to the child class to give implementation details to this method.
This way we ensures that every animal has a sound.

## Interface

Another way to achieve abstraction in Java, is with interfaces.
An `interface` is *like* a completely abstract class that can only have abstract methods.

```java
interface Animal {
    void makeNoise();
}

public class BaldEagle implements Animal {
    public void makeNoise() {
        System.out.println("Chirp, chirp [freedom intensifies]");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal eagle = new BaldEagle();
        eagle.makeNoise();
    }
}
```

```
Output:
Chirp, chirp [freedom intensifies]
```

Note: While our interface **isn't** public (it is package-private - it may only be accessed by classes defined in the same package), Java defaults all methods contained within an interface to *public*.

### Why use an interface?

Interfaces help to achieve encapsulation - they hide certain details, only showing the important aspects of an object.

While Java does not support *multiple inheritance*, as in, a class can only inherit from *one* superclass, using interfaces allows us to bypass this restriction.
To inherit from two or more interfaces, separate the name of the interface by a comma.

```java
interface Animal {
    void makeNoise();
}

interface Fish {
    void swim();
}

public class Shark implements Animal, Fish {
    public void makeNoise() {
        System.out.println("Duh nuh, duh nuh, duh nuh...");
    }

    public void swim() {
        System.out.println("Splish, splosh");
    }

    public static void main(String[] args) {
        Shark shark = new Shark();
        shark.makeNoise();
        shark.swim();
    }
}
```

```
Output:
Duh nuh, duh nuh, duh nuh...
Splish, splosh
```

## Enum

> Enum is short for "enumeration", which means "specifically listed".

An `enum` is a special type that represents a collection of constants (variables that cannot be changed - it is `static` and `final`). 
It is a complete, finite set of options.

Creating an `enum` is simple - replace the word `class` or `interface` with `enum`, then define your constants separated by commas.

Note - constants in an `enum` should be denoted in uppercase letters, with underscores to represent spaces.

```java
enum Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST
}
```

An constant within an `enum` can be accessed via the dot syntax, namely:

```java
Orientation direction1 = Orientation.NORTH;
Orientation direction2 = Orientation.SOUTH;
```

and so on.

### Why use an `enum`?

The `enum` class should be introduced when a variable (especially a method parameter) can only take one out of a small set of possible values.

#### A little more info

1. *All* enums implicitly extend `java.lang.Enum`. Since Java does not support multiple inheritance, an enum cannot extend anything else.

1. An `enum` in Java is type-safe; you can not assign any value other than those specified within the `enum`.
