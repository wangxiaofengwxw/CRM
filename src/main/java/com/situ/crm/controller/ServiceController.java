package com.situ.crm.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.service.IServiceService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private IServiceService serviceService;
	
	@RequestMapping("/add")
	public String index() {
		return "service_add";
	}
	
	@RequestMapping("/allot")
	public String allot() {
		return "service_allot";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService) throws UnsupportedEncodingException {//这些数据都是EasyUI封装好的
		return serviceService.findAll(page, rows, customerService);
	}
	
}
