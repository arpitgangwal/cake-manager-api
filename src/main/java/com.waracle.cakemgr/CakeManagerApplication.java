package com.waracle.cakemgr;

import com.waracle.cakemgr.entity.UserEntity;
import com.waracle.cakemgr.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSecurity
@OpenAPIDefinition(info = @Info(title = "Cake Manager API", version = "v1"))
@SecurityScheme(name = "Authentication",
		description = "JWT token",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER)
public class CakeManagerApplication {
    @Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer configure(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry corsRegistry){
				corsRegistry.addMapping("/*").allowedOrigins("*");
			}
		};
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@PostConstruct
	void addUser(){
		List<UserEntity> users = Stream.of(
				new UserEntity( "user1", "pwd1", "user1@email.com"),
				new UserEntity( "user2", "pwd2", "user2@email.com"),
				new UserEntity( "user3", "pwd3", "user3@email.com")
		).collect(Collectors.toList());
		userRepository.saveAll(users);
	}
}
