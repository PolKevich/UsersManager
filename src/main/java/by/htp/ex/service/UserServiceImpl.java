package by.htp.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DAOException;
import by.htp.ex.dao.UserDao;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User saveUser(User user) throws ServiceException {
		
		try {
	     return userDao.saveUser(user);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<User> getAllUsers() throws ServiceException {
		
		try {
			
		return userDao.getAllUsers();
		
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
