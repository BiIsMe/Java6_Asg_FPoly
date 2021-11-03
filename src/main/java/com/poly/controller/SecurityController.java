package com.poly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.SessionService;

@Controller
public class SecurityController {
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/security/login/form")
	public String form() {
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String success(Model model,HttpServletRequest request) {
		String name = request.getRemoteUser();
		session.set("username", name);
		model.addAttribute("message","Login Successfully");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("security/login/error")
	public String error(Model model) {
		model.addAttribute("message","Login fail");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("security/logoff/success")
	public String logout(Model model) {
		model.addAttribute("message","Log out");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("security/access/denied")
	public String denied(Model model) {
		model.addAttribute("message","Access Denied");
		return "security/login";
	}
	
}
