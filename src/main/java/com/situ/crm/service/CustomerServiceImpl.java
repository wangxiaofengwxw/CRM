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
import com.situ.crm.dao.CustomerMapper;
import com.situ.crm.dao.CustomerOrderMapper;
import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import com.situ.crm.pojo.CustomerExample.Criteria;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.util.Util;
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerLossMapper customerLossMapper;
	
	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,Customer customer) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(customer.getName())){
			criteria.andNameLike(Util.formatLike(customer.getName()));
		}
		if(StringUtils.isNotBlank(customer.getNum())){
			criteria.andNumLike(Util.formatLike(customer.getNum()));
		}
		//2、执行查询  
		List<Customer> list = customerMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public ServerResponse addCustomer(Customer customer) {
		CustomerExample example = new CustomerExample();
		if(customerMapper.insert(customer)>0){
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加成功");
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return ServerResponse.createSuccess("删除数据成功");
		}
		return ServerResponse.createError("删除数据失败");
	}

	@Override
	public ServerResponse findById(Integer id) {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		return ServerResponse.createSuccess("ok", customer);
	}

	@Override
	public ServerResponse update(Customer customer) {
		if(customerMapper.updateByPrimaryKey(customer)>0){
			return ServerResponse.createSuccess("修改数据成功");
		}
		return ServerResponse.createError("修改数据失败");
	}

	@Override
	public void checkCustomerLoss() {
		// 1、查找已流失但为标记的客户
	    List<Customer> customerList = customerMapper.findLossCustomer();
	    for (Customer customer : customerList) {
	       // 2、实例化客户流失实体
	       CustomerLoss customerLoss = new CustomerLoss();
	        customerLoss.setCustomerNo(customer.getNum()); // 客户编号
	        customerLoss.setCustomerName(customer.getName()); // 客户名称
	        customerLoss.setCustomerManager(customer.getManagerName()); // 客户经理
	        // 3、查找指定客户最近的订单
	       CustomerOrder customerOrder = customerOrderMapper.findLastOrderByCustomerId(customer.getId());
	       if (customerOrder == null) {
	           customerLoss.setLastOrderTime(null);
	       } else {
	           customerLoss.setLastOrderTime(customerOrder.getOrderDate()); // 设置最近的下单日期
	       }
	        // 4、添加到客户流失表
	        customerLossMapper.insert(customerLoss);
	       // 5、客户表中客户状态修改成1 流失状态
	       customer.setStatus(1);
	        customerMapper.updateByPrimaryKeySelective(customer);
	    }
	}

}
