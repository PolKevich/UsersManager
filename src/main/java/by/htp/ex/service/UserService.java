package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.User;

public interface UserService {

	public User saveUser(User user) throws ServiceException;

	public List<User> getAllUsers() throws ServiceException;
	
	
}
