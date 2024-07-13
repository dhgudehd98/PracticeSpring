package com.sh.loginbyrestcontroller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class LoginByRestControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginByRestControllerApplication.class, args);
	}

}
