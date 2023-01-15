package com.bike.management.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.bike.management.configurations.GlobalConstants;
import com.bike.management.helpers.BikeHelper;
import com.bike.management.helpers.SessionManager;
import com.bike.management.models.Bike;
import com.bike.management.models.Dealer;
import com.bike.management.services.BikeService;
import com.bike.management.services.DealerService;

@Controller
@RequestMapping("/")
public class ApiControlers {
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private BikeService bikeService;
	
	@RequestMapping("/")
	public String loginPage() {
		
		return "index";
	}
	
	@PostMapping("/login")
	public ModelAndView validateLogin(ModelAndView model,HttpServletRequest req, HttpSession session) throws Exception {
		Dealer dealer = dealerService.validateDealer(req.getParameter("email"), req.getParameter("pwd"));
		SessionManager.createSession(session,req);
		model.setViewName("home/dashboard");
		model.addObject("name", req.getParameter("email"));
	    return model;	
	}
	
	@GetMapping("/home")
	public String getHelp(ModelAndView mv, HttpSession session) {
		SessionManager.isValidAuthentication(session);
		return "home/home";
	}

	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		SessionManager.removeAuthentication(session);
		mv.addObject("errMsg", "Logout Successfully");
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView bikeRegister(ModelAndView mv,HttpSession session)
	{
		SessionManager.isValidAuthentication(session);
		mv.addObject("bike", new Bike());
		mv.addObject("bikeTypes", bikeService.getBikeTypes());
		mv.setViewName("forms/bikeRegister.html");
		return mv;
	}
	
	@PostMapping("/addProduct")
	public ModelAndView addProduct(ModelAndView mv,@RequestPart("img") MultipartFile multipartFile, @Valid @ModelAttribute("bike") Bike bike,BindingResult result, HttpSession session) throws IOException
	{
		SessionManager.isValidAuthentication(session);
		System.out.println(result.getFieldErrors());
		if(result.hasErrors()) {
			mv.addObject("errMsg", GlobalConstants.INAVLID_FIELD);
			mv.addObject("bike", bike);
			mv.addObject("bikeTypes", bikeService.getBikeTypes());
			mv.setViewName("forms/bikeRegister.html");
			return mv;
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		bike.setBikeImg(fileName);
		Bike savedBike = bikeService.createBike(bike);
		BikeHelper.saveProductImage(multipartFile, savedBike.getId(),fileName);
		mv.addObject("bikePreview", bikeService.getOneBike(savedBike.getId()));
		mv.setViewName("forms/bike_preview.html");
		return mv;
	}
	
	@GetMapping("/bikes/{bikeId}")
	public ModelAndView getBikesByBikeId(ModelAndView mv, @PathVariable(value="bikeId",required=true) int bikeId,HttpSession session)
	{
		SessionManager.isValidAuthentication(session);
		List<Bike> bikeModels = bikeService.getBikes(bikeId);
		mv.addObject("bikes", bikeModels);
		mv.setViewName("bikes/bikes.html");
		return mv;
	}
	
	@GetMapping("/bikePreview")
	public ModelAndView previewBike(ModelAndView mv, HttpServletRequest req, HttpSession session)
	{
		SessionManager.isValidAuthentication(session);
		mv.addObject("bikePreview", bikeService.getOneBike(Integer.valueOf( req.getParameter("bikeId"))));
		mv.setViewName("forms/bike_preview.html");
		return mv;
	}
	
	@PostMapping("/shareBike")
	public ModelAndView shareBike(ModelAndView mv, @RequestParam("shareId") String shareId, HttpSession session)
	{
		SessionManager.isValidAuthentication(session);
		mv.addObject("success",bikeService.shareBikeToCustomers(shareId));
		mv.setViewName("home/dashboard");
		return mv;
	}
	
	@GetMapping("/search")
	public ModelAndView searchBike(ModelAndView mv, @RequestParam(value="bikeName",required=true)  String bikeName,HttpSession session)
	{
		SessionManager.isValidAuthentication(session);
		List<Bike> bikeModels = bikeService.searchBike(bikeName);
		mv.addObject("bikes", bikeModels);
		mv.setViewName("bikes/bikes.html");
		return mv;
	}
	
}
