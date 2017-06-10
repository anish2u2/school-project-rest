package school.contracts;

import school.pojo.School;

public interface SchoolService extends GenericSchoolService {

	public School fetchSchoolDetails(Long schoolId);

	public Object getAll();
}
