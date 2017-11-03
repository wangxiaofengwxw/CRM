package com.situ.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

public interface IAdminService {

	ServerResponse toLogin(User user,HttpServletRequest req);

	ServerResponse toUpdatePassword(User user);

}
