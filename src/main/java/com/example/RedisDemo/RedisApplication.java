package com.example.RedisDemo;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RedisApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new RedisApplication().configure(new SpringApplicationBuilder(RedisApplication.class)).run(args);
	}

}
