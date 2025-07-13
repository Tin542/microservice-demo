package com.tinnt.api_gateway;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@Slf4j
@SpringBootApplication
public class ApiGatewayApplication {
	private static final Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);

	public static void main(String[] args) {
		logger.info("<<<<<< INFO LOG <<<<<<");
		logger.debug("<<<<<< DEBUG LOG <<<<<<");
		logger.warn("<<<<<< WARN LOG <<<<<<");
		logger.error("<<<<<< ERROR LOG <<<<<<");
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
