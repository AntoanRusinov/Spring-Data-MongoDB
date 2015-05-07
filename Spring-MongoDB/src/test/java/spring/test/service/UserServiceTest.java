package spring.test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import spring.mongo.db.model.User;
import spring.mongo.db.service.UserService;
import spring.test.base.BaseTest;

public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	private static final String userId = UUID.randomUUID().toString();

	@Test
	public void createAndGet() throws Exception {
		User user = new User();
		user.setId(userId);
		user.setGender("MALE");
		user.setPassword("pass");
		user.setUserName("userNAME");
		userService.save(user);

		User created = userService.get(userId);

		Assert.assertNotNull(created);
	}

	@Test(dependsOnMethods = { "createAndGet" })
	public void getUserById() throws Exception {
		User user = userService.get(userId);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getPassword(), "pass");
	}

	@Test(dependsOnMethods = { "getUserById" })
	public void update() throws Exception {
		User userToUpdate = userService.get(userId);
		Assert.assertNotNull(userToUpdate);
		userToUpdate.setPassword("updated");
		User updatedUser = userService.update(userToUpdate);
		Assert.assertNotNull(updatedUser);
		Assert.assertEquals(updatedUser.getPassword(), "updated");
	}

	@Test(dependsOnMethods = { "update" })
	public void deleteById() throws Exception {
		userService.delete(userId);
	}

}