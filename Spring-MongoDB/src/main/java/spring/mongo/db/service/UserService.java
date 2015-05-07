package spring.mongo.db.service;

import spring.mongo.db.model.User;

public interface UserService {

	public User save(User user);

	public User get(String id);

	public User update(User user);

	public void delete(String id);

}