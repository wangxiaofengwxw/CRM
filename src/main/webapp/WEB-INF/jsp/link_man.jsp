<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/resources/thirdlib/jquery-easyui/jquery.edatagrid.js"></script>
<script type="text/javascript">
	$(function(){
		//查询指定id的销售机会
		$.post("${ctx}/customer/findById.action", 
				{customerId : '${customerId}'}, 
				function(result) {
					if(result.status==Util.SUCCESS) {
						$("#num").val(result.data.num);
						$("#name").val(result.data.name);
					}
					
				}, 
				"json");
		
		/*展示数据的datagrid表格*/
		$("#datagrid").edatagrid({
			url:'${ctx}/customerLinkman/findAll.action?customerId=${customerId}',//只查询已分配咨询师的
			 saveUrl:'${ctx}/customerLinkman/add.action?customerId=${customerId}',
			updateUrl:'${ctx}/customerLinkman/update.action?customerId=${customerId}',
			destroyUrl:'${ctx}/customerLinkman/deleteById.action', 
			title:'',
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'linkName',title:'姓名',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'gender',title:'性别',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'position',title:'职位',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'officePhone',title:'办公电话',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'phone',title:'手机',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}  
			]]
		});
	});

	/* FieldTypeComment
	id  int(11)编号
	customer_id     int(11)所属客户
	link_name      varchar(20)姓名
	gender       varchar(20)性别
	position      varchar(50)职位
	office_phone         varchar(50)办公电话
	phone       varchar(20)手机
 */

	//更新销售机会客户开发状态
	function updateSaleChanceDevResult(devResult){
		 $.post("${ctx}/saleChance/updateDevResult.action",
				 {saleChanceId:'${param.saleChanceId}',devResult:devResult},
				 function(result){
					 if(result.status == Util.SUCCESS){
						 $.messager.alert("系统提示","执行成功！");
					 }else{
						 $.messager.alert("系统提示","执行失败！");
					 }
		 		},
		 		"json");
	 }
		
</script>
</head>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="客户基本信息" fit:'true'>
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户编号：</td>
	   			<td><input type="text" id="num" name="num" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>机会来源</td>
	   			<td><input type="text" id="name" name="name" readonly="readonly"/></td>
	   		</tr>
	   		
	 <!-- 营销机会信息面板  结束  -->
	 
	 <br/>
	 
	<!-- 客户开发计划项table -->
	<table id="datagrid" ></table>
	
	<!-- toolbar 开始 -->
	 <div id="toolbar">
		 	<a href="javascript:$('#datagrid').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加计划</a>
		 	<a href="javascript:$('#datagrid').edatagrid('destroyRow')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除计划</a>
		 	<a href="javascript:$('#datagrid').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销行</a>
		 	<a href="javascript:$('#datagrid').edatagrid('saveRow');$('#datagrid').edatagrid('reload')" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存计划</a>
	 </div>
	<!-- toolbar 结束 -->
	
	


</body>
</html>
