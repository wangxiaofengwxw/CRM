package com.situ.crm.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.User;
import com.situ.crm.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@RequestMapping("/updatePassword")
	public String updatePassword() {
		return "update_password";
	}
	
	@RequestMapping("/toUpdatePassword")
	@ResponseBody
	public ServerResponse toUpdatePassword(User user) {
		return adminService.toUpdatePassword(user);
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/toLogin")
	@ResponseBody
	public ServerResponse toLogin(User user,HttpServletRequest req) {
		return adminService.toLogin(user,req);
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("admin");
		if(user != null) {
			session.removeAttribute("admin");
		}
		return "index";
	}
}
