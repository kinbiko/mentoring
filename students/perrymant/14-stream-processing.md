# Stream Processing

Stream Creation
Let’s first obtain a stream from an existing array:
```
private static Employee[] arrayOfEmps = {
    new Employee(1, "Jeff Bezos", 100000.0),
    new Employee(2, "Bill Gates", 200000.0),
    new Employee(3, "Mark Zuckerberg", 300000.0)
};

Stream.of(arrayOfEmps);
```
We can also obtain a stream from an existing list:
```
private static List<Employee> empList = Arrays.asList(arrayOfEmps);
empList.stream();
```
And we can create a stream from individual objects using Stream.of():
```
Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
```
Or simply using Stream.builder():
```
Stream.Builder<Employee> empStreamBuilder = Stream.builder();

empStreamBuilder.accept(arrayOfEmps[0]);
empStreamBuilder.accept(arrayOfEmps[1]);
empStreamBuilder.accept(arrayOfEmps[2]);

Stream<Employee> empStream = empStreamBuilder.build();
```

Stream operations are chained together into pipelines. For example, if we wanted to color only the blue shapes red, we could say:
```
shapes.stream()
      .filter(s -> s.getColor() == BLUE)
      .forEach(s -> s.setColor(RED));
```
If we wanted to collect the blue shapes into a new List, we could say:
```
List<Shape> blue = shapes.stream()
                         .filter(s -> s.getColor() == BLUE)
                         .collect(Collectors.toList());
 ```
```
foo.stream()
.filter(foo -> foo.getBarValue() < 42)
.map(Foo::getBarString)
.distinct()
.limit(3)
.collect(Collectors.toCollection())
.forEach(System.out::println)

foo.stream()
.filter(foo -> foo.getBarValue() < 42)
.map(Foo::getBarString)
.distinct()
.limit(3)
.collect(Collectors.toList()) <-- this converts it to a List
.forEach(System.out::println)
```


### Lambda Expressions

```
(n) -> n*n
```
parameters required by the expression -> the actions of the lambda expression
“n becomes n\*n”, or “n becomes n squared”.

https://en.wikipedia.org/wiki/Anti-pattern

