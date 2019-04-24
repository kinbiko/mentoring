# Learning Java

*First sessions have been partly documented and are available at: http://learning-java.surge.sh/*

#### Declaration and assignment:

```java
int num1;           // Declaration of a variable num1. 0 by default
num1 = 50;          // Assignment
int num2 = 100;     // Declaration of variable num3 and its initialization
```

#### Printing and array initialisation

```java
//The following snippet of code prints all elements of an int array called numArray:
int[] numArray = {10, 20, 30, 40};
for(int num : numArray) {
    System.out.println(num);
}
```

## Object oriented design

Look around you...
The real-world objects in the area around you (such as computer, clock, mirror, air-conditioner, shelves, door, etc.) have two major characteristics:

| Name      | What                            | OOP terminology             |
|-----------|---------------------------------|-----------------------------|
| state     | Qualities, looks, other objects | stores its state in fields  |
| behaviour | Things it can do                | expose behaviour in methods |

Classes are the basic units of programming in the object-oriented paradigm, and are used as templates to create objects.

A class in Java consist of two main kinds of components:

- Fields
- Methods (and Constructors)

#### The main() method serves as the entry point for a Java application:

This signature must be exactly as below, and is required in all applications. Not all Java code needs to be an application however, as source code can also be used as a library.

```java
public static void main(String[] args) {
    // Method body goes here
}
```

Fields and methods are also known as members of the class. Classes can also be member variables of a class, although this is not as common as fields and methods.

In Java, you should start a class name with an uppercase letter and capitalize the subsequent words (`CamelCase`), for example, Human, Table, SumOfTriangles, etc. The name of fields and methods should start with a lowercase letter and the subsequent words should be capitalized (`pascalCase`), for example, name, firstName, maxDebitAmount, etc.
This is a convention, and not a requirement, but as far as conventions go it's very well established. Violating this convention may very well ruin a job interview.

#### The general syntax for declaring a class in Java is:
```java
<<modifiers>> class <<class name>> {
    // Body of the class goes here
}
```

### Access level modifiers

| Access Level for Class Member | Accessibility                             |
|-------------------------------|-------------------------------------------|
| private                       | Only within the same class                |
| package-private (aka default) | In the same package                       |
| protected                     | Same package or descendant in any package |
| public                        | Everywhere                                |

*It's important to note that these are cascading permissions. E.g. package-private is also accessible from within the same class. Similarly, protected is also visible by classes in the same package, etc.*

The following table may illustrate this better ([credit, slightly modified](https://stackoverflow.com/a/215505)):

```
            │ Class │ Package │ Subclass │ World
────────────┼───────┼─────────┼──────────┼────────
public      │   +   │    +    │     +    │   +
────────────┼───────┼─────────┼──────────┼────────
protected   │   +   │    +    │     +    │
────────────┼───────┼─────────┼──────────┼────────
no modifier │   +   │    +    │          │
────────────┼───────┼─────────┼──────────┼────────
private     │   +   │         │          │
```

Fields of a class represent the state of objects of that class. You might also hear 'property' or 'attribute' in colloquial speak when referring to a field, but these terms should be avoided as they are somewhat overloaded. Suppose every object of human class has two fields: a name and an age. The human class should include declarations of two fields: one to represent name and one to represent age.

*The fields are declared inside the body of the class. The general syntax to declare a field in a class is:*
```java
<<modifiers>> class <<class name>> {
    // A field declaration
    <<modifiers>> <<data type>> <<field name>> = <<initial value>>;
}
```

A minimalist example, ignoring all optional syntax:

```java
// Human.java
class Human {
    String name;
    int age;
}
```

The Human class declares two fields: name and age. The `name` field is declared as as being a String. The `age` field is declared as being an `int`. Every instance (or object) of the Human class will have a their own value in these two fields.

*Java lets you declare two types of fields for a class:*
- Class fields
- Instance fields

```java
class Human {
        String name;        // An instance variable
        int age;            // An instance variable
        static long count;  // A static field because of the static modifier
}
```

A class variable is also known as a static field. An instance/member variable is also known as a non-static variable - or field.

*Create an instance of a class:*
```java
new <<Call to Class Constructor>>;
```

The new operator is followed by a call to the constructor of the class whose instance is being created. The `new` operator creates an instance of a class (by allocating the memory on heap). *no need to think about memory allocation at the moment because Java handles this automagically.*

*The following statement creates an instance of the Human class:*

```java
new Human();
```

When you do not add a constructor to a class, the Java compiler adds one for you. The constructor that is added by the Java compiler is called a `default constructor`. The default constructor is public and accepts no arguments, and does nothing. The name of the constructor of a class is the same as the class name.

The default constructor looks like this:
```
public Human(){

}
```

*The general syntax for a method declaration is of the form:*

```java
<<modifiers>> <<return type>> <<method name>> (<<parameters list>>) <<throws clause>> {
        // Body of the method goes here
}
```

#### TIP: Error-driven development

Write the stuff you wish you had - just pretend it exist. The IDE (IntelliJ) will then complain at you. For example, you can write the name of a method you wish you had - then hotkey (opt + Return) to generate.

### `this` keyword

The `this` keyword is a reference to the current instance of a class. It can be used only in the context of an instance, e.g. it cannot be used in static methods.

```java
public class MyClass {
        int varA = 555;
        int varB = varA;      // Assign value of varA to varB
        int varC = this.varA; // Assign value of varA to varC
}
```
A lot of the time you can get away with not using the `this.` prefix. It's up to the personal preference/project style guide when you should use it.

### A constructor is a named block of code that is used to initialize an object of a class immediately after the object is created.

- The structure of a constructor looks similar to a method.
- A constructor does not have a return type.
*The general syntax for a constructor declaration is:*

```java
<<Modifiers>> <<Constructor Name>>(<<parameters list>>){
        // Body of constructor goes here
        // No return keyword
}
```

#### TIP: Keep your constructors safe.

You will occasionally have to write code that might not work, based on some external factors (e.g. the computer you're currently using, the input parameters of your method etc.) Avoid this type of code in constructors, as this is bad design. It's a clear sign that the constructor is doing more than just setting up it's initial state. (e.g. making database connections). It's better to only do part of the initialisation in a constructor, and do the more dangerous initialisation in a separate method.

*Example:*

```java
public class Dog {
    // Constructor #1
    public Dog() {
        System.out.println("A dog is created.");
    }
    // Constructor #2
    public Dog(String name) {
        System.out.println("A dog named " + name + " is created.");
    }
}
// These objects use different constructors
Dog dog1 = new Dog();
Dog dog2 = new Dog("Cupid");
```

### Getters and Setters

Also known as "accessors" and "mutators"/"modifiers".

```java
class Clock {
        String time;
        void setTime (String t) {   //setter
           time = t;
        }
        String getTime() {          //getter
           return time;
        }
}

public class ClockTest{
    public static void main (String [] args) {
        Clock clock = new Clock();
        clock.setTime("12:45")
        String now = clock.getTime();
        System.out.println("The time is: " + now);
    }
}
```
