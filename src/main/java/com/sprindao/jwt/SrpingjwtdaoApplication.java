package com.sprindao.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SrpingjwtdaoApplication {


	public static void main(String[] args) {


		SpringApplication.run(SrpingjwtdaoApplication.class, args);
	}

}
