package school.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import school.abstracts.AbstractGenericSchoolService;
import school.contracts.StudentService;
import school.dao.StudentDataAccess;
import school.pojo.Student;

@Component
@Service
@Qualifier("studentService")
public class StudentServiceImpl extends AbstractGenericSchoolService implements StudentService {
	@Autowired
	@Qualifier("studentDataAccess")
	private StudentDataAccess studentDataAccess;

	@Transactional(readOnly = true)
	public Student fetchStudent(Long studentId) {
		school.dto.Student studentRecord = studentDataAccess.get(studentId);
		Student student = new Student();
		if (studentRecord != null) {
			student.setStudentId(studentRecord.getId());
			student.setStudentName(studentRecord.getName());
			student.setParentId(studentRecord.getParent().getId());
		}
		return student;
	}

	@Override
	@Transactional(readOnly = true)
	protected Object fetchAll(Long schoolId) {
		@SuppressWarnings("unchecked")
		List<school.dto.Student> studentList = (List<school.dto.Student>) studentDataAccess.fetchData(schoolId);
		List<Student> result = new ArrayList<Student>();
		for (school.dto.Student studentData : studentList) {
			Student student = new Student();
			student.setStudentId(studentData.getId());
			student.setStudentName(studentData.getName());
			student.setParentId(studentData.getParent().getId());
			result.add(student);
		}
		return result;
	}

}
