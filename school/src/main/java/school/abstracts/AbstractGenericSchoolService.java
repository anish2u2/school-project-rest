package school.abstracts;

import school.contracts.GenericSchoolService;

public abstract class AbstractGenericSchoolService implements GenericSchoolService {

	public Object fetchDataOnSchoolId(Long schoolId) {

		return fetchAll(schoolId);
	}

	protected abstract Object fetchAll(Long schoolId);

}
