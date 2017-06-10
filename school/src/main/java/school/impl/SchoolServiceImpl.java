package school.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import school.abstracts.AbstractGenericSchoolService;
import school.contracts.SchoolService;
import school.dao.SchoolDataAccess;
import school.pojo.School;

@Component
@Service
@Qualifier("schoolService")
public class SchoolServiceImpl extends AbstractGenericSchoolService implements SchoolService {

	@Autowired
	@Qualifier("schoolDataAccess")
	private SchoolDataAccess schoolDataAccess;

	@Transactional(readOnly = true)
	public School fetchSchoolDetails(Long schoolId) {
		school.dto.School schoolDetail = schoolDataAccess.get(schoolId);
		School school = new School();
		if (schoolDetail != null) {
			school.setSchoolId(schoolDetail.getId());
			school.setSchoolName(schoolDetail.getName());
		}
		return school;
	}

	@Override
	@Transactional(readOnly = true)
	protected Object fetchAll(Long schoolId) {
		@SuppressWarnings("unchecked")
		List<school.dto.School> schoolList = (List<school.dto.School>) schoolDataAccess.get(schoolId);
		List<School> result = new ArrayList<School>();
		for (school.dto.School SchoolData : schoolList) {
			School school = new School();
			school.setSchoolId(SchoolData.getId());
			school.setSchoolName(SchoolData.getName());
			result.add(school);
		}
		return result;
	}

	public Object getAll() {
		List<school.dto.School> schoolList = (List<school.dto.School>) schoolDataAccess.fetchAll();
		List<School> result = new ArrayList<School>();
		for (school.dto.School SchoolData : schoolList) {
			School school = new School();
			school.setSchoolId(SchoolData.getId());
			school.setSchoolName(SchoolData.getName());
			result.add(school);
		}
		return result;
	}

}
