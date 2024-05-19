package com.gmail._4rl1996.wstesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class WsTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsTestTaskApplication.class, args);
	}

}
