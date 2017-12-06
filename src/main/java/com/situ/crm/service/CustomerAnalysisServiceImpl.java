package com.situ.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerMapper;
import com.situ.crm.dao.CustomerServiceMapper;
import com.situ.crm.vo.CustomerConstitute;
import com.situ.crm.vo.CustomerService;

@Service
public class CustomerAnalysisServiceImpl implements ICustomerAnalysisService {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Override
	public ServerResponse findCustomerConstitute() {
		List<CustomerConstitute> list = customerMapper.findCustomerConstitute() ;
		return ServerResponse.createSuccess("ok", list);
	}

	@Override
	public EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows,
			CustomerConstitute customerConstitute) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNoneBlank(customerConstitute.getCustomerName())) {
			map.put("customerName", customerConstitute.getCustomerName());
		}
		List<CustomerConstitute> list = customerMapper.findCustomerContribute(map);
		//total
		PageInfo<CustomerConstitute> pageInfo = new PageInfo<>(list);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public ServerResponse findCustomerService() {
		List<CustomerService>list = customerServiceMapper.findCustomerService();
		return ServerResponse.createSuccess("ok", list);
	}



}
