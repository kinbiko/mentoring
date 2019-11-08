# SOFTWARE ENGINEERING IN JAVA

## Session 36 (07/06/2019 )

- Research homework [on my project](https://github.com/kinbiko/mentoring/projects/7) board.

### Notes 

Roger was leading me to improve the serialization libraries benchmark project.

#### Benchmark project

The goal of this session was to introduce concept of the [Observer Pattern](https://www.oodesign.com/observer-pattern.html) that is part of the [SOLID principles](https://scotch.io/bar-talk/s-o-l-i-d-the-first-five-principles-of-object-oriented-design), in which `main()` method doesn't needs to know all about system, but a central entity knows about and each benchmark will ask registration.  

We know that benchmarking depends , between others, in the structure of the objects to serialize, so this could take a different ammount of time to serialize a complext object structure (nested objects, arrays, etc.) than simpler object structures.

To this, two new classes were introduced `Configuration.java` and `ConfigurationFactory.java`.

`Configuration.java` is a POJO intended to represent information of the benchmark and the of object that will be serialized:

```java
public class Configuration {
    private int samplesize;
    private int objects;
    private Object pojo;
    private String useCase;

    public int getObjects() {
        return objects;
    }

    public void setObjects(int objects) {
        this.objects = objects;
    }

    public Object getPojo() {
        return pojo;
    }

    public void setPojo(Object pojo) {
        this.pojo = pojo;
    }

    public int getSamplesize() {
        return samplesize;
    }

    public void setSamplesize(int samplesize) {
        this.samplesize = samplesize;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}
```

`ConfigurationFactory.java` allows to create so many benchmark configurations as desired. For example, we created two configurations one for book and other for manage person objects:

```java
public class ConfigurationFactory {

    static List<Configuration> getConfigurations() {
        Configuration bookConfiguration = ConfigurationFactory.getBookConfiguration();
        Configuration personConfiguration = ConfigurationFactory.getPersonConfiguration();

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(bookConfiguration);
        configurations.add(personConfiguration);
        return configurations;
    }

    private static Configuration getBookConfiguration() {
        Configuration bookConfiguration = new Configuration();
        bookConfiguration.setUseCase("Book Objects");
        bookConfiguration.setSamplesize(50);
        bookConfiguration.setObjects(1000);
        bookConfiguration.setPojo(createBook());
        return bookConfiguration;
    }

    private static Configuration getPersonConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setUseCase("Person Objects");
        configuration.setSamplesize(15);
        configuration.setObjects(20_000);
        configuration.setPojo(createPerson());
        return configuration;
    }

    private static Book createBook() {
        Book book = new Book();
        book.setIsbn(123);
        book.setTitle("ABC");
        book.setAuthor(createPerson());
        book.setDescription("asdfasdf");
        return book;
    }

    private static Person createPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        person.setAge(30);
        return person;
    }

}
```

Main class uses `getConfigurations()` method on `ConfigurationFactory` class in order to run benchmarks for each configuration:

```java
    public static void main(String[] args) {

        for (Configuration configuration : ConfigurationFactory.getConfigurations()) {
            new JacksonBenchmark().benchmark(configuration);
            new GsonBenchmark().benchmark(configuration);
            new FastjsonBenchmark().benchmark(configuration);
        }

    }
```


#### Homework. Part 3 - Blog post

I composed the next Blog in Wordpress: https://javiersharesit.wordpress.com/

There, I will be creating posts about this homework. My first post was this:
https://javiersharesit.wordpress.com/2019/06/10/java-serialization-libraries-review/

This post has been already published, but in future, I will be uploading this first to GitHub in Markdown and after approval, this should be published to Wordpress.

#### About API endpoints
Was given the recommendation to handle just one kind of data for each API endpoint. For example, not try to handle json structure for persons and accounts at the same endpoint.

