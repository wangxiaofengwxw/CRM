package com.situ.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerServiceMapper;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.pojo.CustomerServiceExample;
import com.situ.crm.pojo.CustomerServiceExample.Criteria;
import com.situ.crm.util.Util;

@Service
public class ServiceServiceImpl implements IServiceService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerServiceExample customerServiceExample = new CustomerServiceExample();
		//设置分页，第三方插件，
		PageHelper.startPage(page, rows);
		//判断是否有条件，如果有，添加。
		//并且加了分页
		//有几个条件就加几个判断
		Criteria createCriteria = customerServiceExample.createCriteria();
		if (StringUtils.isNotEmpty(customerService.getStatus())) {
			createCriteria.andStatusLike(Util.formatLike(customerService.getStatus()));
		}
		
		List<CustomerService> customerServiceList = customerServiceMapper.selectByExample(customerServiceExample);
		//total
		PageInfo<CustomerService> pageInfo = new PageInfo<>(customerServiceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerServiceList);
		return result;
	}

	
}
