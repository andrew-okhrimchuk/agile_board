package org.disturbed75.application.config;

import org.disturbed75.application.entity.User;
import org.disturbed75.application.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"org.disturbed75.application.*"})
@EnableAutoConfiguration (exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class AgileBoardApplication extends SpringBootServletInitializer implements CommandLineRunner {


	@Autowired
	private MyUserDetailsService myUserDetailsService;

	public AgileBoardApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(AgileBoardApplication.class, args);
	}

	public void run(String... strings) {
		User user = this.myUserDetailsService.findByUsername("admin");
		if (user == null) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			this.myUserDetailsService.save(user);
		}

	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[]{AgileBoardApplication.class});
	}
}


