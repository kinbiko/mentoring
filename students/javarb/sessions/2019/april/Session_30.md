# SOFTWARE ENGINEERING IN JAVA

## Session 30 (16/04/2019)

- Reviewing sprint project (issue) tasks and solving problems and feedback
- Reviewing main project tasks and planning next sprint

## Notes

#### Reviewing sprint project (issue) tasks and solving problems and feedback

#### Unit Tests

Test for service were created and controller test which I had done, were improved.

In controller test, we removed these annotations because they are used when we're creating Integration Tests when we need to run whole application in Controller. :

```java
@RunWith(SpringRunner.class)
@SpringBootTest
```

Integration Tests run the entire application and because of that, take more time to run while Unit Test are just loading Junit which usually takes less than 1 sec. Unit test are used to simulate classes of which depends what is being tested. 

As we are creating unit tests, we don't need to load all Spring machinery, we just need to test the portion of code of interest and for that we mock the dependencies by using Mockito.

The purpose of Mockito is to allow us to test without need to implement anything else in the server side, so we define which answer we want to get into each specific method of class we're mocking.

####  TDD

During all session we were using TDD, first red, after green and then refactor where we were removing repetitions,  importing statically `Assets`, setting inline variables, and making variables repeated values, reformatting code, etc.

Some examples of this are:

Service variable name was renamed to `target` since it is widely used:

```java
private AccountRepository mockRepo = Mockito.mock(AccountRepository.class);
private AccountService target = new AccountService(mockRepo);
```

Static imports:

```java
import static org.junit.Assert.assertEquals;
```

#### Tests

Test were renamed and improved. A test was done to handle an empty answer:

```java
  @Test
public void whenNotDataInDBReturnEmptyJSON() {
    when(mockRepo.findAll()).thenReturn(new ArrayList<>());
    ListAccountsResponse response = target.listAccounts();
    assertEquals(0, response.getData().size());
}
```

Another test was made to check against a hardcoded mocked answer.  In order to achieve this a method called  `makeAccount()` was created in order to populate the Mockito's answer.

```java
@Test
public void whenThereIsFiveAccountInDBReturnFiveAccountJSON() {
    when(mockRepo.findAll()).thenReturn(asList(
        makeAccount(1L),
        makeAccount(2L),
        makeAccount(3L),
        makeAccount(4L),
        makeAccount(5L)
    ));  
...
```

```java

private Account makeAccount(Long i) {
    Account account = new Account();
    account.setId(i);
    account.setAccount("account_" + i);
    account.setFullName("Account number " + i);
    return account;
}
```

The `L` letter means value is converted to `Long`. This also works with lowercase `l` but that tends to be confused with number `1`, so is better to use uppercase.

More test can be done in service, but with what we completed in this session, test to method `listAccounts()` was ready and in the expected shake.

#### Database

About database, in `build.gradle` we setted:

```
runtimeOnly 'mysql:mysql-connector-java'
```

Which means we're going to use MySQL database Java connector.

Also, I was declaring environmental variables, but they are only needed when running Spring application in order to load database user and password values. But when running JUnit tests, that enviromental variables are not needed.

### Homework

- To do the [](https://github.com/javarb/wallet/issues/) tasks as defined in the [project issue board](https://github.com/javarb/wallet/projects/)

