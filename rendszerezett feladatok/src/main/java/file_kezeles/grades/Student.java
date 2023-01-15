package file_kezeles.grades;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String className;
    private List<Integer> grades = new ArrayList<>();

    public Student(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void addGrade(Integer grade){
        grades.add(grade);
    }

    public String getClassName() {
        return className;
    }

    public List<Integer> getGrades() {
        return grades;
    }
}
