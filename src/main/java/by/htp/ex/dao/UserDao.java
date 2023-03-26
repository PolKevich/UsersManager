package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.User;

public interface UserDao {

	public User saveUser(User user) throws DAOException;

	public List<User> getAllUsers() throws DAOException;
	
}
