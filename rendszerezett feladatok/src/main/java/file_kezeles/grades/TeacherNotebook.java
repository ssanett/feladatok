package file_kezeles.grades;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TeacherNotebook {

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void readFromFile(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            while (bf.ready()){
                processLine(bf.readLine());
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }

    public List<String> findFailingStudents(){
        List<String> result = new ArrayList<>();
        for(Student s:students){
            if(countAverage(s.getGrades())<2){
                result.add(s.getName());
            };
        }
        return result;

    }

    private int countAverage(List<Integer> number){
        int size = number.size();
        int sum = 0;
        for(Integer n:number){
            sum+=n;
        }
        return sum/size;
    }

    private void processLine(String line){
        String[] temp = line.split(";");
        String name = temp[0];
        String className = temp[1];
        Student student = new Student(name,className);

        for(int i= 2; i<temp.length; i++){
            student.addGrade(Integer.parseInt(temp[i]));
        }
       students.add(student);

    }

}
