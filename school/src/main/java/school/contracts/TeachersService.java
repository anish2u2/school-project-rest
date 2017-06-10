package school.contracts;

import school.pojo.Teachers;

public interface TeachersService extends GenericSchoolService {

	public Teachers fetchTeacherDetails(Long teacherId);

}
