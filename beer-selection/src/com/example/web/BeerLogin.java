package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

// import listener
import com.example.BeerSessionCounter;

public class BeerLogin extends HttpServlet {

	public void doGet(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException, ServletException {
		
		// Get active sessions from listener	
		int activeSessions = BeerSessionCounter.getSessions();
		request.setAttribute("activeSessions", activeSessions);		

		// Dispatch to JSP
		RequestDispatcher view = 
			request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	} 

    public void doPost(HttpServletRequest request,
                        HttpServletResponse response)
			throws IOException, ServletException {
        
		// Check if login is successfull
		boolean success = true;
		
		if (success) {
			// redirect to form.html
			response.sendRedirect("form.html");
		}
		else {
			// try again!
			this.doGet(request, response);
		}
    }

}
  

