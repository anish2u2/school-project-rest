package school.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import school.dto.School;

@Component
@Repository
@Qualifier("schoolDataAccess")
public class SchoolDataAccess extends DataAccess {

	private static final String FETCH_SCHOOL = "from School where Id=:schoolId";
	private static final String FETCH_ALL_SCHOOL = "from School ";

	public School get(Long schoolId) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_SCHOOL);
			query.setParameter("schoolId", schoolId);
			return (School) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<School> fetchAll() {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_SCHOOL);
			return (List<School>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
