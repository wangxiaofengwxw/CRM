package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.SaleChance;

public interface ISaleChanceService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page 当前页
	 * @param rows 一页多少数据
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance, String beginTime, String endTime);

	ServerResponse delete(String ids);

	ServerResponse add(SaleChance saleChance);

	ServerResponse update(SaleChance saleChance);


	List<SaleChance> findStatus();

	ServerResponse findById(Integer saleChanceId);

	ServerResponse updateDevResult(Integer saleChanceId, Integer devResult);
}
