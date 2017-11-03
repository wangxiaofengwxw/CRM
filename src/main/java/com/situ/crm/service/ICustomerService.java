package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;

public interface ICustomerService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param customer 查询条件
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page, Integer rows, Customer customer);
	/**
	 * 添加customer
	 * @param customer customer的信息
	 * @return
	 */
	ServerResponse addCustomer(Customer customer);
	/**
	 * 删除customer（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	ServerResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个customer的信息  再做修改
	 * @param id 修改customer的id
	 * @return
	 */
	/**
	 * 修改customer
	 * @param customer customer的数据
	 * @return
	 */
	ServerResponse update(Customer customer);
	ServerResponse findById(Integer id);
	/**
	 * 查询流失客户，并把流失客户标记出来
	 */
	void checkCustomerLoss();

}
