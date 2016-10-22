package com.example;

import javax.servlet.*;
import javax.servlet.http.*;

public class BeerSessionCounter implements HttpSessionListener {
	private static int activeSessions;
	
	public static int getSessions() {
		return activeSessions;
	}	

	public void sessionCreated(HttpSessionEvent event) {
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		activeSessions--;
	}
}
