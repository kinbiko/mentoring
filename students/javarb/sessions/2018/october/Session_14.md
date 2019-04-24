# SOFTWARE ENGINEERING IN JAVA

## Session 14 (01/10/2018)

- Spring initializr example

### Notes:

#### Demo application
Roger was teaching me how to to develop a basic API web application in Spring using [Spring initializr][1].

Some Spring annotations ([there are many][2]) were used in this session. A brief explanation of them and his role in the workflow of the Spring application can be seen below:

- ##### [@SpringBootApplication][3]

Spring Initializr adds `@SpringBootApplication` annotation to the main class (same as `@Configuration` `@EnableAutoConfiguration` `@ComponentScan`). This annotation make possible to found controllers, components and dependencies. Also allows to make a runnable web application:
```
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```

- ##### [@RestController][4], [@RequestMapping][5]

Controllers is where the logic of the web application happends and where maps (HTTP request) are processed. Spring looks for `@RestController` (and other `@Controller`) annotations when it starts up.

`@RequestMapping` indicates Spring to map HTTP request to the controller methods:

```java
@RestController
@RequestMapping("/api")
public class DemoController {
  ...
}
```

- ##### [@Service][6]

Classes denoted with `@Service` annotation are intented to perform bussiness logic:
```java
@Service
public class DemoService {
  ...
}
```
Tasks that controller delegates such as validation are common in this kind of classes, whereas database callings or handling HTTP endpoints aren't.

##### Note about databases:
In Spring, the data we get back from the database is the model.

There is _classes for getting the data from the database_, these classes (making the database calls) are annotated with [`@Repository`][6] and might have complicated methods for getting the right data out. E.g: `getProjectsForUsers(List<Long> userIds, int pageCount, int pageIndex)`.

Also exists _data classes_ that has fields, getters and setters. No complicated methods

In other frameworks like Ruby on Rails, the _data classes_ and the _classes for getting the data from the database_ are the same classes, so they could be called indistinguishably "the model" in the MVC approach.

- ##### [@Autowired][7]

`@Autowired` annotations makes Spring inject the field that thinks is right based on the type. Only classes registered as `@Service` or `@RestController` or other [spring annotations][2]  can be injected.
```java
@RestController
@RequestMapping("/api")
public class DemoController {
    @Autowired
    private DemoService service;
    ...
}
```

- ##### [@GetMapping][8], [@PostMapping][8]  

`@GetMapping` annotation is for handle `GET` HTTP requests and `@PostMapping` for receive data through `POST` HTTP request. In order to test `POST` endpoints, an application such as [Postman][10] must be used.


###  Homework

Based in the provided Roger example. Make an application that handle next maps(routes):

| Map | Method | Description |
|---|---|---|
|`/api/customer` |GET| Give me all customers. Hardcoded|
|`/api/book`| POST | Save to an in-memory map in a service. Return a json with the ID of the newly created book.|
|`/api/:id` |GET| Get by ID, should return a previously posted book|

Also, write Integration tests

**TIP**: Following, an example for the POST /book map:
```java
@GetMapping("/{id}")
public MyPojo getData(@PathVariable("id") String id) {
    System.out.println(id);
    return service.getMyPojo();
}
```

**NOTE:** `@PathVariable` annotation allows us to extract variables that form part of the path (URI of the HTTP request). In this way we can use those values in our code logics.

### Resources:
- [Spring Initializr][1]
- [Spring Web Annotations][2]
- [`@SpringBootApplication`][3]
- [The Spring `@Controller` and `@RestController` Annotations][4]
- [`@RequestMapping`][5]
- [`@Component` vs `@Repository` and `@Service` in Spring][6]
- [`@Autowired`][7]
- [Spring `@RequestMapping` New Shortcut Annotations `@GetMapping`, `@PostMapping`, and other HTTP request methods][8]

[1]: https://start.spring.io/
[2]: https://dzone.com/articles/a-guide-to-spring-framework-annotations
[3]: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html
[4]: https://www.baeldung.com/spring-controller-vs-restcontroller
[5]: https://www.baeldung.com/spring-requestmapping
[6]: https://www.baeldung.com/spring-component-repository-service
[7]: https://www.baeldung.com/spring-autowire
[8]: https://www.baeldung.com/spring-new-requestmapping-shortcuts
[10]: https://www.getpostman.com/
