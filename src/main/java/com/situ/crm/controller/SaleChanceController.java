package com.situ.crm.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.util.SwitchingTime;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {

	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}
	
	@Autowired
	private ISaleChanceService saleChanceService;
	
	@RequestMapping("/index")
	public String index() {
		return "sale_chance_manager";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance, String beginTime, String endTime) throws UnsupportedEncodingException {//这些数据都是EasyUI封装好的
		System.out.println(beginTime);
		return saleChanceService.findAll(page, rows, saleChance, beginTime,endTime);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return saleChanceService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(SaleChance saleChance) {
	/*	Date CTime = SwitchingTime.toSwitchingTime(createTime);
		Date ATime = SwitchingTime.toSwitchingTime(assignTime);
		saleChance.setCreateTime(CTime);
		saleChance.setAssignTime(ATime);
		saleChance.setSuccessRate(11);
		saleChance.setStatus(0);
		saleChance.setDevResult(0);
		System.out.println(saleChance);*/
		return saleChanceService.add(saleChance);
	}
	
	/*@RequestMapping("/add")
	@ResponseBody
	public String add(String createTime, String assignTime) {
		System.out.println(createTime);
		System.out.println(assignTime);
		return null;
	}*/
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(SaleChance saleChance) {
		return saleChanceService.update(saleChance);
	}
	
	@RequestMapping("/findStatus")
	@ResponseBody
	public List<SaleChance> findStatus() {
		List<SaleChance> list = saleChanceService.findStatus();
		return list;
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ServerResponse findById(Integer id) {
		return saleChanceService.findById(id);
	}
	
	@RequestMapping("/updateDevResult")
	@ResponseBody
	public ServerResponse updateDevResult(Integer saleChanceId, Integer devResult) {
		return saleChanceService.updateDevResult(saleChanceId, devResult);
	}
	
	@RequestMapping("/cusDevPlan")
	public String cusDevPlan() {
		return "cus_dev_plan_manager";
	}
	
}
