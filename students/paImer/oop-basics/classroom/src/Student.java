import java.util.Map;
import java.util.Set;

public class Student {
    private String name;
    private int age;
    private Map<String, Integer> grades;
    private Set<String> bagContents;

    public void greetTeacher() {
        String despair = (name.equals("Alex") ? ". I live in Japan!" : ". I wish I lived in Japan...");
        System.out.println(name + ": Hello, my name is " + name + despair);
    }

    public void bookCheck () {

        if (bagContents.isEmpty()) {
            System.out.println(name + ": Sorry, I forgot it.");
        } else {
            System.out.println(name + ": You got it, teach.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    public Set getBagContents() {
        return bagContents;
    }

    public void setBagContents(Set<String> bagContents) {
        this.bagContents = bagContents;
    }

}
