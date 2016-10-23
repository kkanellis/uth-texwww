package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

// Used for LDAP
import javax.naming.*;
import javax.naming.directory.*;

// import listener
import com.example.BeerSessionCounter;

public class BeerLogin extends HttpServlet {

	protected boolean doLogin(HttpServletRequest request,
								HttpServletResponse response) 
					throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Hashtable env = new Hashtable(11);
		boolean success = false;

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.uth.gr");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + ",ou=people,dc=uth,dc=gr");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			// Create initial context
			DirContext ctx = new InitialDirContext(env);

			// Close the context when we're done
			success = true;
			ctx.close();
		}
		catch (NamingException e) {
			return false;
		}
	
		return success;	
	}


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
		boolean success = doLogin(request, response); 
		
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
  



