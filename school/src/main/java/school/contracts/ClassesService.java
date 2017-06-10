package school.contracts;

import school.pojo.Classes;

public interface ClassesService extends GenericSchoolService{

	public Classes fetchClasses(Long classId);


}
