package spring.mongo.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import spring.mongo.db.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private MongoTemplate template;

	@Override
	public User save(User user) {
		template.save(user);
		return get(user.getId());
	}

	@Override
	public User get(String userId) {
		Query query = new Query(Criteria.where("userId").is(userId));
		return template.findOne(query, User.class);
	}

	@Override
	public User update(User user) {
		Query query = new Query(Criteria.where("userId").is(user.getId()));
		template.updateMulti(query, new Update(), User.class);
		return get(user.getId());
	}

	@Override
	public void delete(String userId) {
		template.remove(get(userId));
	}

}