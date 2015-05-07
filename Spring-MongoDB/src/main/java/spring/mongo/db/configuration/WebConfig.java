package spring.mongo.db.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gson.Gson;

@WebAppConfiguration
@EnableWebMvc
@Import(AppConfig.class)
@ComponentScan({ "spring.mongo.db.dao", "spring.mongo.db.service",
		"spring.mongo.db.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

	public static final class Constants {

		public static final String MONGO_DB_PROPERTIES_LOCATION = "classpath:/config/mongo.db.properties";

	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

}