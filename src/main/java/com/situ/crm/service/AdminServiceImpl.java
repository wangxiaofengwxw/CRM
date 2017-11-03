package com.situ.crm.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.pojo.UserExample.Criteria;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ServerResponse toLogin(User user,HttpServletRequest req) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andNameEqualTo(user.getName());
		createCriteria.andPasswordEqualTo(user.getPassword());
		User admin = null;
		List<User> userList=  userMapper.selectByExample(userExample);
		if(userList == null) {
			return ServerResponse.createError("登录失败");
		}
		for (User user2 : userList) {
			admin = user2;
		}
		HttpSession session = req.getSession();
		session.setAttribute("admin", admin);
		return ServerResponse.createSuccess("登录成功");
	}

	@Override
	public ServerResponse toUpdatePassword(User user) {
		return null;
	}
	
	
}
