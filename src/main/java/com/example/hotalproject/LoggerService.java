package com.example.hotalproject;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {

	@Getter
	private static final LoggerService instance = new LoggerService();

	private final Logger log = LoggerFactory.getLogger(LoggerService.class);

	private LoggerService() {
	}

	public void info(String message) {
		log.info(message);
	}

	public void error(String message) {
		log.error(message);
	}

	public void warn(String message) {
		log.warn(message);
	}
}