# Spring App

## Why Use Spring?

- Generates a container that creates and manages application components (also called *beans*).
- Spring wires beans together (referred to as *autowiring*) based on a pattern that allows for *dependency injection*.
- Allows for RESTful API design using HTTP requests and responses.
- Allows easy conversion between POJOs and JSON formatted data.

### Common Spring Annotations

- `@Autowired`: applied on fields, setter methods, and constructors, injects object dependency implicitly.
- `@Configuration`: used on classes which define beans
Stereotype Annotations:
```
                       +--------------+
                       |  @Component  |
                       +-------^------+
         +--------------------/|\-----------------------+
         |                     |                        |
+--------|--------+     +------|------+     +-----------|---------+
|   @Controller   |     |  @Service   |     |     @Repository     |
|  (presentation) |     |  (service)  |     |  (persistance/DAO)  |
+-----------------+     +-------------+     +---------------------+
```
- `@Component`: a generic stereotype for any Spring-managed component. The following three are specializations:
    - `@Controller`: this class serves the role of a controller.
    - `@Service`:  this handles the business logic.
    - `@Repository`: used to access the database.
- Spring Web Annotations:
    - `@RestController`: combines `@Controller` and `@ResponseBody`, this results in web requests returning data rather than a view.
    - `@RequestMapping`: used both at class and method level. `@RequestMapping` maps all HTTP operations by default; In order to specify this mapping `@RequestMapping(method=GET)` can be used and further configuration and specification is possible using path & compatible HTTP methods such as in the following example: `@RequestMapping(value = "/guitars/fender", method = RequestMethod.GET)`
    - This will create an endpoint at the location: `http://localhost:8080/guitars/fender` that listens to a `GET` request.
    - Additionally, it can be very useful to generate a JSON response from a method that returns a string: `@GetMapping(value = "/report", produces = "application/json; charset=utf-8")`.

## Setting up a basic `Spring App`:

#### Build.gradle:

A new `Spring App` project can be started through use of `Spring Initialzr` (Available @ https://start.spring.io/), though a project in progress can make use of the Spring Framework by making sure the dependincies are included in the `Build.gradle` file:
```java
buildscript {
    ext {
        springBootVersion = '2.0.4.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group = 'io.github.perrymant'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8

bootJar {
    baseName = 'gs-spring-boot'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
}
```

#### Main.java:

A typical `Main` class for the application will look like this:
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

#### Mapping.java:

A typical `GET` endpoint can be created in the following way:
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Yo Planet!";
    }

}
```
The returned string can be accessed via: `http://localhost:8080/hello`.

# Todo:

- use of `postman`

https://spring.io/guides/gs/spring-boot/

## POJO -> JSON (and vice versa)
```java
@RestController
public class CarController {

    @GetMapping("/api/car/1")
    public Car getCar() {
        return makeCar();
    }

     private Car makeCar() {
        final Car car = new Car();
        car.setId(1);
        car.setColour(Colour.METALLIC_WHITE);
        car.setModel("Toyota Prius");
        car.setEngineDetails(makeEngineDetails());
        car.getCds().add(makeCD());
        return car;
    }

    private Car.CD makeCD() {
        Car.CD cd = new Car.CD();
        cd.setArtist("Metallica");
        cd.setSongCount(13);
        return cd;
    }

        private Car.EngineDetails makeEngineDetails() {
        Car.EngineDetails engineDetails = new Car.EngineDetails();
        engineDetails.setHorsepower(2134);
        engineDetails.setManufacturer("Rolls Royce");
        return engineDetails;
    }
}

class Car {
    private long id;
    private int wheels;
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String model;
}
```


