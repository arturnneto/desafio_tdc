package com.artur.callforpapers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.artur.callforpapers")
public class CallForPapers {

	public static void main(String[] args) {
		SpringApplication.run(CallForPapers.class, args);
	}

}
