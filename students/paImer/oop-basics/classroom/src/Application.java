import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Application {
    void start() {
        Classroom classroom = makeClassroom();
        classroom.getTeacher().greetClassroom(classroom);
        classroom.getTeacher().startTeaching(classroom);
    }

    private Classroom makeClassroom() {
        Classroom classroom = new Classroom();
        classroom.setTeacher(makeTeacher());
        classroom.setTextbooks(makeTextbooks());
        classroom.setStudents(makeStudents());
        classroom.setDesks(makeDesks());
        classroom.setChairs(makeChairs());
        return classroom;
    }

    private Set<Chair> makeChairs() {
        Set<Chair> chairs = new HashSet<>();
        chairs.add(makeChair());
        return chairs;
    }

    private Chair makeChair() {
        Chair chair = new Chair();
        chair.setEmpty(true);
        chair.setLegCount(6);
        return chair;
    }

    private Set<Desk> makeDesks() {
        Set<Desk> desks = new HashSet<>();
        desks.add(makeDesk1());
        desks.add(makeDesk2());
        return desks;
    }

    private Desk makeDesk1() {
        Desk desk = new Desk();
        desk.setLegCount(5);
        desk.setTextbook(false);
        return desk;
    }

    private Desk makeDesk2() {
        Desk desk = new Desk();
        desk.setLegCount(1);
        desk.setTextbook(true);
        return desk;
    }

    private Set<Student> makeStudents() {
        Set<Student> students = new HashSet<>();
        students.add(makeStudent1());
        students.add(makeStudent2());
        return students;
    }

    private Student makeStudent1() {
        Student student = new Student();
        student.setName("Nate");
        student.setAge(25);
        student.setGrades(makeGrades1());
        student.setBagContents(makeBagContents1());
        return student;
    }

    private Student makeStudent2() {
        Student student = new Student();
        student.setName("Alex");
        student.setAge(27);
        student.setGrades(makeGrades2());
        student.setBagContents(makeBagContents2());
        return student;
    }

    private Map<String, Integer> makeGrades1() {
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Physics", 79);
        grades.put("Japanese", 99);
        return grades;
    }

    private Map<String, Integer> makeGrades2() {
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Physics", 1);
        grades.put("Japanese", 100);
        return grades;
    }

    private Set<String> makeBagContents1() {
        Set<String> bagContents = new HashSet<>();
        bagContents.add(makeTextbook1() + " textbook");
        bagContents.add(makeTextbook2() + " textbook");
        bagContents.add("Pencil case");
        bagContents.add("Water bottle");
        bagContents.add("Infinity gems");
        return bagContents;
    }

    private Set<String> makeBagContents2() {
        return new HashSet<>();
    }

    private Set<Textbook> makeTextbooks() {
        Set<Textbook> textbooks = new HashSet<>();
        textbooks.add(makeTextbook1());
        textbooks.add(makeTextbook2());
        return textbooks;
    }

    private Textbook makeTextbook1() {
        Textbook textbook = new Textbook();
        textbook.setSubject("Algebra");
        textbook.setPageCount(1000);
        return textbook;
    }

    private Textbook makeTextbook2() {
        Textbook textbook = new Textbook();
        textbook.setSubject("Algebra");
        textbook.setPageCount(1000);
        return textbook;
    }

    private Teacher makeTeacher() {
        Teacher teacher = new Teacher();
        teacher.setAge(60);
        teacher.setName("Smith");
        teacher.setSubject("Algebra");
        return teacher;
    }
}
