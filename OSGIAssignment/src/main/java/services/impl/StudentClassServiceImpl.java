package services.impl;

import bean.Student;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import services.ClassConfigurationService;
import services.StudentClassService;

import java.util.ArrayList;
import java.util.List;

@Component(service = StudentClassService.class)
public class StudentClassServiceImpl implements StudentClassService {

    int count;
    List<Student> studentList = new ArrayList<Student>();

    @Reference
    ClassConfigurationService classConfigurationService;

    public void addStudent(Integer id, String name, Integer marks, Integer age) {

        if(!classConfigurationService.isClassLimitReached(studentList)){
            studentList.add(new Student(id, name, marks, age));
            count++;
        } else {
            System.out.println("Class Limit reached. Can't add more students to class");
        }

    }

    public void deleteStudent(Integer studentId) {
        if(!studentList.isEmpty()){
            studentList.remove(getStudent(studentId));
        } else {
            System.out.println("Student List is empty");
        }
    }

    public boolean isStudentPassed(Integer studentId) {
        return getStudent(studentId).getMarksObtained() >= classConfigurationService.getPassingMarks();
    }

    public Student getStudent(Integer studentId) {
        return studentList.stream().filter(student -> student.getId() == studentId).findFirst().orElse(null);
    }

    public void getAllStudents() {
        System.out.println("List of all student");
        for (Student student : studentList) {
            System.out.println(student);
        }

    }
}
