package spring.mongo.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mongo.db.dao.UserDao;
import spring.mongo.db.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		userDao.save(user);
		return get(user.getId());
	}

	@Override
	public User get(String userId) {
		return userDao.get(userId);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void delete(String userId) {
		userDao.delete(userId);
	}

}
