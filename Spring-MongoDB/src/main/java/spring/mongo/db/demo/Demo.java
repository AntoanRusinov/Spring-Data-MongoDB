package spring.mongo.db.demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import spring.mongo.db.configuration.AppConfig;
import spring.mongo.db.model.User;

public class Demo {

	private static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	public static void main(String[] args) {

		MongoTemplate template = (MongoTemplate) context.getBean("mongoTemplate");

		User user = new User();
		user.setGender("male");
		user.setUserName("first_user_123");
		user.setPassword("password_test");

		// save
		template.save(user);
		
		// now user object got the created id.
		System.err.println("1. User : " + user);

		// query to search user
		Query searchUserQuery = new Query(Criteria.where("userName").is(
				"first_user_123"));

		// find the saved user again.
		User savedUser = template.findOne(searchUserQuery, User.class);
		System.err.println("2. Found saved user: " + savedUser);

		// update password
		template.updateFirst(searchUserQuery, Update.update("password", "new password"), User.class);

		// find the updated user object
		User updatedUser = template.findOne(searchUserQuery, User.class);
		System.err.println("3. Updated User : " + updatedUser);

		// delete
		Query deleteQuery = new Query(Criteria.where("password").is("new password"));
		template.remove(deleteQuery, User.class);

		List<User> listUser = template.findAll(User.class);
		System.err.println("4. Number of users = " + listUser.size());
	}

}