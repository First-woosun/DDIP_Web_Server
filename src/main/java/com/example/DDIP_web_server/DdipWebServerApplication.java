package com.example.DDIP_web_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 스케줄링 활성화
public class DdipWebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdipWebServerApplication.class, args);
	}

}


