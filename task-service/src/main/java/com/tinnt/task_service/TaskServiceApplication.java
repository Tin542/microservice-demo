package com.tinnt.task_service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class TaskServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceApplication.class);
	public static void main(String[] args) {
		logger.info("<<<<<< INFO LOG <<<<<<");
		logger.debug("<<<<<< DEBUG LOG <<<<<<");
		logger.warn("<<<<<< WARN LOG <<<<<<");
		logger.error("<<<<<< ERROR LOG <<<<<<");
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
