package spring.mongo.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.mongo.db.model.User;
import spring.mongo.db.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public User save(User user) {
		return userService.save(user);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User get(String userId) {
		return userService.get(userId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public User update(User user) {
		return userService.update(user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(String userId) {
		userService.delete(userId);
	}

}