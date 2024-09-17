package com.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.models.User;
import com.learn.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityLearncodewithdurgeshApplication implements CommandLineRunner{

	@Autowired
	UserRepository userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLearncodewithdurgeshApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User user = new User();
		user.setEmail("JOHN@GMAIL.COM");
		user.setUsername("john");
		user.setPassword(passwordEncoder.encode("durgesh"));
		user.setRole("ROLE_NORMAL");
		
		userrepo.save(user);
		
		
		User user1 = new User();
		user1.setEmail("YASH@GMAIL.COM");
		user1.setUsername("yash");
		user1.setPassword(passwordEncoder.encode("yash"));
		user1.setRole("ROLE_ADMIN");
	
		userrepo.save(user1);
		
	}
	
	
}
