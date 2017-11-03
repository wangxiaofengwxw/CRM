package com.situ.crm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.service.ICustomerLossService;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController {

	@Autowired
	private ICustomerLossService customerLossService;
	
	@RequestMapping("/index")
	public String index() {
		return "customer_loss";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerLoss customerLoss) throws UnsupportedEncodingException {//这些数据都是EasyUI封装好的
		return customerLossService.findAll(page, rows, customerLoss);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerLossService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerLoss customerLoss) {
		return customerLossService.add(customerLoss);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerLoss customerLoss) {
		return customerLossService.update(customerLoss);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ServerResponse findById(Integer customerId) {
		return customerLossService.findById(customerId);
	}
	
	@RequestMapping("/toLossMeasure")
	public String toLossMeasure() {
		return "loss_measure";
	}
	
}
