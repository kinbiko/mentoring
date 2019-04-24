package farm;

public class Pig extends Animal {
    @Override
    public void speak() {
        System.out.println("Oink! Oink! I'm a pig!");
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm a pig");
    }
}
