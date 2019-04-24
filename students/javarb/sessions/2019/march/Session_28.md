# SOFTWARE ENGINEERING IN JAVA

## Session 28 (19/03/2019)

- Reviewing sprint project (issue) tasks and solving problems & feedback
- Reviewing main project tasks  & planning next Sprint

## Notes

#### Reviewing sprint project (issue) tasks and solving problems & feedback

Public classes goes into separate files, to separate it quickly we can copy the class code and paste into the package directory and IntellJ Idea creates a class for us)

There was an error when executing application: `Error: Spring Boot RestController Error: “No Converter Found for Return Value of Type”`.  Problem here were [getters and setters](https://dzone.com/articles/spring-boot-restcontroller-error-no-converter-foun)

#### Final keyword

Whenever possible we have to make attributes `final`. This keyword, means our object, field or property will be assigned (using `=`) exactly once. Benefits are thread-safety and potentially allowing compiler optimizations.

A final variable in Java cannot be reassigned:
```java
final int x = 1;
x=2;
```
The above code will throw a compilation error.

This could be seen as a constant that is in a fixed memory position like in C++, but considering the following case we find some detachment from that concept:

If we have a list declared as `final List<String> strings = new ArrayList<>();` we can still add and remove elements, this is because the `List` nature self and as we are adding elements, new memory is being reserved, so memory use is not constant in this sense while the declaration of the List is final. What this means is that the `strings` List cannot be reassigned.


### Homework

- To do the [Setup a database and read from it](https://github.com/javarb/wallet/issues/5) tasks as defined in the [project issue board](https://github.com/javarb/wallet/projects/2)

####  Docker compose file that provisions a MySQL database.

First we need to [install docker-compose][1]:

```bash
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

$ sudo chmod +x /usr/local/bin/docker-compose
```

**Note:** If desired to connect from host to docker host database using MySQL client, is need to [use IP address instead `localhost`][13].

#### Data Definition Language (SQL file and comments).

This was done following [MySQL docs][19] and samples for [table creation][2], [inserting][3], [indexing][20] and [commenting][4] (see also notice COMMENT keyword [here][5]).

Below in links section there are several resources to clarify DDL concepts and also some Stackoverflow's useful answers.

#### Connect the database (locally and Travis CI) from application (Spring).

MySQL access from Spring was configured by following this [tutorial][16]

### Resources

- [Docker compose Installation][1]
- [MariaDB vs MySQL][7]
- [Docker hub MariaDB repo & compose instructions][10]
- [Example: How to create an MySQL instance using docker compose][12]
- [Using environment variables in docker compose][11]
- [Use IP address when connect to docker's MySQL mapped port on localhost (Stack Overflow)][13]
- [SQL | DDL, DML, DCL and TCL Commands][14]
- [MySQL Data Definition Statements][19]
- [MySQL CREATE TABLE reference][5] 
- [MySQL CREATE TABLE DDL Examples][2]
- [MySQL INSERT DDL Examples][3]
- [MySQL comments][4]
- [MySQL comments example][6]
- [MySQL CREATE INDEX Examples][20]
- [MariaDB Storage Engines][8]
- [MySQL INT length (Stack Overflow answer)][9]
- [Datagrip DDL code generator][15]
- [Access MySQL database from Spring][16]
- [Spring JPA][17]
- [JPA][18]

[1]: https://docs.docker.com/compose/install/
[2]: http://www.mysqltutorial.org/mysql-create-table/
[3]: http://www.mysqltutorial.org/mysql-insert-statement.aspx
[4]: https://dev.mysql.com/doc/en/comments.html
[5]: https://dev.mysql.com/doc/refman/8.0/en/create-table.html
[6]: https://stackoverflow.com/a/9099699
[7]: https://hackr.io/blog/mariadb-vs-mysql
[8]: https://mariadb.com/kb/en/library/choosing-the-right-storage-engine/
[9]: https://stackoverflow.com/a/27519793
[10]:  https://hub.docker.com/_/mariadb
[11]:  https://docs.docker.com/compose/environment-variables/
[12]:https://medium.com/@chrischuck35/how-to-create-a-mysql-instance-with-docker-compose-1598f3cc1bee
[13]: https://serverfault.com/a/306423
[14]: https://www.geeksforgeeks.org/sql-ddl-dml-dcl-tcl-commands/
[15]:https://www.jetbrains.com/datagrip/features/generation.html
[16]:https://spring.io/guides/gs/accessing-data-mysql/
[17]:https://spring.io/projects/spring-data-jpa
[18]: https://wikipedia.org/wiki/Java_Persistence_API
[19]: https://dev.mysql.com/doc/refman/8.0/en/sql-syntax-data-definition.html
[20]: http://www.mysqltutorial.org/mysql-index/mysql-create-index/

