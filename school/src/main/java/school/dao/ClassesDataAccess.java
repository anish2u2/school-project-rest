package school.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import school.contracts.FetchDataPerSchool;
import school.dto.Classes;

@Component
@Repository
@Qualifier("classDataAccess")
public class ClassesDataAccess extends DataAccess implements FetchDataPerSchool {

	private static final String FETCH_CLASSES = "from Classes where Id=:Id";
	private static final String FETCH_ALL_CLASSES = "from Classes ";
	private static final String FETCH_ALL_CLASSES_ON_SCHOOL = "from Classes where teacher.school.id=:id";

	public Classes get(Long Id) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_CLASSES);
			query.setParameter("Id", Id);
			return (Classes) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Classes> fetchAll() {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_CLASSES);
			return (List<Classes>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object fetchData(Long schoolId) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_CLASSES_ON_SCHOOL);
			query.setParameter("id", schoolId);
			return (List<Classes>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
