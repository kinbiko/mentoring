# SOFTWARE ENGINEERING IN JAVA

## Session 12 (18/09/2018)

- Gradle(CLI tool for building java projects)
- Generics (e.g. List and Map, and also custom ones)
- Spring Initializr(website to quickly generate java (gradle) projects)

### Notes:

#### Gradle Installation

following [Gradle installation guide][2].

Firs I installed _SDKMAN_ according to instruction in [its website][1]:
```bash
$ curl -s "https://get.sdkman.io" | bash
```
So proceed to install Gradle:
```bash
$ sdk install gradle
```

In order to use it I followed [this tutorial][3] to make this example application:
```java
public static void main(String args[]){

    LocalTime time = LocalTime.now();
    System.out.println("Local time is = " + time);
    System.out.println("Hello Gradle!");

}
```
And this was my `build.grade` file:

```java
plugins {
    //id 'java'
    id 'application'
}

group 'co.org.osso'
version '1.0-SNAPSHOT'

mainClassName = 'demo.HelloGradle'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'

}
```
Then, in order to compile and run with gradle the following commans must be done
```bash
$ gradle build
$ gradle run
```

There is another step that is run with [Gradle Wrapper][4] in order to run projects without need an specific Gradle version:

``` bash
$ gradle wrapper
$./gradlew build
$ ./gradlew run
Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE

> Task :run
Local time is = 16:37:33.459
Hello Gradle!
```

#### Spring Initializr

This is a web application that allow us to generate a Spring Java Web Framework application folder with all dependencies we choose between a wide range of provided options.

The we enter to the [Spring Initializr website][5] and we select in this case a Gradle project with Java and certain version of Spring Boot.

We'll have to set the `Group`, `Artifact` and `Name`. We can switch to full version to expand the options to set.

In the `Dependencies` section, I selected the `web` package dependency that allows full stack web development with Tomcat and [Spring MVC][6].

Finally, click on the `Generate Project` button. this will download a folder named as the `Artifact` name submited. In this case the name of the file is `test-spring.zip`.

We have to move that file into the desired location and open with IntelliJ IDEA in order to program the corresponding application.

#### Java Generics

`Generics` enable types (classes and interfaces) to be parameters when defining classes, interfaces and methods([see here][8]).

Code using `Generics` obtains following benefits:

- Allows adding [stronger type checks at compile time]:
As bugs surely happend during in any develop process, the process of detect them easier is something desirable. Generics allows to do that, detecting more bugs in compiling time, which makes easier to address them in regards to make it in runtime.

- Eliminate need to casting.
- Enable programmers to implement generic algoritms that work on collections of different types.


### Resources:

- [SDKMAN Installation][1]
- [Installing Gradle][2]
- [Gradle Wrapper][4]
- [Spring Initializr][5]
- [Spring MVC][6]
- [Java Generics][7]

[1]: https://sdkman.io/install
[2]: https://docs.gradle.org/current/userguide/installation.html
[3]: https://www.youtube.com/watch?v=RrVURuzcFhY
[4]: https://docs.gradle.org/current/userguide/gradle_wrapper.html
[5]: https://start.spring.io/
[6]: https://spring.io/guides/gs/serving-web-content/
[7]: https://docs.oracle.com/javase/tutorial/java/generics/index.html
[8]: https://docs.oracle.com/javase/tutorial/java/generics/why.html
