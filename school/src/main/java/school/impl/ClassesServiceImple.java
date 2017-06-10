package school.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import school.abstracts.AbstractGenericSchoolService;
import school.contracts.ClassesService;
import school.dao.ClassesDataAccess;
import school.pojo.Classes;

@Component
@Service
@Qualifier("classesService")
public class ClassesServiceImple extends AbstractGenericSchoolService implements ClassesService {

	@Autowired
	@Qualifier("classDataAccess")
	public ClassesDataAccess dataAccess;

	@Transactional(readOnly = true)
	public Classes fetchClasses(Long classId) {
		school.dto.Classes classes = dataAccess.get(classId);
		Classes result = new Classes();
		if (classes != null) {
			result.setClassName(classes.getName());
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	protected Object fetchAll(Long schoolId) {
		@SuppressWarnings("unchecked")
		List<school.dto.Classes> classesList = (List<school.dto.Classes>) dataAccess.fetchData(schoolId);
		List<Classes> result = new ArrayList<Classes>();
		for (school.dto.Classes classesData : classesList) {
			Classes classes = new Classes();
			classes.setClassId(classesData.getId());
			classes.setClassName(classesData.getName());
			result.add(classes);
		}
		return result;
	}

}
