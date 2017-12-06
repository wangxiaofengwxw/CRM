package com.situ.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.SaleChanceMapper;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.SaleChanceExample;
import com.situ.crm.pojo.SaleChanceExample.Criteria;
import com.situ.crm.util.JsonDateValueProcessor;
import com.situ.crm.util.SwitchingTime;
import com.situ.crm.util.Util;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service
public class SaleChanceServiceImpl implements ISaleChanceService{
	@Autowired
	private SaleChanceMapper saleChanceMapper;
	
	@InitBinder    
    protected void initBinder(HttpServletRequest request,  
        ServletRequestDataBinder binder) throws Exception {  
        binder.registerCustomEditor(Date.class,   
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
    }  

	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance, String beginTime, String endTime) {
		
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		SaleChanceExample saleChanceExample = new SaleChanceExample();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = saleChanceExample.createCriteria();
		if (StringUtils.isNotEmpty(saleChance.getCustomerName())) {
			createCriteria.andCustomerNameLike(Util.formatLike(saleChance.getCustomerName()));
		}
		if (StringUtils.isNotEmpty(saleChance.getOverview())) {
			createCriteria.andOverviewLike(Util.formatLike(saleChance.getOverview()));
		}
		if (StringUtils.isNotEmpty(saleChance.getCreateMan())) {
			createCriteria.andCreateManLike(Util.formatLike(saleChance.getCreateMan()));
		}
		if (saleChance.getStatus() != null && saleChance.getStatus() != -1) {
			createCriteria.andStatusEqualTo(saleChance.getStatus());
		}
		if (StringUtils.isNotEmpty(beginTime)) {
			createCriteria.andCreateTimeGreaterThan(SwitchingTime.toSwitchingTime(beginTime));
		}if (StringUtils.isNotEmpty(endTime)) {
			createCriteria.andCreateTimeLessThan(SwitchingTime.toSwitchingTime(endTime));
		}
		/*'customer_name':$("#customer_name").val(),
		'overview':$("#overview").val(),
		'createMan':$("#createMan").val(),
		'status':$("#status").val()*/
		List<SaleChance> saleChanceList = saleChanceMapper.selectByExample(saleChanceExample);
		//total
		PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(saleChanceList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(SaleChance saleChance) {
		if(StringUtils.isNotEmpty(saleChance.getAssignMan())){
			saleChance.setStatus(1);//已分配
		}else{
			saleChance.setStatus(0);//0：:未分配
		}
		saleChance.setDevResult(0);
		if (saleChanceMapper.insert(saleChance) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	@Override
	public ServerResponse update(SaleChance saleChance) {
		if (saleChanceMapper.updateByPrimaryKey(saleChance) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
		}
		return ServerResponse.createError("修改失败!");
	}

	@Override
	public List<SaleChance> findStatus() {
		return saleChanceMapper.findStatus();
	}

	@Override
	public ServerResponse findById(Integer id) {
		SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
		if(saleChance == null) {
			return ServerResponse.createError();
		}
		
		return ServerResponse.createSuccess("ok", saleChance);
	}

	@Override
	public ServerResponse updateDevResult(Integer saleChanceId, Integer devResult) {
		SaleChance saleChance = new SaleChance();
		saleChance.setId(saleChanceId);
		saleChance.setDevResult(devResult);
		if (saleChanceMapper.updateByPrimaryKeySelective(saleChance) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}
		return ServerResponse.createError("更新失败");
	}

	@Override
	public void exportExcel(ServletOutputStream outputStream) {
		List<SaleChance> list = saleChanceMapper.selectByExample(new SaleChanceExample());
		try {
			// 1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);// 起始行号，结束行号，起始列号，结束列号

			// 1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);

			// 1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);
			
			HSSFCellStyle cellStyle = workbook.createCellStyle();

            HSSFDataFormat format= workbook.createDataFormat();

            cellStyle.setDataFormat(format.getFormat("yyyy年m月d日"));

           

			// 2、创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			// 2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);

			// 3、创建行
			// 3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			// 加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");

			// 3.2、创建列标题行；并且设置列标题
			HSSFRow rowHead = sheet.createRow(1);
			String[] titles = { "编号", "客户名称", "概要", "联系人", "练习电话","创建人","创建时间","状态" };
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = rowHead.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			HSSFCell cell = rowHead.createCell(titles.length - 2);
			 cell.setCellStyle(cellStyle);
			// 4、操作单元格；将用户列表写入excel
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					HSSFRow row = sheet.createRow(j + 2);
					HSSFCell cellId = row.createCell(0);
					cellId.setCellValue(list.get(j).getId());
					HSSFCell cellName = row.createCell(1);
					cellName.setCellValue(list.get(j).getCustomerName());
					HSSFCell cellOverview = row.createCell(2);
					cellOverview.setCellValue(list.get(j).getOverview());
					HSSFCell cell14 = row.createCell(3);
					cell14.setCellValue(list.get(j).getLinkMan());
					HSSFCell cellLinkPhone = row.createCell(4);
					cellLinkPhone.setCellValue(list.get(j).getLinkPhone());
					HSSFCell cellCreateMan = row.createCell(5);
					cellCreateMan.setCellValue(list.get(j).getCreateMan());
					HSSFCell cellCreateTime = row.createCell(6);
					if (list.get(j).getCreateTime() != null) {
						cellCreateTime.setCellValue(list.get(j).getCreateTime());
					} else {
						cellCreateTime.setCellValue("");
					}
					HSSFCell cellStatus = row.createCell(7);
					if (list.get(j).getStatus() != null && list.get(j).getStatus() == 1) {
						cellStatus.setCellValue("已分配");
					} else {
						cellStatus.setCellValue("未分配");
					}
				}
			}
			// 5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗字体
		font.setFontHeightInPoints(fontSize);
		// 加载字体
		style.setFont(font);
		return style;
	}

}
