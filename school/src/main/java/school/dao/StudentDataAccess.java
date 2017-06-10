package school.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import school.contracts.FetchDataPerSchool;
import school.dto.Student;

@Component
@Repository
@Qualifier("studentDataAccess")
public class StudentDataAccess extends DataAccess implements FetchDataPerSchool {

	private static final String FETCH_STUDENT = "from Student where Id=:Id";
	private static final String FETCH_ALL_STUDENT = "from Student ";
	private static final String FETCH_ALL_STUDENT_ON_SCHOOL = "from Student where school.id=:id";

	public Student get(Long Id) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_STUDENT);
			query.setParameter("Id", Id);
			return (Student) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Student> fetchAll() {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_STUDENT);
			return (List<Student>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object fetchData(Long schoolId) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_STUDENT_ON_SCHOOL);
			query.setParameter("id", schoolId);
			return (List<Student>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
