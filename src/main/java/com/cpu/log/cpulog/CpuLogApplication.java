package com.cpu.log.cpulog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CpuLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpuLogApplication.class, args);
	}

}
