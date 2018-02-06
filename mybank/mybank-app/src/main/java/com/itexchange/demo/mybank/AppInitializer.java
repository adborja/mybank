package com.itexchange.demo.mybank;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInitializer {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication app = new SpringApplication(AppInitializer.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}