package school.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import school.dto.Parent;

@Component
@Repository
@Qualifier("parentDataAccess")
public class ParentDataAccess extends DataAccess {

	private static final String FETCH_PARNT = "from Parent where Id=:Id";
	private static final String FETCH_ALL_PARENT = "from Parent ";

	public Parent get(Long Id) {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_PARNT);
			query.setParameter("Id", Id);
			return (Parent) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Parent> fetchAll() {
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(FETCH_ALL_PARENT);
			return (List<Parent>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
