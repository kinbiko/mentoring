package farm;

public class Cow extends Animal {
    @Override
    public void speak() {
        System.out.println("Moo");
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm a cow!");
    }

}
