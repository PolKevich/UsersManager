package by.htp.ex.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.htp.ex.bean.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User saveUser(User user) throws DAOException {
		try {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<User> theQuery = currentSession.createQuery("from User order by email desc");
			List<User> theUsers = theQuery.getResultList();
			
			return theUsers;
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


}
