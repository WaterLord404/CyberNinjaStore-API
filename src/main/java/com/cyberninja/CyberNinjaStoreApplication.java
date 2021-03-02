package com.cyberninja;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cyberninja.model.entity.dto.CustomerDTO;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.model.entity.enun.UserRole;
import com.cyberninja.security.model.repository.UserRepository;
import com.cyberninja.security.services.UserServiceI;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = {"com.cyberninja"})
public class CyberNinjaStoreApplication implements CommandLineRunner{

	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CyberNinjaStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepo.findUserByUsername("admin") == null) {
			
			UserDTO admin = new UserDTO();
			admin.setUsername("admin");
			admin.setPassword("admin");
			
			CustomerDTO adminX = new CustomerDTO();
			adminX.setName("");
			adminX.setSurname("");
			adminX.setEmail("");
			adminX.setTelephone("");
			adminX.setRegion("");
			adminX.setProvince("");
			adminX.setLocality("");
			adminX.setPostalCode("");
			adminX.setAddress("");
			
			admin.setCustomer(adminX);
			userService.createUser(admin);
			
			User user = userRepo.findByUsername("admin").get();
			user.setRoles(Set.of(UserRole.ADMIN));
			userRepo.save(user);
		}
	}

}
