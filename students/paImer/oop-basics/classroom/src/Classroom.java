import java.util.Set;

public class Classroom {
    private Set<Desk> desks;
    private Teacher teacher;
    private Set<Student> students;
    private Set<Chair> chairs;
    private Set<Textbook> textbooks;

    public Set<Desk> getDesks() {
        return desks;
    }

    public void setDesks(Set<Desk> desks) {
        this.desks = desks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(Set<Chair> chairs) {
        this.chairs = chairs;
    }

    public Set<Textbook> getTextbooks() {
        return textbooks;
    }

    public void setTextbooks(Set<Textbook> textbooks) {
        this.textbooks = textbooks;
    }
}
