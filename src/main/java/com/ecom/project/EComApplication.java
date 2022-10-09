package com.ecom.project;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom.project.models.Role;
import com.ecom.project.respositories.RoleRepository;

@SpringBootApplication
public class EComApplication implements CommandLineRunner {
	 
	@Autowired
	private RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(EComApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

        try {
            Role role1 = new Role();
            role1.setId(1234);
            role1.setName("ROLE_ADMIN");

            Role role2 = new Role();
            role2.setId(2345);
            role2.setName("ROLE_NORMAL");

            Role role3 = new Role();
            role3.setId(3456);
            role3.setName("ROLE_STAFF");

            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roleRepo.saveAll(roles);

        } catch (Exception e) {
            System.out.println("User already exists !!");
            e.printStackTrace();
        }
		
	}

}
