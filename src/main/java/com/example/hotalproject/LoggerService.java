package com.example.hotalproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {

	private static LoggerService instance;

	private LoggerService() {
	}

	public static synchronized LoggerService   getInstance() {
		if (instance == null) {
			instance = new LoggerService();
		}
		return instance;
	}

	public void info(String message) {
		log("INFO", message);
	}

	public void warn(String message) {
		log("WARN", message);
	}

	public void error(String message) {
		log("ERROR", message);
	}

	private void log(String level, String message) {
		String time = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		System.out.println("[" + time + "] [" + level + "] " + message);
	}
}