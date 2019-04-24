# SOFTWARE ENGINEERING IN JAVA

## Session 17 (13/11/2018)

- Java API backend and JavaScript+HTML+CSS frontend application demo.

### Notes

Roger was showing me an example of an application he developed in the past, this was the game called Hangman, that consists in guess a word with a maximum of tries.

The code of the Java API Backend is on <https://github.com/kinbiko/hangman> and the Frontend code is on <https://github.com/kinbiko/hangman-frontend>

#### Backend

##### Groovy

The backend application was written with a service in [Groovy][11] which is a language based in Java but with several advantages such as simpler variable definition, not need of final semicolons and several useful methods such as `bytes` as is shown following:

```groovy
final String stringRepresentation = new String(new File("src/main/resources/passwords.txt").bytes)
```

In this case that piece of code is extracting the content of the `passwords.txt` as bytes and and placing it in a `String ` variable.

Groovy is a good language to learn but is dependant on be updated with Java versions, because Java is the dominant programming language.

Other languaged based o Java are Kotlin and Scala.


##### JavaScript vs CoffeeScript

Another example of a Programming Languaje based in another is how CoffeeScript and TypeScript which are both based on javaScript ([some details here][4]). About [CoffeeScript][10], a real life example was given by Roger in how the things can be complicate when using CoffeeScript. When CoffeeScript born at the end of 2009, it was intended to be a solution to all JavaScript problems at that time, but also introduced syntax changes. With the pass of the time, JavaScript solved its problems and improved but in a different way that CoffeeScript did, so this made separate paths between both of these languages plus all the syntax differences. Then now, its an effort migrate or translate applications between them, JavaScript goes ahead and CoffeeScript goes behind applying JavaScript improvements. Some of the arguments against CoffeeScript can be found [here][5] and [here][6].

About TypeScript its unlikely will fail in the future for the same reasons that CoffeeScript did, this is because TypeScript is a superset of JavaScript (as Groovy is to Java). So we can write JavaScript into a TypeScript file and it just will work.

##### Domain Specific Language   

Domain Specific Languages (DSL) are small programming languages used inside an application with the purpose to execute certain focused tasks into applications, examples of them are SQL, CSS, Regular Expressions. Gradle scripts written with Groovy are other example of a DLS, that means an DLS can be written in another programming language that isn't a DSL. When a DLS is built based in another language (as Groovy for example) the're called 'Internal DSL'. Conversely, when it's a language completely separated from another languages its called an 'External DSL'.

##### Java vs JavaScript comparison

Was stated that Java produce much more enterprise ready applications because its multithreading support.

Very good things can been achieved with JavaScript in the backend and the fronted -besides of the fact to be using the same programming language for both of them- but with JavaScript there is a need that the programmer deal beforehand with the problems can arise with a single threaded JavaScript application, such as blocking and long time of response when making operations. In general callback functions (that could leads to what is known as [callback hell][9]), [promises][7], [run-parallel][12] and [run-series][13] are used as alternatives to overcome the breaking application risks (without be a solution for single threading), but additional to that, we should too think in horizontal scaling and load balancing to have a enterprise grade application that behaves similarly as a backend Java application.

So, at the end still Node is simpler, Java will do it better at the long time to build stable backend applications.

### Homework
Make the fronted for the Java API calculator.

NOTES:

CORS Filter has to be added to provide access to the JS frontend to the Java Backend, else I'll get the error:

`Failed to load http://localhost:8080/api/fibonacci/2: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'null' is therefore not allowed access.`


### Resources

- [Apache Groovy Language][11]
- [Domain-specific language][1]
- [Domain Specific Languages Book][2]
- [Domain Specific Languages Video][3]
- [JavaScript vs CoffeeScript vs TypeScript][4]
- [CoffeeScript][10]
- [Coffeescript vs. Javascript: Dog eat Dog][5]
- [A Case Against Using CoffeeScript][6]
- [Understanding promises in JavaScript][7]
- [Implementing Promises In JavaScript][8]
- [Callback Hell][9]
- [run-parallel][12]
- [run-series][13]


[1]: https://martinfowler.com/books/dsl.html
[2]: https://martinfowler.com/dsl.html
[3]: https://channel9.msdn.com/blogs/charles/expert-to-expert-martin-fowler-and-chris-sells-perspectives-on-domain-specific-languages
[4]: https://bytescout.com/blog/2016/07/javascript-vs-coffeescript-vs-typescript.html
[5]: https://lostechies.com/bradcarleton/2013/10/23/coffeescript-vs-javascript-dog-eat-dog/
[6]: http://ryanflorence.com/2011/case-against-coffeescript/
[7]: https://hackernoon.com/understanding-promises-in-javascript-13d99df067c1
[8]: https://medium.freecodecamp.org/how-to-implement-promises-in-javascript-1ce2680a7f51
[9]: http://callbackhell.com/
[10]: https://en.wikipedia.org/wiki/CoffeeScript
[11]: http://groovy-lang.org/
[12]: https://github.com/feross/run-parallel
[13]: https://github.com/feross/run-series
