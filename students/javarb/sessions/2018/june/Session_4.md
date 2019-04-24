# SOFTWARE ENGINEERING IN JAVA

## Session 4 (19/06/2018)

- Access modifiers
- Java packages
- Inheritance
	- Interfaces

### Notes:

#### Access modifiers

Access modifiers allows restrict in more or less measure the access to resources. The following chart describes the access scopes:

|              | Class | Package | Subclass | World |
|--------------|-------|---------|----------|-------|
|*public*      |   +   |    +    |    +     |   +   |
|*protected*   |   +   |    +    |    +     |       |
|*no modifier* |   +   |    +    |          |       |
|*private*     |   +   |         |          |       |

More information in the from the [Java control access] documentation and this [stackOverflow answer][1].

In general this corresponds with the [Law of Demeter](https://en.wikipedia.org/wiki/Law_of_Demeter) (only talk with classes related with you) over the following elements:

- Classes 
- Methods
- Fields
- Enums
- Interfaces

What is suggested is always to use this *Access Modifiers* in order from above to below. So, depending on the needs, if this isn't possible to use the more restrictive wich is `private`, then use the next `nothing`, and doing in the same way still reach the last `public` wich is the most permisive and of common use:

- private
- nothing
- protected
- public

An example of use is when we need to use a class from another package, we'll need to make it `public`. The reason of it is that anything private is only visible to the surrounding class but not visible to top level classes, hence if we've already a top level class, this not make sense to declare it as `private`. Then we should always to use `public` or no modifier in top level *classes*, *enums* or *interfaces*.

#### Java packages

Java packages allows to group related code in a way be easy to use and import from another `Classes`. This allows to group and manage code easily, overall for big projects with a lot of files. Furthermore, use of Java packages could easily leverage the adoption of best practices by grouping code by features instead of by type of classes (or *layers*), which helps to clarity and cleaness of code.

More information about Java packages definition [here][Java Packages] and more about grouping by features can be seen [here][16] and [here][17].

Still packages are similar to namespaces and modules in other languages, they have some differences. One of them is that packages names are commonly set using the [rDNS](https://en.wikipedia.org/wiki/Reverse_DNS_lookup) hostname of the application. For example:

- `com.kinbiko.bugsnagmavenplugin.build`
- `co.org.osso.databasic`

When we group classes into packages, is needed to consider that all classes inside same package can use each other methods directly, but if they are calling classes inside other packages, is needed to import them:

```java
import co.org.osso.databasic.query.Query;
```

#### Inheritance

There exists two kinds of inheritance *Interfaces* and *Abstract Classes*.

##### Iterfaces

Interfaces are a kind of "contract" which we use to define functionalities (methods) and/or fields of a class in order to other classes can implement them by accepting this "contract". Thus, interfaces cannot implement any method but just define it, where implementation is made into classes are inheriting the interface (implementing it).

We were working with a shapes example for the use of interfaces. So we create a new package called `inheritance` (this wasn't using rDNS convention)

Thereby, the `Shape` interface was created inside `ìnheritance` package and defined as follows:

```java
package inheritance;

interface Shape{

    double area();
    double circumference();

}
```

After were created 2 classes: `Square.java` and `Circle.java` for implement (inherit) `Shape` interface and set methods to calculate area and circumference (perimeter).

After, that implementations of `Shape` interface could be instantiated and used in `Main` class:

```java
Square square = new Square(10);
System.out.println("Square area = " + square.area());
System.out.println("Square circumference = " + square.circumference());

```

But an useful improvement was made to that code using the characteristics of interfaces: 

A method called `reportShape` was created. That method receives a `Shape` as argument and prints to console the returning value of the two methods defined in the interface: `area()` and `circumference()`. I this way, when the call to that method is made, a `Circle` and a `Square` object are passed in order to print the results according what object was passed as argument. This works because both objects Square and Circle are using the same interface `Shape` so we can use this as base inside the method. Following the code:

```java
package inheritance;

public class Main {

    //    Alt enter and run this (when there are several main)
    public static void main(String[] args) {

        reportShape(new Square(10));
        reportShape(new Circle(1));
    }

    private static void reportShape(Shape shape) {
        System.out.println("area = " + shape.area());
        System.out.println("circumference = " + shape.circumference());
    }

}
```

Interfaces are widely used, even in the real world where we can found several examples as a keyboard, the hands, the eyes, etc.

In programming, an use case for interfaces is a database repository where it's possible to switch between DBMS easily because the interface, then each class implementation have a particular implementation for the use of a specific DBMS.

Another example are the APIs (Application Programming Interfaces) that show a base layer to use, then we use it indifferently of the underlying code.

Use of interfaces allows to not affect programs when changes arise inside implementations. However, the only way an interface can affect it is if the interface definition changes. Indeed this is the reason for crashes in Operating Systems.

#### Other notes

To print a variable defined in low level (array of bytes) we need to build a new String
```java
byte[] jsonData = Files.readAllBytes(Paths.get(jsonFile));
System.out.println("jsonData = " + new String(jsonData));            

```

### Resources:
**Packages**

 - [Java packages] - Java Docs
 - [Java: Feature-oriented package structures and the default access modifier][16]
 - [Package by feature, not layer][17]

**Control Access**

 - [Java control access] - [Java Access Modifiers][9]
 - [StackOverflow answer][1] about control access 

**Interfaces and Abstract Classes**

 - [Interface vs Abstract class vs Concrete class][2]
 - [Iterfaces, Abstract classes - StackOverflow answer][6]
 - [Difference Between Abstract Class and Interface in Java](https://beginnersbook.com/2013/05/abstract-class-vs-interface-in-java/)
 - [Abstract Methods and Classes][3] 
 - [Difference between `extend` and `implements`][10]
 - [Multiple Inheritance in Java][19]

**Exceptions Handling**

 - [Catch or Specify Requirement (Throws clause) and The Three Kinds of Exceptions -Java Docs][12]
 - [How to Specify and Handle Exceptions in Java][13]
 - [Why, When and How to Implement Custom Exceptions in Java][14]
 - [Checked vs Unchecked Exceptions in Java][15]
 
**Complementary information**

 - [Java `static` Keyword][4]
 - [Java `final` Keyword][5]
 - [Computational efficiency - Big O notation][7]
 - [Duck Typing][8]
 - [C++ interfaces][11] 
 - [Template method pattern][18]
 - Recommended websites for Java tutorials and articles: 
    - https://www.mkyong.com/
    - http://www.baeldung.com/
 
[Java control access]: https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
[Java packages]: https://docs.oracle.com/javase/tutorial/java/package/packages.html
[1]: https://stackoverflow.com/a/215505
[2]: https://medium.com/heuristics/interface-vs-abstract-class-vs-concrete-class-196f20c3af9a
[3]: https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
[4]: https://www.javatpoint.com/static-keyword-in-java
[5]: https://www.javatpoint.com/final-keyword
[6]: https://stackoverflow.com/a/1913185
[7]: https://rob-bell.net/2009/06/a-beginners-guide-to-big-o-notation/
[8]: https://es.wikipedia.org/wiki/Duck_typing
[9]: https://beginnersbook.com/2013/05/java-access-modifiers/
[10]: https://stackoverflow.com/a/10839155
[11]: https://www.tutorialspoint.com/cplusplus/cpp_interfaces.htm
[12]: https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html
[13]: https://stackify.com/specify-handle-exceptions-java/
[14]: https://stackify.com/java-custom-exceptions/
[15]: https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/
[16]: https://kinbiko.com/java/feature-oriented-architecture/
[17]: http://www.javapractices.com/topic/TopicAction.do?Id=205
[18]: https://en.wikipedia.org/wiki/Template_method_pattern
[19]: https://www.journaldev.com/1775/multiple-inheritance-in-java
[exceptions]: ../../../resources/sessions/session4/exceptions_classes.png "Exceptions Classes"


### TODO
- [x] Review Abstract classes

- [x] Work more with exceptions. Make own exception (*worked on session 5*).

- [x] Review answers to the following interview questions:
	
 	
**What is the difference between the abstract class and a interface and when you would to use them?**
  	
[Interfaces are like a contract][6] bewteen parts: the one making the program accepts what an interface proposes but you implements as you want. Abstract classes are like setted (implemented) predefined behaviours (methods) that inherited classes adapts (if they aren't [`final`][5]) and use.

Tecnically, fields in interfaces are automatically set to `public`, [`static`][4] and `final` and methods are always [abstract][3] (optional keyword) and set to `public`. While in [abstact classes][3] we can define `static`, `final` or `static final` fields, and can have abstract (mandatory keyword) or [concreted][2] (implemented) methods that can be defined as `public`, `protected` or `privated`.

Other difference lies is the fact that classes can implements multiple interfaces but only can to extend one abstract or normal class. In this sense is also util to mention that interfaces are implemented, while abstract or concrete clases are extended ([more info][10]). More about multiple inheritance and a comparison between *Inheritance vs Composition* can be seen [here][19]

Also, one interface can only extends another interface while abstract classes can extend from other classes be abstract or concrete.

In terms of [computational efficiency][7], interfaces consumes less CPU since they aren't a class but a place where the inherited classes look-up for the methods and fields to implement themself. While abstract classes are real classes that can have implemented methods and their consequent computational time and load.

However, in practical terms, it depends on the programming language. For example, in C++ [interfaces are implemented using abstract classes][11], so these terms aren't well differentiated.  Other examples of this can be read in part of a [StackOverflow answer][6] about this topic:

  > While abstract classes and interfaces are supposed to be different concepts, the implementations make that statement sometimes untrue. Sometimes, they are not even what you think they are.

  > In Java, this rule is strongly enforced, while in PHP, interfaces are abstract classes with no method declared.

  > In Python, abstract classes are more a programming trick you can get from the ABC module and is actually using metaclasses, and therefore classes. And interfaces are more related to [duck typing][8] in this language and it's a mix between conventions and special methods that call descriptors (the __method__ methods).

  > As usual with programming, there is theory, practice, and practice in another language :-)
  	
For other side, talking about when to use one or other option is addressed in the official [Java Documentation][3]:

  > Consider using abstract classes if any of these statements apply to your situation:

   > - You want to share code among several closely related classes.

   > - You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).

   > - You want to declare non-static or non-final fields. This enables you to define methods that can access and modify the state of the object to which they belong.

  > Consider using interfaces if any of these statements apply to your situation:

   > - You expect that unrelated classes would implement your interface. For example, the interfaces Comparable and Cloneable are implemented by many unrelated classes.

   > - You want to specify the behavior of a particular data type, but not concerned about who implements its behavior.

   > - You want to take advantage of multiple inheritance of type.

In regards to one of the previous statements, where is established: 

  > *"You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private)".* 

It's debatable, since if we have many non-private methods and fields, the best approach should be to split into smaller classes and use interfaces instead to use a big abstract class.

Also, is important to mention that interfaces should be prefered than abstract classes, since the use of these latter leads to the code be hard to break apart when be necessary and, ideally, the Software should be soft and easy to adapt and manipulate.

**What is the difference between checked and uncheck exceptions?**

There is three kind of exception in Java: *Checked Exceptions*, *errors* and *runtime exceptions*.

`Checked exeptions` are those that a well-written application should anticipate from the beggining, for example a program configured for opening a file by using file manipulation method `java.io.FileReader`, while the file could just open fine, there exists a possibility that file not exists, then this must throws the `java.io.FileNotFoundException` and that exception handling should be defined by the programmer through the *Catch* or *Throws clause (also called Specify Requirement in Java docs)*. Then `checked exeptions` are necessary and verified in compilation time, in such a away the program will not compile if they not exists.

The secound class of exception in `error` and these are conditions that cannot be predicted in order to do a *Catch* or *Throws clause* beforehand. This kind of exceptions aren't very common but when they occurs the most are dued to running out space in memory or disk.

The third class are those exceptions that are internal to the applications, but that usually the application cannot anticipate or recover from, they're bugs. For example, in the application could to exists an logic error that pass `null` the `FileReader` object instead to pass the valid file name, so this will throw a `NullPointerException`. In fact `NullPointerException` are the most common type of exception, and the most of the time they occurs dued to the invokation of methods on a null variable. For example:

```java
  String a = null;
  a.length();
```

So, `uncheked exceptions` cannot be cheked in compilation time. Therefore, when a program is compiled, the compiler will not indicate that is required any special handling for errors, but anyway if the program fails in some point this will throws an Exception.

In the following image is possible see the Class structure of the error handling in Java and that all under `Throwable` is checked except for `Error` and `RuntimeException`:

![alt text][exceptions]

Exception handling is specially different in other programming languages where all exceptions are unchecked and, hence, a programmer responsability.

See more of it [here][12], [here][13] and [here][14]

