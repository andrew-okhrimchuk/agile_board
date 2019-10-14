package org.application;

import org.application.entity.User;
import org.application.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(excludeName ={
		"org.application.container",
		"org.application.entity",
		"org.application.DAO",
		"org.application.util"})
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
			System.out.println(user.getUsername());
			this.myUserDetailsService.save(user);
		}
		else System.out.println(user.getUsername() + "ggg");

	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[]{AgileBoardApplication.class});
	}
}


