package edu.kh.checkMember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CheckMemberProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckMemberProjectApplication.class, args);
	}

}
