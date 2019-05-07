# SOFTWARE ENGINEERING IN JAVA

## Session 29 (02/04/2019)

- Reviewing sprint project (issue) tasks, solving problems and feedback
- Reviewing main project tasks and planning next Sprint

## Notes

#### Reviewing sprint project (issue) tasks, solving problems and feedback

Revision were made in order to PR (Pull Request) changes were approved.

The application.properties file was renamed to application.yml in order to use that format. So database configuration was written in the YAML syntax:

```yaml
spring:
  jpa.hibernate.ddl-auto: none
  datasource:
    url: "jdbc:mysql://127.0.0.1:3306/wallet"
    username: "${MYSQL_USER:my_default_value}"
    password: "${MYSQL_PASSWD}"
```

As can be seen, `username` and `password` are being stored into environment variables. So if we try to launch application from IntelliJ IDEA this will not work and an error similar to this will be shown:

`Error: Spring Boot RestController Error: “No Converter Found for Return Value of Type”`

In order to solve this we have to provide to Gradle our environment variables in order can be used by the corresponding configuration file. SO one way to do this is using the CLI (Command Line Interface):

```bash
$ MYSQL_USER=wallet MYSQL_PASSWD=wallet ./gradlew bootRun
```



**NOTE:** Database must be running. This is done by running: `docker-compose up` on wallet directory.

### Homework

- To do the [Insert new accounts in database from the API](https://github.com/javarb/wallet/issues/4) tasks as defined in the [project issue board](https://github.com/javarb/wallet/projects/2)

####  Unit Tests

Working in `Write the Service and some unit test and mock out (mockito) database` I was using [this][1] (in Spanish) resource.

Service test were stored in `WalletApplicationServiceTests` class and Controller test in `WalletApplicationControllerTests`.

One problem to overcome was getting the error: 

[`Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]`][4].  

Problem here were the environmental variables to database connection weren't not found by Gradle when running tests.

So in order to overcome this, I had two choices:

1. In order to [run tests in CLI by passing environmental variables to Gradle][2]. For example for run the Service test called inMemoryAccountsNotNull:
  ```bash
  MYSQL_USER=wallet MYSQL_PASSWD=wallet ./gradlew test --tests WalletApplicationServiceTests.inMemoryAccountsNotNull
  ```
  All test can be runned if we don't specify class or specific tests.

2. Specifying enviroment variables into IntelliJIDEA. This can be done by [setting them into `Run/Degub Configurations`][3]. Configuration menu can be access by pressing `Alt+Shift+F9` or browsing to `Run`/`Debug`/`Edit Configurations`.

  

  **NOTE:** These variables must be setted up into classes and methods were be necessary to use them. In this case is we need to do it in all of them, else we will have the hibernate error.

  

**WIP**
I'm missing Integration tests. Also missing database mocking, because I still have not defined schema.


### Resources

- [Pruebas usando Junit, Mockito, y MockMVC][1]
- [Testing in Java & JVM projects][2]
- [Setting up and using environmental variables in IntelliJ Idea][3]
- [Error: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]][4]
- [Docker Compose and App Deployment with MySQL][5]
- [Docker Forum: SQL file with Dockerfile][6]
- [Example Import SQL file with Dockerfile][7]

[1]: https://platzi.com/tutoriales/1464-jee/1581-pruebas-usando-junit-mockito-y-mockmvc/
[2]: https://docs.gradle.org/current/userguide/java_testing.html
[3]: https://stackoverflow.com/a/13749192
[4]: https://stackoverflow.com/a/45025558
[5]: https://mysqlrelease.com/2017/11/docker-compose-and-app-deployment-with-mysql/
[6]: https://forums.docker.com/t/mysql-create-database-and-import-sql-file-with-dockerfile/30300/7
[7]: https://qiita.com/furu8ma/items/75e5b1df29fef04ec7f1
