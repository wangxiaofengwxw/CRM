package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.pojo.User;

public interface IServiceService {

	EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService);

}
