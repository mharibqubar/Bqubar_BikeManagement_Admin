package com.bike.management.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bike.management.helpers.SessionManager;
import com.bike.management.services.DealerService;

@Controller
@RequestMapping("/bqubar")
public class PagesController {
	
	@Autowired
	private DealerService dealerService;

	@RequestMapping("/about")
	public ModelAndView aboutUs(ModelAndView mv, HttpSession session) {
		SessionManager.isValidAuthentication(session);
		mv.setViewName("bqubar/aboutus.html");
		return mv;
	}
	
	@RequestMapping("/dealers")
	public ModelAndView viewDealers(ModelAndView mv,HttpServletRequest req, HttpSession session) {
		SessionManager.isValidAuthentication(session);
		mv.setViewName("bqubar/dealers_view.html");
		return mv;
	}
}
