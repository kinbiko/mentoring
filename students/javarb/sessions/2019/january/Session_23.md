# SOFTWARE ENGINEERING IN JAVA

## Session 23 (22/01/2019)

- Mockito testing library
- Quality Assurance
- Kanban feedback

### Notes

#### Mockito

The current landscape to testing is basically composed by TDD, Junit, mocking and integration tests. [The Mockito Library][1] was introduced as an alternate way to test our programs.

**NOTE:** Above the previous kind of test are the Acceptance Tests or BDD, as will be showed in this notes under the section called Quality Assurance

A general recommendation made was that since Mockito is very big (still is pretty performant), should be used for test big things third party services, but for own projects the best should be to use our own tests implementation, since we have all the control of the code.

So, we did some changes to our Calculator test to use Mockito:

```java
package co.org.osso.api;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class CalculatorControllerTests {

    Logger logger = Mockito.mock(Logger.class);

    CalculatorController target = new CalculatorController(new Calculator(logger), logger);

    @Test
    public void logFibonacciNumber(){
        Mockito.doThrow(new RuntimeException()).when(logger).log("/fibonacci/10");
        target.getFibonacci(10);
        Mockito.verify(logger).log("/fibonacci/10");
    }
}
```

One thing to have present is that by using Mockito, we have not worry anymore for implement our `Logger` interface, since we are passing the class to Mockito and it knows how to operate using it. At the same time, that is the reason for which we can feel we are loosing control of things.

As was said, the interface implementation was not neccesary when we are using Mockito, but as we have this as a dependency in other tests we couldn't delete it at this time.

We created another branch and we can develop maybe all tests there in Mockito, still it isn't in the kanban.

#### Quality assurance

Was introduced the concept of [BDD - Behavior-driven development][2] also refeered as acceptance tests. These are advanced and higher level tests. In order to use these kind of approach, is needed to have experience writing own code.

A language used for write test definitions is called [Gherkin][3] inside a framework called [Cucumber][4].

The expected behaviour with these kind of tests is that them can be readed by non technical people, making sure of the top level behaviour of the application. This is specially useful when making consultancy.

Embrace this approach is expensive and requires big effort/time/money but have a very good [ROI][5] (get a lot in return).

This begin with easy to read steps or test that are written usually in a folder called `features` inside the project's folder. There, some list of features can be defined as high level steps legible for non technical people. For example:

```text
Feature: Feature 1

Background:
    Given X set something
    And X configure other thing
    ...

Scenario: A error report contains something
    Given X set some value
    When X start some service
    ...   
```

A think to notice here, is that as the requirements change in the time, the test has to change also.

Following to feature definition, in `features/steps` folder are the actual test implementations. There are the translation of the features to technical terms.

Finally, in `features/fixtures` folder are the running applications.

#### Kanban feedback

We fixed the kanban to this week. Some things were talked about:

- When using kanban method consistency is more important, first we achieve a consistent behaviour and after we can increase values.

- Was introduced the concept of retrospective: A feedback time to speak about what happened during the week and what could be improved. Still this isn't required to speak during a retro, is good to do so.

- If we have big values for a task a good aproach could be to split in smaller tasks. Generally, If a task have a number of 8, we split it. If 5, we consider to split and if tasks are 1, 2 or 3, this is fine.

As this week complete stuff was 5 and a task of weight 8 was missing (because is under revision and thus not completed), I was asked to considered how much points from that 8 points were missing, knowing changes could be asked during the PR, etc. So from that weight of 8, I considered that 5 effort was missing. 

Also, Roger considered that for this week the mark should be 10 and not 13. Then as I said that the past missing to review task was 5, I had still to add 5. So I added another 5 points that were all about sessions documentation, and if I finish earlier, then I could do somehting about programming.

#### API NOTE:

The branch that remains into API application GitHub repository is for test all the HTTP endpoints (Pending at this time).

### Resources

- [The Mockito Library][1]
- [Behavior-driven development][2]
- [Gherkin Language][3]
- [Cucumber][4]

[1]: https://site.mockito.org/
[2]: https://en.wikipedia.org/wiki/Behavior-driven_development
[3]: https://docs.cucumber.io/gherkin/
[4]: https://cucumber.io/
[5]: https://www.investopedia.com/terms/r/returnoninvestment.asp
