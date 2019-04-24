public class Desk {
    private int legCount;
    private boolean textbook;

    public void greetTeacher(int n) {
        String placeholder = (n == 0 ? " " : " also ");
        if (legCount < 4) {
            String legs = legCount + (legCount == 1 ? " leg." : " legs.");
            System.out.println("Talking desk: Good morning, I am" + placeholder + "a desk. I have " + legs);
        } else {
            System.out.println("Talking desk: Good morning, I am" + placeholder + "a desk. I have " + legCount + " legs.");
        }
    }

    public void apology() {
        legCount = (int) (Math.random() * 100 + 4);
        System.out.println("Talking desk: I am very sorry. I now have " + legCount + " legs.");
    }

    public int getLegCount() {
        return legCount;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }

    public boolean isTextbook() {
        return textbook;
    }

    public void setTextbook(boolean textbook) {
        this.textbook = textbook;
    }
}
