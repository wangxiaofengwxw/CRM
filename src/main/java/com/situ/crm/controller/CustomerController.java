package com.situ.crm.controller;

import org.apache.taglibs.standard.lang.jstl.ModulusOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;
import com.situ.crm.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/index")
	public String customerIndex() {
		return "customer_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, Customer customer) {
		System.out.println(customer);
		return customerService.findAll(page, rows, customer);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Customer customer) {
		return customerService.addCustomer(customer);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Customer customer) {
		return customerService.update(customer);
	}
	
	@RequestMapping("/linkMan")
	public ModelAndView linkMan(String customerIds) {
		int customerId = Integer.parseInt(customerIds);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customerId", customerId);
		modelAndView.setViewName("link_man");
		return modelAndView;
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ServerResponse findById(Integer customerId) {
		System.out.println(customerId);
		return customerService.findById(customerId);
	}
	
}
