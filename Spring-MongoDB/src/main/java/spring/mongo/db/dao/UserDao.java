package spring.mongo.db.dao;

import spring.mongo.db.model.User;

public interface UserDao {

	public User save(User user);

	public User get(String id);

	public User update(User user);

	public void delete(String id);

}