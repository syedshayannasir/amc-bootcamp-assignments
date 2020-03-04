import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import services.StudentClassService;

@Component
public class AppComponent {

    @Reference
    StudentClassService studentClassService;

    @Activate
    void activate(){
        studentClassService.addStudent(101, "Ammar", 80, 22);
        studentClassService.addStudent(102, "Shaozae", 67, 21);
        studentClassService.addStudent(103, "Shayan", 12, 23);

        studentClassService.getAllStudents();

        System.out.println("Ammar has passed: " + studentClassService.isStudentPassed(101));
        System.out.println("Shayan has passed: " + studentClassService.isStudentPassed(103));

        System.out.println("Deleting a student with ID 103");

        System.out.println(studentClassService.getStudent(103));

        studentClassService.deleteStudent(103);

        studentClassService.getAllStudents();

    }

}
