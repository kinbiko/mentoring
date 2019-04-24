# SOFTWARE ENGINEERING IN JAVA

## Session 13 (25/09/2018)

- Lists, Maps and Generics
- Gradle
- Spring Initializr

### Notes:

#### Lists

When we define a List in Java we do in the following way:

```java
List<String> list = new ArrayList<>();
list.add("asdf");
list.add("123");
list.add("qwer");
```
Here, we are creating an ArrayList (is the most common used implementation of Java `List`) of `Strings` and adding some values. There is a lot of methods that Java integrates to allow the manipulation of `Lists`.

A quick way to loop over the elements, introduced in Java 8, is using forEach:
```java
list.forEach(System.out::println);
```

We also could to define list datatype as `Object`:
```java
int a=2;
List<Object> list_b = Arrays.asList(new GenericTest(), "asdf", 123, a);
```
In this example, all that we are storing in the list is an object even the `String "asdf"`, the `Integer 123` and even the primitive `int a` variable, because from Java 7 this values are intelligently autoboxed to map towards the List datatype, so at the end is stored as an `Integer` object.

#### Maps

Maps are structures to keep data in the <key, value> form. The definition, usage and iteration over are similar to the way to handle `Lists` and also has a lot of built-in methods to manipulate and make different operations over our `Maps`:

```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
map.forEach((k,v)-> System.out.println(k+v));
```
Something to notice in the previous `print` statement, is that two different datatapes are mixed using the `+` operator, so since the first parameter is a `String`, both of the values will be treated as `String`, and a concatenated string is printed out.

#### Generics

In the following example we are defining a class that receives any datatype when is instantiated, so is `Generic`. So operations can be defined without matter the received datatype. First we send a `String`:

```java
public class GenericTest {

  public void mog () {
      String name = "Javier";
      MyOwnGeneric<String> mog = new MyOwnGeneric<>();
      mog.set(name);
      Assert.assertEquals(name, mog.get());

  }

}

class MyOwnGeneric<T> {
    T v;

    void set ( T t ) {
        v = t;
        v.forEach(System.out::println);
    }

    T get () {
        return v;
    }
}
```

But we could also send an `ArrayList`:


```java
public class GenericTest {

  @Test
  public void mog (){
      List list = new ArrayList();
      MyOwnGeneric<List> mog = new MyOwnGeneric<>();
      mog.set(list);
      Assert.assertEquals(list, mog.get());
  }
}
class MyOwnGeneric<T extends List> {
    T v;

    void set ( T t ) {
        v = t;
        v.forEach(System.out::println);
    }

    T get () {
        return v;
    }
}
```

#### Gradle / Gradlew

`Gradle` is an application used to build code, and automatization (test,...) that can be installed, but also comes with an embeeded executable called `gradlew`, whose use is prefereable over the installed one since it can be distributed in order different project members have same gradle version running.

Introductory commands seen:
```bash
./gradlew build
./gradlew test
./gradlew bootrun # starts a web server!!
```

#### Spring Initializr

Spring initializr is an assintant for set up a project template that can have different Spring dependencies built-in in a downloable pakage that can be openened in any editor. Dependencies includes MVC framework, Cloud Providers integration, Database capabilities, etc.

In this sense, a new API Spring project was created and uploaded to GitHub

###  Homework

Set up a build server for your API project using [travis CI][1]. Roger recommend checking out Travis CI and integrating the free version with the project.

Add a badge to your README.md file to indicate whether or not the build is passing.

Do all of this on a separate branch (not master) and create a PR with me as a reviewer once you’re done. You will have to add me as a collaborator to your project first.

A Roger's project as an example can be found [here][2].

### Resources:

- [Travis CI][1]

[1]: https://travis-ci.com
[2]: https://github.com/kinbiko/bugsnag-maven-plugin
