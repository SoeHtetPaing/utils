package com.genius.utils;

import com.genius.utils.lib.MediaHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UtilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilsApplication.class, args);
		System.out.println(MediaHandler.getServerPath());

	}

}
