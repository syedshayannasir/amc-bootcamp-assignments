package services.impl;

import bean.Student;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import services.ClassConfigurationService;
import java.util.List;

@Component(service = ClassConfigurationService.class)
@Designate(ocd = StudentConfig.class)

public class ClassConfigurationServiceImpl implements ClassConfigurationService {

    @Activate
    StudentConfig studentConfig;

    public boolean isClassLimitReached(List<Student> studentList) {
        return (studentList.size() > studentConfig.maxStudents());
    }

    public Integer getPassingMarks() {
        return studentConfig.passingMarks();
    }
}
