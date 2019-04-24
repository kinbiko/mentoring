public class Textbook {
    private String subject;
    private int pageCount;

    public void declareSubject() {
        System.out.println(subject + " textbook: I am a " + subject + " textbook.");
    }

    public void declarePageCount() {
        System.out.println("I have " + getPageCount() + " pages.");
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
