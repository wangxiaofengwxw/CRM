package com.situ.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.DataDicMapper;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.DataDicExample;
import com.situ.crm.pojo.DataDicExample.Criteria;
import com.situ.crm.util.Util;

@Service
public class DataDicServiceImpl implements IDataDicService {

	@Autowired
	private DataDicMapper dataDicMapper;
	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, DataDic dataDic) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		DataDicExample dataDicExample = new DataDicExample();
		//设置分页，第三方插件，
		PageHelper.startPage(page, rows);
		//判断是否有条件，如果有，添加。
		//并且加了分页
		Criteria createCriteria = dataDicExample.createCriteria();
		if (StringUtils.isNotEmpty(dataDic.getDataDicName())) {
			createCriteria.andDataDicNameLike(Util.formatLike(dataDic.getDataDicName()));
		}
		if (StringUtils.isNotEmpty(dataDic.getDataDicValue())) {
			createCriteria.andDataDicValueLike(Util.formatLike(dataDic.getDataDicValue()));
		}
		List<DataDic> dataDicList = dataDicMapper.selectByExample(dataDicExample);
		//total
		PageInfo<DataDic> pageInfo = new PageInfo<>(dataDicList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(dataDicList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			dataDicMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(DataDic dataDic) {
		 if (0 < dataDicMapper.insert(dataDic)) {
			 return ServerResponse.createSuccess("添加成功");
		 }
		 return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(DataDic dataDic) {
		if (0 < dataDicMapper.updateByPrimaryKey(dataDic)) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<DataDic> findDataDicName() {
		return dataDicMapper.findDataDicName();
	}

	@Override
	public List<DataDic> findService() {
		return dataDicMapper.findService();
	}
	
}
