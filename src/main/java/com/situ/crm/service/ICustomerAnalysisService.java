package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.vo.CustomerConstitute;

public interface ICustomerAnalysisService {

	ServerResponse findCustomerConstitute();

	EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows, CustomerConstitute customerConstitute);

	ServerResponse findCustomerService();


}
