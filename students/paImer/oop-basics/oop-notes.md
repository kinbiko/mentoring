# Object Oriented Programming (OOP)

> "OOP models the real world as things that have things and do things."
> \- RG, 2019

The **three** fundamental concepts of OOP are:

- [Encapsulation](#encapsulation)
- [Inheritance](#inheritance)
- [Polymorphism](#polymorphism)

## Encapuslation

> "Tell, don't ask"

Having information wide open for anyone and anything to access poses risk of tampering, and a single change. leading to a potentially catastrophic chain reaction.
Encapsulation is the wrapping up of data under a single unit.
This unit acts as a shield, preventing data from being accessed by code outside the shield.

There are four visibillity modifiers in Java, `public`, `protected`, `<default>` (no modifier), and `private`.

Their access levels are given in the table below.

|Modifier   | Class  |Package |Subclass|  World |
|-----------|--------|--------|--------|--------|
|`public`   |   Y    |    Y   |   Y    |    Y   |
|`protected`|   Y    |    Y   |   Y    |    N   |
|`<default>`|   Y    |    Y   |   N    |    N   |
|`private`  |   Y    |    N   |   N    |    N   |

The `Car` class below is using one common form of encapsulation. It has three private fields, and each of them has its own set of *getter and setter* methods.

```java
class Car {
    private String brand;
    private String colour;
    private double topSpeed;

    // Getter methods
    public String getBrand() {
        return brand;
    }

    public String getColour() {
        return colour;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    // Setter methods
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }
}
```

```java
public class TestCar {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.setBrand("Mitsubishi");
        myCar.setColour("White");
        myCar.setTopSpeed(150);

        System.out.println("Brand: " + myCar.getBrand());
        System.out.println("Colour: " + myCar.getColour());
        System.out.println("Top speed: " + myCar.getTopSpeed() + " mph");
    }
}
```

Running `TestCar` will return

```
Brand: Mitsubishi
Colour: White
Top speed: 150 mph
```

as we would have hoped!

## Inheritance

Inheritance allows the extension of a class using the `extends` keyword, improving code reusability.

Real life example:

All *ostriches* are *birds*, but **not** all *birds* are *ostriches*.

As much as an ostrich may wish to fly, it cant. As a result, a method pertaining to flight ability in the superclass would be innappropriate (and unfair to Mr. Ostrich).

In this case, it would make sense to create a couple of different individual sub-classes (ostrich, wren, etc.), and from there to extract a superclass (bird) with the features and traits *all* birds have.

With our car example:

```java
// Define traits (fields and methods) common to *all* cars
class Car {
    public String vehicle = "Car";
    public int numOfWheels = 4;

    public void drive() {
        System.out.println("Driving along");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
    }

    public void stop() {
        System.out.println("Squirrel!!! Stop!!!");
    }
}
```

```java
// Define traits specific to subclass (Mercedes)
public class Mercedes extends Car {
    public String brand = "Mercedes";
    public String colour = "silver";
    public double topSpeed = 200;
    public String licencePlate = "2 F4ST";
}
```

```java
// Create a car!
public class TestCar {
    public static void main(String[] args) {
        Mercedes myCar = new Mercedes();

        System.out.println("Vehicle type: " + myCar.vehicle);
        System.out.println("No. of wheels: " + myCar.numOfWheels + "\n");

        System.out.println("Description: A stunning " + myCar.colour + " " + myCar.brand);
        System.out.println("Top speed: " + myCar.topSpeed + " mph");
        System.out.println("Licence plate: " + myCar.licencePlate + "\n");

        myCar.drive();
        myCar.stop();
    }
}
```

`TestCar` instantiates a new `Car` object, and prints out all the information. This includes the fields and methods inherited from `Car`, and the four extra fields defined in the `Mercedes` class.

Here is the console output when `TestCar.main()` is run:

```
Vehicle type: Car
No. of wheels: 4

Description: A stunning silver Mercedes
Top speed: 200.0 mph
Licence plate: 2 F4ST

Driving along
...
...
...
Squirrel!!! Stop!!!
```

## Polymorphism

> When things look the same, but don't behave the same.

### Static polymorphism

Here we can define some more methods on the `Car` class, each with the same name, `drive()`. This is an example of *method overloading*.

While these methods all share the same method name, the method behaviour is dependent on the input parameters.

```java
// Different method signature = different behaviour
class Car {
    public void drive() {
        System.out.println("The car is moving");
    }

    public void drive(int speed) {
        System.out.println("The car is travelling at " + speed + " mph.");
    }

    public void drive(String brand, int speed) {
        System.out.println("The " + brand + " is travelling at " + speed + " mph.");
    }
}
```

Below, we instantiate a new `Car` object, and call the `drive()` method three times, each with different parameters.

```java
public class TestCar {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.drive();
        myCar.drive(56);
        myCar.drive("Honda", 100);
    }
}
```

Console output:

```
The car is moving
The car is travelling at 56 mph.
The Honda is travelling at 100 mph.
```

### Dynamic polymorphism

Instead of *method overloading*, here we use *method overriding*.

With this Java feature, you are able to override the methods of a parent class from a child class, and the methods of interfaces.

There are three rules overriding methods:

1. The method must have the same name as in the parent class.
1. The method must have the same parameter as in the parent class.
1. There must be an IS-A relationship (inheritance).

```java
class Car {
    public void drive() {
        System.out.println("The car is moving.");
    }
}
```

Aside - abstract methods:
It is not necessary to implement the method you wish to override in the parent class.
An abstract method is a method that is declared, but contains no implementation.

```java
abstract class Car {
    abstract void drive();
}
```

In the above code, the `abstract` keywords modifies `drive()` to do exactly that.

```java
public class BrokenCar extends Car {
    public void drive() {
        System.out.println("The car wont start.");
    }
}
```

Are our rules being followed?

> 1. The method must have the same name as in the parent class.

Yep, both `drive()`.

> 2. The method must have the same parameter as in the parent class.

`drive()` doesn't take any parameters, so not a problem.

> 3. There must be an IS-A relationship (inheritance).

A `BrokenCar` __is a__ `Car`, so this checks out too.

In the above code, instantiating objects from both `Car` and `BrokenCar` will lead to the `drive()` method behaving differently.

```java
public class TestCar {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.drive();

        BrokenCar myBrokenCar = new BrokenCar();
        myBrokenCar.drive();
    }
}
```
Note - this code will not compile if `Car` is declared abstract.
You cannot instantiate (`new`) an abstract method!

Output:

```
The car is moving.
The car wont start.
```
