# Dependency Injection

## Dependency

Two classes that uses each other are called "coupled".
Whenever a class A uses another class or interface B, then A depends on B. A cannot carry out it's work without B, and A cannot be reused without also reusing B. In such a situation the class A is called the "dependant" and the class or interface B is called the "dependency". A dependant depends on its dependencies.
Dependencies are bad because they decrease reuse.

Dependency is just another object that your class needs to function.

```
|-------|           |-------------|
| Class | --uses--> | Other Class |
|-------|           |-------------|
                            ^
                            |
                       dependency
                            |
                            v
|-------|           |-------------|
| Model | --fetch-> |   Database  |
|-------|           |-------------|
```

We can say of the example above that the model class has a dependency on the database object.

Injecting dependencies means that the dependencies are pushed into the class from the outside.
That means that you shouldn't instantiate dependencies using the `new` operator from inside the class.
Instead, take it as a constructor parameter.
It decouples the class' construction from the construction of its dependencies.
Code should depend upon abstractions - and by doing this we're de-coupling implementations from classes.

### Dependency injection in action

[example PR where dependency injection is being applied to facilitate testing](https://github.com/JStonham/budjen/pull/10)

When creating dependency injection think:
- The thing (class/object) that relies on the dependency should only ask for an abstraction.
- whoever wants to use this thing needs to specify the implementation of the abstraction.
- the abstraction is ideally an interface.
- Coding to interfaces.
