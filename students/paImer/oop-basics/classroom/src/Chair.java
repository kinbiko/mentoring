public class Chair {
    private int legCount;
    private boolean empty;

    public void greetTeacher() {
        System.out.println("Talking chair: Hi, I'm a chair. There is" + (isEmpty() ? " not" : "") + " a book on me.");
    }

    public int getLegCount() {
        return legCount;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
