public class Teacher {
    private String name;
    private String subject;
    private int age;
    private int counter;

    public void greetClassroom(Classroom classroom) {
        System.out.println(name + ": Good morning everyone! My name is Mr. " + name + ".");
        for (Student student : classroom.getStudents()) {
            student.greetTeacher();
        }
        for (Desk desk : classroom.getDesks()) {
            desk.greetTeacher(counter);
            int oldLegCount = desk.getLegCount();
            if (desk.getLegCount() < 4) {
                System.out.println(name + ": You need more legs. Go and get at least " + (4 - oldLegCount) + " more.");
                desk.apology();
                legApologyResponse(desk, oldLegCount);
            } else {
                sufficentLegResponse(desk);
            }
            counter++;
        }
        for (Chair chair : classroom.getChairs()) {
            chair.greetTeacher();
        }

    }

    public void startTeaching(Classroom classroom) {
        System.out.println(name + ": OK, class, take out your textbook please.");
        for (Student student : classroom.getStudents()) {
            student.bookCheck();
        }
    }

    public void sufficentLegResponse(Desk desk) {
        System.out.println("Smith: That's a fantastic number of legs!");
    }

    public void legApologyResponse(Desk desk, int oldLegCount) {
        System.out.println(name + ": That's more like it. " + desk.getLegCount() + " legs are better than " + oldLegCount + ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
