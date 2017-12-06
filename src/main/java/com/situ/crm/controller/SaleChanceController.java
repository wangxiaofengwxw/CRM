package com.situ.crm.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.User;
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
	
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) {
		try {
      		response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			saleChanceService.exportExcel(outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@RequestMapping("/inputExcel")
	@ResponseBody
	public ServerResponse inputExcel(MultipartFile file) {
		//C:\fakepath\新建文本文档.txt
		try {
			 //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream inputStream=file.getInputStream();
            System.out.println(inputStream);
            System.out.println(file.getOriginalFilename());
			if(file.getOriginalFilename().indexOf(".xls")>0){
				//读取工作薄
				HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
				//读取工作表
				HSSFSheet sheet = workbook.getSheetAt(0);
				//读取行
				HSSFRow row = sheet.getRow(0);
				//读取单元格
				User user = new User();
				//读取单元格
				for (int i = 0; i < 6; i++) {
					HSSFCell cell = row.getCell(i);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
					String value = cell.getStringCellValue();
					if(i == 0){
						user.setName(value);
					}else if (i == 1) {
						user.setPassword(value);
					}else if (i == 2) {
						user.setTrueName(value);
					}else if (i == 3) {
						user.setPhone(value);
					}else if (i == 4) {
						user.setEmail(value);
					}else {
						user.setRoleName(value);
					}
				}
				workbook.close();
				inputStream.close();
				return userService.addUser(user);
			}else {
				return ServerResponse.createError("请选择正确的文件");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
