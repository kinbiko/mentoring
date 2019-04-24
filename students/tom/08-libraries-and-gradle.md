# Libraries and Gradle

Don't reinvent the wheel. Instead, use libraries that others have written.

## JAR libraries

To manually add a JAR (Java ARchive -- most common form of libraries 'in the wild') to an Intellij project download the JAR onto your computer and add it through `Intellij > project structure > libraries`.

If, after some Googling you've come upon a library you think will fit your use case, you will almost always find this library on [Maven-Central](https://search.maven.org/) - the de facto standard place to fetch Java libraries and dependencies.

### Testing the library

It's often worth checking that a library works by applying it to your project and then taking it for a spin in a throw-away test.

```java
package io.github.perrymant.moneymaker;

import com.jakewharton.fliptables.FlipTableConverters;
import org.junit.Test;

import java.util.List;

public class FlipTableTest {
    @Test
    public void test_1() {
        List<Transaction> people = new TransactionMaker().getTransactions();
        System.out.println(FlipTableConverters.fromIterable(people, Transaction.class));
    }
}
```

# Gradle

Gradle is an open source build automation tool used by Java developers to define and organize the build process.
It builds the deployment artifacts (any file used such as: Java source code, Java byte code, XML files, graphic and audio files, etc.), and it manages dependencies (often open-source Java libraries that somebody else has created).

Basically build automation is the act of scripting or automating a wide variety of tasks that software developers do in their day-to-day activities like:
- Downloading dependencies.
- Compiling source code into binary code.
- Packaging that binary code.
- Running tests.
- Deployment to production systems.

Build tools are programs that automate the creation of executable applications from source code(e.g. .apk for android apps).
Building incorporates compiling,linking and packaging the code into a usable or executable form.

Whereas previous build tools (Ant, Maven) were written in XML - Gradle is written in a DSL based on the Groovy programming language, which in turn is based on Java. In other words, you can write Java code in your Gradle files.

The most important file is `build.gradle` -> this is the file where you update the dependencies. You might also want to put ".gradle" in the .gitignore file.

## To initialise a Gradle project

The most popular and common framework for writing (mainly web and back-end) services in Java is called Spring. There's a really convenient website called [Spring Initializr](https://start.spring.io/) that will generate a bespoke project structure for your application, which includes setting up Gradle.

Once you've created your Spring Initializr project it will download a (Gradle) project with the following structure (note the mirrored `src/main/` and `src/test/` directory structure):

```
├── build
│   └── ...
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── moneymaker.iml
├── out
│   └── ...
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── io
    │   │       └── github
    │   │           └── perrymant
    │   │               └── moneymaker
    │   │                   ├── Balance.java
    │   │                   ├── Budget.java
    │   │                   ├── Logger.java
    │   │                   ├── Moneymaker.java
    │   │                   ├── ReportLine.java
    │   │                   ├── Transaction.java
    │   │                   ├── TransactionMaker.java
    │   │                   └── TransactionType.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── io
                └── github
                    └── perrymant
                        └── moneymaker
                            ├── BalanceTest.java
                            ├── BudgetTest.java
                            ├── FlipTableTest.java
                            └── ReportLineTest.java
```

#### Maven-Central revisited

You may also use Maven-Central find the build info needs to be added to the `build.gradle` file to build your project using Gradle.

### Commands for gradle

Projects generated with Spring Initializr come with a standalone version of Gradle called `gradlew` (pronounced "gradeloo" for extra nerd points). It is recommended to use this application instead of installing a version of Gradle on your computer. It does mean that you'll have to run `./gradlew` wherever you would otherwise run `gradle`.

`./gradlew build` - builds your project
`./gradlew clean` - Deletes the build directory - I.e. ensures that there's no chance that the result of a previous build can affect the next build.
`./gradlew test` - Runs the unit tests. (also builds - cannot run tests without compiling first) - good to check the build status.
`./gradlew tasks` - get a list of all the possible Gradle tasks I can run for this project (not that many by default, can be extended indefinitely with custom tasks)

### Travis CI

Travis CI is a free platform for running continuous integration.

Continuous Integration is the practice of checking in and verifying small code changes frequently - rather than merging in a large change at the end of a development cycle.

### Steps to include a Github Status badge:

1. Activate the repo via Travis profile @ https://travis-ci.org/
1. Create `.Travis.yml` file in project and added:
    ```
    language: java
    jdk:
      - oraclejdk8
    ```
1. Git add, commit & push.
1. Check all is well in Travis
1. Badge on Travis page, alternatively https://shields.io/
