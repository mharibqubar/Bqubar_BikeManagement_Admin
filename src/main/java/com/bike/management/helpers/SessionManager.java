package com.bike.management.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionException;
import org.springframework.stereotype.Component;

import com.bike.management.configurations.GlobalConstants;
import com.bike.management.exceptions.SessionExpiredException;


@Component
public class SessionManager {
	
	public static void createSession(HttpSession session,HttpServletRequest req) {
    
		try {
			session.setAttribute("emailid", req.getParameter("email"));
		}catch(Exception e) {
			System.out.println(e);
		}
    }

	public static void isValidAuthentication(HttpSession session) {
		if(session == null || session.getAttribute("emailid") == null || session.getAttribute("emailid").equals("")) {
			throw new SessionExpiredException(GlobalConstants.UNAUTHERIZED_ACCESS);
		}
	}

	public static void removeAuthentication(HttpSession session) {
		session.removeAttribute("emailid");
	}

}
