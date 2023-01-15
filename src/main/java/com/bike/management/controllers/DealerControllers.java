package com.bike.management.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bike.management.configurations.GlobalConstants;
import com.bike.management.models.Dealer;
import com.bike.management.services.DealerService;

@Controller
public class DealerControllers {
	
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping("/dealer/create")
	public ModelAndView createDealer(ModelAndView mv, @ModelAttribute("dealer") Dealer dealer, HttpSession session) {
		
		dealerService.createDealer(dealer);
		mv.addObject("msg", GlobalConstants.SUCCESS_MSG);
		return mv;
		
	}
	
	@RequestMapping("/dealer/edit")
	public ModelAndView editDealer(ModelAndView mv,HttpServletRequest req,HttpSession session) {
		mv.addObject("dealer", dealerService.findDealer(Long.valueOf(req.getParameter("dealer_id"))));
		return mv;
		
	}

	@RequestMapping("/dealer/update")
	public ModelAndView updateDealer(ModelAndView mv,@ModelAttribute("dealer") Dealer dealer,HttpSession session) {
		dealerService.updateDealer(dealer);
		mv.addObject("msg", GlobalConstants.UPDATE_MSG);
		return mv;
		
	}
	
	@RequestMapping("/dealer/delete")
	public ModelAndView cancelDealer(ModelAndView mv,HttpServletRequest req,HttpSession session) {
		dealerService.cancelDealer(Long.valueOf(req.getParameter("dealer_id")));
		mv.addObject("msg",GlobalConstants.DELETE_MSG);
		return mv;
		
	}
	
	@RequestMapping("/dealer/all")
	public ModelAndView getDealers(ModelAndView mv, HttpSession session) {
		
		mv.addObject("dealers", dealerService.getDealers());
		return mv;
	}
}
