package by.htp.ex.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.htp.ex.bean.User;
import by.htp.ex.service.UserService;
import by.htp.ex.service.ServiceException;

@RestController
@RequestMapping("/")
public class FrontController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> saveUsers(@RequestBody User user) {

		try {
			userService.saveUser(user);
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {

		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}

	}

}
