package spring.mongo.db.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;

import com.mongodb.Mongo;

@Configuration
@PropertySource(WebConfig.Constants.MONGO_DB_PROPERTIES_LOCATION)
public class AppConfig {

	@Autowired
	public Environment environment;

	Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	@SuppressWarnings("deprecation")
	public MongoDbFactory getMongoDbFactory() throws Exception {

		UserCredentials userCredentials = new UserCredentials(
				environment.getProperty("spring.data.mongodb.username"),
				environment.getProperty("spring.data.mongodb.password"));

		return new SimpleMongoDbFactory(new Mongo(
				environment.getProperty("spring.data.mongodb.host"),
				Integer.valueOf(environment
						.getProperty("spring.data.mongodb.port"))),
				environment.getProperty("spring.data.mongodb.database"),
				userCredentials);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
		mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		return mongoTemplate;
	}

}