package school.contracts;

import school.pojo.Student;

public interface StudentService extends GenericSchoolService{

	public Student fetchStudent(Long studentId);

}
