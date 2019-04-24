# Testing end-to-end functionality of a REST API:

 Spring Boot makes it easy to build automated unit tests for our RESTful endpoints. First make sure that the spring-boot-starter-test dependency is included in the `build.gradle` file: `testCompile('org.springframework.boot:spring-boot-starter-test')`.

- The test class is annotated with:
`@RunWith(SpringRunner.class)`      Makes use of Spring's testing suite instead of the standard JUnit test runner.
`@SpringBootTest`                   Tells SpringRunner to start the test as a SpringBoot application
`@AutoConfigureMockMvc`             Configure the MockMvc instance that will be used to make our RESTful calls, helping to isolate the web layer.

This MockMvc instance needs the `@Autowired` annotation:
```java
@Autowired
private MockMvc mockMvc;
```

Typical use of the MockMvc instance can be seen in this test. Notice the `.perform()` and `.andExpect()` methods. More about these later.
```java
@Test
public void greetingShouldReturnMessageFromService() throws Exception {
    when(service.greet()).thenReturn("Hello Mock");
    this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello Mock")));
}
```

The following targetJSON file:
```json
{
  "report":[
    {
      "date":"2018-01-01",
      "amount":125,
      "description":"Got paid"
    },
    {
      "date":"2018-01-02",
      "amount":-72,
      "description":"Paid bill"
    }
  ]
}
```

Can be tested with the following test class:
```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetReportTest {

    @Autowired
    private MockMvc mvc;

    private static final String BASE_URL = "/report";

    @Test
    public void assertJSONResponse() throws Exception {
        mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.report[0].date", is("2018-01-01")))
                .andExpect(jsonPath("$.report[0].amount", is(125)))
                .andExpect(jsonPath("$.report[0].description", is("Got paid")))
                .andExpect(jsonPath("$.report[1].date", is("2018-01-02")))
                .andExpect(jsonPath("$.report[1].amount", is(-72)))
                .andExpect(jsonPath("$.report[1].description", is("Paid bill")))
                .andReturn();
    }

    @Test
    public void otherTests() throws Exception {
        this.mockMvc.perform(get(BASE_URL)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Got paid")));
                .andExpect(content().string(equalTo(targetJSON)));
    }
```

## JSONPath Syntax

In the `assertJSONResponse` test, you can see the use of the `MockMvc`'s `jsonPath` method, this makes use of the JSONPath expressions, which are based on the syntax given in the following table:

| XPath | JSONPath         | Description                                                                                                                                       |
|-------|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| /     | $                | the root object/element                                                                                                                           |
| .     | @                | the current object/element                                                                                                                        |
| /     | . or []          | child operator                                                                                                                                    |
| ..    | n/a              | parent operator                                                                                                                                   |
| //    | ..               | recursive descent. JSONPath borrows this syntax from E4X.                                                                                         |
| *     | *                | wildcard. All objects/elements regardless their names.                                                                                            |
| @     | n/a              | attribute access. JSON structures don't have attributes.                                                                                          |
| []    | []               | subscript operator. XPath uses it to iterate over element collections and for predicates. In Javascript and JSON it is the native array operator. |
| |     | [,]              | Union operator in XPath results in a combination of node sets. JSONPath allows alternate names or array indices as a set.                         |
| n/a   | [start:end:step] | array slice operator borrowed from ES4.                                                                                                           |
| []    | ?()              | applies a filter (script) expression.                                                                                                             |
| n/a   | ()               | script expression, using the underlying script engine.                                                                                            |
| ()    | n/a              | grouping in Xpath                                                                                                                                 |
for more info see http://jsonpath.com/ for an online evaluator.

- MockMvc's `jsonPath` takes two parameters, a `JSONPath expression` and a `Hamcrest matcher method` (for example: `is`, `contains`, `containsInAnyOrder` or `hasSize`).
- Using the above JSONfile, the JSONPath expression `$.report[0].amount` results in the value of `125`.


