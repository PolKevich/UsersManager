package by.htp.ex.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import by.htp.ex.bean.User;
import by.htp.ex.bean.UserRole;
import by.htp.ex.dao.UserDao;
import by.htp.ex.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class UserControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserService userService;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

	private CsrfToken csrfToken;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();
		csrfToken = csrfTokenRepository.generateToken(new MockHttpServletRequest());
	}

	@Test
	public void testAddUser() throws Exception {
		mockMvc.perform(post("/users").param("firstName", "John").param("lastName", "Doe")
				.param("email", "johndoe@example.com").with(csrf().tokenRepository(csrfTokenRepository))
				.with(user("admin").password("adminpass").roles("ADMIN"))).andExpect(status().isFound())
				.andExpect(redirectedUrl("/users"));

		User user = userService.getUserByEmail("johndoe@example.com");
		assertNotNull(user);
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
	}

	@Test
	public void testGetAllUsers() throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Doe");
		user1.setEmail("johndoe@example.com");
		userService.saveUser(user1);

		User user2 = new User();
		user2.setFirstName("Jane");
		user2.setLastName("Doe");
		user2.setEmail("janedoe@example.com");
		userService.saveUser(user2);

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].firstName", is("John"))).andExpect(jsonPath("$[0].lastName", is("Doe")))
				.andExpect(jsonPath("$[0].email", is("johndoe@example.com")))
				.andExpect(jsonPath("$[1].firstName", is("Jane"))).andExpect(jsonPath("$[1].lastName", is("Doe")))
				.andExpect(jsonPath("$[1].email", is("janedoe@example.com")));
	}
}
