# SOFTWARE ENGINEERING IN JAVA

## Session 34 (21/05/2019 )

- Research homework [on my project](https://github.com/kinbiko/mentoring/projects/7) board.

## Notes

### Homework

#### Part 1
Figure out the most popular libraries for turning JSON strings into POJOs, and for turning POJOs into JSON.

#### Part 2
Write a simple project to benchmark their performance for serialization and serialization.

#### Part 3
Write a blog post (preferably in English) about what you discovered.

I have get to decide:

- how many libraries to check
- how you can create a benchmark
- how to write your blog post.

### Solution

#### Part 1

I found this [Top 5 Libraries for Serialization and Deserialization JSON in Java](https://simplesolution.dev/top-5-libraries-for-serialization-and-deserialization-json-in-java/). There they are mentioning following libraries:

- [Gson](https://github.com/google/gson)
- [Jackson](https://github.com/FasterXML/jackson)
- [Fastjson](https://github.com/alibaba/fastjson)
- [Moshi](https://github.com/square/moshi)
- [Jsoniter](https://github.com/square/moshi)

I found [this other article](http://dgimenes.com/blog/2014/03/14/java-comparing-json-encoding-libraries.html) when the person who wrote is concluding that Gson and Jackson are best libraries but also is mentioning [JSON.org](http://json.org/) library which [seems to be obsolete now](https://stackoverflow.com/a/7623694). There in JSON.org, we can see these list of links to different Java libraries to handle serialization:

- JSON-java.
- JSONUtil.
- jsonp.
- Json-lib.
- Stringtree.
- SOJO.
- json-taglib.
- Flexjson.
- Argo.
- jsonij.
- fastjson.
- mjson.
- jjson.
- json-simple.
- json-io.
- google-gson.
- FOSS Nova JSON.
- Corn CONVERTER.
- Apache johnzon.
- Genson
- cookjson.
- progbase.

Stil is a extensive list it's strange Jackson isn't there.

I decide to use: **Gson, Jackson and FastJson ** for my checks.


## Session 34 (21/05/2019 )

#### Part 2 - Benchmarking

In this session Roger was reviewing my first approximation to solution. I was using a single class called `Serialization.java` and a POJO called `Person.java`. 
The idea behind the benchmark was use a single time in miliseconds comparison after serialize a list set of persons. 

Receiving feedback of Roger, I finished to write `main()` method of `Serialization.java` There a for loop was populating the `List` of `Person`s of size `n`, that corresponds to number of object to be created, and passing over another loop of size `m` that corresponds to sample size and was storing a `List` with the results of benchmark for each used serialization library.  After this, the mean was calculated for each library benchmarking list.

```java
//...
List<Person> persons = new ArrayList<>();

for (int i = 0; i < 20000; i++) {
	Person person = new Person();
    person.setId(i);
    person.setName("Name_" + i);
    person.setAge(i % 100);
    persons.add(i, person);
}

List<Long> gsonResults = new ArrayList<>();
List<Long> jacksonResults = new ArrayList<>();
List<Long> fastjsonResults = new ArrayList<>();

for (int i = 0; i < 150; i++) {
	gsonResults.add(benchmarkGson(persons));
	jacksonResults.add(benchmarkJackson(persons));
	fastjsonResults.add(benchmarkFastjson(persons));
}

long gsonMean = mean(gsonResults);
System.out.println("Gson = " + gsonMean + " ms");

long jacksonMean = mean(jacksonResults);
System.out.println("Gson = " + gsonMean + " ms");

long fastjsonMean = mean(fastjsonResults);
System.out.println("Gson = " + gsonMean + " ms");
//...
```

Following code chunk, performs serialization returning miliseconds time benchmark in this example for Gson serialization library:
```java
private static long benchmarkGson(List<Person> persons) {
	long begin = System.currentTimeMillis();
	new Gson().toJson(persons);
	return System.currentTimeMillis() - begin;
}
```

Finally, method on charge of return the mean can be seen following:
```java
private long mean(List<Long> benchmarks) {
	long sum = 0;  
	for (Long benchmark : benchmarks) {
    	sum += benchmark;
	}
    return sum / benchmarks.size();
}
```
A for loop was used in order to add up benchmarks in order to after get the final mean time benchmarked. 

As a side note there is another way to iterate by using `foreach()` lambda funcion:
```java
benchmarks.forEach(b -> sum +=b);
```
As can be seen, this kind of function is practical to use but isn't possible to modify the elements we are iterating over, so we cannot use it here.


#### Part 2 - Benchmarking - OOP

After code was running, we saw code could be improved through asimilation of OOP concepts which allow us to include any library we want to our benchmark without need to modify our main funcions and code, just by including libraries and call corresponding object's methods.

`Serialization.java` continues being main class whicn instantiate each kind of benchmark:

```java
public class Serialization {
    public static void main(String[] args) {
        new JacksonBenchmark().benchmark(15, 20000);
        new GsonBenchmark().benchmark(15, 20000);
        new FastjsonBenchmark().benchmark(15, 20000);
    }
}
```

Each type of bechmark inherit from abstarct class `Benchmark` class, there `bechmark()` contructor  method is implemented:, altogether with `mean()` and `runBenchmark()` which is defined as an abstract method that is implemented in each subclass:

```java
public abstract class Benchmark {
    public void benchmark(int samplesize, int objects) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < objects; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("Name_" + i);
            person.setAge(i % 100);
            persons.add(i, person);
        }

        List<Long> results = new ArrayList<>();

        for (int i = 0; i < samplesize; i++) {
            results.add(runBenchmark(persons));
        }

        long mean = mean(results);
        System.out.println("Result of " + getClass().getSimpleName() + " = "+ mean + " ms");

    }

    protected abstract Long runBenchmark(List<Person> persons);

    private long mean(List<Long> benchmarks) {
        long sum = 0;
        // benchmarks.forEach(b -> sum +=b); // lambda not to modify things inside. Quick function
        for (Long benchmark : benchmarks) {
            sum += benchmark;
        }
        return sum / benchmarks.size();
    }
}
```

Then, when a specific implementation is running the constructor, for example for `new JacksonBenchmark().benchmark(15, 20000);` this will execute `runBenchmark()` for that specific library implementation, in this case called `JacksonBenchmark` which is inheriting from `Benchmark` and implementing `runBenchmark()` method according to library specifications:

```java
public class JacksonBenchmark extends Benchmark {
    @Override
    protected Long runBenchmark(List<Person> persons) {
        long begin = System.currentTimeMillis();
        try {
            new ObjectMapper().writeValueAsString(persons);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - begin;
    }
}
```




