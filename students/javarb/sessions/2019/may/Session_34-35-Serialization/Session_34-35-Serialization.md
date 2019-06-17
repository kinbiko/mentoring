# SOFTWARE ENGINEERING IN JAVA

## Session 34 (21/05/2019 )

- Research homework [on my project](https://github.com/kinbiko/mentoring/projects/7) board.

## Notes

### Homework

#### Part 1
Figure out the most popular libraries for turning JSON strings into POJOs, and for turning POJOs into JSON.

#### Part 2
Write a simple project to benchmark their performance for serialization and deserialization.

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
- [Jsoniter](http://jsoniter.com/)

I found [this other article](http://dgimenes.com/blog/2014/03/14/java-comparing-json-encoding-libraries.html) where the author concludes that Gson and Jackson are the best libraries, but it also mentions [JSON.org](http://json.org/) library, which [seems to be obsolete now](https://stackoverflow.com/a/7623694). JSON.org provides a huge list of links to different Java libraries to handle serialization:

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

## Session 34 (21/05/2019 )

#### Part 2 - Benchmark project

I decided to use: **Gson, Jackson and FastJson** for my checks. In order to use them I needed to include them on `build.gradle` file:
```java
dependencies {
	//...
	compile group: 'com.google.code.gson', name: 'gson', version: '2.8.5'
	compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.8'
	compile group: 'com.alibaba', name: 'fastjson', version: '1.2.56'
}
```

**Note:** Version number were extracted from installation instructions and corresponds to current versions at the moment to include these libraries.

In my first approximation solution. I was using a single class called `Serialization.java` and a POJO called `Person.java`. 
The idea behind the benchmark was to use a single-time comparison (in miliseconds) after serialising a list of people. 

Receiving feedback of Roger, I finished to write `main()` method of `Serialization.java`. There, a for loop was populating a `List` of `Person` objects called `people` and passing over another for loop to store the benchmark results of calculate the miliseconds time that takes to serialize the list of people. These results are stored in another `List` that is passed to the `mean()` method in order to calculate the mean value and print it:
```java
//...
List<Person> people = new ArrayList<>();

for (int i = 0; i < 20000; i++) {
	Person person = new Person();
	person.setId(i);
	person.setName("Name_" + i);
	person.setAge(i % 100);
	people.add(i, person);
}

List<Long> gsonResults = new ArrayList<>();
List<Long> jacksonResults = new ArrayList<>();
List<Long> fastjsonResults = new ArrayList<>();

for (int i = 0; i < 150; i++) {
	gsonResults.add(benchmarkGson(people));
	jacksonResults.add(benchmarkJackson(people));
	fastjsonResults.add(benchmarkFastjson(people));
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
private static long benchmarkGson(List<Person> people) {
	long begin = System.currentTimeMillis();
	new Gson().toJson(people);
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

A for loop was used in order to add up benchmarks in order to after get the final mean time benchmarked. More metrics can be added to reach more precision in benchmark results, but for purposes of this exercise, just _mean_ was used by now.

**Note:** As a side note, there is another way to iterate by using `foreach()` lambda funcion:
```java
benchmarks.forEach(b -> sum +=b);
```
As can be seen, this kind of function is practical to use but it isn't possible to modify the elements we are iterating over, so we cannot use it here.

#### Part 2 - Benchmark project in OOP

After the code was running, we saw that the code could be improved through assimilation of OOP concepts which allow us to include any library we want to our benchmark without the need to modify our main functions and code. So instead of that, we could add another serialization library just by including the library in `build.gradle`, create the corresponding class for that implementation,  and instantiating/ executing the library's `benchmark()` method.

So, following explanation about our OOP code.

`Serialization.java` continues being main class which instantiates each kind of benchmark:

```java
public class Serialization {
	public static void main(String[] args) {
		new JacksonBenchmark().benchmark(150, 200000);
		new GsonBenchmark().benchmark(150, 200000);
		new FastjsonBenchmark().benchmark(150, 200000);
	}
}
```

Each type of bechmark inherits from the abstract class `Benchmark`. In that class, a  `bechmark()` constructor method is implemented, altogether with `mean()` and `runBenchmark()`, the latter is defined as an abstract method that is implemented in each subclass:

```java
public abstract class Benchmark {
	public void benchmark(int samplesize, int objects) {
		List<Person> people = new ArrayList<>();

		for (int i = 0; i < objects; i++) {
			Person person = new Person();
			person.setId(i);
			person.setName("Name_" + i);
			person.setAge(i % 100);
			people.add(i, person);
		}

		List<Long> results = new ArrayList<>();

		for (int i = 0; i < samplesize; i++) {
			results.add(runBenchmark(people));
		}

		long mean = mean(results);
			System.out.println("Result of " + getClass().getSimpleName() + " = "+ mean + " ms");

	}

	protected abstract Long runBenchmark(List<Person> people);

	private long mean(List<Long> benchmarks) {
		long sum = 0;       
		for (Long benchmark : benchmarks) {
			sum += benchmark;
		}
		return sum / benchmarks.size();
	}
}
```

Then, when a specific implementation is running the constructor, for example for, for Jackson `new JacksonBenchmark().benchmark(150, 200000);` this will execute `runBenchmark()` for that specific library implementation, in this case called `JacksonBenchmark` which is inheriting from the `Benchmark` class and implementing `runBenchmark()` method according to library specifications:

```java
public class JacksonBenchmark extends Benchmark {
	@Override
	protected Long runBenchmark(List<Person> people) {
		long begin = System.currentTimeMillis();
		try {
			new ObjectMapper().writeValueAsString(people);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() - begin;
	}
}
```
**Note:** `@Override` annotation specifies we are overriding the specfied method, this is specially useful for a [couple of reasons](https://stackoverflow.com/a/94411/4278635):
- The compiler will warn us if there is a misspelling in the name of our method, or wrong use of the method's parameters.
- Make code easier to understand because becomes obvious what that method is doing.

Other implementations can be seen on correspondig code project on this same directory. 