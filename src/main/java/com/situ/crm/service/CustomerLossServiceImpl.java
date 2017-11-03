package com.situ.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerLossMapper;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.pojo.CustomerLossExample;
import com.situ.crm.pojo.CustomerLossExample.Criteria;
import com.situ.crm.util.Util;

@Service
public class CustomerLossServiceImpl implements ICustomerLossService {

	@Autowired
	private CustomerLossMapper customerLossMapper;
	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerLoss customerLoss) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLossExample customerLossExample = new CustomerLossExample();
		//设置分页，第三方插件，
		PageHelper.startPage(page, rows);
		//判断是否有条件，如果有，添加。
		//并且加了分页
		//有几个条件就加几个判断
		Criteria createCriteria = customerLossExample.createCriteria();
		/*if (StringUtils.isNotEmpty(customerLoss.getName())) {
			createCriteria.andNameLike(Util.formatLike(customerLoss.getName()));
		}
		if (StringUtils.isNotEmpty(customerLoss.getTrueName())) {
			createCriteria.andTrueNameLike(Util.formatLike(customerLoss.getTrueName ()));
		}*/
		List<CustomerLoss> customerLossList = customerLossMapper.selectByExample(customerLossExample);
		//total
		PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLossList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerLossList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerLossMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(CustomerLoss customerLoss) {
		 if (0 < customerLossMapper.insert(customerLoss)) {
			 return ServerResponse.createSuccess("添加成功");
		 }
		 return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerLoss customerLoss) {
		if (0 < customerLossMapper.updateByPrimaryKey(customerLoss)) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse findById(Integer customerId) {
		CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(customerId);
		
		return ServerResponse.createSuccess("ok", customerLoss);
	}

	
}
