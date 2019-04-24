# Casting
```
class A {}
class B extends A{
    public void methodThatExistsOnlyOnB() {
    }
}
class Whatev {
    public static void main(String[] args) {
        A obj = new B();
        //....
        if (obj instanceof B) {
            ((B) obj).methodThatExistsOnlyOnB();
        }
    }
}
```
// "/".equals(stringRep.substring(1))  --> null safe-er (... not super null safe though...)


# Debugging Tool in IntelliJ
TODO: write about debugging



---
GIT speedup: git add .;git commit -m "review comment: constructors in abstract classes";git push
Markdown -> HTML:   https://dillinger.io/
Tables:             https://www.tablesgenerator.com/markdown_tables
Reliable sites: mkyong, baeldung
Note: When dealing with finance/money - leave values as integers, and when displaying, divide by 100.
---



---
Remember: avoid checked exception - always wrap in (preferably your own implementation of a) RuntimeException.
a method declaration that throws BlahException is a sign that you're doing something wrong!
checked exception: caught as low as possible
unchecked can be causght close to main level.

HW: 10 Questions about computing.

1. How much JS should I know? -> node, npm
1. Closures/inner functions
1. ports and sockets
1. S SH
1. REST APIs
1. When would you avoid using an external library
1. When will you adopt Java 11? http://jdk.java.net/

```javascript
function fish() {
    var a = "hello"
    function dog() {
        console.log(a)
    }
    someOtherObject.doStuff(dog) //not invoking dog, just passing the function itself
}
```


CSV -> JSON online converter



### Q: What's the difference between an interface and an abstract class?
#### interface: To implement a contract by multiple unrelated objects
An interface is a contract: The person writing the interface says, "hey, I accept things looking that way", and the person using the interface says "OK, the class I write looks that way".
An interface is an empty shell. There are only the signatures of the methods, which implies that the methods do not have a body. The interface can't do anything. It's just a pattern.
Consider using interfaces if :
You expect that unrelated classes would implement your interface. For example,many unrelated objects can implement Serializable interface.
You want to specify the behaviour of a particular data type, but not concerned about who implements its behaviour.
You want to take advantage of multiple inheritance of type.
interface provides "has a" capability for classes.
#### abstract class: To implement the same or different behaviour among multiple related objects
Consider using abstract classes if :
You want to share code among several closely related classes.
You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).
You want to declare non-static or non-final fields.
abstract class establishes "is a" relation with concrete classes.


https://en.wikipedia.org/wiki/Anti-pattern


### Lambda Expressions

```
(n) -> n*n
```
parameters required by the expression -> the actions of the lambda expression
“n becomes n\*n”, or “n becomes n squared”.


Q2) You can pretty safely ignore getters/setters for pojos, built-in Java interfaces, and the mention of the Main class. Also, let's keep arrows super simple for now, only indicating what calls what (not relationships between classes)

Q3) where/when do you add backlog requirements - specific branch?

Q4) Not sure how to deal with:
Let's be explicit about how the application knows it's a transaction, and not a report or even gibberish.

An introductory note to a diagram is not neccessary - treat the image as text.

Reply to the individual comment, not global comment.

Timeboxing: investigations


Q0) How is this choice made?
When `Main` is called, it starts the `Application` class, where a choice mey be made to create a `transaction` or a `report` using the eponymous public methods.


Q1) Note that the design phase is where you have the power to make decisions, and they should be made at this stage rather than in development. Be assertive. If there's something you don't like about the current design of the application then re-write it. You don't have to stick to what's already been done (within reason, of course).
Don't like the class names? Change them. Don't like the way we've structured the data model? Change it.
Let's avoid any temporal references (e.g. 'future sprints' etc) in designs as the time of implementation and design could be months apart. When designing we instead mention that feature X is out of scope for this design.

Tradeoff between: `What I want it to do` vs `this sprint`.
--> out of scope for this design


As the application develops, the future commands might be:

| Request                               | CLI command                                                                   |
|:--------------------------------------|:------------------------------------------------------------------------------|
| Open new account:                     | $java moneymaker -accnew <username> <DOB> <address> <contact details>         |
| Delete existing account:              | $java moneymaker -accdelete <id> <username>                                   |
| Edit account details                  | $java moneymaker -accedit <id> <description> <field to update> <updated info> |
| Report: last 10 transactions          | $java moneymaker -report10 <id>                                               |
| Report: last months' transactions     | $java moneymaker -reportmonth <id>                                            |
| Report: transactions between dates    | $java moneymaker -reportdates <id> <start date> <end date>                    |
| Report: all transactions              | $java moneymaker -reportall <id>                                              |
| Report: search by description keyword | $java moneymaker -reportsearchkw <id> <keyword>                               |




## Check contents of directory and sub-directories:

```java
void checkFile() {
    File curDir = new File(".");
    getAllFiles(curDir);
}

private static void getAllFiles(File curDir) {
    File[] filesList = curDir.listFiles();
    for (File f : filesList) {
        if (f.isDirectory())
            System.out.println(f.getName());
        if (f.isFile()) {
            System.out.println(f.getName());
        }
    }
}
from: https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory
```

- The thing (class/object) that relies on the dependency should only ask for an abstraction.
- whoever wants to use this thing needs to specify the implementation of the abstraction.
- the abstraction is ideally an interface.
- Coding to interfaces.


PO - product owner

---

Remember: avoid checked exception - always wrap in (preferably your own implementation of a) RuntimeException.
a method declaration that throws BlahException is a sign that you're doing something wrong!
checked exception: caught as low as possible
unchecked can be causght close to main level.

HW: 10 Questions about computing.

1. How much JS should I know? -> node, npm
1. Closures/inner functions
1. ports and sockets
1. S SH
1. REST APIs
1. When would you avoid using an external library
1. When will you adopt Java 11? http://jdk.java.net/

```javascript
function fish() {
    var a = "hello"
    function dog() {
        console.log(a)
    }
    someOtherObject.doStuff(dog) //not invoking dog, just passing the function itself
}
```


CSV -> JSON online converter
---
### SOLID Principals:
1. Single Responsibility Principle <<-- go focus on this one Tom.
> A class or module should have one, and only one, reason to be changed.
> Every module or class should have responsibility over a single part of the functionality provided by the software, and that responsibility should be entirely encapsulated by the class.
> Gather together the things that change for the same reasons. Separate those things that change for different reasons. - Robert Martin
- Separation of concern
- Level of cohesion between elements in the class
2. Open / Close Principle
3. Liskov Substitution Principle
4. Interface-Substitution Principle
5. Dependency Inversion Principle

### Persisting Data: data that outlives the process that created it
Taking an object that exists in runtime and store that oject out to a bytestream - and take this bytestream and completely reproduce that object. This means that you can save the state of your program to your filesystem or database. This is known as serialization and deserialization.
Simply speaking serialization is a process of converting an object into stream of bytes so that it can be transferred over a network or stored in a persistent storage. A Java object is serializable if its class or any of its superclasses implements the java.io.Serializable interface.
Deserialzation is exact opposite - Fetch a stream of bytes from network or persistence storage and convert it back to the Object with the same state.
Requirements to being serializable: Primitives are serializable by default, but classes must implement Serializable.
- Serializable: an interface that is a marker interface (has no methods) - it indicates that the type can be serialized/deserialized.
Types that perform Serialization:
- ObjectOutputStream
- ObjectInputStream
Marking fields `transient` means that the field won't be serialized.
Serializable pojo methods HAVE to be `public`.


### Refactoring Tips
command-option + m: If you spot `Single Responsibility Principle`
command-option + n: if only used once, and not going to make the line too long.
command-option + c: for `magic literals`. Variables should only have to be changed in one location

`Sprint Unit`:
`Backlog`: requirements identified that don't belong in the current sprint - work for later.

HW: What are my goals???
Goldfish Sprint Goals: Over the next week I will:
1. More commits and pull requests per session
Medium Term:
1. Answer 5 StackOverflow questions: get reps to avoid ads
1. Write a short essay on an area I'm confused about: technical blog post
Long term:
1. Complete the Java SE 8 Programmer I 1Z0-808 certification
1. Learn best practices on how to TDD with Python. And then teach Roger
1.

### Q: What's the difference between an interface and an abstract class?
#### interface: To implement a contract by multiple unrelated objects
An interface is a contract: The person writing the interface says, "hey, I accept things looking that way", and the person using the interface says "OK, the class I write looks that way".
An interface is an empty shell. There are only the signatures of the methods, which implies that the methods do not have a body. The interface can't do anything. It's just a pattern.
Consider using interfaces if :
You expect that unrelated classes would implement your interface. For example,many unrelated objects can implement Serializable interface.
You want to specify the behaviour of a particular data type, but not concerned about who implements its behaviour.
You want to take advantage of multiple inheritance of type.
interface provides "has a" capability for classes.
#### abstract class: To implement the same or different behaviour among multiple related objects
Consider using abstract classes if :
You want to share code among several closely related classes.
You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).
You want to declare non-static or non-final fields.
abstract class establishes "is a" relation with concrete classes.

### Stream Processing

Stream Creation
Let’s first obtain a stream from an existing array:
```
private static Employee[] arrayOfEmps = {
    new Employee(1, "Jeff Bezos", 100000.0),
    new Employee(2, "Bill Gates", 200000.0),
    new Employee(3, "Mark Zuckerberg", 300000.0)
};

Stream.of(arrayOfEmps);
```
We can also obtain a stream from an existing list:
```
private static List<Employee> empList = Arrays.asList(arrayOfEmps);
empList.stream();
```
And we can create a stream from individual objects using Stream.of():
```
Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
```
Or simply using Stream.builder():
```
Stream.Builder<Employee> empStreamBuilder = Stream.builder();

empStreamBuilder.accept(arrayOfEmps[0]);
empStreamBuilder.accept(arrayOfEmps[1]);
empStreamBuilder.accept(arrayOfEmps[2]);

Stream<Employee> empStream = empStreamBuilder.build();
```

Stream operations are chained together into pipelines. For example, if we wanted to color only the blue shapes red, we could say:
```
shapes.stream()
      .filter(s -> s.getColor() == BLUE)
      .forEach(s -> s.setColor(RED));
```
If we wanted to collect the blue shapes into a new List, we could say:
```
List<Shape> blue = shapes.stream()
                         .filter(s -> s.getColor() == BLUE)
                         .collect(Collectors.toList());
 ```
```
foo.stream()
.filter(foo -> foo.getBarValue() < 42)
.map(Foo::getBarString)
.distinct()
.limit(3)
.collect(Collectors.toCollection())
.forEach(System.out::println)

foo.stream()
.filter(foo -> foo.getBarValue() < 42)
.map(Foo::getBarString)
.distinct()
.limit(3)
.collect(Collectors.toList()) <-- this converts it to a List
.forEach(System.out::println)
```


### Lambda Expressions

```
(n) -> n*n
```
parameters required by the expression -> the actions of the lambda expression
“n becomes n\*n”, or “n becomes n squared”.

https://en.wikipedia.org/wiki/Anti-pattern

Q0) How is this choice made?
When `Main` is called, it starts the `Application` class, where a choice mey be made to create a `transaction` or a `report` using the eponymous public methods.


Q1) Note that the design phase is where you have the power to make decisions, and they should be made at this stage rather than in development. Be assertive. If there's something you don't like about the current design of the application then re-write it. You don't have to stick to what's already been done (within reason, of course).
Don't like the class names? Change them. Don't like the way we've structured the data model? Change it.
Let's avoid any temporal references (e.g. 'future sprints' etc) in designs as the time of implementation and design could be months apart. When designing we instead mention that feature X is out of scope for this design.

Tradeoff between: `What I want it to do` vs `this sprint`.
--> out of scope for this design


As the application develops, the future commands might be:

| Request                               | CLI command                                                                   |
|:--------------------------------------|:------------------------------------------------------------------------------|
| Open new account:                     | $java moneymaker -accnew <username> <DOB> <address> <contact details>         |
| Delete existing account:              | $java moneymaker -accdelete <id> <username>                                   |
| Edit account details                  | $java moneymaker -accedit <id> <description> <field to update> <updated info> |
| Report: last 10 transactions          | $java moneymaker -report10 <id>                                               |
| Report: last months' transactions     | $java moneymaker -reportmonth <id>                                            |
| Report: transactions between dates    | $java moneymaker -reportdates <id> <start date> <end date>                    |
| Report: all transactions              | $java moneymaker -reportall <id>                                              |
| Report: search by description keyword | $java moneymaker -reportsearchkw <id> <keyword>                               |


Q2) You can pretty safely ignore getters/setters for pojos, built-in Java interfaces, and the mention of the Main class. Also, let's keep arrows super simple for now, only indicating what calls what (not relationships between classes)

Q3) where/when do you add backlog requirements - specific branch?

Q4) Not sure how to deal with:
Let's be explicit about how the application knows it's a transaction, and not a report or even gibberish.

An introductory note to a diagram is not neccessary - treat the image as text.

Reply to the individual comment, not global comment.

Timeboxing: investigations


## Dependency Injection

Dependency is just another object that your class needs to function.

|-------|           |-------------|
| Class | --uses--> | Other Class |
|-------|           |-------------|
                            ^
                            |
                       dependency
                            |
                            v
|-------|           |-------------|
| Model | --fetch-> |   Database  |
|-------|           |-------------|

We can say of the example above that the model class has a dependency on the database object.

Injecting dependencies means that the dependencies are pushed into the class from the outside.
That means that you shouldn't instantiate dependencies using the `new` operator from inside the class.
Instead, take it as a constructor parameter, or via a setter
It decouples the class' construction from the construction of its dependencies.
Code should depend upon abstractions - and by doing this we're de-coupling implementations from classes.


Dependency injection: Fancy way of saying "The fields I need should be given to me in a constructor".
- I use this to set a different Logger and TransactionData in the ApplicationTest, where I define my own implementations of the Logger and TransactionData.
- change the main method to give the Application an instance of a DefaultLogger and a DefaultTransactionData.
- the goal of a small main method is to limit the use of static.
- Seeing as constructors are effectively static as well (you can't call the constructor on an instance of a class) this seemed reasonable.
- This 'constructors-are-static' is a subtle technicality, so you might just have to take my word for it.

## Check contents of directory and sub-directories:

```java
void checkFile() {
    File curDir = new File(".");
    getAllFiles(curDir);
}

private static void getAllFiles(File curDir) {
    File[] filesList = curDir.listFiles();
    for (File f : filesList) {
        if (f.isDirectory())
            System.out.println(f.getName());
        if (f.isFile()) {
            System.out.println(f.getName());
        }
    }
}
from: https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory
```

- The thing (class/object) that relies on the dependency should only ask for an abstraction.
- whoever wants to use this thing needs to specify the implementation of the abstraction.
- the abstraction is ideally an interface.
- Coding to interfaces.




## Multiple users
- Multiple users should be able to make use of the system at the same time.
- Further consideration is needed for simultaneous transactions. For example, if Alice makes a transaction to Bob, and Bob makes a transaction to Alice at the same time then this needs to be carefully handled.
- All modifications of persistent objects performed within the boundary of the transaction will either [(link to SO article)](https://stackoverflow.com/questions/34832758/how-to-handle-transactions-with-concurrency-access-in-spring):
1. be commited at the end of the transaction (i.e. all modification are persisted in the DB)
1. be rollbacked at the end of the transaction (i.e. none of the modifications are persisted in the DB)

## Locking and Concurrency
`Locking` is a technique for handling database transaction concurrency (`concurrency`: the ability to work with the data on more than one queue at the same time.) When two or more database transactions concurrently access the same data, locking is used to ensure that only one transaction at a time can change the data.
There are generally two locking approaches: `optimistic` and `pessimistic`. Optimistic locking assumes that there
will be infrequent conflicts between concurrent transactions, that is, they won't often try to read and change the
same data at the same time. In optimistic locking, the objective is to give concurrent transactions a lot of freedom to process simultaneously, but to detect and prevent collisions. Two transactions can access
the same data simultaneously. However, to prevent collisions, a check is made to detect any changes made to the data since the data was last read.
[(information from Oracle.com)](https://blogs.oracle.com/enterprisetechtips/locking-and-concurrency-in-java-persistence-20)

`Pessimistic concurrency control` – This implies that the service locks the resource so that a client cannot updated it. While the resource is locked, no other client can modify it.

`Optimistic concurrency control` – This implies that a client first obtains a token for the update operation. Once the token is received, it allows the client to perform the update. However, the changes will only apply if the token is still valid.
[(information from 4psa.com)](https://blog.4psa.com/rest-best-practices-managing-concurrent-updates/)



PO - product owner


TODO
- Roger to be contibuter to notes


- definition of done: PR made, approved PR, Merged to master, master passes the build, old branch deleted.
- for design as well




# Casting
```
class A {}
class B extends A{
    public void methodThatExistsOnlyOnB() {
    }
}
class Whatev {
    public static void main(String[] args) {
        A obj = new B();
        //....
        if (obj instanceof B) {
            ((B) obj).methodThatExistsOnlyOnB();
        }
    }
}
```
// "/".equals(stringRep.substring(1))  --> null safe-er (... not super null safe though...)


# Debugging Tool in IntelliJ
TODO: write about debugging

---
Markdown -> HTML:   https://dillinger.io/
Tables:             https://www.tablesgenerator.com/markdown_tables
Reliable sites: mkyong, baeldung
Note: When dealing with finance/money - leave values as integers, and when displaying, divide by 100.
---

```java
"/".equals(stringRep);
```

is null safe-er than

```java
stringRep.equals("/")
```
