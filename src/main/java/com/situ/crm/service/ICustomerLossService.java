package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLoss;

public interface ICustomerLossService {

	EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerLoss customerLoss);

	ServerResponse delete(String ids);

	ServerResponse add(CustomerLoss customerLoss);

	ServerResponse update(CustomerLoss customerLoss);

	ServerResponse findById(Integer customerId);


}
