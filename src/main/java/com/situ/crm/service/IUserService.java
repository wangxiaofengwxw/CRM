package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

public interface IUserService {

	EasyUIDataGrideResult findAll(Integer page, Integer rows, User user);

	ServerResponse delete(String ids);

	ServerResponse add(User user);

	ServerResponse update(User user);

	List<User> findAssignMan();

}
