package com.situ.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.pojo.UserExample.Criteria;
import com.situ.crm.util.Util;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, User user) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		//设置分页，第三方插件，
		PageHelper.startPage(page, rows);
		//判断是否有条件，如果有，添加。
		//并且加了分页
		//有几个条件就加几个判断
		Criteria createCriteria = userExample.createCriteria();
		if (StringUtils.isNotEmpty(user.getName())) {
			createCriteria.andNameLike(Util.formatLike(user.getName()));
		}
		if (StringUtils.isNotEmpty(user.getTrueName())) {
			createCriteria.andTrueNameLike(Util.formatLike(user.getTrueName ()));
		}
		List<User> userList = userMapper.selectByExample(userExample);
		//total
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(userList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(User user) {
		 if (0 < userMapper.insert(user)) {
			 return ServerResponse.createSuccess("添加成功");
		 }
		 return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(User user) {
		if (0 < userMapper.updateByPrimaryKey(user)) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<User> findAssignMan() {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andRoleNameLike(Util.formatLike("客户经理"));
		return userMapper.selectByExample(userExample);
	}
	
}
