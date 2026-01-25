package com.tinnt.finance_service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class FinanceServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(FinanceServiceApplication.class);
	public static void main(String[] args) {
		logger.info("<<<<<< INFO LOG <<<<<<");
		logger.debug("<<<<<< DEBUG LOG <<<<<<");
		logger.warn("<<<<<< WARN LOG <<<<<<");
		logger.error("<<<<<< ERROR LOG <<<<<<");
		SpringApplication.run(FinanceServiceApplication.class, args);
	}

}
