package school.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import school.contracts.FetchDataPerSchool;
import school.dto.Teacher;

@Component
@Repository
@Qualifier("teacherDataAccess")
public class TeacherDataAccess extends DataAccess implements FetchDataPerSchool {

	private static final String FETCH_TEACHER = "from Teacher where Id=:Id";
	private static final String FETCH_ALL_TEACHER = "from Teacher ";
	private static final String FETCH_ALL_TEACHER_ON_SCHOOL = "from Teacher where school.id=:id";

	public Teacher get(Long Id) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_TEACHER);
			query.setParameter("Id", Id);
			return (Teacher) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> fetchAll() {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_TEACHER);
			return (List<Teacher>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object fetchData(Long schoolId) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_TEACHER_ON_SCHOOL);
			query.setParameter("id", schoolId);
			return (List<Teacher>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
