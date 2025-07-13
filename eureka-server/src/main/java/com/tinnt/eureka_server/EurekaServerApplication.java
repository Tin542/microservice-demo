package com.tinnt.eureka_server;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {
	private static final Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);
	public static void main(String[] args) {
		logger.info("<<<<<< INFO LOG <<<<<<");
		logger.debug("<<<<<< DEBUG LOG <<<<<<");
		logger.warn("<<<<<< WARN LOG <<<<<<");
		logger.error("<<<<<< ERROR LOG <<<<<<");
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
