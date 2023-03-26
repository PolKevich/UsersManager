package by.htp.ex.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import by.htp.ex.bean.User;
import by.htp.ex.bean.UserRole;
import by.htp.ex.dao.DAOException;
import by.htp.ex.dao.UserDao;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;


public class UserServiceUnitTest {

	@Mock
    private UserDao userRepository;
   
    @InjectMocks
    private UserService userService;

    
    @Test
    public void testAddUser() throws ServiceException, DAOException {
        User user = new User("Ivanov", "Ivan", "Ivanovich", "ivanov@example.com", UserRole.Customer_User);
        when(userRepository.saveUser(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getMiddleName(), savedUser.getMiddleName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getRole(), savedUser.getRole());

    }
    
    @Test
    public void testGetAllUsers() throws DAOException, ServiceException {
        User user1 = new User("Ivanov", "Ivan", "Ivanovich", "ivanov@example.com", UserRole.Customer_User);
        User user2 = new User("Petrov", "Petr", "Petrovich", "petrov@example.com", UserRole.Customer_User);
        List<User> userList = Arrays.asList(user1, user2);
        when(userRepository.getAllUsers()).thenReturn(userList);
        List<User> allUsers = userService.getAllUsers();
        assertEquals(userList, allUsers);
    }
}
