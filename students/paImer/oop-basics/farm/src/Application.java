import farm.*;

class Application {
    private int counter = 0;
    private int iterator = 0;

    void start() {
        /*
        public - visible for any class, anywhere
        protected - visible to sub-classes and classes in the same package
        <default> - visible to classes in the same package aka "package private"
        private - least slutty. only visible to the class itself
         */
        while (iterator < 1) {
            makeAnimal().speak();
            makeAnimal().introduce();
        }
    }

    private Animal makeAnimal() {
        counter++;
        if (counter == 31) {
            iterator++;
            return new Tractor();
        }
        if (counter % 3 == 0) {
            return new Cow();
        }
        if (counter % 3 == 1) {
            return new Pig();
        }
        return new Sheep();
    }
}
