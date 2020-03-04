package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student(11, "Shayan", 24, 23));
        list.add(new Student(22, "Shaya", 24, 23));
        list.add(new Student(33, "Shayn", 24, 23));
        list.add(new Student(44, "Shaan", 24, 23));
        list.add(new Student(55, "Shyan", 24, 23));

        Integer studentId = 44;

        list.removeIf(student -> student.getId() == studentId);

        list.stream().collect(Collectors.toList()).forEach(System.out::println);
    }
}
