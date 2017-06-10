package school.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import school.abstracts.AbstractGenericSchoolService;
import school.contracts.TeachersService;
import school.dao.TeacherDataAccess;
import school.dto.Teacher;
import school.pojo.Teachers;

@Component
@Service
@Qualifier("teacherService")
public class TeacherServiceImple extends AbstractGenericSchoolService implements TeachersService {

	@Autowired
	@Qualifier("teacherDataAccess")
	private TeacherDataAccess teacherDataAccess;

	@Transactional(readOnly = true)
	public Teachers fetchTeacherDetails(Long teacherId) {
		Teacher teacher = teacherDataAccess.get(teacherId);
		Teachers teachers = new Teachers();
		if (teacher != null) {
			teachers.setTeacherId(teacher.getId());
			teachers.setTeacherName(teacher.getName());
		}
		return teachers;
	}

	@Override
	@Transactional(readOnly = true)
	protected Object fetchAll(Long schoolId) {
		@SuppressWarnings("unchecked")
		List<school.dto.Teacher> teacherList = (List<school.dto.Teacher>) teacherDataAccess.fetchData(schoolId);
		List<Teachers> result = new ArrayList<Teachers>();
		for (school.dto.Teacher teacherData : teacherList) {
			Teachers teacher = new Teachers();
			teacher.setTeacherId(teacherData.getId());
			teacher.setTeacherName(teacherData.getName());
			result.add(teacher);
		}
		return result;
	}

}
