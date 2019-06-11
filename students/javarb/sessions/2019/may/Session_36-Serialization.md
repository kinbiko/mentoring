# SOFTWARE ENGINEERING IN JAVA

## Session 34 (07/06/2019 )

- Research homework [on my project](https://github.com/kinbiko/mentoring/projects/7) board.

### Notes 

Roger was leading me to improve the serialization libraries benchmark project.

#### Benchmark project

The goal of this session was to introduce concept of the [Observer Pattern](https://www.oodesign.com/observer-pattern.html) that is part of the [SOLID principles](https://scotch.io/bar-talk/s-o-l-i-d-the-first-five-principles-of-object-oriented-design), in which `main()` method doesn't needs to know all about system, but a central entity knows about and each benchmark will ask registration.  

To this, two new classes were introduced `Configuration.java` and `ConfigurationFactory.java`


__Side note about API endpoints:__
Was given the recommendation to handle just one kind of data for each API endpoint. For example, not try to handle json structure for persons and accounts at the same endpoint.

