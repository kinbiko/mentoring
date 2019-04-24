# SOFTWARE ENGINEERING IN JAVA

## Session 10 and 11 (04/09/2018, 10/09/2018)

- TDD challenge solved
- Coverage Tests

### Notes:

#### TDD challenge Session 9 was solved

The challenge of [counting houses positions][1] proposed at Session 9 was completed using TDD.

During Session 10, Roger showed me an example of how to do this program in order I have resolved it for the Session 11. I solved it based in what I saw and during Session 11 he reviewed my work making corrections and suggestions as necessary.

Concept of *test coverage* was introduced, meaning the percentaje of lines of code in our project that our tests are covering.

In practice this is not much manageable to have a 100% of coverage, since this is not always possible to cover
all possible test conditions. For example in database consults where the test are out of scope of Java Code
and pass into the DBMS level. Also, tests can be heavy because the load of make hundreds of tests cases can produce. So this has to be manageable in a convenient way.

Also, set up 100% as a test coverage as goal isn't a good idea since we'll finish focused in the wrong thing. An example of that can be seen in the next test:

```js
@Test
public void testMyPojo() {
    Pojo pojo = new Pojo();
    Thing thing = new Thing();
    pojo.setThing(thing);
    actual = pojo.getThing(thing);
    Assert.assertEquals(thing, actual);
}
```  
Here, we're testing both set and get methods of our object because we want to cover the 100% of our code.  So the focus here has been to achieve the coverage test even if that means to do naive things.

Thus, go for 100% coverage is just doing the programmer focus in the coverage more than in the code logic as such.

### Resources:

- [Project Euler - Programming to solve mathematical problems][1]
- [Codewars - Practice programming through solving challenges][2]

[1]: https://projecteuler.net/
[2]: https://www.codewars.com/
