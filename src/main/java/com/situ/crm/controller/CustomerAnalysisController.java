package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ICustomerAnalysisService;
import com.situ.crm.vo.CustomerConstitute;

@Controller
@RequestMapping("/analysis")
public class CustomerAnalysisController {
	
	@Autowired
	private ICustomerAnalysisService customerAnalysisService;
	/**
	 * 转到客户贡献分析
	 * @return
	 */
	@RequestMapping("/contribute")
	public String contribute() {
		return "contribute_analysis";
	}
	/**
	 * 转到客户构成分析
	 * @return
	 */
	@RequestMapping("/constitute")
	public String constitute() {
		return "constitute_analysis";
	}
	
	@RequestMapping("/service")
	public String service() {
		return "constitute_service";
	}
	
	@RequestMapping("/findCustomerConstitute")
	@ResponseBody
	public ServerResponse findCustomerConstitute() {
		return customerAnalysisService.findCustomerConstitute();
	}
	
	@RequestMapping("/findCustomerContribute")
	@ResponseBody
	public EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows, CustomerConstitute customerConstitute) {
		return customerAnalysisService.findCustomerContribute(page, rows, customerConstitute);
	}
	
	@RequestMapping("/findCustomerService")
	@ResponseBody
	public ServerResponse findCustomerService() {
		return customerAnalysisService.findCustomerService();
	}
	
}
