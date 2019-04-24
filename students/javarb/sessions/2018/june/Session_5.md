# SOFTWARE ENGINEERING IN JAVA

## Session 5 (25/06/2018)

- GitHub practice - pull request
- Working with exceptions
- Java primitive datatypes vs objects datatype 
- Difference between `==` and `.equals()`

### Notes:

**Pull requests**
When making a pull request is useful to use comments that advice the tasks or steps pending or in process. Also is very helpful to make a brief description the things are being covered in order to provide enough fedback to the interested parties.

Roger was talking me about the possibility to adapt [CI/CD][1] process to the development pipeline of our code. He showed to me an example of CI for one of his repositories using [Travis](https://travis-ci.org/kinbiko/bugsnag-maven-plugin)

We were talking about possibility to include these processes into our classes and the possibility to deploy into a real cloud hosted application using kubernetes. Roger was talking me also about [Zeit][4] [Cloud Broker][5] and recommended to me the reading about microservices, specially the book [***Building Microservices**, Designing Fine-Grained Systems*][6].

**Exceptions**

Java uses `checked exceptions` in several methods in order to avoid programmers omissions of certain conditions for handle adequately error events. For example the fact to open a file needs an error handling when the path doesn't exists. So if a error handling isn't configured for Java's open file method, the program will not be compiled.

In Java exists two ways to handle exceptions: *Catch* and *Throws* clause. The *Catch* clause is prefereable since if we use *Throws* clause in a method, this will be needed to use it in every method and classes related with the current method.

In the following example code, the class `Application` is implementing the interface `ExceptionThrower` which has defined the method `void e() throws Exception;`. We can see there several nested methods and how the last called method is the implementation of interface's method with the *Throws* clause. Therefore, all the calling methods, even the main method, have to add that same *Throws* clause:

```java
package exceptions;

class Application implements ExceptionThrower{

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        a();
    }

    private void a() throws Exception {
        b();
    }

    private void b() throws Exception { 
        c(); 
    }

    private void c() throws Exception {
        d();
    }

    private void d() throws Exception {
        e();
    }

    public void e() throws Exception {
        throw new Exception();
    }

}

interface ExceptionThrower{
    void e() throws Exception;
}
```

That is very inconvenient, and is prefereable to use a *Catch* clause:

```java
package exceptions;

class Application implements ExceptionThrower{

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        a();
    }

    private void a() {
        b();
    }

    private void b() { 
        c(); 
    }

    private void c() {
        d();
    }

    private void d() {
        e();
    }

    public void e() {
        try {
            throw new Exception();
        } catch (Exception e) {

        }
    }

}

interface ExceptionThrower{
    void e() throws Exception;
}
```

By other side, in *databasic* project we were changing *checked exception* for *unchecked exceptions* throwing from `RuntimeException`, and commenting error printings or `printStackTrace()` error messages, since now they are being managed in a different way.

Also, there is not need to return when `throw` is used, since it finishes execution and is returning to calling method bubbling still find a *try/catch* block that handles the error.

In `JackSonObjectMapper.java`:

```java
public void processJSONFile (String jsonFile) {

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = null;

    try {
         rootNode = objectMapper.readTree(openJSONFile(jsonFile));

    } catch (IOException e) {
        throw new RuntimeException(e);
        /*System.err.println("Error: cannot open file: " + jsonFile);
        e.printStackTrace();
        return;*/
    }

    JsonNode nameNode = rootNode.path("name");
    JsonNode ageNode = rootNode.path("age");
    System.out.println("nameNode.asText() = " + nameNode.asText());
    System.out.println("ageNode.asInt() = " + ageNode.asInt());
}

private byte[] openJSONFile(String jsonFile) {

    byte[] jsonData = new byte[0];
    ObjectMapper objectMapper = new ObjectMapper();

    try {
        jsonData = Files.readAllBytes(Paths.get(jsonFile));

    } catch (IOException io) {
        throw new RuntimeException("Error: cannot open JSON file: " + jsonFile, io);
        /*System.err.println("Error: cannot open JSON file: " + jsonFile);
        io.printStackTrace();*/

    }
    return jsonData;
}

```

In `InputProcessor.java` we are invoking methods from `JackSonObjectMapper.java` but we are handling possible error conditions also:

```java
void validateInput(String[] args) {

    if (args[0].equals(DataBasicCommands.INSERT.toString())) {
        validateInsert(args);
        return;
    }

    // Default, make a query
    // TODO: Validate and process the query
    System.out.println("Querying...");

}

private void validateInsert(String[] args) {
    if (args.length == 2) {
        processInsert(args[1]);

    } else {
        throw new RuntimeException("Error: You have to provide a path to JSON file");
       /* System.err.println("Error: You have to provide a path to JSON file");
        help();*/
    }
}

private void processInsert(String arg) {

    // Corner case: Provided file is empty ""
    if (arg.isEmpty()) {
        throw new RuntimeException("Error: The provided path is empty");
        /*System.err.println("Error: The provided path is empty");
        help();
        return;*/
    }

    new JacksonObjectMapper().processJSONFile(arg);

}
```

Finally, in our new *Application.java* class, we define a *try/catch* block that capture and handle all errors that come bubbling from `throw` in called methods and we print them using the `.getMessage()` method. Also this block has a condition to print the complete `printStacktrace()` error if the string *"debug"* is found at the end of the given command arguments.

*Note:* `.getMessage()` method is part of `Throwable` class, this can be seen pressing `ctrl`+`click`(left) over the method.


```java
try {
    new InputProcessor().validateInput(args);

} catch (RuntimeException e) {
    if ("debug".equals(args[args.length - 1])){
        e.printStackTrace();

    } else {
        System.err.println(e.getMessage());
        help();

    }
}
```

In that way we can print "friendly" personalized error messages for each defined error condition, but also print the complete trace for error debug purposes.

**Java primitive datatypes vs object datatypes**

Java primitives are low level datatypes such as `int`, `boolean`, `float`, `char`, etc., and they are written in lowercase. By the other side. Java datatypes are objects that are written with capitalization in the first letter, e.g: `Integer`, `Boolean`, `Float`, `String`, etc. Notice that `String` is an object and hasn't a primitive type since is an array of chars. 

The major difference between Java primitive datatypes and object based datatypes, is that datatype objects inherit several predefined methods. But also another very important characteristic is that objects can contain `null` values, while primitives cannot. For example the following declaration should make not sense for primitives and finish with a compiler error:

```java
int number=null;
```

Have support for `null` values is specially useful if we are dealing with objects values that need to be setted only during the execution of the program, so we can set this values as `null` from the beginning. Also is applicable when we work with databases, since is very common find `null` values into database fields/objects.

Finally, is also important to note that use of primitives is more efficient than objects, but this efficiency is only appreciable when we are working at large scale. 

See more [here][7]


**Java `==` vs `.equals` for comparing Strings**

When Java found two different `primitives` and `String` variables containing the same value, automatically places them to the same memory address. 

In that order of ideas, if we have two `String` variables *a* and *b*:

```java
String a = "2";
String b = "2";
```

The `==` operator works with primitives and objects and compares memory addressess, so this operator will evaluate these variables as being the same. By the other side, `.equals()` method only works with objects non primitives, and compares the value of each variable, and in this case also evaluates them as being the same.

But this not always works the same. Indeed for `String` comparison is prefereable always to use `.equals()` instead of `==`, first  because `String` is an object and not a primitive datatype, and second because there is a corner case where for a value that's inserted from command line, it's assigned to a new memory address so `==` that compares memory addresses always will evaluate strings being different, while `.equals()` will return a result depending on the real values.

### Resources:

- [What is CI/CD? Continuous integration and continuous delivery explained][1]
- [Continuous integration vs. continuous delivery vs. continuous deployment][2]
- [What Is CI/CD?][3]
- [Book: Building Microservices][6]
- [Zeit Cloud Broker][4]
- [Cloud broker][5]
- [Why is there no primitive type for String? - StackOverflow Question][7]


[1]: https://www.infoworld.com/article/3271126/application-development/what-is-cicd-continuous-integration-and-continuous-delivery-explained.html
[2]: https://www.atlassian.com/continuous-delivery/ci-vs-ci-vs-cd
[3]: https://dzone.com/articles/what-is-cicd
[4]: https://zeit.co/
[5]: https://en.wikipedia.org/wiki/Cloud_broker
[6]: http://shop.oreilly.com/product/0636920033158.do
[7]: https://stackoverflow.com/a/2099311

### TODO:

- [x] Databasic: Make principal Application Class and move all main stuff there.

**Note:**

When methods where in `Main` class, they needed to be `static` since `Main()` is always static. To get rid of this, we migrated those methods from `Main` to a new `Application.java` class and we instantiated a new `Application()` object into `Main()`, so our methods aren't anymore `static`.