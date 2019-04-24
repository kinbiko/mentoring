package farm;

public class Sheep extends Animal {
    @Override
    public void speak() {
        System.out.println("Bah");
    }

    @Override
    public void introduce() {
        String noise = "Bah!";
        System.out.println(noise + ("! I'm a sheep!"));
    }
}
