# SOFTWARE ENGINEERING IN JAVA

## Session 9 (07/08/2018)

- TDD

### Notes:

#### TDD

There exists 3 types of Test (terms can vary): *Unit*, *Component* and *Integration*.

##### Unit Tests
Unit tests are used for only test a single class.

##### Component Tests
Component tests are very fast and are intended for multiple clases but not for I/O or databases. A common pattern of this is to test a package.

##### Integration Tests
Integration tests are used to test classes that interact with databases. In this category we can found two tendencies:
- System tests: A separate project test our project.
- Acceptance tests: A guided test for manual tests, can be automated, but in general terms is slow.

TDD is a discipline that we have to stick to, following these laws we will end with good code.

In TDD we implement the simplest solution we can.

The results using TDD shoud be more elegant and better than any clever idea (we can write our idea and after compare the results).

We program in order to provoke crashes, so we can fix them before they occurs in the reality. There are 3 steps to follow:

- Red
- Green
- Refactor

##### <span style="color:red;">Red</span>
Write as little test code as possible for make a failing test. Has to be relevant to the test that we're doing. A compilation error is a valid case.

##### <span style="color:green;">Green</span>
Write as little production code as possible to make all the tests we wrote in unit to pass.

##### <span style="color:blue;">Refactor</span>
Cleaning up our code. This allows to have maintenable code (not a entangle)

We want to program naturally green code all time, but we will end with better code and cleaner when we begin with <span style="color:red;">*Red*</span>, <span style="color:green;">*Green*</span>, <span style="color:blue;">*Refactor*</span>

In practice the time to go over these steps can vary depending on different aspects such as expertise of the developer, complexity of the solution or the amount of test data that has to be handled. However, an indicator we are doing things right should be take 2 minutes in each step.

An example of TDD was made for some single cases. For this, we created a class called `PracticeTest.java`.

There we import the `junit`(Java's test framework) libraries `Assert` and `Test`.

```java
package co.org.osso.tdd;

import org.junit.Assert;
import org.junit.Test;


public class PracticeTest {

    // Convention for tests is to call this field object target in order not depend of the class name
    // or "mut" module under test? - not recommended target is more common
    Practice target = new Practice();
    // hast to be defined so
    /*  @Test
        - public
        - void
        - no args
    * */
    @Test
    public void a() {
        //Assert.assertEquals(1,2);
        Assert.assertEquals(2, target.returnTwo());

    }

    @Test
    public void greetsMeProperly() {

        String expected = "Hello, Javier!";
        String actual = target.greet("Javier");
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void greetRogerProperly() {

        String expected = "Hello, Roger!";
        String actual = target.greet("Roger");
        Assert.assertEquals(expected, actual);

    }

}
```

### Resources:

[Sync local branches with remote](https://stackoverflow.com/a/10313379)

```bash
[jaar@port-staff notes]$ git branch -r
  origin/HEAD -> origin/master
  origin/master
  origin/session-8
  origin/session-9

[jaar@port-staff notes]$ git checkout -b session-9 origin/session-9
Rama 'session-9' configurada para hacer seguimiento a la rama remota 'session-9' de 'origin'.
Cambiado a nueva rama 'session-9'
```

### TODO:

Folowing these rules use TDD approach to solve this challenge:

https://github.com/rob-lowcock/gophercon-2018


Use one of these use cases to build it:

https://github.com/rob-lowcock/gophercon-2018/blob/master/main_test.go
